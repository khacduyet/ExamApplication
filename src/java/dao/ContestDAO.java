/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import common.DungChung.ReturnMessage;
import common.DungChung.ReturnMessage.eState;
import common.DungChung.general;
import entities.Class;
import entities.Contest;
import entities.ContestClass;
import entities.ContestUser;
import entities.DetailContestExam;
import entities.DetailExam;
import entities.Users;
import java.util.List;
import java.util.UUID;
import model.CurrentUser;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class ContestDAO implements ICommon<Contest> {

    Session ss;
    public CurrentUser currentUser;

    public CurrentUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public ReturnMessage getData() {
        ReturnMessage msg = new ReturnMessage(ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            Query q = ss.createQuery("from Contest");
            List<Contest> c = q.list();
            ss.close();
            msg.data = c;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

    @Override
    public ReturnMessage getById(String id) {
        ReturnMessage msg = new ReturnMessage(ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            Contest r = (Contest) ss.get(Contest.class, id);
            DetailContestExam dce = (DetailContestExam) ss.createQuery("from DetailContestExam d WHERE d.idContest = :id").setParameter("id", r.getId()).list().get(0);
            r.setIdExam(dce.getIdExam());
            ss.close();
            msg.data = r;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

    @Override
    public ReturnMessage setData(Contest entity) {
        ReturnMessage msg = new ReturnMessage(ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            ss.beginTransaction();
            if ("".equals(entity.getId())) {
                entity.setId(UUID.randomUUID().toString());
                general<Contest> c = new general<>();
                c.getObject(entity, currentUser, 1);
                ss.save(entity);

                // Bộ đề chi tiết
                DetailContestExam dce = new DetailContestExam();
                dce.setId(UUID.randomUUID().toString());
                dce.setIdContest(entity.getId());
                dce.setIdExam(entity.getIdExam());
                general<DetailContestExam> dceObj = new general<>();
                dceObj.getObject(dce, currentUser, 1);
                ss.save(dce);

                // Cuộc thi lớp học
                ContestClass cc = new ContestClass();
                cc.setId(UUID.randomUUID().toString());
                cc.setIdClass(entity.getIdClass());
                cc.setIdContest(entity.getId());
                general<ContestClass> ccObj = new general<>();
                ccObj.getObject(cc, currentUser, 1);
                ss.save(cc);

                // Cuộc thi sinh viên
                entities.Class _class = (entities.Class) ss.get(Class.class, entity.getIdClass());
                List<Users> users = ss.createQuery("from Users where idClass = :id").setParameter("id", _class.getId()).list();
                for (Users user : users) {
                    ContestUser cu = new ContestUser();
                    cu.setId(UUID.randomUUID().toString());
                    cu.setIdContest(entity.getId());
                    cu.setIdUser(user.getId());
                    general<ContestUser> cuObj = new general<>();
                    cuObj.getObject(cu, currentUser, 1);
                    ss.save(cu);
                }
                msg.status = eState.ADD;
                msg.setStatus();
            } else {
                general<Contest> c = new general<>();
                c.getObject(entity, currentUser, 2);
                ss.update(entity);
                msg.status = eState.UPDATE;
                msg.setStatus();
            }
            ss.getTransaction().commit();
            ss.close();
            msg.data = entity;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

    @Override
    public ReturnMessage removeData(String id) {
        ReturnMessage msg = new ReturnMessage(ReturnMessage.eState.DELETE);
        msg.setStatus();
        try {
            Contest r = (Contest) this.getById(id).data;
            ss = HibernateUtil.getSessionFactory().openSession();
            ss.beginTransaction();
            Query dceQuery = ss.createQuery("DELETE FROM DetailContestExam WHERE idContest = :id").setParameter("id", r.getId());
            dceQuery.executeUpdate();
            Query ccQuery = ss.createQuery("DELETE FROM ContestClass WHERE idContest = :id").setParameter("id", r.getId());
            ccQuery.executeUpdate();
            Query cuQuery = ss.createQuery("DELETE FROM ContestUser WHERE idContest = :id").setParameter("id", r.getId());
            cuQuery.executeUpdate();
            ss.delete(r);
            ss.getTransaction().commit();
            ss.close();
            msg.data = id;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

}
