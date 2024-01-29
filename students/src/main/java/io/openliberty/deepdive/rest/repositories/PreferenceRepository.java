package io.openliberty.deepdive.rest.repositories;

import io.openliberty.deepdive.rest.entities.Preference;
import io.openliberty.deepdive.rest.entities.Student;
import io.openliberty.deepdive.rest.models.PreferenceDTO;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.util.ArrayList;
import java.util.List;

public class PreferenceRepository {

    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    @Resource
    UserTransaction utx;

    @Inject
    private StudentRepository studentRepository;

    public List<Preference> getPreferences() {
        return  em.createNamedQuery("Preference.findAllByUsername", Preference.class)
                .getResultList();
//        List<String> myRooms = new ArrayList<>();
//        for (Preference preference : preferences) {
//            myRooms.add(preference.getDormitory());
//
//            preference.getDormitory()
//            List<Preference> MyPreferences = em.createNamedQuery("Preference.findAllRoomsByDormitoryAndUsername", Preference.class)
//                    .setParameter("name", "da")
//                    .setParameter("dormitory", preference.getDormitory())
//                    .getResultList();
//        }
//        if(!myRooms.isEmpty())
//            return new PreferenceDTO(preferences.get(0).getDormitory(), preferences.get(0).getStudent().getName(), myRooms);
//        return null;
    }

    public List<Preference> getPreferencesByUsername(String username) {
//        List<Preference> preferences =  em.createNamedQuery("Preference.findAllByUsername", Preference.class)
//                .setParameter("name", username)
//                .getResultList();
//        List<String> myRooms = new ArrayList<>();
//        for (Preference preference : preferences) {
//            List<Preference> myPreferences = em.createNamedQuery("Preference.findAllRoomsByDormitoryAndUsername", Preference.class)
//                    .setParameter("name", username)
//                    .setParameter("dormitory", preference.getDormitory())
//                    .getResultList();
//
//        }
        return em.createNamedQuery("Preference.findAllByUsername", Preference.class)
                .getResultList();
    }

    public void addPreference(PreferenceDTO preferenceDTO) throws Exception {
        Student studentToAdd = studentRepository.getStudent(preferenceDTO.getUsername());
        if(studentToAdd != null) {
            Preference newPreference = new Preference();
            newPreference.setDormitory(preferenceDTO.getDormitory());
            newPreference.setStudent(studentToAdd);
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