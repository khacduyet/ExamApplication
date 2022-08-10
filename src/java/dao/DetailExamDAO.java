/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import common.DungChung;
import common.DungChung.ReturnMessage;
import common.DungChung.general;
import entities.DetailExam;
import java.util.List;
import java.util.UUID;
import model.CurrentUser;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class DetailExamDAO implements ICommon<DetailExam>{

    Session ss;
    
    @Override
    public DungChung.ReturnMessage getData() {
        ReturnMessage msg = new ReturnMessage(ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            Query q = ss.createQuery("from DetailExam");
            List<DetailExam> data = q.list();
            ss.close();
            msg.data = data;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

    @Override
    public DungChung.ReturnMessage getById(String id) {
        ReturnMessage msg = new ReturnMessage(ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            DetailExam data = (DetailExam) ss.get(DetailExam.class, id);
            ss.close();
            msg.data = data;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

    @Override
    public DungChung.ReturnMessage setData(DetailExam entity) {
        ReturnMessage msg = new ReturnMessage(ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            ss.beginTransaction();
            if ("".equals(entity.getId())) {
                entity.setId(UUID.randomUUID().toString());
                general<DetailExam> c = new general<>();
                CurrentUser user = new CurrentUser();
                c.getObject(entity, user, 1);
                ss.save(entity);
                msg.status = ReturnMessage.eState.ADD;
                msg.setStatus();
            } else {
                general<DetailExam> c = new general<>();
                CurrentUser user = new CurrentUser();
                c.getObject(entity, user, 2);
                ss.update(entity);
                msg.status = ReturnMessage.eState.UPDATE;
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
    public DungChung.ReturnMessage removeData(String id) {
        ReturnMessage msg = new ReturnMessage(ReturnMessage.eState.DELETE);
        msg.setStatus();
        try {
            DetailExam data = (DetailExam) this.getById(id).data;
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
