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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asd
 */
@Entity
@Table(name = "users_x_courses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersXCourses.findAll", query = "SELECT u FROM UsersXCourses u"),
    @NamedQuery(name = "UsersXCourses.findById", query = "SELECT u FROM UsersXCourses u WHERE u.id = :id"),
    @NamedQuery(name = "UsersXCourses.findByUserId", query = "SELECT u FROM UsersXCourses u WHERE u.userId = :userId"),
    @NamedQuery(name = "UsersXCourses.findByCourseId", query = "SELECT u FROM UsersXCourses u WHERE u.courseId = :courseId")})
public class UsersXCourses implements Serializable {

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
    @Column(name = "course_id")
    private int courseId;

    public UsersXCourses() {
    }

    public UsersXCourses(Integer id) {
        this.id = id;
    }

    public UsersXCourses(Integer id, int userId, int courseId) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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
        if (!(object instanceof UsersXCourses)) {
            return false;
        }
        UsersXCourses other = (UsersXCourses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vizsga.vizsgaprojekt.modell.UsersXCourses[ id=" + id + " ]";
    }
    
}
