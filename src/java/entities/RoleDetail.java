/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "Role_Detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoleDetail.findAll", query = "SELECT r FROM RoleDetail r"),
    @NamedQuery(name = "RoleDetail.findById", query = "SELECT r FROM RoleDetail r WHERE r.id = :id"),
    @NamedQuery(name = "RoleDetail.findByIdRole", query = "SELECT r FROM RoleDetail r WHERE r.idRole = :idRole"),
    @NamedQuery(name = "RoleDetail.findByCreated", query = "SELECT r FROM RoleDetail r WHERE r.created = :created"),
    @NamedQuery(name = "RoleDetail.findByCreatedBy", query = "SELECT r FROM RoleDetail r WHERE r.createdBy = :createdBy"),
    @NamedQuery(name = "RoleDetail.findByCreatedByName", query = "SELECT r FROM RoleDetail r WHERE r.createdByName = :createdByName"),
    @NamedQuery(name = "RoleDetail.findByModified", query = "SELECT r FROM RoleDetail r WHERE r.modified = :modified"),
    @NamedQuery(name = "RoleDetail.findByModifiedBy", query = "SELECT r FROM RoleDetail r WHERE r.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "RoleDetail.findByModifiedByName", query = "SELECT r FROM RoleDetail r WHERE r.modifiedByName = :modifiedByName")})
public class RoleDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "Id")
    private String id;
    @Size(max = 1073741823)
    @Column(name = "IdRole")
    private String idRole;
    @Size(max = 1073741823)
    @Column(name = "IdUser")
    private String idUser;
    @Size(max = 27)
    @Column(name = "Created")
    public String created;
    @Size(max = 36)
    @Column(name = "CreatedBy")
    public String createdBy;
    @Size(max = 200)
    @Column(name = "CreatedByName")
    public String createdByName;
    @Size(max = 27)
    @Column(name = "Modified")
    public String modified;
    @Size(max = 36)
    @Column(name = "ModifiedBy")
    public String modifiedBy;
    @Size(max = 200)
    @Column(name = "ModifiedByName")
    public String modifiedByName;

    public RoleDetail() {
    }

    public RoleDetail(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleDetail)) {
            return false;
        }
        RoleDetail other = (RoleDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RoleDetail[ id=" + id + " ]";
    }
    
}
