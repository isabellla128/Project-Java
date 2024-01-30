package io.openliberty.deepdive.rest.entities;

import java.io.Serializable;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Schema(name = "Student",
        description = "POJO that represents a single inventory entry.")
@Entity
@Table(name = "Student")
@NamedQuery(name = "Student.findAll", query = "SELECT e FROM Student e")
@NamedQuery(name = "Student.findStudent",
        query = "SELECT e FROM Student e WHERE e.name = :name")
@NamedQuery(name = "Student.findStudentByUsername",
        query = "SELECT e FROM Student e WHERE e.username = :username")
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
    @Column(name = "username")
    private String username;

    @Column(name = "grade")
    private String grade;

    @Column(name = "dormitory")
    private String dormitory;

    @Column(name = "room")
    private String room;

    public Student() {
    }

    public Student(String name, String username, String grade) {
        this.name = name;
        this.username = username;
        this.grade = grade;
        this.dormitory=null;
        this.room=null;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object user) {
        if (user instanceof Student) {
            return username.equals(((Student)user).getUsername());
        }
        return false;
    }
}