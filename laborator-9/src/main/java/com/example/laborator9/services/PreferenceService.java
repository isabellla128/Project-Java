package com.example.laborator9.services;

import com.example.laborator9.models.PreferenceDTO;
import com.example.laborator9.models.UserDTO;
import com.example.laborator9.repositories.PreferenceRepository;
import lombok.Getter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("PreferenceService")
@ApplicationScoped
@Getter
public class PreferenceService {

    @Inject
    PreferenceRepository preferenceRepository;

    public void addPreference(PreferenceDTO preference)
    {
        UserDTO loggedInUser = (UserDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
        preference.setUsername(loggedInUser.getUsername());
        System.out.println(preference.getDormitory() + " " + preference.getMyRooms() + " " + preference.getUsername());
        //preferenceRepository.addPreference(preference);

    }


}
