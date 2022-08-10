/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import common.Encryptor;
import entities.Users;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class LoginDAO {

    Session ss;

    public boolean CheckLogin(Users user) {
        ss = HibernateUtil.getSessionFactory().openSession();
        String encrypt = Encryptor.EncryptMd5(user.getPassword());
        Query q = ss.createQuery("from Users where username = :username and password = :password");
        q.setParameter("username", user.getUsername());
        q.setParameter("password", encrypt);
        List<Users> users = q.list();
        boolean b = users != null && users.size() > 0;
        ss.close();
        return b;
    }
}
