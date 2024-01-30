package io.openliberty.deepdive.rest.entities;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Getter
@Setter
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

    @SequenceGenerator(name = "SEQS",
            sequenceName = "student_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQS")
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