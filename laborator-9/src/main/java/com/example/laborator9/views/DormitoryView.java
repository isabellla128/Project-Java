package com.example.laborator9.views;

import com.example.laborator9.retriever.CampusRetriever;
import lombok.Getter;

import javax.annotation.PostConstruct;
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

    @Inject
    private CampusRetriever campusRetriever;

    @PostConstruct
    public void init() {
        dormitories = campusRetriever.getDormitoriesNames();
        rooms = campusRetriever.getRooms(dormitories.get(0));
    }

    public void onDormitoryChange(String dormitory) {
        // You can implement the logic to retrieve rooms based on the selected dormitory here
        // For example, if you have a method in campusRetriever to get rooms by dormitory:
        rooms = campusRetriever.getRooms(dormitory);
    }
}
