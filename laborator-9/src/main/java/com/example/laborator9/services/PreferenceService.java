package com.example.laborator9.services;

import com.example.laborator9.models.PreferenceDTO;
import com.example.laborator9.models.UserDTO;
import com.example.laborator9.repositories.PreferenceRepository;
import lombok.Getter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named("PreferenceService")
@ApplicationScoped
@Getter
public class PreferenceService {

    @Inject
    PreferenceRepository preferenceRepository;

    public void addPreference(PreferenceDTO preference) throws Exception {
        UserDTO loggedInUser = (UserDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
        preference.setUsername(loggedInUser.getUsername());
        preferenceRepository.addPreference(preference);
    }

    public List<PreferenceDTO> getDormitoryRooms() {
        UserDTO loggedInUser = (UserDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
        return preferenceRepository.getPreferencesByUsername(loggedInUser.getUsername());
    }
}
