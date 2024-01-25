package com.registration.custom_userdetails;

import com.registration.model.RegisterModel;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final RegisterModel registerModel;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = registerModel.getAppRole().contains("ROLE_")?registerModel.getAppRole():"ROLE_"+registerModel.getAppRole();
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return registerModel.getPassword();
    }

    @Override
    public String getUsername() {
        return registerModel.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
