/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import dao.JWT;
import dao.LoginDAO;
import dao.UserDAO;
import entities.Users;
import javax.annotation.security.PermitAll;
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
import org.springframework.http.HttpStatus;

/**
 *
 * @author Admin
 */
@Stateless
@Path("/login")
@PermitAll
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
    public Response checkPassword(Users user) {
        String result = "";
        try {
            if (db.CheckLogin(user)) {
                Users u = udao.getRoleByUsername(user.getUsername());
                result = jwt.generateTokenLogin(u);
            } else {
                result = "Wrong userId and password";
            }
        } catch (Exception ex) {
            return Response.serverError().build();
        }
        return Response.ok(result).build();
    }
}
