/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import com.google.gson.Gson;
import dao.DemoDAO;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Admin
 */
@Stateless
@Path("/demo")
public class DemoAPI {
    DemoDAO ddao;

    public DemoAPI() {
        ddao = new DemoDAO();
    }
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll(){
        Gson g = new Gson();
        String data = g.toJson(ddao.getAll());
        return data;
    }
    
}
