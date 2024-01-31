package com.example.laborator9.services;

import com.example.laborator9.models.StudentDTO;
import com.example.laborator9.models.UserDTO;
import com.example.laborator9.repositories.UserRepository;
import com.example.laborator9.utils.SendEmail;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "UserService")
@SessionScoped
public class UserService implements Serializable {
    @Inject
    private UserRepository userRepository;
    @Inject
    private SendEmail sendEmail;

    private final String FROM ="danapetrea.dp@gmail.com";

    public String getUserRole(UserDTO userDTO) {
        return userRepository.getUserRole(userDTO);
    }
    public void addStudent(String username) {
        String password = userRepository.addStudent(username);
        sendEmail.sendEmail(username,FROM,password);
    }
}
