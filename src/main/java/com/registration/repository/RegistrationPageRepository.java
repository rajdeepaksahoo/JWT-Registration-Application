package com.registration.repository;

import com.registration.model.RegisterModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationPageRepository extends JpaRepository<RegisterModel,Long> {
    public Optional<RegisterModel> findByEmail(String email);
}
