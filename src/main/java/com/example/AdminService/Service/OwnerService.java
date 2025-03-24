package com.example.AdminService.Service;

import com.example.AdminService.Entity.Owner;
import com.example.AdminService.Repository.OwnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OwnerService {


    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    public OwnerService(OwnerRepository ownerRepository, PasswordEncoder passwordEncoder) {
        this.ownerRepository = ownerRepository;
        this.passwordEncoder = passwordEncoder;
    }

   // Register a new owner
     public Map<String,Object> addOwner(Owner owner) {

         owner.setPassword(passwordEncoder.encode(owner.getPassword()));

         Owner owner1=  ownerRepository.save(owner);
         return Map.of(
                 "status", HttpStatus.CREATED.value(),
                 "message", " item successfully created",
                 "data", owner1
         );
     }

   // Fetch all registered owners
    public Map<String,Object> getAllOwners() {
        List<Owner> owner1 =  ownerRepository.findAll();
        return Map.of(
                "status", HttpStatus.OK.value(),
                "message", " owners fetched successfully",
                "data", owner1
        );
    }



   // Fetch a specific owner by ID
//    public Owner getOwnerById(Long id) {
//        return ownerRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Owner not found"));
//    }

    public Map<String, Object> updateOwner(Long ownerId, Owner owner) {
        Owner existingOwner = ownerRepository.findById(ownerId).orElseThrow(() -> {
            throw new RuntimeException("owner not exist");
        });

        existingOwner.setAddress(owner.getAddress());
        existingOwner.setNumber(owner.getNumber());

        Owner finalSave = ownerRepository.save(existingOwner);

        return Map.of(
                "status", HttpStatus.OK.value(),
                "message", " ownersDetails updated successfully",
                "data", finalSave
        );
    }



    // Delete owner
    public Map<String, Object> deleteOwner(Long ownerId) {
         ownerRepository.deleteById(ownerId);
        return Map.of(
                "status", HttpStatus.OK.value(),
                "message", "Owner deleted successfully"

        );

    }
}

