package com.example.laborator9.repositories;

import com.example.laborator9.entities.Preference;
import com.example.laborator9.models.PreferenceDTO;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
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

    public List<PreferenceDTO> getPreferencesByUsername(String username) {
        List<PreferenceDTO> preferencesDTO = new ArrayList<>();
        List<String> dormitories =  em.createQuery("SELECT DISTINCT e.dormitory FROM Preference e WHERE e.username=?1", String.class)
                .setParameter(1, username)
                .getResultList();
        for (String dormitory : dormitories) {
            List<String> myRooms =  em.createQuery("SELECT DISTINCT e.room FROM Preference e WHERE e.username=?1 and e.dormitory = ?2", String.class)
                    .setParameter(1, username)
                    .setParameter(2, dormitory)
                    .getResultList();
            preferencesDTO.add(new PreferenceDTO(dormitory, username, myRooms));
        }
        return preferencesDTO;
    }

    public void addPreference(PreferenceDTO preferenceDTO) throws Exception {
        if(preferenceDTO.getUsername() != null) {
            Preference newPreference = new Preference();
            newPreference.setDormitory(preferenceDTO.getDormitory());
            newPreference.setUsername(preferenceDTO.getUsername());
            if(!preferenceDTO.getMyRooms().isEmpty()) {
//                utx.begin();
                for (int i = 0; i < preferenceDTO.getMyRooms().size(); i++) {
                    newPreference.setRoom(preferenceDTO.getMyRooms().get(i));
                    utx.begin();
                    em.persist(newPreference);
                    utx.commit();
                }
            }
        }
    }
}
