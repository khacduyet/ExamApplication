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
@Table(name = "ContestUser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContestUser.findAll", query = "SELECT c FROM ContestUser c"),
    @NamedQuery(name = "ContestUser.findById", query = "SELECT c FROM ContestUser c WHERE c.id = :id"),
    @NamedQuery(name = "ContestUser.findByIdContest", query = "SELECT c FROM ContestUser c WHERE c.idContest = :idContest"),
    @NamedQuery(name = "ContestUser.findByIdUser", query = "SELECT c FROM ContestUser c WHERE c.idUser = :idUser"),
    @NamedQuery(name = "ContestUser.findByCreated", query = "SELECT c FROM ContestUser c WHERE c.created = :created"),
    @NamedQuery(name = "ContestUser.findByCreatedBy", query = "SELECT c FROM ContestUser c WHERE c.createdBy = :createdBy"),
    @NamedQuery(name = "ContestUser.findByCreatedByName", query = "SELECT c FROM ContestUser c WHERE c.createdByName = :createdByName"),
    @NamedQuery(name = "ContestUser.findByModified", query = "SELECT c FROM ContestUser c WHERE c.modified = :modified"),
    @NamedQuery(name = "ContestUser.findByModifiedBy", query = "SELECT c FROM ContestUser c WHERE c.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "ContestUser.findByModifiedByName", query = "SELECT c FROM ContestUser c WHERE c.modifiedByName = :modifiedByName")})
public class ContestUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "Id")
    private String id;
    @Size(max = 36)
    @Column(name = "IdContest")
    private String idContest;
    @Size(max = 36)
    @Column(name = "IdUser")
    private String idUser;
    private boolean isPassed;
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

    public boolean isIsPassed() {
        return isPassed;
    }

    public void setIsPassed(boolean isPassed) {
        this.isPassed = isPassed;
    }
    

    public ContestUser() {
    }

    public ContestUser(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdContest() {
        return idContest;
    }

    public void setIdContest(String idContest) {
        this.idContest = idContest;
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
        if (!(object instanceof ContestUser)) {
            return false;
        }
        ContestUser other = (ContestUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ContestUser[ id=" + id + " ]";
    }
    
}
