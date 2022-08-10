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

    @Override
    public ReturnMessage getData() {
        ReturnMessage msg = new ReturnMessage(ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            Query q = ss.createQuery("from Report");
            List<Report> data = q.list();
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
                general<Report> c = new general<>();
                CurrentUser user = new CurrentUser();
                c.getObject(entity, user, 1);
                ss.save(entity);
                msg.status = eState.ADD;
                msg.setStatus();
            } else {
                general<Report> c = new general<>();
                CurrentUser user = new CurrentUser();
                c.getObject(entity, user, 2);
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
