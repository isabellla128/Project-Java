package com.example.laborator9.views;

import com.example.laborator9.retriever.CampusRetriever;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Getter
//@ViewScoped
//public class DormitoryView {
//    private List<String> dormitories;
//    private List<String> rooms ;
//
//    @Inject
//    private CampusRetriever campusRetriever;
//
//    @PostConstruct
//    public void init() {
//        dormitories = campusRetriever.getDormitoriesNames();
//        rooms = new ArrayList<>();
//    }
//}
