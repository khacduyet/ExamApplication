/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import com.google.gson.Gson;
import common.DungChung.ReturnMessage;
import dao.DetailExamDAO;
import entities.DetailExam;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Admin
 */
@Stateless
@Path("/detail-exam")
public class DetailExamAPI {

    DetailExamDAO db;

    public DetailExamAPI() {
        db = new DetailExamDAO();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        Gson g = new Gson();
        String data = g.toJson(db.getData());
        return data;
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
        DetailExam p = g.fromJson(entity, DetailExam.class);
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
