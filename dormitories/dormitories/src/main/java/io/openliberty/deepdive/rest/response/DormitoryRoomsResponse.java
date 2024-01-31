package io.openliberty.deepdive.rest.response;

import java.util.List;

public class DormitoryRoomsResponse {
    private List<String> rooms;

    public DormitoryRoomsResponse(List<String> rooms) {
        this.rooms = rooms;
    }

    public List<String> getRooms() {
        return this.rooms;
    }
}
