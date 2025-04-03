//package com.example.AdminService.config;
//
//
//import com.example.AdminService.Entity.Owner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//import java.util.List;
//
//@Component
//@Configuration
//public class UserPrinciple implements UserDetails {
//
//    private  Owner owner;
//
//    public UserPrinciple(Owner owner) {
//        this.owner = owner;
//    }
//
//
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of();
//    }
//
//    @Override
//    public String getPassword() {
//        return owner.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return owner.getName();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
//
