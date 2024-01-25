package com.registration.user_details_service;

import com.registration.custom_userdetails.CustomUserDetails;
import com.registration.repository.RegistrationPageRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {
    private RegistrationPageRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).map(CustomUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("User Not Found.."));
    }
}
