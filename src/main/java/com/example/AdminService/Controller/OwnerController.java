package com.example.AdminService.Controller;


import com.example.AdminService.Entity.Owner;
import com.example.AdminService.Service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/admin/api/owner")
public class OwnerController {

   private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
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

}

