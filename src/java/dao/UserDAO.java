/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import common.DungChung;
import common.DungChung.general;
import common.Encryptor;
import entities.Role;
import entities.RoleDetail;
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
public class UserDAO implements ICommon<Users> {

    Session ss;
    public CurrentUser currentUser;
    RoleDetailDAO rddao;

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
            Query q = ss.createQuery("from Users");
            List<Users> data = q.list();
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
            Users data = (Users) ss.get(Users.class, id);
            ss.close();
            msg.data = data;
        } catch (Exception e) {
            msg.setError("Error " + e.toString());
        }
        return msg;
    }

    @Override
    public DungChung.ReturnMessage setData(Users entity) {
        DungChung.ReturnMessage msg = new DungChung.ReturnMessage(DungChung.ReturnMessage.eState.SUCCESS);
        msg.setStatus();
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            ss.beginTransaction();
            if ("".equals(entity.getId())) {
                entity.setId(UUID.randomUUID().toString());
                general<Users> c = new general<>();
                c.getObject(entity, currentUser, 1);
                entity.setPassword(Encryptor.EncryptMd5(entity.getPassword()));
                ss.save(entity);
                msg.status = DungChung.ReturnMessage.eState.ADD;
                msg.setStatus();
            } else {
                general<Users> c = new general<>();
                c.getObject(entity, currentUser, 2);
                entity.setPassword(Encryptor.EncryptMd5(entity.getPassword()));
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
            Users data = (Users) this.getById(id).data;
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
    
    public Users loadUserByUsername(String username) {
        ss = HibernateUtil.getSessionFactory().openSession();
        Users user = (Users) ss.get(Users.class, username);
        ss.close();
        return user;
    }

    public Users getRoleByUsername(String username) {
        List<String> roles = new ArrayList<>();
        ss = HibernateUtil.getSessionFactory().openSession();
        Query u = ss.createQuery("from Users where username = :username");
        u.setParameter("username", username);
        Users data = (Users) u.list().get(0);
        if (data != null) {
            Query q = ss.createQuery("from RoleDetail where idUser = :idUser");
            q.setParameter("idUser", data.getId());
            List<RoleDetail> rd = q.list();
            List<String> rolesId = new ArrayList<>();
            rd.stream().forEach((roled) -> {
                rolesId.add(roled.getIdRole());
            });
            rolesId.stream().map((role) -> (Role) ss.get(Role.class, role)).forEach((r) -> {
                roles.add(r.getCode());
            });
            data.setRoles(roles);
        }
        ss.close();
        return data;
    }

}
