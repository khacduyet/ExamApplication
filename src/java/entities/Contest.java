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
@Table(name = "Contest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contest.findAll", query = "SELECT c FROM Contest c"),
    @NamedQuery(name = "Contest.findById", query = "SELECT c FROM Contest c WHERE c.id = :id"),
    @NamedQuery(name = "Contest.findByClassName", query = "SELECT c FROM Contest c WHERE c.className = :className"),
    @NamedQuery(name = "Contest.findByTestDate", query = "SELECT c FROM Contest c WHERE c.testDate = :testDate"),
    @NamedQuery(name = "Contest.findByIdSubject", query = "SELECT c FROM Contest c WHERE c.idSubject = :idSubject"),
    @NamedQuery(name = "Contest.findByIdClass", query = "SELECT c FROM Contest c WHERE c.idClass = :idClass"),
    @NamedQuery(name = "Contest.findByTestTime", query = "SELECT c FROM Contest c WHERE c.testTime = :testTime"),
    @NamedQuery(name = "Contest.findByStatus", query = "SELECT c FROM Contest c WHERE c.status = :status"),
    @NamedQuery(name = "Contest.findByCreated", query = "SELECT c FROM Contest c WHERE c.created = :created"),
    @NamedQuery(name = "Contest.findByCreatedBy", query = "SELECT c FROM Contest c WHERE c.createdBy = :createdBy"),
    @NamedQuery(name = "Contest.findByCreatedByName", query = "SELECT c FROM Contest c WHERE c.createdByName = :createdByName"),
    @NamedQuery(name = "Contest.findByModified", query = "SELECT c FROM Contest c WHERE c.modified = :modified"),
    @NamedQuery(name = "Contest.findByModifiedBy", query = "SELECT c FROM Contest c WHERE c.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "Contest.findByModifiedByName", query = "SELECT c FROM Contest c WHERE c.modifiedByName = :modifiedByName")})
public class Contest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "Id")
    private String id;
    @Size(max = 255)
    @Column(name = "ClassName")
    private String className;
    @Size(max = 27)
    @Column(name = "TestDate")
    private String testDate;
    @Size(max = 36)
    @Column(name = "IdSubject")
    private String idSubject;
    @Size(max = 36)
    @Column(name = "IdClass")
    private String idClass;
    @Column(name = "TestTime")
    private Integer testTime;
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

    public Contest() {
    }

    public Contest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public String getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(String idSubject) {
        this.idSubject = idSubject;
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public Integer getTestTime() {
        return testTime;
    }

    public void setTestTime(Integer testTime) {
        this.testTime = testTime;
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
        if (!(object instanceof Contest)) {
            return false;
        }
        Contest other = (Contest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Contest[ id=" + id + " ]";
    }
    
}
