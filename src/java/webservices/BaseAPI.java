/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import dao.JWT;
import model.CurrentUser;

/**
 *
 * @author Admin
 */
public class BaseAPI {

    private final JWT jwt;

    public BaseAPI() {
        jwt = new JWT();
    }

    public CurrentUser getCurrentUser(String authorization) {
        CurrentUser user = null;
        boolean check = jwt.validateTokenLogin(authorization);
        if (check) {
            user = jwt.getUserFromToken(authorization);
        }
        return user;
    }
}
