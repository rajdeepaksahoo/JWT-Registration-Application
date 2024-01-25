package com.registration.service;

import com.registration.dto.LoginDto;
import com.registration.model.RegisterModel;

public interface RegistrationService {
    public String getToken(LoginDto loginDto);
    public String register(RegisterModel registerModel);
}
