package com.example.AdminService.Repository;

import com.example.AdminService.Entity.OwnerRestaurant;
import com.example.AdminService.Entity.RestaurantMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantMenuRepository extends JpaRepository<RestaurantMenu,Long> {

    List<RestaurantMenu> findByOwnerRestaurant(OwnerRestaurant ownerRestaurant);
}
