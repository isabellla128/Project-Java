package io.openliberty.deepdive.rest.entities;

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
        query = "SELECT e FROM Preference e WHERE e.studentName =: name")
@NamedQuery(name = "Preference.findAllRoomsByDormitoryAndUsername",
        query = "SELECT e FROM Preference e WHERE e.studentName =: name AND e.dormitory =: dormitory")
//@NamedQuery(name = "Student.findStudentByEmail",
//        query = "SELECT e FROM Student e WHERE e.email = :email")
public class Preference {
    //id, camin, camera, username_student
    @SequenceGenerator(name = "SEQP",
            sequenceName = "preference_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQP")
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
    @Column(name = "student_name")
    private String studentName;
}