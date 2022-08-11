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
@Table(name = "ResultExam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResultExam.findAll", query = "SELECT r FROM ResultExam r"),
    @NamedQuery(name = "ResultExam.findById", query = "SELECT r FROM ResultExam r WHERE r.id = :id"),
    @NamedQuery(name = "ResultExam.findByPoint", query = "SELECT r FROM ResultExam r WHERE r.point = :point"),
    @NamedQuery(name = "ResultExam.findByNote", query = "SELECT r FROM ResultExam r WHERE r.note = :note"),
    @NamedQuery(name = "ResultExam.findByStatus", query = "SELECT r FROM ResultExam r WHERE r.status = :status"),
    @NamedQuery(name = "ResultExam.findByIdExam", query = "SELECT r FROM ResultExam r WHERE r.idExam = :idExam"),
    @NamedQuery(name = "ResultExam.findByIdUser", query = "SELECT r FROM ResultExam r WHERE r.idUser = :idUser"),
    @NamedQuery(name = "ResultExam.findByIdContest", query = "SELECT r FROM ResultExam r WHERE r.idContest = :idContest"),
    @NamedQuery(name = "ResultExam.findByMinuted", query = "SELECT r FROM ResultExam r WHERE r.minuted = :minuted"),
    @NamedQuery(name = "ResultExam.findByCorrected", query = "SELECT r FROM ResultExam r WHERE r.corrected = :corrected"),
    @NamedQuery(name = "ResultExam.findByScore", query = "SELECT r FROM ResultExam r WHERE r.score = :score"),
    @NamedQuery(name = "ResultExam.findByCreated", query = "SELECT r FROM ResultExam r WHERE r.created = :created"),
    @NamedQuery(name = "ResultExam.findByCreatedBy", query = "SELECT r FROM ResultExam r WHERE r.createdBy = :createdBy"),
    @NamedQuery(name = "ResultExam.findByCreatedByName", query = "SELECT r FROM ResultExam r WHERE r.createdByName = :createdByName"),
    @NamedQuery(name = "ResultExam.findByModified", query = "SELECT r FROM ResultExam r WHERE r.modified = :modified"),
    @NamedQuery(name = "ResultExam.findByModifiedBy", query = "SELECT r FROM ResultExam r WHERE r.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "ResultExam.findByModifiedByName", query = "SELECT r FROM ResultExam r WHERE r.modifiedByName = :modifiedByName")})
public class ResultExam implements Serializable {
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
    @Size(max = 1073741823)
    @Column(name = "IdUser")
    private String idUser;
    @Size(max = 1073741823)
    @Column(name = "IdContest")
    private String idContest;
    @Column(name = "Minuted")
    private Integer minuted;
    @Size(max = 1073741823)
    @Column(name = "Corrected")
    private String corrected;
    @Size(max = 1073741823)
    @Column(name = "Score")
    private String score;
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

    public ResultExam() {
    }

    public ResultExam(String id) {
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

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdContest() {
        return idContest;
    }

    public void setIdContest(String idContest) {
        this.idContest = idContest;
    }

    public Integer getMinuted() {
        return minuted;
    }

    public void setMinuted(Integer minuted) {
        this.minuted = minuted;
    }

    public String getCorrected() {
        return corrected;
    }

    public void setCorrected(String corrected) {
        this.corrected = corrected;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
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
        if (!(object instanceof ResultExam)) {
            return false;
        }
        ResultExam other = (ResultExam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ResultExam[ id=" + id + " ]";
    }
    
}
