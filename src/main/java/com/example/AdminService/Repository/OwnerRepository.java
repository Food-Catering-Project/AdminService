package com.example.AdminService.Repository;

import com.example.AdminService.Entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long> {
    Owner findByName(String name);
//    Optional<Owner> findByNumber(String number); // Find owner by phone number



}
