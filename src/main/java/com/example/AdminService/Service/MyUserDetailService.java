package com.example.AdminService.Service;

import com.example.AdminService.Entity.Owner;
import com.example.AdminService.Repository.OwnerRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailService implements UserDetailsService {

    private final OwnerRepository ownerRepository;
    //private final UserPrinciple userPrinciple;

    public MyUserDetailService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
        //this.userPrinciple = userPrinciple;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Owner owner = ownerRepository.findByName(username);


        if (owner == null) {
            System.out.println("Jwt user not found");
            throw new UsernameNotFoundException("jwt user not found");
        }
        return new Owner(owner.getName(), owner.getPassword());
    }


}