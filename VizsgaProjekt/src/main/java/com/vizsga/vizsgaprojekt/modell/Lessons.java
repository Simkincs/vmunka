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
import javax.persistence.Lob;
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
@Table(name = "lessons")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lessons.findAll", query = "SELECT l FROM Lessons l"),
    @NamedQuery(name = "Lessons.findById", query = "SELECT l FROM Lessons l WHERE l.id = :id"),
    @NamedQuery(name = "Lessons.findByCoursesId", query = "SELECT l FROM Lessons l WHERE l.coursesId = :coursesId"),
    @NamedQuery(name = "Lessons.findByTitles", query = "SELECT l FROM Lessons l WHERE l.titles = :titles"),
    @NamedQuery(name = "Lessons.findByIsDeleted", query = "SELECT l FROM Lessons l WHERE l.isDeleted = :isDeleted"),
    @NamedQuery(name = "Lessons.findByCreatedAt", query = "SELECT l FROM Lessons l WHERE l.createdAt = :createdAt"),
    @NamedQuery(name = "Lessons.findByDeletedAt", query = "SELECT l FROM Lessons l WHERE l.deletedAt = :deletedAt")})
public class Lessons implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "courses_id")
    private int coursesId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "titles")
    private String titles;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "contens")
    private String contens;
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

    public Lessons() {
    }

    public Lessons(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            Lessons l = em.find(Lessons.class, id);
            
            this.id = l.getId();
            this.coursesId = l.getCoursesId();
            this.titles = l.getTitles();
            this.contens = l.getContens();
            this.isDeleted = l.getIsDeleted();
            this.createdAt = l.getCreatedAt();
            this.deletedAt = l.getDeletedAt();
            
        } catch (Exception e) {
            System.err.println("Hiba: " + e.getLocalizedMessage());
        }finally{
            em.clear();
            em.close();
        }
        
    }

    public Lessons(Integer id, int coursesId, String titles, String contens, boolean isDeleted, Date createdAt, Date deletedAt) {
        this.id = id;
        this.coursesId = coursesId;
        this.titles = titles;
        this.contens = contens;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }
    
    public Lessons(Integer coursesId, String titles, String contens){
        this.coursesId = coursesId;
        this.titles = titles;
        this.contens = contens;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCoursesId() {
        return coursesId;
    }

    public void setCoursesId(int coursesId) {
        this.coursesId = coursesId;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getContens() {
        return contens;
    }

    public void setContens(String contens) {
        this.contens = contens;
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
        if (!(object instanceof Lessons)) {
            return false;
        }
        Lessons other = (Lessons) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vizsga.vizsgaprojekt.modell.Lessons[ id=" + id + " ]";
    }
    
    public Boolean addLesson(Lessons l){
        EntityManager em = emf.createEntityManager();
        
        try {
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addLesson");
            
            spq.registerStoredProcedureParameter("coursesIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("titleIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("contentIN", String.class, ParameterMode.IN);
            
            spq.setParameter("coursesIdIN", l.getCoursesId());
            spq.setParameter("titleIN", l.getTitles());
            spq.setParameter("contentIN", l.getContens());
            
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
