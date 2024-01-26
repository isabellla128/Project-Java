package io.openliberty.deepdive.rest.model;

import java.io.Serializable;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Schema(name = "Student",
        description = "POJO that represents a single inventory entry.")
@Entity
@Table(name = "Student")
@NamedQuery(name = "Student.findAll", query = "SELECT e FROM Student e")
@NamedQuery(name = "Student.findStudent",
        query = "SELECT e FROM Student e WHERE e.name = :name")
@NamedQuery(name = "Student.findStudentByEmail",
        query = "SELECT e FROM Student e WHERE e.email = :email")
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    @SequenceGenerator(name = "SEQ",
            sequenceName = "student_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQ")
    @Id
    @Column(name = "id")
    private int id;

    @Schema(required = true)
    @Column(name = "name")
    private String name;

    @Schema(required = true)
    @Column(name = "email")
    private String email;

    @Column(name = "grade")
    private String grade;

    @Column(name = "role")
    private String role;

    @Column(name = "password")
    private String password;

    public Student() {
    }

    public Student(String name, String email, String grade) {
        this.name = name;
        this.email = email;
        this.grade = grade;
        this.role = "student";
        this.password = "1234";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public boolean equals(Object emailname) {
        if (emailname instanceof Student) {
            return email.equals(((Student)emailname).getEmail());
        }
        return false;
    }
}