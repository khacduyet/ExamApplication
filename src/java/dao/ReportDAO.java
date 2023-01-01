/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import common.DungChung.ReturnMessage;
import common.DungChung.ReturnMessage.eState;
import common.DungChung.general;
import entities.Report;
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
public class ReportDAO implements ICommon<Report> {

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
            Query q = ss.createQuery("from Report");
            List<Report> data = q.list();
            data.forEach((report) -> {
                Users u = (Users) ss.get(Users.class, report.getIdUser());
                if (u != null) {
                    report.setNameUser(u.getName());
                    report.setEmailUser(u.getEmail());
                }
            });
            ss.close();
            msg.data = data;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

    public ReturnMessage getData(CurrentUser cu) {
        ReturnMessage msg = new ReturnMessage(ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            List<Report> data = null;
            if (cu.getRoles().get(0).equals("ROLE_USER")) {
                Query q = ss.createQuery("from Report where idUser = :idUser").setParameter("idUser", cu.getId());
                data = q.list();
            } else {
                Query q = ss.createQuery("from Report");
                data = q.list();
            }
            data.forEach((report) -> {
                Users u = (Users) ss.get(Users.class, report.getIdUser());
                if (u != null) {
                    report.setNameUser(u.getName());
                    report.setEmailUser(u.getEmail());
                }
            });
            ss.close();
            msg.data = data;
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
            Report data = (Report) ss.get(Report.class, id);
            ss.close();
            msg.data = data;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

    @Override
    public ReturnMessage setData(Report entity) {
        ReturnMessage msg = new ReturnMessage(ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            ss.beginTransaction();
            if ("".equals(entity.getId())) {
                entity.setId(UUID.randomUUID().toString());
                entity.setStatus(Boolean.FALSE);
                general<Report> c = new general<>();
                c.getObject(entity, currentUser, 1);
                ss.save(entity);
                msg.status = eState.ADD;
                msg.setStatus();
            } else {
                general<Report> c = new general<>();
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
            Report data = (Report) this.getById(id).data;
            ss = HibernateUtil.getSessionFactory().openSession();
            ss.beginTransaction();
            ss.delete(data);
            ss.getTransaction().commit();
            ss.close();
            msg.data = id;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

}
