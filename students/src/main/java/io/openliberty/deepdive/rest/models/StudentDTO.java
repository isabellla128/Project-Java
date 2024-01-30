package io.openliberty.deepdive.rest.models;

import io.openliberty.deepdive.rest.entities.Student;
import lombok.Getter;

import javax.enterprise.context.RequestScoped;

@Getter
@RequestScoped
public class StudentDTO {
    private Integer id;
    private String name;
    private String username;
    private String grade;

    public StudentDTO(){}

    public StudentDTO(Integer id, String name, String username, String grade) {
        this.id=id;
        this.name = name;
        this.username = username;
        this.grade = grade;
    }

    public StudentDTO(Student student) {
        this.id=student.getId();
        this.name=student.getName();
        this.username=student.getUsername();
        this.grade=student.getGrade();
    }

    public Student toEntity() {
        Student student = new Student();
        student.setId(this.id);
        student.setName(this.name);
        student.setUsername(this.username);
        student.setGrade(this.grade);
        return student;
    }
}
