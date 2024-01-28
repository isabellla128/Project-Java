package com.example.laborator9.models;

import com.example.laborator9.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Named(value = "userBean")
@RequestScoped
public class UserDTO {
    private String username;
    private String password;
    private UserRole role;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public User toEntity() {
        User user = new User();
        user.setUsername(this.username);
        user.setPassword(this.password);
        return user;
    }
    public void reset() {
        this.username = null;
        this.password = null;
        this.role = null;
    }
}
