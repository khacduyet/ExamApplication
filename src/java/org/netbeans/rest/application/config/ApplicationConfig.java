/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Admin
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {        
        resources.add(webservices.ClassAPI.class);
        resources.add(webservices.DetailExamAPI.class);
        resources.add(webservices.ExamAPI.class);
        resources.add(webservices.LevelPointAPI.class);
        resources.add(webservices.LoginAPI.class);
        resources.add(webservices.QuestionAPI.class);
        resources.add(webservices.QuestionItemAPI.class);
        resources.add(webservices.ReportAPI.class);
        resources.add(webservices.RoleAPI.class);
        resources.add(webservices.RoleDetailAPI.class);
        resources.add(webservices.SubjectAPI.class);
        resources.add(webservices.UserAPI.class);

    }
    
}
