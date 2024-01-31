package com.example.laborator9.models;

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
}
