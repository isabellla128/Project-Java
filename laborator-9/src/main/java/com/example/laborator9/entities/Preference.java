package com.example.laborator9.entities;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Setter
@Getter
@Schema(name = "Preference",
        description = "POJO that represents a single inventory entry.")
@Entity
@Table(name = "Preference")
@NamedQuery(name = "Preference.findAll", query = "SELECT e FROM Preference e")
@NamedQuery(name = "Preference.findAllByUsername",
        query = "SELECT e FROM Preference e WHERE e.username = :name")
@NamedQuery(name = "Preference.findAllRoomsByDormitoryAndUsername",
        query = "SELECT e FROM Preference e WHERE e.username = :name AND e.dormitory = :dormitory")
//@NamedQuery(name = "Student.findStudentByEmail",
//        query = "SELECT e FROM Student e WHERE e.email = :email")
public class Preference {
    //id, camin, camera, username_student
    @SequenceGenerator(name = "SEQ",
            sequenceName = "preference_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQ")
    @Id
    @Column(name = "id")
    private int id;

    @Schema(required = true)
    @Column(name = "dormitory")
    private String dormitory;

    @Schema(required = true)
    @Column(name = "room")
    private String room;

    @Schema(required = true)
    @Column(name = "username")
    private String username;


}