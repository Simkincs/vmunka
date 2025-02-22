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
@Table(name = "tests_answers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestsAnswers.findAll", query = "SELECT t FROM TestsAnswers t"),
    @NamedQuery(name = "TestsAnswers.findById", query = "SELECT t FROM TestsAnswers t WHERE t.id = :id"),
    @NamedQuery(name = "TestsAnswers.findByTestsId", query = "SELECT t FROM TestsAnswers t WHERE t.testsId = :testsId"),
    @NamedQuery(name = "TestsAnswers.findByAnswer", query = "SELECT t FROM TestsAnswers t WHERE t.answer = :answer"),
    @NamedQuery(name = "TestsAnswers.findByValideAnswer", query = "SELECT t FROM TestsAnswers t WHERE t.valideAnswer = :valideAnswer"),
    @NamedQuery(name = "TestsAnswers.findByRightAnswer", query = "SELECT t FROM TestsAnswers t WHERE t.rightAnswer = :rightAnswer")})
public class TestsAnswers implements Serializable {

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
    @Column(name = "answer")
    private String answer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valide_answer")
    private boolean valideAnswer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "right_answer")
    private String rightAnswer;

    public TestsAnswers() {
    }

    public TestsAnswers(Integer id) {
        this.id = id;
    }

    public TestsAnswers(Integer id, int testsId, String answer, boolean valideAnswer, String rightAnswer) {
        this.id = id;
        this.testsId = testsId;
        this.answer = answer;
        this.valideAnswer = valideAnswer;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean getValideAnswer() {
        return valideAnswer;
    }

    public void setValideAnswer(boolean valideAnswer) {
        this.valideAnswer = valideAnswer;
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
        if (!(object instanceof TestsAnswers)) {
            return false;
        }
        TestsAnswers other = (TestsAnswers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vizsga.vizsgaprojekt.modell.TestsAnswers[ id=" + id + " ]";
    }
    
}
