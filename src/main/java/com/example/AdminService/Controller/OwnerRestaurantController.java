package com.example.AdminService.Controller;


import com.example.AdminService.Entity.OwnerRestaurant;
import com.example.AdminService.Service.OwnerRestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/api/OwnerRestaurant")
public class OwnerRestaurantController {


    private final OwnerRestaurantService ownerRestaurantService;

    public OwnerRestaurantController(OwnerRestaurantService ownerRestaurantService) {
        this.ownerRestaurantService = ownerRestaurantService;
    }

    @PostMapping("/addRestuarants/{ownerId}")
    public ResponseEntity<Map<String, Object>> addRestaurant(@PathVariable Long ownerId,@RequestBody OwnerRestaurant ownerRestaurant) {
        try{
            Map<String,Object> res  = ownerRestaurantService.addRestaurant(ownerId,ownerRestaurant);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Restuarant to added", "error", e.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/getAllRestuarants")
    public ResponseEntity<Map<String, Object>> getAllRestaurants() {
        try{
            Map<String,Object> res  = ownerRestaurantService.getAllRestaurants();
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Restuarant fetched suucessfully", "error", e.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping("/updateRestaurant{RestaurantId}")
    public ResponseEntity<Map<String, Object>> updateRestaurant(@PathVariable Long RestaurantId, @RequestBody OwnerRestaurant ownerRestaurant) {
        try{
            Map<String,Object> res  = ownerRestaurantService.updateRestaurant(RestaurantId,ownerRestaurant);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Restuarant updated suucessfully", "error", e.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/deleteRestaurant/{RestaurantId}")
    public ResponseEntity<Map<String, Object>> deleteRestaurant(@PathVariable Long RestaurantId){
        try{
            Map<String,Object> res  = ownerRestaurantService.deleteRestaurant(RestaurantId);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Restuarant deleted suucessfully", "error", e.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }

}
