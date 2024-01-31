package com.example.laborator9.models;

import lombok.Getter;

import java.util.List;

@Getter
public class DormitoryRoomsResponse {
    private List<String> rooms;

    public DormitoryRoomsResponse() {
    }

    public DormitoryRoomsResponse(List<String> rooms) {
        this.rooms = rooms;
    }

}
