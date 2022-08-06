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
@Table(name = "LevelMember")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LevelMember.findAll", query = "SELECT l FROM LevelMember l"),
    @NamedQuery(name = "LevelMember.findById", query = "SELECT l FROM LevelMember l WHERE l.id = :id"),
    @NamedQuery(name = "LevelMember.findByName", query = "SELECT l FROM LevelMember l WHERE l.name = :name"),
    @NamedQuery(name = "LevelMember.findByPointLevelUp", query = "SELECT l FROM LevelMember l WHERE l.pointLevelUp = :pointLevelUp"),
    @NamedQuery(name = "LevelMember.findByNote", query = "SELECT l FROM LevelMember l WHERE l.note = :note"),
    @NamedQuery(name = "LevelMember.findByIcon", query = "SELECT l FROM LevelMember l WHERE l.icon = :icon"),
    @NamedQuery(name = "LevelMember.findByStatus", query = "SELECT l FROM LevelMember l WHERE l.status = :status"),
    @NamedQuery(name = "LevelMember.findByCreated", query = "SELECT l FROM LevelMember l WHERE l.created = :created"),
    @NamedQuery(name = "LevelMember.findByCreatedBy", query = "SELECT l FROM LevelMember l WHERE l.createdBy = :createdBy"),
    @NamedQuery(name = "LevelMember.findByCreatedByName", query = "SELECT l FROM LevelMember l WHERE l.createdByName = :createdByName"),
    @NamedQuery(name = "LevelMember.findByModified", query = "SELECT l FROM LevelMember l WHERE l.modified = :modified"),
    @NamedQuery(name = "LevelMember.findByModifiedBy", query = "SELECT l FROM LevelMember l WHERE l.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "LevelMember.findByModifiedByName", query = "SELECT l FROM LevelMember l WHERE l.modifiedByName = :modifiedByName")})
public class LevelMember implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "Id")
    private String id;
    @Size(max = 255)
    @Column(name = "Name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PointLevelUp")
    private Float pointLevelUp;
    @Size(max = 3000)
    @Column(name = "Note")
    private String note;
    @Size(max = 500)
    @Column(name = "Icon")
    private String icon;
    @Column(name = "Status")
    private Boolean status;
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

    public LevelMember() {
    }

    public LevelMember(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPointLevelUp() {
        return pointLevelUp;
    }

    public void setPointLevelUp(Float pointLevelUp) {
        this.pointLevelUp = pointLevelUp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
        if (!(object instanceof LevelMember)) {
            return false;
        }
        LevelMember other = (LevelMember) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.LevelMember[ id=" + id + " ]";
    }
    
}
