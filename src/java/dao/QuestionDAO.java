/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import common.DungChung;
import common.DungChung.general;
import entities.Question;
import java.util.List;
import java.util.UUID;
import model.CurrentUser;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class QuestionDAO implements ICommon<Question>{
    Session ss;

    @Override
    public DungChung.ReturnMessage getData() {
        DungChung.ReturnMessage msg = new DungChung.ReturnMessage(DungChung.ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            Query q = ss.createQuery("from Question");
            List<Question> data = q.list();
            ss.close();
            msg.data = data;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

    @Override
    public DungChung.ReturnMessage getById(String id) {
        DungChung.ReturnMessage msg = new DungChung.ReturnMessage(DungChung.ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            Question data = (Question) ss.get(Question.class, id);
            ss.close();
            msg.data = data;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

    @Override
    public DungChung.ReturnMessage setData(Question entity) {
        DungChung.ReturnMessage msg = new DungChung.ReturnMessage(DungChung.ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            ss.beginTransaction();
            if ("".equals(entity.getId())) {
                entity.setId(UUID.randomUUID().toString());
                general<Question> c = new general<>();
                CurrentUser user = new CurrentUser();
                c.getObject(entity, user, 1);
                ss.save(entity);
                msg.status = DungChung.ReturnMessage.eState.ADD;
                msg.setStatus();
            } else {
                general<Question> c = new general<>();
                CurrentUser user = new CurrentUser();
                c.getObject(entity, user, 2);
                ss.update(entity);
                msg.status = DungChung.ReturnMessage.eState.UPDATE;
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
        DungChung.ReturnMessage msg = new DungChung.ReturnMessage(DungChung.ReturnMessage.eState.DELETE);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            Question data = (Question) this.getById(id).data;
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