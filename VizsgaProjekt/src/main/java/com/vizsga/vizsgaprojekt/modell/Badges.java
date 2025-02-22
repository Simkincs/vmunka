/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vizsga.vizsgaprojekt.modell;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asd
 */
@Entity
@Table(name = "badges")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Badges.findAll", query = "SELECT b FROM Badges b"),
    @NamedQuery(name = "Badges.findById", query = "SELECT b FROM Badges b WHERE b.id = :id"),
    @NamedQuery(name = "Badges.findByUserId", query = "SELECT b FROM Badges b WHERE b.userId = :userId"),
    @NamedQuery(name = "Badges.findByTestsResultId", query = "SELECT b FROM Badges b WHERE b.testsResultId = :testsResultId"),
    @NamedQuery(name = "Badges.findByBadge", query = "SELECT b FROM Badges b WHERE b.badge = :badge")})
public class Badges implements Serializable {

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
    @Column(name = "tests_result_id")
    private int testsResultId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "badge")
    private String badge;

    public Badges() {
    }

    public Badges(Integer id) {
        this.id = id;
    }

    public Badges(Integer id, int userId, int testsResultId, String badge) {
        this.id = id;
        this.userId = userId;
        this.testsResultId = testsResultId;
        this.badge = badge;
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

    public int getTestsResultId() {
        return testsResultId;
    }

    public void setTestsResultId(int testsResultId) {
        this.testsResultId = testsResultId;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
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
        if (!(object instanceof Badges)) {
            return false;
        }
        Badges other = (Badges) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vizsga.vizsgaprojekt.modell.Badges[ id=" + id + " ]";
    }
    
}
