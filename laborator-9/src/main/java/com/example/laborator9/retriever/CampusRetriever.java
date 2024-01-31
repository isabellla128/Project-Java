package com.example.laborator9.retriever;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class CampusRetriever {
    private final String LIBERTY_URL = "http://localhost:9081/dormitories/api/service";

    public List<String> getDormitoriesNames() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(LIBERTY_URL).path("dormitories/names");
        Invocation.Builder builder = target.request();
        Response response = builder.get();
        List<String> content = response.readEntity(new GenericType<List<String>>(){});
        System.out.println("Response Content: " + content);
        return content;
    }

    public List<String> getRooms(String name) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(LIBERTY_URL).path("dormitory").path(name).path("rooms");
        Invocation.Builder builder = target.request();
        Response response = builder.get();
        List<String> content = response.readEntity(new GenericType<List<String>>(){});
        System.out.println("Response Content: " + content);
        return content;
    }
}
