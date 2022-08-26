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
import entities.Exam;
import entities.Question;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.CurrentUser;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class ExamDAO implements ICommon<Exam> {

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
            Query q = ss.createQuery("from Exam");
            List<Exam> data = q.list();
            ss.close();
            msg.data = data;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

    public DungChung.ReturnMessage getDataByIdSubject(String id) {
        DungChung.ReturnMessage msg = new DungChung.ReturnMessage(DungChung.ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            Query q = ss.createQuery("from Question where idSubject = :id");
            q.setParameter("id", id);
            List<Exam> data = q.list();
            ss.close();
            msg.data = data;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

    public DungChung.ReturnMessage getExamByIdSubject(String id) {
        DungChung.ReturnMessage msg = new DungChung.ReturnMessage(DungChung.ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            Query q = ss.createQuery("SELECT DISTINCT de.idExam from Question as q, DetailExam as de where q.id = de.idQuestion and q.idSubject = :id");
            q.setParameter("id", id);
            List<String> data = q.list();
            List<Exam> exams = new ArrayList<>();
            for (String exam : data) {
                Exam e = (Exam) ss.get(Exam.class, exam);
                if (e != null) {
                    exams.add(e);
                }
            }
            ss.close();
            msg.data = exams;
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
            Exam data = (Exam) ss.get(Exam.class, id);
            Query q = ss.createQuery("from DetailExam d WHERE d.idExam = :idExam");
            q.setParameter("idExam", id);
            List<DetailExam> ques = q.list();
            List<String> ids = new ArrayList<>();
            String idQues = "";
            for (DetailExam que : ques) {
                ids.add(que.getIdQuestion());
                idQues = que.getIdQuestion();
            }
            Question qu = (Question) ss.get(Question.class, idQues);
            data.setQuestions(ids);
            data.setIdSubject(qu.getIdSubject());
            ss.close();
            msg.data = data;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

    @Override
    public DungChung.ReturnMessage setData(Exam entity) {
        ReturnMessage msg = new ReturnMessage(ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            ss.beginTransaction();
            if ("".equals(entity.getId())) {
                entity.setId(UUID.randomUUID().toString());
                general<Exam> c = new general<>();
                c.getObject(entity, currentUser, 1);
                ss.save(entity);
                List<String> questions = entity.getQuestions();
                List<DetailExam> detailExams = new ArrayList<>();
                questions.stream().map((question) -> {
                    DetailExam de = new DetailExam();
                    de.setId(UUID.randomUUID().toString());
                    de.setIdExam(entity.getId());
                    de.setIdQuestion(question);
                    return de;
                }).map((de) -> {
                    general<DetailExam> d = new general<>();
                    d.getObject(de, currentUser, 1);
                    return de;
                }).forEach((de) -> {
                    detailExams.add(de);
                });
                detailExams.stream().forEach((detailExam) -> {
                    ss.save(detailExam);
                });
                msg.status = ReturnMessage.eState.ADD;
                msg.setStatus();
            } else {
                general<Exam> c = new general<>();
                c.getObject(entity, currentUser, 1);
                ss.update(entity);
                Query q = ss.createQuery("DELETE from DetailExam where idExam = :id");
                q.setParameter("id", entity.getId());
                q.executeUpdate();

                List<String> questions = entity.getQuestions();
                List<DetailExam> detailExams = new ArrayList<>();
                questions.stream().map((question) -> {
                    DetailExam de = new DetailExam();
                    de.setId(UUID.randomUUID().toString());
                    de.setIdExam(entity.getId());
                    de.setIdQuestion(question);
                    return de;
                }).map((de) -> {
                    general<DetailExam> d = new general<>();
                    d.getObject(de, currentUser, 1);
                    return de;
                }).forEach((de) -> {
                    detailExams.add(de);
                });
                detailExams.stream().forEach((detailExam) -> {
                    ss.save(detailExam);
                });
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
        ReturnMessage msg = new ReturnMessage(ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            Exam data = (Exam) this.getById(id).data;
            ss = HibernateUtil.getSessionFactory().openSession();
            ss.beginTransaction();
            ss.delete(data);
            Query q = ss.createQuery("DELETE from DetailExam where idExam = :id");
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

}
