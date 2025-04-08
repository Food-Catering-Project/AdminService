package com.example.AdminService.Repository;

import com.example.AdminService.Entity.Owner;
import com.example.AdminService.Entity.OwnerRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRestaurantRepository extends JpaRepository<OwnerRestaurant,Long> {
    OwnerRestaurant findByOwner(Owner owner);

    List<OwnerRestaurant> findAllByOrderByRatingDesc();
}
