package com.example.AdminService.Service;

import com.example.AdminService.Entity.Owner;
import com.example.AdminService.Repository.OwnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

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

    public Map<String, Object> getOwnerByName(String name) {
        Owner owner = ownerRepository.findByName(name);
        return Map.of(
                "status", HttpStatus.OK.value(),
                "message", "Owner fetched successfully",
                "data", owner

        );
    }

    // Generate OTP (6-digit number)
    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generates a 6-digit OTP
        return String.valueOf(otp);
    }

//    // Step 1: Send OTP
//    public Map<String, Object> sendOtp(String number) {
//        Optional<Owner> ownerOptional = ownerRepository.findByNumber(number);
//
//        if (ownerOptional.isEmpty()) {
//            return Map.of("status", HttpStatus.NOT_FOUND.value(), "message", "Owner not found");
//        }
//
//        String otp = generateOtp();
//
//        return Map.of("status", HttpStatus.OK.value(), "message", "OTP sent successfully", "otp", otp);
//    }
//
//    // Step 2: Verify OTP
//    public Map<String, Object> verifyOtp(String number, String otp) {
//        Optional<Owner> ownerOptional = ownerRepository.findByNumber(number);
//
//        if (ownerOptional.isEmpty()) {
//            return Map.of("status", HttpStatus.NOT_FOUND.value(), "message", "Owner not found");
//        }
//
//        Owner owner = ownerOptional.get();
//
//        if (!owner.isOtpValid() || !owner.getOtp().equals(otp)) {
//            return Map.of("status", HttpStatus.BAD_REQUEST.value(), "message", "Invalid or expired OTP");
//        }
//
//        return Map.of("status", HttpStatus.OK.value(), "message", "OTP verified successfully");
//    }
//
//    // Step 3: Reset Password
//    public Map<String, Object> resetPassword(String number, String newPassword) {
//        Optional<Owner> ownerOptional = ownerRepository.findByNumber(number);
//
//        if (ownerOptional.isEmpty()) {
//            return Map.of("status", HttpStatus.NOT_FOUND.value(), "message", "Owner not found");
//        }
//
//        Owner owner = ownerOptional.get();
//
//        owner.setPassword(passwordEncoder.encode(newPassword));
//        ownerRepository.save(owner);
//
//        return Map.of("status", HttpStatus.OK.value(), "message", "Password reset successfully");
//    }
}

