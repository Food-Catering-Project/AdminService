package com.example.AdminService.Repository;

import com.example.AdminService.Entity.RestaurantMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantMenuRepository extends JpaRepository<RestaurantMenu,Long> {
}
