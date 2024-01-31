package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Schema(name = "Dormitory",
        description = "POJO that represents a single inventory entry.")
@Entity
@Table(name = "Dormitory")
@NamedQuery(name = "Dormitory.findAllDormitories", query = "SELECT e.name FROM Dormitory e")
@NamedQuery(name = "Dormitory.findAllRoomsForDormitory",
        query = "SELECT e.room FROM Dormitory e WHERE e.name = :name")
//@NamedQuery(name = "Student.findStudentByUsername",
//        query = "SELECT e FROM Student e WHERE e.username = :username")
public class Dormitory implements Serializable {
    private static final long serialVersionUID = 1L;

    @SequenceGenerator(name = "SEQD",
            sequenceName = "dormitory_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQD")
    @Id
    @Column(name = "id")
    private int id;

    @Schema(required = true)
    @Column(name = "name")
    private String name;

    @Schema(required = true)
    @Column(name = "room")
    private String room;
}
