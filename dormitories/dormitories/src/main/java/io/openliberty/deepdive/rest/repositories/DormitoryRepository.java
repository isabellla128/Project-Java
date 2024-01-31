package io.openliberty.deepdive.rest.repositories;

import io.openliberty.deepdive.rest.models.DormitoryDTO;
import io.openliberty.deepdive.rest.response.DormitoriesNamesResponse;
import io.openliberty.deepdive.rest.response.DormitoryRoomsResponse;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DormitoryRepository {

    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;


    public List<DormitoryDTO> getDormitories() {
        List<DormitoryDTO> dormitoriesDTO = new ArrayList<>();
        List<String> dormitories = em.createQuery("SELECT DISTINCT e.name FROM Dormitory e").getResultList();
        for(String dormitory : dormitories) {
            List<String> rooms = em.createQuery("SELECT DISTINCT e.room FROM Dormitory e WHERE e.name = ?1")
                    .setParameter(1, dormitory)
                    .getResultList();
            dormitoriesDTO.add(new DormitoryDTO(dormitory, rooms));
        }
        return dormitoriesDTO;
    }
    public DormitoriesNamesResponse getDormitoriesNames() {
        return new DormitoriesNamesResponse(em.createQuery("SELECT distinct e.name FROM Dormitory e").getResultList());
    }

    public List<String> getDormitoryRooms(String dormitoryName) {
        return em.createQuery("SELECT distinct e.room FROM Dormitory e WHERE e.name = ?1")
                .setParameter(1, dormitoryName)
                .getResultList();
    }
}