package com.example.laborator9.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username", unique = true)
    @NotNull(message = "Username cannot be null")
    private String username;

    @Column(name = "password")
    @NotNull(message = "Password cannot be null")
    private String password;
}
