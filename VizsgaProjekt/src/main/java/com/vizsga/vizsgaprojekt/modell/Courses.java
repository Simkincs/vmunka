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
@Table(name = "courses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Courses.findAll", query = "SELECT c FROM Courses c"),
    @NamedQuery(name = "Courses.findById", query = "SELECT c FROM Courses c WHERE c.id = :id"),
    @NamedQuery(name = "Courses.findByName", query = "SELECT c FROM Courses c WHERE c.name = :name"),
    @NamedQuery(name = "Courses.findByDescription", query = "SELECT c FROM Courses c WHERE c.description = :description"),
    @NamedQuery(name = "Courses.findByIsDeleted", query = "SELECT c FROM Courses c WHERE c.isDeleted = :isDeleted"),
    @NamedQuery(name = "Courses.findByCreatedAt", query = "SELECT c FROM Courses c WHERE c.createdAt = :createdAt"),
    @NamedQuery(name = "Courses.findByDeletedAt", query = "SELECT c FROM Courses c WHERE c.deletedAt = :deletedAt")})
public class Courses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
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
    
    public Courses() {
    }

    public Courses(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            Courses c = em.find(Courses.class, id);
            
            this.id = c.getId();
            this.name = c.getName();
            this.description = c.getDescription();
            this.isDeleted = c.getIsDeleted();
            this.createdAt = c.getCreatedAt();
            this.deletedAt = c.getDeletedAt();
            
            
        } catch (Exception e) {
            System.err.println("Hiba: " + e.getLocalizedMessage());
        }finally{
            em.clear();
            em.close();
        }
        
    }

    public Courses(Integer id, String name, String description, boolean isDeleted, Date createdAt, Date deletedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }
    
    public Courses(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Courses)) {
            return false;
        }
        Courses other = (Courses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vizsga.vizsgaprojekt.modell.Courses[ id=" + id + " ]";
    }
    
    public Boolean addCourses(Courses c) {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addCourses");
            
            spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("descriptionIN", String.class, ParameterMode.IN);
            
            spq.setParameter("nameIN", c.getName());
            spq.setParameter("descriptionIN", c.getDescription());
            
            spq.execute();
            
            return true;
        } catch (Exception e) {
            System.err.println("Hiba"+e.getLocalizedMessage());
            return null;
        }finally{
            em.clear();
            em.close();
        }
    }
    
    
}
