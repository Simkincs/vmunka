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

/**
 *
 * @author asd
 */
@Entity
@Table(name = "tests_question")
@NamedQueries({
    @NamedQuery(name = "TestsQuestion.findAll", query = "SELECT t FROM TestsQuestion t"),
    @NamedQuery(name = "TestsQuestion.findById", query = "SELECT t FROM TestsQuestion t WHERE t.id = :id"),
    @NamedQuery(name = "TestsQuestion.findByTestsId", query = "SELECT t FROM TestsQuestion t WHERE t.testsId = :testsId"),
    @NamedQuery(name = "TestsQuestion.findByDescription", query = "SELECT t FROM TestsQuestion t WHERE t.description = :description"),
    @NamedQuery(name = "TestsQuestion.findByChoices", query = "SELECT t FROM TestsQuestion t WHERE t.choices = :choices"),
    @NamedQuery(name = "TestsQuestion.findByUsersAnswers", query = "SELECT t FROM TestsQuestion t WHERE t.usersAnswers = :usersAnswers"),
    @NamedQuery(name = "TestsQuestion.findByRightAnswer", query = "SELECT t FROM TestsQuestion t WHERE t.rightAnswer = :rightAnswer")})
public class TestsQuestion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tests_id")
    private int testsId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "choices")
    private String choices;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "users_answers")
    private String usersAnswers;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "right_answer")
    private String rightAnswer;

    public TestsQuestion() {
    }

    public TestsQuestion(Integer id) {
        this.id = id;
    }

    public TestsQuestion(Integer id, int testsId, String description, String choices, String usersAnswers, String rightAnswer) {
        this.id = id;
        this.testsId = testsId;
        this.description = description;
        this.choices = choices;
        this.usersAnswers = usersAnswers;
        this.rightAnswer = rightAnswer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTestsId() {
        return testsId;
    }

    public void setTestsId(int testsId) {
        this.testsId = testsId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChoices() {
        return choices;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    public String getUsersAnswers() {
        return usersAnswers;
    }

    public void setUsersAnswers(String usersAnswers) {
        this.usersAnswers = usersAnswers;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
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
        if (!(object instanceof TestsQuestion)) {
            return false;
        }
        TestsQuestion other = (TestsQuestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vizsga.vizsgaprojekt.modell.TestsQuestion[ id=" + id + " ]";
    }
    
}
