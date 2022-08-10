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
@Table(name = "DetailContestExam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailContestExam.findAll", query = "SELECT d FROM DetailContestExam d"),
    @NamedQuery(name = "DetailContestExam.findById", query = "SELECT d FROM DetailContestExam d WHERE d.id = :id"),
    @NamedQuery(name = "DetailContestExam.findByIdContest", query = "SELECT d FROM DetailContestExam d WHERE d.idContest = :idContest"),
    @NamedQuery(name = "DetailContestExam.findByIdExam", query = "SELECT d FROM DetailContestExam d WHERE d.idExam = :idExam"),
    @NamedQuery(name = "DetailContestExam.findByCreated", query = "SELECT d FROM DetailContestExam d WHERE d.created = :created"),
    @NamedQuery(name = "DetailContestExam.findByCreatedBy", query = "SELECT d FROM DetailContestExam d WHERE d.createdBy = :createdBy"),
    @NamedQuery(name = "DetailContestExam.findByCreatedByName", query = "SELECT d FROM DetailContestExam d WHERE d.createdByName = :createdByName"),
    @NamedQuery(name = "DetailContestExam.findByModified", query = "SELECT d FROM DetailContestExam d WHERE d.modified = :modified"),
    @NamedQuery(name = "DetailContestExam.findByModifiedBy", query = "SELECT d FROM DetailContestExam d WHERE d.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "DetailContestExam.findByModifiedByName", query = "SELECT d FROM DetailContestExam d WHERE d.modifiedByName = :modifiedByName")})
public class DetailContestExam implements Serializable {
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
    @Column(name = "IdExam")
    private String idExam;
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

    public DetailContestExam() {
    }

    public DetailContestExam(String id) {
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

    public String getIdExam() {
        return idExam;
    }

    public void setIdExam(String idExam) {
        this.idExam = idExam;
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
        if (!(object instanceof DetailContestExam)) {
            return false;
        }
        DetailContestExam other = (DetailContestExam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DetailContestExam[ id=" + id + " ]";
    }
    
}
