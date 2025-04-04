package com.example.AdminService.Controller;


import com.example.AdminService.Entity.Owner;
import com.example.AdminService.Service.AuthService;
import com.example.AdminService.Service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/admin/api/owner")
public class OwnerController {

   private final OwnerService ownerService;
   private final AuthService authService;

    public OwnerController(OwnerService ownerService, AuthService authService) {
        this.ownerService = ownerService;
        this.authService = authService;
    }

    @PostMapping("/addowner")
    public ResponseEntity<Map<String, Object>> addOwner(@RequestBody Owner owner) {
        try{
            Map<String,Object> res  = ownerService.addOwner(owner);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Unable to add", "error", e.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }


    @PostMapping("/login")
    public String login(@RequestParam String name, @RequestParam String password) {
        String token = authService.verify(name, password);
        return token;

    }

    @GetMapping("/getOwnerByName/{name}")
    public ResponseEntity<Map<String,Object>> getOwnerByName(@PathVariable String name) {
        try{
            Map<String,Object> res  = ownerService.getOwnerByName(name);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Unable to fetch owner details", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getAllOwners")
    public ResponseEntity<Map<String,Object>> getAllOwners() {
        try{
            Map<String,Object> res  = ownerService.getAllOwners();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Unable to fetch", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/updateOwner/{id}")
    public ResponseEntity<Map<String,Object>> updateOwner(@PathVariable Long ownerId, @RequestBody Owner owner) {
        try{
            Map<String,Object> res  = ownerService.updateOwner(ownerId,owner);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Unable to update", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteOwner/{id}")
    public ResponseEntity<Map<String, Object>> deleteOwner(@PathVariable Long ownerId) {
        try {
            Map<String, Object> response = ownerService.deleteOwner(ownerId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Unable to delete", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

//    // Step 1: Send OTP
//    @PostMapping("/forgot-password/send-otp")
//    public ResponseEntity<Map<String, Object>> sendOtp(@RequestParam String number) {
//        try {
//            Map<String, Object> response = ownerService.sendOtp(number);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(Map.of("message", "Error sending OTP", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    // Step 2: Verify OTP
//    @PostMapping("/forgot-password/verify-otp")
//    public ResponseEntity<Map<String, Object>> verifyOtp(@RequestParam String number, @RequestParam String otp) {
//        try {
//            Map<String, Object> response = ownerService.verifyOtp(number, otp);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(Map.of("message", "Error verifying OTP", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    // Step 3: Reset Password
//    @PostMapping("/forgot-password/reset")
//    public ResponseEntity<Map<String, Object>> resetPassword(@RequestParam String number, @RequestParam String newPassword) {
//        try {
//            Map<String, Object> response = ownerService.resetPassword(number, newPassword);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(Map.of("message", "Error resetting password", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//    }

}

