package io.openliberty.deepdive.rest.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

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

    @Column(name = "dormitory")
    private String dormitory;

    @Column(name = "room")
    private String room;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
    private List<Preference> preferences = new ArrayList<>();

    public Student() {
    }

    public Student(String name, String email, String grade) {
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
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