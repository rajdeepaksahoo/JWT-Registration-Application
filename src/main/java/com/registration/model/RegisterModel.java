package com.registration.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REGISTERED_USER")
public class RegisterModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String firstName;
    private String lastName;
    private Long phone;
    private String organization;
    private Integer orgSize;
    private String department;
    private String jobRole;
    private String country;
    private String email;
    private String password;
    private String confirmPassword;
    private String appRole;
}
