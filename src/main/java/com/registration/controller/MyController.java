package com.registration.controller;

import com.registration.dto.LoginDto;
import com.registration.dto.TokenDto;
import com.registration.exception.JwtAuthenticationException;
import com.registration.model.RegisterModel;
import com.registration.repository.RegistrationPageRepository;
import com.registration.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class MyController {
    private final RegistrationService registrationService;
    private final RegistrationPageRepository repository;
    @GetMapping("/")
    public ResponseEntity<String> home(){
        return new ResponseEntity<>("Home Page", HttpStatus.OK);
    }
    @GetMapping("/admin")
    public ResponseEntity<String> admin(){
        return new ResponseEntity<>("Admin Page", HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<RegisterModel> user(Principal principal){
        Optional<RegisterModel> map = repository.findByEmail(principal.getName());
        return new ResponseEntity<>(map.get(), HttpStatus.OK);
    }
    @PostMapping("/token")
    public ResponseEntity<TokenDto> token(@RequestBody LoginDto loginDto) throws JwtAuthenticationException {
        TokenDto tokenDto= TokenDto.builder()
                .token(registrationService.getToken(loginDto))
                .status("SUCCESS")
                .build();
        return new ResponseEntity<>(tokenDto, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterModel registerModel){
        return new ResponseEntity<>(registrationService.register(registerModel), HttpStatus.OK);
    }

}
