package com.registration.controller;

import com.registration.dto.LoginDto;
import com.registration.model.RegisterModel;
import com.registration.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MyController {
    private final RegistrationService registrationService;
    @GetMapping("/")
    public ResponseEntity<String> home(){
        return new ResponseEntity<>("Home Page", HttpStatus.OK);
    }
    @GetMapping("/admin")
    public ResponseEntity<String> admin(){
        return new ResponseEntity<>("Admin Page", HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<String> user(){
        return new ResponseEntity<>("User Page", HttpStatus.OK);
    }
    @PostMapping("/token")
    public ResponseEntity<String> token(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(registrationService.getToken(loginDto), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterModel registerModel){
        return new ResponseEntity<>(registrationService.register(registerModel), HttpStatus.OK);
    }

}
