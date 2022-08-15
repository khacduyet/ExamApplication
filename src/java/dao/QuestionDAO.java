/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import common.DungChung;
import common.DungChung.general;
import entities.LevelPoint;
import entities.Question;
import entities.QuestionItem;
import entities.Subject;
import java.util.List;
import java.util.UUID;
import model.CurrentUser;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class QuestionDAO implements ICommon<Question> {

    Session ss;
    public CurrentUser currentUser;

    public CurrentUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public DungChung.ReturnMessage getData() {
        DungChung.ReturnMessage msg = new DungChung.ReturnMessage(DungChung.ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            Query q = ss.createQuery("from Question");
            List<Question> data = q.list();
            data.stream().forEach((ques) -> {
                Subject s = (Subject) ss.get(Subject.class, ques.getIdSubject());
                LevelPoint lp = (LevelPoint) ss.get(LevelPoint.class, ques.getIdLevel());
                if (s != null) {
                    ques.setNameSubject(s.getName());
                }
                if (lp != null) {
                    ques.setNameLevel(lp.getName());
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
    public DungChung.ReturnMessage getById(String id) {
        DungChung.ReturnMessage msg = new DungChung.ReturnMessage(DungChung.ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            Question data = (Question) ss.get(Question.class, id);
            Query q = ss.createQuery("from QuestionItem where idQuestion = :id order by name asc");
            q.setParameter("id", data.getId());
            data.setItems(q.list());
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
                // Question
                entity.setId(UUID.randomUUID().toString());
                general<Question> c = new general<>();
                c.getObject(entity, currentUser, 1);
                ss.save(entity);
                // Question Item
                List<QuestionItem> items = entity.Items;
                items.stream().map((item) -> {
                    item.setId(UUID.randomUUID().toString());
                    item.setIdQuestion(entity.getId());
                    return item;
                }).forEach((item) -> {
                    general<QuestionItem> g = new general<>();
                    g.getObject(item, currentUser, 1);
                    ss.save(item);
                });
                msg.status = DungChung.ReturnMessage.eState.ADD;
                msg.setStatus();
            } else {
                general<Question> c = new general<>();
                c.getObject(entity, currentUser, 2);
                ss.update(entity);
                Query q = ss.createQuery("DELETE from QuestionItem where idQuestion = :id");
                q.setParameter("id", entity.getId());
                q.executeUpdate();
                // Question Item
                List<QuestionItem> items = entity.Items;
                items.stream().map((item) -> {
                    item.setId(UUID.randomUUID().toString());
                    item.setIdQuestion(entity.getId());
                    return item;
                }).forEach((item) -> {
                    general<QuestionItem> g = new general<>();
                    g.getObject(item, currentUser, 1);
                    ss.save(item);
                });
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
            Question data = (Question) this.getById(id).data;
            ss = HibernateUtil.getSessionFactory().openSession();
            ss.beginTransaction();
            ss.delete(data);
            Query q = ss.createQuery("DELETE from QuestionItem where idQuestion = :id");
            q.setParameter("id", id);
            q.executeUpdate();
            ss.getTransaction().commit();
            ss.close();
            msg.data = id;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

    public List<QuestionItem> getQuesItemByQues(String id) {
        ss = HibernateUtil.getSessionFactory().openSession();
        Query q = ss.createQuery("from QuestionItem where idQuestion = :idQuestion");
        q.setParameter("idQuestion", id);
        List<QuestionItem> data = q.list();
        return data;
    }

}
