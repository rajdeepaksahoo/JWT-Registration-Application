package com.registration.service.impl;

import com.registration.dto.LoginDto;
import com.registration.exception.JwtAuthenticationException;
import com.registration.jwt.JwtAuthenticationEntryPoint;
import com.registration.jwt.JwtHelper;
import com.registration.model.RegisterModel;
import com.registration.repository.RegistrationPageRepository;
import com.registration.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final JwtHelper jwtHelper;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationPageRepository repository;
    public String getToken(LoginDto loginDto) throws JwtAuthenticationException {
        if(repository.findByEmail(loginDto.getUsername()).isPresent()){
            UserDetails userDetails = User.builder()
                    .username(loginDto.getUsername())
                    .password(loginDto.getPassword())
                    .build();
            return jwtHelper.generateToken(userDetails);
        }
        else {
            throw new JwtAuthenticationException();
        }
    }

    @Override
    public String register(RegisterModel registerModel) {
        try {
            registerModel.setPassword(passwordEncoder.encode(registerModel.getPassword()));
            repository.save(registerModel);
        }catch (Exception e){
            e.printStackTrace();
            return "Not Registered";
        }

        return registerModel.getFirstName()+" "+registerModel.getLastName()+" Registered Successfully";
    }
}