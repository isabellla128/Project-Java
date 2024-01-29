package com.example.laborator9.services;

import com.example.laborator9.beans.PreferenceBean;
import com.example.laborator9.models.UserDTO;
import lombok.Getter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("PreferenceService")
@ApplicationScoped
@Getter
public class PreferenceService {

    public void addPreference(PreferenceBean preference)
    {
        UserDTO loggedInUser = (UserDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
        preference.setUsername(loggedInUser.getUsername());
        System.out.println(preference.getDormitory() + " " + preference.getMyRooms() + " " + preference.getUsername());
    }
}
