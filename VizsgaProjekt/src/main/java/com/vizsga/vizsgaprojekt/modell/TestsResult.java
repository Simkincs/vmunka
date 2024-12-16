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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "tests_result")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestsResult.findAll", query = "SELECT t FROM TestsResult t"),
    @NamedQuery(name = "TestsResult.findById", query = "SELECT t FROM TestsResult t WHERE t.id = :id"),
    @NamedQuery(name = "TestsResult.findByPoint", query = "SELECT t FROM TestsResult t WHERE t.point = :point"),
    @NamedQuery(name = "TestsResult.findByComment", query = "SELECT t FROM TestsResult t WHERE t.comment = :comment"),
    @NamedQuery(name = "TestsResult.findByTestsId", query = "SELECT t FROM TestsResult t WHERE t.testsId = :testsId"),
    @NamedQuery(name = "TestsResult.findByGradeId", query = "SELECT t FROM TestsResult t WHERE t.gradeId = :gradeId"),
    @NamedQuery(name = "TestsResult.findByUsersId", query = "SELECT t FROM TestsResult t WHERE t.usersId = :usersId"),
    @NamedQuery(name = "TestsResult.findByCreatedAt", query = "SELECT t FROM TestsResult t WHERE t.createdAt = :createdAt")})
public class TestsResult implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "point")
    private int point;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "comment")
    private String comment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tests_id")
    private int testsId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "grade_id")
    private int gradeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "users_id")
    private int usersId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public TestsResult() {
    }

    public TestsResult(Integer id) {
        this.id = id;
    }

    public TestsResult(Integer id, int point, String comment, int testsId, int gradeId, int usersId, Date createdAt) {
        this.id = id;
        this.point = point;
        this.comment = comment;
        this.testsId = testsId;
        this.gradeId = gradeId;
        this.usersId = usersId;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTestsId() {
        return testsId;
    }

    public void setTestsId(int testsId) {
        this.testsId = testsId;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
        if (!(object instanceof TestsResult)) {
            return false;
        }
        TestsResult other = (TestsResult) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vizsga.vizsgaprojekt.modell.TestsResult[ id=" + id + " ]";
    }
    
}
