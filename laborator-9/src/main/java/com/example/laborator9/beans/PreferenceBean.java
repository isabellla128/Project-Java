package com.example.laborator9.beans;

import com.example.laborator9.models.PreferenceDTO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Setter
@Getter
@Named("PreferenceBean")
@RequestScoped
public class PreferenceBean {
    private PreferenceDTO preference;

    //IAU DIN CONTROLLER
    private List<String> dormitories = Arrays.asList("C1", "C2", "C3");
    private List<String> rooms = Arrays.asList("101", "102", "103");
    @PostConstruct
    public void init() {
//        projects = service.getProjects();
//        availableProjects = service.getAvailableProjects();
//        categories = Arrays.asList(CategoryEnum.values());
    }
}
