/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import common.DungChung;
import common.DungChung.general;
import entities.Contest;
import entities.ResultExam;
import entities.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.CurrentUser;
import model.ModelChartClass;
import model.ModelChartSubject;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class SubjectDAO implements ICommon<Subject> {

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
            Query q = ss.createQuery("from Subject");
            List<Subject> data = q.list();
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
            Subject data = (Subject) ss.get(Subject.class, id);
            ss.close();
            msg.data = data;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

    @Override
    public DungChung.ReturnMessage setData(Subject entity) {
        DungChung.ReturnMessage msg = new DungChung.ReturnMessage(DungChung.ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            ss.beginTransaction();
            if ("".equals(entity.getId())) {
                entity.setId(UUID.randomUUID().toString());
                general<Subject> c = new general<>();
                c.getObject(entity, currentUser, 1);
                ss.save(entity);
                msg.status = DungChung.ReturnMessage.eState.ADD;
                msg.setStatus();
            } else {
                general<Subject> c = new general<>();
                c.getObject(entity, currentUser, 2);
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
            Subject data = (Subject) this.getById(id).data;
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

    public DungChung.ReturnMessage getChart(String idSubject) {
        DungChung.ReturnMessage msg = new DungChung.ReturnMessage(DungChung.ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            List<ResultExam> re = null;
            if (idSubject.equals("null") || idSubject.isEmpty() || idSubject.equals("0")) {
                re = ss.createQuery("from ResultExam").list();
            } else {
                re = ss.createQuery("select re from ResultExam re, Contest c where re.idContest = c.id and c.idSubject = :idSubject").setParameter("idSubject", idSubject).list();
            }
            ModelChartSubject mcs = new ModelChartSubject();
            int weak = 0;
            int medium = 0;
            int aboveAvg = 0;
            int good = 0;
            for (ResultExam c : re) {
                if (c.getPoint() < 5) {
                    weak++;
                } else if (c.getPoint() < 8) {
                    medium++;
                } else if (c.getPoint() < 15) {
                    aboveAvg++;
                } else {
                    good++;
                }
            }
            mcs.setWeak(weak);
            mcs.setMedium(medium);
            mcs.setAboveAvg(aboveAvg);
            mcs.setGood(good);
            mcs.setTotal(mcs.getWeak() + mcs.getMedium() + mcs.getAboveAvg() + mcs.getGood());
            ss.close();
            msg.data = mcs;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }
}
