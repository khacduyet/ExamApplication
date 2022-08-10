/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import com.google.gson.Gson;
import common.DungChung;
import common.DungChung.MESSAGE;
import common.DungChung.ReturnMessage;
import dao.ClassDAO;
import dao.IRole;
import dao.IRole.REQUEST;
import entities.Class;
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
@Path("/class")
public class ClassAPI extends BaseAPI {

    ClassDAO db;

    public ClassAPI() {
        db = new ClassDAO();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll(@Context HttpHeaders httpHeader) {
        List<String> auths = httpHeader.getRequestHeader("authorization");
        if (auths != null) {
            CurrentUser cu = getCurrentUser(auths.get(0));
            if (cu != null) {
                List<String> roles = cu.getRoles();
                if (IRole.isRole(roles, REQUEST.GET)) {
                    Gson g = new Gson();
                    db.setCurrentUser(cu);
                    String data = g.toJson(db.getData());
                    return data;
                }
                return MESSAGE.NOT_AUTHORIZATION;
            }
            return MESSAGE.NOT_LOGIN;
        }
        return MESSAGE.NOT_AUTHORIZATION;
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getById(@Context HttpHeaders httpHeader, @PathParam("id") String id) {
        List<String> auths = httpHeader.getRequestHeader("authorization");
        if (auths != null) {
            CurrentUser cu = getCurrentUser(auths.get(0));
            if (cu != null) {
                List<String> roles = cu.getRoles();
                if (IRole.isRole(roles, REQUEST.GET)) {
                    Gson g = new Gson();
                    String data = g.toJson(db.getById(id));
                    return data;
                }
                return MESSAGE.NOT_AUTHORIZATION;
            }
            return MESSAGE.NOT_LOGIN;
        }
        return MESSAGE.NOT_AUTHORIZATION;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insert(@Context HttpHeaders httpHeader, String entity) {
        List<String> auths = httpHeader.getRequestHeader("authorization");
        if (auths != null) {
            CurrentUser cu = getCurrentUser(auths.get(0));
            if (cu != null) {
                List<String> roles = cu.getRoles();
                if (IRole.isRole(roles, REQUEST.POST)) {
                    Gson g = new Gson();
                    Class p = g.fromJson(entity, Class.class);
                    db.setCurrentUser(cu);
                    ReturnMessage msg = db.setData(p);
                    String data = g.toJson(msg);
                    return data;
                }
                return MESSAGE.NOT_AUTHORIZATION;
            }
            return MESSAGE.NOT_LOGIN;
        }
        return MESSAGE.NOT_AUTHORIZATION;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String delete(@Context HttpHeaders httpHeader, @PathParam("id") String id) {
        List<String> auths = httpHeader.getRequestHeader("authorization");
        if (auths != null) {
            CurrentUser cu = getCurrentUser(auths.get(0));
            if (cu != null) {
                List<String> roles = cu.getRoles();
                if (IRole.isRole(roles, REQUEST.DELETE)) {
                    Gson g = new Gson();
                    ReturnMessage msg = db.removeData(id);
                    String data = g.toJson(msg);
                    return data;
                }
                return MESSAGE.NOT_AUTHORIZATION;
            }
            return MESSAGE.NOT_LOGIN;
        }
        return MESSAGE.NOT_AUTHORIZATION;
    }

}
