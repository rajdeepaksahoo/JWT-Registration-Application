package com.registration.service;

import com.registration.dto.LoginDto;
import com.registration.exception.JwtAuthenticationException;
import com.registration.model.RegisterModel;

public interface RegistrationService {
    public String getToken(LoginDto loginDto) throws JwtAuthenticationException;
    public String register(RegisterModel registerModel);
}
