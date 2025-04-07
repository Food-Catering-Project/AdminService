package com.example.AdminService.Service;

import com.example.AdminService.Entity.Owner;
import com.example.AdminService.Repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class OwnerService {

    @Value("${fast2sms.api.key}")
    private String apiKey;


    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    public OwnerService(OwnerRepository ownerRepository, PasswordEncoder passwordEncoder) {
        this.ownerRepository = ownerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register a new owner
    public Map<String, Object> addOwner(Owner owner) {

        owner.setPassword(passwordEncoder.encode(owner.getPassword()));

        Owner owner1 = ownerRepository.save(owner);
        return Map.of(
                "status", HttpStatus.CREATED.value(),
                "message", " item successfully created",
                "data", owner1
        );
    }

    // Fetch all registered owners
    public Map<String, Object> getAllOwners() {
        List<Owner> owner1 = ownerRepository.findAll();
        return Map.of(
                "status", HttpStatus.OK.value(),
                "message", " owners fetched successfully",
                "data", owner1
        );
    }


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

    // Service: Send OTP logic
    public Map<String, Object> sendOtp(String number) {
        Optional<Owner> ownerOptional = ownerRepository.findByNumber(number);

        if (ownerOptional.isEmpty()) {
            return Map.of(
                    "status", HttpStatus.NOT_FOUND.value(),
                    "message", "Owner not found"
            );
        }

        String otp = generateOtp();
        System.out.println("otp"+ otp);


        // Store the OTP temporarily
        this.storeOtp(number, otp);

        // Send OTP via Fast2SMS
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("authorization", apiKey);

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("sender_id", "TXTIND");
            body.add("message", "Your OTP for change password is " + otp);
            body.add("language", "english");
//            body.add("route", "otp");
            body.add("route", "default");
            body.add("numbers", number);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
            String smsApiUrl = "https://www.fast2sms.com/dev/bulkV2";

            ResponseEntity<String> response = restTemplate.postForEntity(smsApiUrl, request, String.class);

            return Map.of(
                    "status", HttpStatus.OK.value(),
                    "message", "OTP sent successfully",
                    "otp", otp,
                    "response", response.getBody()
            );

        } catch (Exception e) {
            return Map.of(
                    "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "message", "Failed to send OTP",
                    "error", e.getMessage()
            );
        }
    }

    // In-memory OTP store: phoneNumber -> OTP
    private final Map<String, String> otpStore = new HashMap<>();

    // Store OTP
    public void storeOtp(String phoneNumber, String otp) {
        otpStore.put(phoneNumber, otp);
    }

    // Verify OTP
    public boolean verifyOtp(String phoneNumber, String enteredOtp) {
        System.out.println("enteredOtp" + enteredOtp);
        String savedOtp = otpStore.get(phoneNumber);
        System.out.println("savedOtp" + savedOtp);
        return savedOtp != null && savedOtp.equals(enteredOtp);
    }

    // Optional: clear OTP after successful verification
    public void clearOtp(String phoneNumber) {
        otpStore.remove(phoneNumber);
    }


    public Map<String, Object> resetPassword(String number, String newPassword) {
        Optional<Owner> optionalOwner = ownerRepository.findByNumber(number);

        if (optionalOwner.isEmpty()) {
            return Map.of(
                    "status", HttpStatus.NOT_FOUND.value(),
                    "message", "Owner not found with this number"
            );
        }

        Owner owner = optionalOwner.get();
        String encodedPassword = passwordEncoder.encode(newPassword);
        owner.setPassword(encodedPassword);
        ownerRepository.save(owner);

        return Map.of(
                "status", HttpStatus.OK.value(),
                "message", "Password reset successful"
        );
    }
}