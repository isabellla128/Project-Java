package com.example.laborator9.models;

import java.util.List;

public class Dormitory {

    private String name;
    private List<String> rooms;

    public Dormitory(String name, List<String> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public List<String> getRooms() {
        return rooms;
    }

}
