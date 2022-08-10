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
@Table(name = "DetailLevelMember")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailLevelMember.findAll", query = "SELECT d FROM DetailLevelMember d"),
    @NamedQuery(name = "DetailLevelMember.findById", query = "SELECT d FROM DetailLevelMember d WHERE d.id = :id"),
    @NamedQuery(name = "DetailLevelMember.findByStatus", query = "SELECT d FROM DetailLevelMember d WHERE d.status = :status"),
    @NamedQuery(name = "DetailLevelMember.findByIdLevel", query = "SELECT d FROM DetailLevelMember d WHERE d.idLevel = :idLevel"),
    @NamedQuery(name = "DetailLevelMember.findByIdUser", query = "SELECT d FROM DetailLevelMember d WHERE d.idUser = :idUser"),
    @NamedQuery(name = "DetailLevelMember.findByCreated", query = "SELECT d FROM DetailLevelMember d WHERE d.created = :created"),
    @NamedQuery(name = "DetailLevelMember.findByCreatedBy", query = "SELECT d FROM DetailLevelMember d WHERE d.createdBy = :createdBy"),
    @NamedQuery(name = "DetailLevelMember.findByCreatedByName", query = "SELECT d FROM DetailLevelMember d WHERE d.createdByName = :createdByName"),
    @NamedQuery(name = "DetailLevelMember.findByModified", query = "SELECT d FROM DetailLevelMember d WHERE d.modified = :modified"),
    @NamedQuery(name = "DetailLevelMember.findByModifiedBy", query = "SELECT d FROM DetailLevelMember d WHERE d.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "DetailLevelMember.findByModifiedByName", query = "SELECT d FROM DetailLevelMember d WHERE d.modifiedByName = :modifiedByName")})
public class DetailLevelMember implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "Id")
    private String id;
    @Column(name = "Status")
    private Boolean status;
    @Size(max = 1073741823)
    @Column(name = "IdLevel")
    private String idLevel;
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

    public DetailLevelMember() {
    }

    public DetailLevelMember(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getIdLevel() {
        return idLevel;
    }

    public void setIdLevel(String idLevel) {
        this.idLevel = idLevel;
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
        if (!(object instanceof DetailLevelMember)) {
            return false;
        }
        DetailLevelMember other = (DetailLevelMember) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DetailLevelMember[ id=" + id + " ]";
    }
    
}
