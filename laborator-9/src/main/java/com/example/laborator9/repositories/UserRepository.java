package com.example.laborator9.repositories;

import com.example.laborator9.entities.Group;
import com.example.laborator9.entities.User;
import com.example.laborator9.entities.UserGroup;
import com.example.laborator9.models.UserDTO;
import com.example.laborator9.models.UserRole;
import org.apache.commons.lang3.RandomStringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
@Transactional
public class UserRepository implements Serializable {
    @PersistenceContext(unitName = "Lab9PersistenceUnit")
    EntityManager entityManager;

    public String add(UserDTO userDTO) {
        Group group = entityManager.find(Group.class, userDTO.getRole().name().toLowerCase());
        if (group == null) {
            throw new IllegalArgumentException("Invalid user role");
        }
        User user = userDTO.toEntity();
        entityManager.persist(user);
        entityManager.flush();

        UserGroup userGroup = new UserGroup();
        userGroup.setUser(user);
        userGroup.setGroup(group);
        entityManager.persist(userGroup);
        entityManager.flush();

        return user.getUsername();
    }

    public String addStudent(String username) {
        Group group = entityManager.find(Group.class, UserRole.user.name().toLowerCase());
        if (group == null) {
            throw new IllegalArgumentException("Invalid user role");
        }

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        String password = RandomStringUtils.random( 15, characters);
        User user = new User(username, password);
        entityManager.persist(user);
        entityManager.flush();

        UserGroup userGroup = new UserGroup();
        userGroup.setUser(user);
        userGroup.setGroup(group);
        entityManager.persist(userGroup);
        entityManager.flush();

        return password;
    }

    public UserDTO getByUsernamePassword(UserDTO userDTO) {
        return ((Collection<User>) entityManager.createQuery(String.format(
                "SELECT u FROM User u WHERE u.username = '%s' AND u.password = '%s'",
                userDTO.getUsername(), userDTO.getPassword()
        )).getResultList())
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList())
                .get(0);
    }

    public String getUserRole(UserDTO userDTO) {
        return ((Collection<String>) entityManager.createQuery(String.format(
                "SELECT ug.group.id FROM UserGroup ug WHERE ug.user.username = '%s'",
                userDTO.getUsername())).getResultList())
                .stream()
                .collect(Collectors.toList())
                .get(0);
    }
}
