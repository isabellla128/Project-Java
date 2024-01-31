package io.openliberty.deepdive.rest.models;

import java.util.List;

public class DormitoryDTO {

    private String name;
    private List<String> rooms;

    public DormitoryDTO(String name, List<String> rooms) {
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
