package com.example.laborator9.models;

import lombok.Getter;

import java.util.List;

@Getter
public class DormitoriesNamesResponse {
    private List<String> names;

    public DormitoriesNamesResponse() {
    }

    public DormitoriesNamesResponse(List<String> names) {
        this.names = names;
    }

}
