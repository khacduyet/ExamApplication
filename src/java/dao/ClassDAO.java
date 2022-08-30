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
import entities.ResultExam;
import entities.Users;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.CurrentUser;
import model.ModelChartClass;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class ClassDAO implements ICommon<Class> {

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
            Query q = ss.createQuery("from Class");
            List<Class> c = q.list();
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
            Class r = (Class) ss.get(Class.class, id);
            ss.close();
            msg.data = r;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

    @Override
    public ReturnMessage setData(Class entity) {
        ReturnMessage msg = new ReturnMessage(ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            ss.beginTransaction();
            if ("".equals(entity.getId())) {
                entity.setId(UUID.randomUUID().toString());
                general<Class> c = new general<>();
                c.getObject(entity, currentUser, 1);
                ss.save(entity);
                msg.status = eState.ADD;
                msg.setStatus();
            } else {
                general<Class> c = new general<>();
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
            Class r = (Class) this.getById(id).data;
            ss = HibernateUtil.getSessionFactory().openSession();
            ss.beginTransaction();
            ss.delete(r);
            ss.getTransaction().commit();
            ss.close();
            msg.data = id;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

    public ReturnMessage getChart(String idClass) {
        ReturnMessage msg = new ReturnMessage(ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            List<Contest> contests = null;
            if (idClass.equals("null") || idClass.isEmpty() || idClass.equals("0")) {
                contests = ss.createQuery("from Contest").list();
            } else {
                contests = ss.createQuery("from Contest where idClass = :idClass").setParameter("idClass", idClass).list();
            }
            List<ModelChartClass> mccs = new ArrayList<>();
            for (Contest c : contests) {
                int passed = 0;
                int failed = 0;
                int rate = 0;
                List<String> idUsers = ss.createQuery("select idUser from ContestUser where idContest = :idContest").setParameter("idContest", c.getId()).list();
                for (String idUser : idUsers) {
                    List<ResultExam> res = ss.createQuery("from ResultExam where idContest = :idContest and idUser = :idUser").setParameter("idContest", c.getId()).setParameter("idUser", idUser).list();
                    if (res != null) {
                        if (res.size() > 0) {
                            ResultExam re = res.get(0);
                            rate++;
                            if (re.getPoint() > 8) {
                                passed++;
                            } else {
                                failed++;
                            }
                        }
                    }
                }
                float percent = rate * 100 / idUsers.size();
                int total = passed + failed;
                ModelChartClass mcc = new ModelChartClass();
                mcc.setPassed(passed);
                mcc.setFailed(failed);
                mcc.setTotal(total);
                mcc.setRate(percent);
                mcc.setContest(c);
                mccs.add(mcc);
            }
            ss.close();
            msg.data = mccs;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }
}
