package com.example.demo.services;
import org.json.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DormitoryService {

    public String getAll() {

        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("C:\\Users\\isabe\\Desktop\\demo\\service-b\\src\\main\\java\\com\\example\\demo\\utils\\dormitories.json")));
            JSONObject obj = new JSONObject(jsonString);
            JSONArray dormitories = obj.getJSONArray("dormitories");

            StringBuilder response = new StringBuilder();
            for (int i = 0; i < dormitories.length(); i++) {
                response.append(dormitories.getJSONObject(i).getString("name"));
            }
            return response.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
