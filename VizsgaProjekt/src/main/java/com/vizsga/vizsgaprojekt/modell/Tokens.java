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
@Table(name = "tokens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tokens.findAll", query = "SELECT t FROM Tokens t"),
    @NamedQuery(name = "Tokens.findById", query = "SELECT t FROM Tokens t WHERE t.id = :id"),
    @NamedQuery(name = "Tokens.findByUserId", query = "SELECT t FROM Tokens t WHERE t.userId = :userId"),
    @NamedQuery(name = "Tokens.findByIsDeleted", query = "SELECT t FROM Tokens t WHERE t.isDeleted = :isDeleted"),
    @NamedQuery(name = "Tokens.findByCreatedAt", query = "SELECT t FROM Tokens t WHERE t.createdAt = :createdAt"),
    @NamedQuery(name = "Tokens.findByDeletedAt", query = "SELECT t FROM Tokens t WHERE t.deletedAt = :deletedAt")})
public class Tokens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "token")
    private String token;
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
    
    public Tokens() {
    }

    public Tokens(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            Tokens t = em.find(Tokens.class , token);
            
            this.id = t.getId();
            this.userId = t.getUserId();
            this.token = t.getToken();
            this.isDeleted = t.getIsDeleted();
            this.createdAt = t.getCreatedAt();
            this.deletedAt = t.getDeletedAt();
            
        }  catch (Exception e) {
            System.err.println("Hiba: " + e.getLocalizedMessage());
        }finally{
            em.clear();
            em.close();
        }
    }

    public Tokens(Integer id, int userId, String token, boolean isDeleted, Date createdAt, Date deletedAt) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
        if (!(object instanceof Tokens)) {
            return false;
        }
        Tokens other = (Tokens) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vizsga.vizsgaprojekt.modell.Tokens[ id=" + id + " ]";
    }
    
    public static void saveToken(Integer userId, String token) {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("saveToken");
            
            spq.registerStoredProcedureParameter("userIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("tokenIN", String.class, ParameterMode.IN);
            
            spq.setParameter("userIdIN", userId);
            spq.setParameter("tokenIN", token);
            
            spq.execute();
            
        } catch (Exception e) {
            System.err.println("Hiba: "+ e.getLocalizedMessage());
        }finally{
            em.clear();
            em.close();
        }
    }
    
}
