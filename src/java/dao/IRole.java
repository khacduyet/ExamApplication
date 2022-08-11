/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author Admin
 */
public abstract class IRole {

    static String ROLE_ADMIN = "ROLE_ADMIN";
    static String ROLE_USER = "ROLE_USER";
    static String ROLE_TEACHER = "ROLE_TEACHER";

    static String[] _ROLES_ALL = {ROLE_USER, ROLE_ADMIN, ROLE_TEACHER};
    static String[] _ROLES_LIMIT = {ROLE_ADMIN, ROLE_TEACHER};

    static String ROLES_ALL = String.join(",", _ROLES_ALL);
    static String ROLES_LIMIT = String.join(",", _ROLES_LIMIT);

    public static boolean isRole(List<String> roles, LEVEL req) {
        switch (req) {
            case LOW:
                if (roles.stream().anyMatch((role) -> (ROLES_ALL.contains(role)))) {
                    return true;
                }
                break;
            case MEDIUM:
                if (roles.stream().anyMatch((role) -> (ROLES_LIMIT.contains(role)))) {
                    return true;
                }
                break;
            case HIGHT:
                if (roles.stream().anyMatch((role) -> (ROLES_LIMIT.contains(role)))) {
                    return true;
                }
                break;
        }
        return false;
    }

    public enum ROLE {

        ROLE_ADMIN, ROLE_USER, ROLE_TEACHER;
    }

    public enum LEVEL {

        HIGHT, MEDIUM, LOW
    }

    public enum REQUEST {

        GET, POST, PUT, DELETE
    }
}
