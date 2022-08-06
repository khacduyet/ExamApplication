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
@Table(name = "Question_Item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuestionItem.findAll", query = "SELECT q FROM QuestionItem q"),
    @NamedQuery(name = "QuestionItem.findById", query = "SELECT q FROM QuestionItem q WHERE q.id = :id"),
    @NamedQuery(name = "QuestionItem.findByName", query = "SELECT q FROM QuestionItem q WHERE q.name = :name"),
    @NamedQuery(name = "QuestionItem.findByNote", query = "SELECT q FROM QuestionItem q WHERE q.note = :note"),
    @NamedQuery(name = "QuestionItem.findByIsTrue", query = "SELECT q FROM QuestionItem q WHERE q.isTrue = :isTrue"),
    @NamedQuery(name = "QuestionItem.findByIdQuestion", query = "SELECT q FROM QuestionItem q WHERE q.idQuestion = :idQuestion"),
    @NamedQuery(name = "QuestionItem.findByCreated", query = "SELECT q FROM QuestionItem q WHERE q.created = :created"),
    @NamedQuery(name = "QuestionItem.findByCreatedBy", query = "SELECT q FROM QuestionItem q WHERE q.createdBy = :createdBy"),
    @NamedQuery(name = "QuestionItem.findByCreatedByName", query = "SELECT q FROM QuestionItem q WHERE q.createdByName = :createdByName"),
    @NamedQuery(name = "QuestionItem.findByModified", query = "SELECT q FROM QuestionItem q WHERE q.modified = :modified"),
    @NamedQuery(name = "QuestionItem.findByModifiedBy", query = "SELECT q FROM QuestionItem q WHERE q.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "QuestionItem.findByModifiedByName", query = "SELECT q FROM QuestionItem q WHERE q.modifiedByName = :modifiedByName")})
public class QuestionItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "Id")
    private String id;
    @Size(max = 3000)
    @Column(name = "Name")
    private String name;
    @Size(max = 3000)
    @Column(name = "Note")
    private String note;
    @Column(name = "isTrue")
    private Boolean isTrue;
    @Size(max = 1073741823)
    @Column(name = "IdQuestion")
    private String idQuestion;
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

    public QuestionItem() {
    }

    public QuestionItem(String id) {
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(Boolean isTrue) {
        this.isTrue = isTrue;
    }

    public String getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
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
        if (!(object instanceof QuestionItem)) {
            return false;
        }
        QuestionItem other = (QuestionItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.QuestionItem[ id=" + id + " ]";
    }
    
}
