/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import com.google.gson.Gson;
import dao.JWT;
import dao.LoginDAO;
import dao.UserDAO;
import entities.Users;
//import helper.JwtTokenHelper;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
@Stateless
@Path("/login")
public class LoginAPI {
    UserDAO udao;
    LoginDAO db;
    JWT jwt;

    public LoginAPI() {
        db = new LoginDAO();
        jwt = new JWT();
        udao = new UserDAO();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/{username}")
    public Users loadUserByUsername(@PathParam("username") String username){
        return udao.getRoleByUsername(username);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String checkPassword(String user) {
        String result = "";
        try {
            Gson g = new Gson();
            Users us = g.fromJson(user, Users.class);
            if (db.CheckLogin(us)) {
                Users u = udao.getRoleByUsername(us.getUsername());
                result = jwt.generateTokenLogin(u);
            } else {
                result = "Wrong userId and password";
            }
        } catch (Exception ex) {
            return "Exception: " + ex.toString();
        }
        return result;
    }
}
