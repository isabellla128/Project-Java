package com.example.laborator9.services;

import com.example.laborator9.models.UserDTO;
import com.example.laborator9.repositories.UserRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "UserService")
@SessionScoped
public class UserService implements Serializable {
    @Inject
    private UserRepository userRepository;

    public String getUserRole(UserDTO userDTO) {
        return userRepository.getUserRole(userDTO);
    }
}
