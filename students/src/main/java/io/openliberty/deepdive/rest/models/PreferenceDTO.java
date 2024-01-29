package io.openliberty.deepdive.rest.models;

import io.openliberty.deepdive.rest.entities.Preference;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
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
