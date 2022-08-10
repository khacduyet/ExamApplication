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
@Table(name = "QA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Qa.findAll", query = "SELECT q FROM Qa q"),
    @NamedQuery(name = "Qa.findById", query = "SELECT q FROM Qa q WHERE q.id = :id"),
    @NamedQuery(name = "Qa.findByContent", query = "SELECT q FROM Qa q WHERE q.content = :content"),
    @NamedQuery(name = "Qa.findByIsCauHoi", query = "SELECT q FROM Qa q WHERE q.isCauHoi = :isCauHoi"),
    @NamedQuery(name = "Qa.findByStatus", query = "SELECT q FROM Qa q WHERE q.status = :status"),
    @NamedQuery(name = "Qa.findByIdParent", query = "SELECT q FROM Qa q WHERE q.idParent = :idParent"),
    @NamedQuery(name = "Qa.findByIdSubject", query = "SELECT q FROM Qa q WHERE q.idSubject = :idSubject"),
    @NamedQuery(name = "Qa.findByIdUser", query = "SELECT q FROM Qa q WHERE q.idUser = :idUser"),
    @NamedQuery(name = "Qa.findByCreated", query = "SELECT q FROM Qa q WHERE q.created = :created"),
    @NamedQuery(name = "Qa.findByCreatedBy", query = "SELECT q FROM Qa q WHERE q.createdBy = :createdBy"),
    @NamedQuery(name = "Qa.findByCreatedByName", query = "SELECT q FROM Qa q WHERE q.createdByName = :createdByName"),
    @NamedQuery(name = "Qa.findByModified", query = "SELECT q FROM Qa q WHERE q.modified = :modified"),
    @NamedQuery(name = "Qa.findByModifiedBy", query = "SELECT q FROM Qa q WHERE q.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "Qa.findByModifiedByName", query = "SELECT q FROM Qa q WHERE q.modifiedByName = :modifiedByName")})
public class Qa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "Id")
    private String id;
    @Size(max = 3000)
    @Column(name = "Content")
    private String content;
    @Column(name = "isCauHoi")
    private Boolean isCauHoi;
    @Column(name = "Status")
    private Boolean status;
    @Size(max = 1073741823)
    @Column(name = "IdParent")
    private String idParent;
    @Size(max = 1073741823)
    @Column(name = "IdSubject")
    private String idSubject;
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

    public Qa() {
    }

    public Qa(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIsCauHoi() {
        return isCauHoi;
    }

    public void setIsCauHoi(Boolean isCauHoi) {
        this.isCauHoi = isCauHoi;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getIdParent() {
        return idParent;
    }

    public void setIdParent(String idParent) {
        this.idParent = idParent;
    }

    public String getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(String idSubject) {
        this.idSubject = idSubject;
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
        if (!(object instanceof Qa)) {
            return false;
        }
        Qa other = (Qa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Qa[ id=" + id + " ]";
    }
    
}
