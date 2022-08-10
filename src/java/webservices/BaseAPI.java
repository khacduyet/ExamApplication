/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import javax.ws.rs.core.Cookie;
import model.CurrentUser;

/**
 *
 * @author Admin
 */
public class BaseAPI {
    public CurrentUser getCurrentUser(){
        CurrentUser user = new CurrentUser();
        
        return user;
    }
}
