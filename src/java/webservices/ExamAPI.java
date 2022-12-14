/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import com.google.gson.Gson;
import common.DungChung.MESSAGE;
import common.DungChung.ReturnMessage;
import dao.ExamDAO;
import dao.IRole;
import dao.IRole.LEVEL;
import entities.Exam;
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
@Path("/exam")
public class ExamAPI extends BaseAPI {

    ExamDAO db;

    public ExamAPI() {
        db = new ExamDAO();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll(@Context HttpHeaders httpHeader) {
        CurrentUser cu = getCurrentUser(httpHeader);
        if (cu != null) {
            List<String> roles = cu.getRoles();
            if (IRole.isRole(roles, LEVEL.LOW)) {
                Gson g = new Gson();
                String data = g.toJson(db.getData());
                return data;
            }
            return MESSAGE.NOT_AUTHORIZATION;
        }
        return MESSAGE.NOT_LOGIN;

    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getById(@Context HttpHeaders httpHeader, @PathParam("id") String id) {
        CurrentUser cu = getCurrentUser(httpHeader);
        if (cu != null) {
            List<String> roles = cu.getRoles();
            if (IRole.isRole(roles, LEVEL.LOW)) {
                Gson g = new Gson();
                String data = g.toJson(db.getById(id));
                return data;
            }
            return MESSAGE.NOT_AUTHORIZATION;
        }
        return MESSAGE.NOT_LOGIN;
    }

    @Path("/subject/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getByIdSubject(@Context HttpHeaders httpHeader, @PathParam("id") String id) {
        CurrentUser cu = getCurrentUser(httpHeader);
        if (cu != null) {
            List<String> roles = cu.getRoles();
            if (IRole.isRole(roles, LEVEL.LOW)) {
                Gson g = new Gson();
                String data = g.toJson(db.getDataByIdSubject(id));
                return data;
            }
            return MESSAGE.NOT_AUTHORIZATION;
        }
        return MESSAGE.NOT_LOGIN;
    }
    
    @Path("/subject_exam/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getExamByIdSubject(@Context HttpHeaders httpHeader, @PathParam("id") String id) {
        CurrentUser cu = getCurrentUser(httpHeader);
        if (cu != null) {
            List<String> roles = cu.getRoles();
            if (IRole.isRole(roles, LEVEL.LOW)) {
                Gson g = new Gson();
                String data = g.toJson(db.getExamByIdSubject(id));
                return data;
            }
            return MESSAGE.NOT_AUTHORIZATION;
        }
        return MESSAGE.NOT_LOGIN;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insert(@Context HttpHeaders httpHeader, String entity) {
        CurrentUser cu = getCurrentUser(httpHeader);
        if (cu != null) {
            List<String> roles = cu.getRoles();
            if (IRole.isRole(roles, LEVEL.MEDIUM)) {
                db.setCurrentUser(cu);
                Gson g = new Gson();
                Exam p = g.fromJson(entity, Exam.class);
                ReturnMessage msg = db.setData(p);
                String data = g.toJson(msg);
                return data;
            }
            return MESSAGE.NOT_AUTHORIZATION;
        }
        return MESSAGE.NOT_LOGIN;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String delete(@Context HttpHeaders httpHeader, @PathParam("id") String id) {
        CurrentUser cu = getCurrentUser(httpHeader);
        if (cu != null) {
            List<String> roles = cu.getRoles();
            if (IRole.isRole(roles, LEVEL.MEDIUM)) {
                Gson g = new Gson();
                ReturnMessage msg = db.removeData(id);
                String data = g.toJson(msg);
                return data;
            }
            return MESSAGE.NOT_AUTHORIZATION;
        }
        return MESSAGE.NOT_LOGIN;
    }

}
