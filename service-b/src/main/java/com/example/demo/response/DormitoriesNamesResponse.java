package com.example.demo.response;

import java.util.List;

public class DormitoriesNamesResponse {
    private List<String> names;

    public DormitoriesNamesResponse(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return this.names;
    }
}
