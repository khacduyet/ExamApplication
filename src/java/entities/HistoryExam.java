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
@Table(name = "HistoryExam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoryExam.findAll", query = "SELECT h FROM HistoryExam h"),
    @NamedQuery(name = "HistoryExam.findById", query = "SELECT h FROM HistoryExam h WHERE h.id = :id"),
    @NamedQuery(name = "HistoryExam.findByPoint", query = "SELECT h FROM HistoryExam h WHERE h.point = :point"),
    @NamedQuery(name = "HistoryExam.findByNote", query = "SELECT h FROM HistoryExam h WHERE h.note = :note"),
    @NamedQuery(name = "HistoryExam.findByStatus", query = "SELECT h FROM HistoryExam h WHERE h.status = :status"),
    @NamedQuery(name = "HistoryExam.findByIdExam", query = "SELECT h FROM HistoryExam h WHERE h.idExam = :idExam"),
    @NamedQuery(name = "HistoryExam.findByCreated", query = "SELECT h FROM HistoryExam h WHERE h.created = :created"),
    @NamedQuery(name = "HistoryExam.findByCreatedBy", query = "SELECT h FROM HistoryExam h WHERE h.createdBy = :createdBy"),
    @NamedQuery(name = "HistoryExam.findByCreatedByName", query = "SELECT h FROM HistoryExam h WHERE h.createdByName = :createdByName"),
    @NamedQuery(name = "HistoryExam.findByModified", query = "SELECT h FROM HistoryExam h WHERE h.modified = :modified"),
    @NamedQuery(name = "HistoryExam.findByModifiedBy", query = "SELECT h FROM HistoryExam h WHERE h.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "HistoryExam.findByModifiedByName", query = "SELECT h FROM HistoryExam h WHERE h.modifiedByName = :modifiedByName")})
public class HistoryExam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "Id")
    private String id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Point")
    private Float point;
    @Size(max = 3000)
    @Column(name = "Note")
    private String note;
    @Column(name = "Status")
    private Boolean status;
    @Size(max = 1073741823)
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

    public HistoryExam() {
    }

    public HistoryExam(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getPoint() {
        return point;
    }

    public void setPoint(Float point) {
        this.point = point;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
        if (!(object instanceof HistoryExam)) {
            return false;
        }
        HistoryExam other = (HistoryExam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.HistoryExam[ id=" + id + " ]";
    }
    
}
