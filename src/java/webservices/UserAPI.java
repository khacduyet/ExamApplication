/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import com.google.gson.Gson;
import common.DungChung;
import common.DungChung.ReturnMessage;
import dao.IRole;
import dao.UserDAO;
import entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import model.CurrentUser;

/**
 *
 * @author Admin
 */
@Stateless
@Path("/user")
public class UserAPI extends BaseAPI {

    UserDAO db;

    public UserAPI() {
        db = new UserDAO();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll(@Context HttpHeaders httpHeader) {
        CurrentUser cu = getCurrentUser(httpHeader);
        if (cu != null) {
            List<String> roles = cu.getRoles();
            if (IRole.isRole(roles, IRole.LEVEL.LOW)) {
                Gson g = new Gson();
                String data = g.toJson(db.getData());
                return data;
            }
            return DungChung.MESSAGE.NOT_AUTHORIZATION;
        }
        return DungChung.MESSAGE.NOT_LOGIN;
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getById(@Context HttpHeaders httpHeader, @PathParam("id") String id) {
        CurrentUser cu = getCurrentUser(httpHeader);
        if (cu != null) {
            List<String> roles = cu.getRoles();
            if (IRole.isRole(roles, IRole.LEVEL.LOW)) {
                Gson g = new Gson();
                String data = g.toJson(db.getById(id));
                return data;
            }
            return DungChung.MESSAGE.NOT_AUTHORIZATION;
        }
        return DungChung.MESSAGE.NOT_LOGIN;

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insert(@Context HttpHeaders httpHeader, String entity) {
        CurrentUser cu = getCurrentUser(httpHeader);
        if (cu != null) {
            List<String> roles = cu.getRoles();
            if (IRole.isRole(roles, IRole.LEVEL.MEDIUM)) {
                db.setCurrentUser(cu);
                Gson g = new Gson();
                Users p = g.fromJson(entity, Users.class);
                ReturnMessage msg = db.setData(p);
                String data = g.toJson(msg);
                return data;
            }
            return DungChung.MESSAGE.NOT_AUTHORIZATION;
        }
        return DungChung.MESSAGE.NOT_LOGIN;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String delete(@Context HttpHeaders httpHeader, @PathParam("id") String id) {
        CurrentUser cu = getCurrentUser(httpHeader);
        if (cu != null) {
            List<String> roles = cu.getRoles();
            if (IRole.isRole(roles, IRole.LEVEL.MEDIUM)) {
                Gson g = new Gson();
                ReturnMessage msg = db.removeData(id);
                String data = g.toJson(msg);
                return data;
            }
            return DungChung.MESSAGE.NOT_AUTHORIZATION;
        }
        return DungChung.MESSAGE.NOT_LOGIN;

    }

}
