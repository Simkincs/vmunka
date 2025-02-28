/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vizsga.vizsgaprojekt.modell;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asd
 */
@Entity
@Table(name = "tests")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tests.findAll", query = "SELECT t FROM Tests t"),
    @NamedQuery(name = "Tests.findById", query = "SELECT t FROM Tests t WHERE t.id = :id"),
    @NamedQuery(name = "Tests.findByLessonsId", query = "SELECT t FROM Tests t WHERE t.lessonsId = :lessonsId"),
    @NamedQuery(name = "Tests.findByTitle", query = "SELECT t FROM Tests t WHERE t.title = :title"),
    @NamedQuery(name = "Tests.findByQuestin", query = "SELECT t FROM Tests t WHERE t.questin = :questin"),
    @NamedQuery(name = "Tests.findByIsDeleted", query = "SELECT t FROM Tests t WHERE t.isDeleted = :isDeleted"),
    @NamedQuery(name = "Tests.findByCreatedAt", query = "SELECT t FROM Tests t WHERE t.createdAt = :createdAt"),
    @NamedQuery(name = "Tests.findByDeletedAt", query = "SELECT t FROM Tests t WHERE t.deletedAt = :deletedAt")})
public class Tests implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lessons_id")
    private int lessonsId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "questin")
    private String questin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.vizsga_VizsgaProjekt_war_1.0-SNAPSHOTPU");

    public Tests() {
    }

    public Tests(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            Tests t = em.find(Tests.class, id);
            
            this.id = t.getId();
            this.lessonsId = t.getLessonsId();
            this.title = t.getTitle();
            this.questin = t.getQuestin();
            this.isDeleted = t.getIsDeleted();
            this.createdAt = t.getCreatedAt();
            this.deletedAt = t.getDeletedAt();
            
        } catch (Exception e) {
            System.err.println("Hiba: " + e.getLocalizedMessage());
        }finally{
            em.clear();
            em.close();
        }
    }

    public Tests(Integer id, int lessonsId, String title, String questin, boolean isDeleted, Date createdAt, Date deletedAt) {
        this.id = id;
        this.lessonsId = lessonsId;
        this.title = title;
        this.questin = questin;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }
    
    public Tests(Integer lessonsId, String title, String questin){
        this.lessonsId = lessonsId;
        this.title = title;
        this.questin = questin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getLessonsId() {
        return lessonsId;
    }

    public void setLessonsId(int lessonsId) {
        this.lessonsId = lessonsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestin() {
        return questin;
    }

    public void setQuestin(String questin) {
        this.questin = questin;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
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
        if (!(object instanceof Tests)) {
            return false;
        }
        Tests other = (Tests) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vizsga.vizsgaprojekt.modell.Tests[ id=" + id + " ]";
    }
    
    public Boolean addTest(Tests t){
        EntityManager em = emf.createEntityManager();
        
        try {
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addTest");
            
            spq.registerStoredProcedureParameter("lessonIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("titleIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("questionIN", String.class, ParameterMode.IN);
            
            spq.setParameter("lessonIdIN", t.getLessonsId());
            spq.setParameter("titleIN", t.getTitle());
            spq.setParameter("questionIN", t.getQuestin());
            
            spq.execute();
            
            return true;
        } catch (Exception e) {
            System.err.println("Hiba"+e.getLocalizedMessage());
            return false;
        }finally{
            em.clear();
            em.close();
        }
        
    }
    
}
