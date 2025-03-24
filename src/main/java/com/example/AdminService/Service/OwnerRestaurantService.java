package com.example.AdminService.Service;

import com.example.AdminService.Entity.Owner;
import com.example.AdminService.Entity.OwnerRestaurant;
import com.example.AdminService.Repository.OwnerRepository;
import com.example.AdminService.Repository.OwnerRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OwnerRestaurantService {


   private final OwnerRestaurantRepository ownerRestaurantRepository;
    private final  OwnerRepository ownerRepository;

    public OwnerRestaurantService(OwnerRestaurantRepository ownerRestaurantRepository, OwnerRepository ownerRepository) {
        this.ownerRestaurantRepository = ownerRestaurantRepository;
        this.ownerRepository = ownerRepository;
    }

    // Register a restaurant under an owner
    public Map<String,Object> addRestaurant( Long ownerId,OwnerRestaurant ownerRestaurant) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        ownerRestaurant.setOwner(owner);
        OwnerRestaurant ownerRestaurant1 = ownerRestaurantRepository.save(ownerRestaurant);
        return Map.of(
                "status", HttpStatus.CREATED.value(),
                "message", " Restaurant added succesfully",
                "data", ownerRestaurant1
        );
    }

    public Map<String, Object> getAllRestaurants() {
        List<OwnerRestaurant>list = ownerRestaurantRepository.findAll();
        return Map.of(
                "status", HttpStatus.OK.value(),
                "message", " All  Restaurant fetched succesfully",
                "data", list
        );

    }

    public Map<String, Object> updateRestaurant(Long RestaurantId, OwnerRestaurant ownerRestaurant) {
        OwnerRestaurant existing = ownerRestaurantRepository.findById(RestaurantId).orElseThrow(
                () -> { throw  new RuntimeException("this restaurant does not exist"); }
        );
        existing.setAddress(ownerRestaurant.getAddress());
        existing.setNumber(ownerRestaurant.getNumber());
        existing.setRating(ownerRestaurant.getRating());

        OwnerRestaurant updated = ownerRestaurantRepository.save(existing);
        return Map.of(
                "status", HttpStatus.OK.value(),
                "message", "   Restaurant updated succesfully",
                "data", updated
        );

    }

    public Map<String, Object> deleteRestaurant(Long RestaurantId) {
        ownerRestaurantRepository.deleteById(RestaurantId);
        return Map.of(
                "status", HttpStatus.OK.value(),
                "message", "Owner deleted successfully"

        );
    }


    // Fetch a specific restaurant by ID
//    public Restaurant getRestaurantById(Long id) {
//        return restaurantRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
//    }

}
