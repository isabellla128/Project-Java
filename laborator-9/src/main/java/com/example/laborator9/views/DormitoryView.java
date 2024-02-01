package com.example.laborator9.views;

import com.example.laborator9.models.PreferenceDTO;
import com.example.laborator9.models.UserDTO;
import com.example.laborator9.repositories.PreferenceRepository;
import com.example.laborator9.retriever.CampusRetriever;
import com.example.laborator9.services.PreferenceService;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Named("DormitoryView")
@ViewScoped
public class DormitoryView implements Serializable {
    private List<String> dormitories;
    private List<String> rooms;
    private List<PreferenceDTO> myPreferences;

    @Inject
    private CampusRetriever campusRetriever;

    @Inject
    private PreferenceService preferenceService;

    @PostConstruct
    public void init() {
        dormitories = campusRetriever.getDormitoriesNames();
        rooms = campusRetriever.getRooms(dormitories.get(0));
        myPreferences = preferenceService.getDormitoryRooms();
    }

    public void onDormitoryChange(String dormitory) {
        rooms = campusRetriever.getRooms(dormitory);
    }
}
