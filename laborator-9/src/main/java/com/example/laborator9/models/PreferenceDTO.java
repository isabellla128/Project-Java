package com.example.laborator9.models;

import lombok.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Named(value = "PreferenceBean")
@RequestScoped
public class PreferenceDTO {
    private String dormitory;
    private List<String> myRooms;
    private String username;

    public PreferenceDTO(String dormitory, String username, List<String> myRooms) {
        this.dormitory = dormitory;
        this.username = username;
        this.myRooms = myRooms;
    }
}