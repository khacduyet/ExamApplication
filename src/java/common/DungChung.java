/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CurrentUser;

/**
 *
 * @author Admin
 */
public class DungChung {

    public static class ReturnMessage {

        public ReturnMessage() {
        }

        public enum eState {
            SUCCESS, ERROR, NOTFOUND, ADD,
            UPDATE, DELETE, DUPLICATE, NOTLOGIN,
            FINISHED
        }
        public String message;
        public Object data;
        public eState status;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public ReturnMessage(eState nState) {
            this.status = nState;
        }

        public void setStatus() {
            switch (status) {
                case ADD:
                    this.message = "Add successfully!";
                    break;
                case SUCCESS:
                    this.message = "Successfully!";
                    break;
                case ERROR:
                    this.message = "Error!";
                    break;
                case NOTFOUND:
                    this.message = "Not found!";
                    break;
                case UPDATE:
                    this.message = "Update successfully!";
                    break;
                case DELETE:
                    this.message = "Delete successfully!";
                    break;
                case DUPLICATE:
                    this.message = "Key duplicate!";
                    break;
                case NOTLOGIN:
                    this.message = "Not login!";
                    break;
                case FINISHED:
                    this.message = "Successfully submitted!";
                    break;
            }
        }

        public void setError(String sMess) {
            this.message = sMess;
            this.status = eState.ERROR;
        }
    }

    public static class Auditable {

        public String created;
        public String createdBy;
        public String createdByName;
        public String modified;
        public String modifiedBy;
        public String modifiedByName;

        public void isCreateOrUpdate(int type, CurrentUser user) {
            switch (type) {
                case 1: // Create
                    setCreated(LocalDateTime.now().toString());
                    setCreatedBy(user.id);
                    setCreatedByName(user.name);
                    setModified(LocalDateTime.now().toString());
                    setModifiedBy(user.id);
                    setModifiedByName(user.name);
                    break;
                case 2: // Modify
                    setModified(LocalDateTime.now().toString());
                    setModifiedBy(user.id);
                    setModifiedByName(user.name);
                    break;
            }
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getCreatedByName() {
            return createdByName;
        }

        public void setCreatedByName(String createdByName) {
            this.createdByName = createdByName;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public String getModifiedBy() {
            return modifiedBy;
        }

        public void setModifiedBy(String modifiedBy) {
            this.modifiedBy = modifiedBy;
        }

        public String getModifiedByName() {
            return modifiedByName;
        }

        public void setModifiedByName(String modifiedByName) {
            this.modifiedByName = modifiedByName;
        }
    }

    public static class container<T> {

        public T entity;
        public List<T> entities;

        public T getEntity() {
            return entity;
        }

        public void setEntity(T entity) {
            this.entity = entity;
        }

        public List<T> getEntities() {
            return entities;
        }

        public void setEntities(List<T> entities) {
            this.entities = entities;
        }

    }

    public static class general<T> {

        public void setObject(T entity, Auditable obj, int type) {
            try {
                if (type == 1) {
                    Field created = entity.getClass().getField("created");
                    created.set(entity, obj.getCreated());
                    Field createdBy = entity.getClass().getField("createdBy");
                    createdBy.set(entity, obj.getCreatedBy());
                    Field createdByName = entity.getClass().getField("createdByName");
                    createdByName.set(entity, obj.getCreatedByName());
                }
                Field modified = entity.getClass().getField("modified");
                modified.set(entity, obj.getModified());
                Field modifiedBy = entity.getClass().getField("modifiedBy");
                modifiedBy.set(entity, obj.getModifiedBy());
                Field modifiedByName = entity.getClass().getField("modifiedByName");
                modifiedByName.set(entity, obj.getModifiedByName());
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(DungChung.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void getObject(T entity, CurrentUser user, int type) {
            user.setId(user.getId());
            user.setName(user.getName());
            Auditable aud = new Auditable();
            aud.isCreateOrUpdate(type, user);
            general<T> c = new general<>();
            c.setObject(entity, aud, type);
        }
    }
    
    public static class MESSAGE{
        public static String NOT_AUTHORIZATION = "Not authorization!";
        public static String NOT_LOGIN = "Not login!";
    }
}
