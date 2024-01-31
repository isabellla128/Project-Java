package com.example.demo.repositories;

import com.example.demo.entities.Dormitory;
import com.example.demo.models.DormitoryDTO;
import com.example.demo.response.DormitoriesNamesResponse;
import com.example.demo.response.DormitoryRoomsResponse;

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

    @Resource
    UserTransaction utx;

    public List<DormitoryDTO> getDormitories() {
        List<DormitoryDTO> dormitoriesDTO = new ArrayList<>();
        List<String> dormitories = em.createQuery("SELECT e.name FROM Dormitory e").getResultList();
        for(String dormitory : dormitories) {
            List<String> rooms = em.createQuery("SELECT e.room FROM Dormitory e WHERE e.name = ?1")
                    .setParameter(1, dormitory)
                    .getResultList();
            dormitoriesDTO.add(new DormitoryDTO(dormitory, rooms));
        }
        return dormitoriesDTO;
    }
    public DormitoriesNamesResponse getDormitoriesNames() {
        return new DormitoriesNamesResponse(em.createQuery("SELECT e.name FROM Dormitory e").getResultList());
    }

    public DormitoryRoomsResponse getDormitoryRooms(String dormitoryName) {
        String dormitory = (String) em.createQuery("SELECT e.name FROM Dormitory e WHERE e.name = ?1")
                .setParameter(1, dormitoryName)
                .getResultList().get(0);
        return new DormitoryRoomsResponse (em.createQuery("SELECT e.room FROM Dormitory e WHERE e.name = ?1")
                .setParameter(1, dormitory)
                .getResultList());
    }
}