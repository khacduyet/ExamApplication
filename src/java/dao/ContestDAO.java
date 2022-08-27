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
import entities.Exam;
import entities.LevelPoint;
import entities.Question;
import entities.QuestionItem;
import entities.ResultExam;
import entities.Users;
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

    public ReturnMessage getContestByUser(String idUser) {
        ReturnMessage msg = new ReturnMessage(ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            Query q = ss.createQuery("from ContestUser where idUser = :idUser").setParameter("idUser", idUser);
            List<ContestUser> cu = q.list();
            List<String> idContests = new ArrayList<>();
            for (ContestUser c : cu) {
                idContests.add(c.getIdContest());
            }
            List<Contest> contests = new ArrayList<>();
            for (String idContest : idContests) {
                contests.add((Contest) ss.get(Contest.class, idContest));
            }
            ss.close();
            msg.data = contests;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

    public ReturnMessage getQuestionByIdContest(String idContest) {
        ReturnMessage msg = new ReturnMessage(ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            Query qExam = ss.createQuery("select dce.idExam from DetailContestExam as dce where dce.idContest = :idContest").setParameter("idContest", idContest);
            String idExam = (String) qExam.list().get(0);
            Query qDetailQues = ss.createQuery("SELECT de.idQuestion FROM DetailExam as de where idExam = :idExam").setParameter("idExam", idExam);
            List<String> detailExams = qDetailQues.list();
            List<Question> questions = new ArrayList<>();
            for (String de : detailExams) {
                Question q = (Question) ss.get(Question.class, de);
                q.Items = ss.createQuery("from QuestionItem where idQuestion = :idQuestion").setParameter("idQuestion", de).list();
                questions.add(q);
            }
            ss.close();
            msg.data = questions;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

    public ReturnMessage finishedExam(ResultExam re) {
        ReturnMessage msg = new ReturnMessage(ReturnMessage.eState.FINISHED);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            ss.beginTransaction();
            float point = 0;
            List<String> correcteds = new ArrayList<>();
            for (String ans : re.getIdAnswer()) {
                QuestionItem qi = (QuestionItem) ss.get(QuestionItem.class, ans);
                if (qi.getIsTrue()) {
                    Question q = (Question) ss.get(Question.class, qi.getIdQuestion());
                    correcteds.add(q.getId());
                    LevelPoint lp = (LevelPoint) ss.get(LevelPoint.class, q.getIdLevel());
                    point += lp.getPoint();
                }
            }
            String corrected = String.join(",", correcteds);
            re.setCorrected(corrected);
            re.setPoint(point);
            Query detailContest = ss.createQuery("select idExam from DetailContestExam where idContest = :idContest ").setParameter("idContest", re.getIdContest());
            Exam e = (Exam) detailContest.list().get(0);
            re.setIdExam(e.getId());
            Query qQuestions = ss.createQuery("select q.idLevel from DetailExam as de inner join Question as q on de.idQuestion = q.id where de.idExam = :idExam").setParameter("idExam", e.getId());
            List<String> idLevels = qQuestions.list();
            float score = 0;
            for (String idLevel : idLevels) {
                LevelPoint lp = (LevelPoint) ss.get(LevelPoint.class, idLevel);
                score += lp.getPoint();
            }
            re.setScore(score + "");
            general<ResultExam> dceObj = new general<>();
            dceObj.getObject(re, currentUser, 1);
            ss.save(re);
            ss.getTransaction().commit();
            ss.close();
            msg.data = null;
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
