/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Demo;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class DemoDAO {
    Session ss;
    
    public List<Demo> getAll(){
        ss = HibernateUtil.getSessionFactory().openSession();
        Query q = ss.createQuery("from Demo");
        List<Demo> d = q.list();
        ss.close();
        return d;
    }
}
