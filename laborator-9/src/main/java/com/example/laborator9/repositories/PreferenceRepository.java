package com.example.laborator9.repositories;

import com.example.laborator9.entities.Preference;
import com.example.laborator9.models.PreferenceDTO;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PreferenceRepository {

    @PersistenceContext(name = "Lab9PersistenceUnit")
    private EntityManager em;

    @Resource
    UserTransaction utx;


    public List<PreferenceDTO> getPreferences() {
        List<PreferenceDTO> preferencesDTO = new ArrayList<>();
        List<Preference> preferences =  em.createNamedQuery("Preference.findAll", Preference.class).getResultList();
        for(Preference userPreference : preferences) {
            String username = userPreference.getUsername();
            createPreferenceDTO(preferencesDTO, preferences, username);
        }
        return preferencesDTO;
    }

    private void createPreferenceDTO(List<PreferenceDTO> preferencesDTO, List<Preference> preferences, String username) {
        List<String> myRooms;
        for (Preference preference : preferences) {
            String dormitory = preference.getDormitory();
            List<Preference> myPreferences = em.createNamedQuery("Preference.findAllRoomsByDormitoryAndUsername", Preference.class)
                    .setParameter("name", username)
                    .setParameter("dormitory", dormitory)
                    .getResultList();
            myRooms = new ArrayList<>();
            for (Preference newPreference : myPreferences) {
                myRooms.add(newPreference.getRoom());
            }
            preferencesDTO.add(new PreferenceDTO(dormitory, username, myRooms));
        }
    }

    public List<PreferenceDTO> getPreferencesByUsername(String username) {
        List<PreferenceDTO> preferencesDTO = new ArrayList<>();
        List<Preference> preferences =  em.createNamedQuery("Preference.findAllByUsername", Preference.class)
                .setParameter("name", username)
                .getResultList();
        createPreferenceDTO(preferencesDTO, preferences, username);
        return preferencesDTO;
    }

    public void addPreference(PreferenceDTO preferenceDTO) throws Exception {
        if(preferenceDTO.getUsername() != null) {
            Preference newPreference = new Preference();
            newPreference.setDormitory(preferenceDTO.getDormitory());
            newPreference.setUsername(preferenceDTO.getUsername());
            if(!preferenceDTO.getMyRooms().isEmpty()) {
                utx.begin();
                for (int i = 0; i < preferenceDTO.getMyRooms().size(); i++) {
                    em.persist(newPreference);
                }
                utx.commit();
            }
        }
    }
}