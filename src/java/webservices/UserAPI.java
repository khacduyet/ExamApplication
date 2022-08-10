/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import com.google.gson.Gson;
import common.DungChung.ReturnMessage;
import dao.IRole;
import dao.UserDAO;
import entities.Users;
import java.util.Enumeration;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
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
import javax.ws.rs.core.MultivaluedMap;
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
        List<String> auths = httpHeader.getRequestHeader("authorization");
        if (auths != null) {
            CurrentUser cu = getCurrentUser(auths.get(0));
            if (cu != null) {
                Gson g = new Gson();
                db.setCurrentUser(cu);
                String data = g.toJson(db.getData());
                return data;
            }
            return "Not login!";
        }
        return "Not authorization!";
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getById(@PathParam("id") String id) {
        Gson g = new Gson();
        String data = g.toJson(db.getById(id));
        return data;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insert(String entity) {
        Gson g = new Gson();
        Users p = g.fromJson(entity, Users.class);
        ReturnMessage msg = db.setData(p);
        String data = g.toJson(msg);
        return data;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("id") String id) {
        Gson g = new Gson();
        ReturnMessage msg = db.removeData(id);
        String data = g.toJson(msg);
        return data;
    }

}
