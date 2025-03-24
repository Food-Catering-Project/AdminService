package com.example.AdminService.Service;

import com.example.AdminService.Entity.OwnerRestaurant;
import com.example.AdminService.Entity.RestaurantMenu;
import com.example.AdminService.Repository.OwnerRestaurantRepository;
import com.example.AdminService.Repository.RestaurantMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RestaurantMenuService {


    private final RestaurantMenuRepository restaurantMenuRepository;
    private final OwnerRestaurantRepository ownerRestaurantRepository;

    public RestaurantMenuService(RestaurantMenuRepository restaurantMenuRepository, OwnerRestaurantRepository ownerRestaurantRepository) {
        this.restaurantMenuRepository = restaurantMenuRepository;
        this.ownerRestaurantRepository = ownerRestaurantRepository;
    }
//
//    public Map<String, Object> addMenu(Long restaurantId, RestaurantMenu restaurantMenu) {
//        OwnerRestaurant ownerRestaurant = ownerRestaurantRepository.findById(restaurantId).orElseThrow(
//                ()->{
//                    throw  new RuntimeException("no restuarant id found");
//                }
//        );
//         restaurantMenu.setOwnerRestaurant(ownerRestaurant);
//        RestaurantMenu restaurantMenu1 = restaurantMenuRepository.save(restaurantMenu);
//        return Map.of(
//                "status", HttpStatus.CREATED.value(),
//                "message", "Menu added successfully",
//                "data",restaurantMenu1
//
//        );
//    }

    public Map<String, Object> getAllMenus() {
        List<RestaurantMenu> list = restaurantMenuRepository.findAll();
        return Map.of(
                "status", HttpStatus.CREATED.value(),
                "message", "Menu added successfully",
                "data",list

        );

    }

    public Map<String, Object> deleteMenu(Long menuId) {
        restaurantMenuRepository.deleteById(menuId);
        return Map.of(
                "status", HttpStatus.OK.value(),
                "message", "menu deleted successfully"

        );
    }


    public Map<String, Object> addMenus(List<RestaurantMenu> restaurantMenus, Long restaurantId) {
        Map<String, Object> response = new HashMap<>();
        try {
            OwnerRestaurant ownerRestaurant = ownerRestaurantRepository.findById(restaurantId)
                    .orElseThrow(() -> new RuntimeException("No restaurant found with ID: " + restaurantId));

            restaurantMenus.forEach(menu -> menu.setOwnerRestaurant(ownerRestaurant));
            List<RestaurantMenu> savedMenus = restaurantMenuRepository.saveAll(restaurantMenus);

            response.put("message", "Menus added successfully");
            response.put("menus", savedMenus);
            response.put("status", HttpStatus.CREATED.value());
        } catch (Exception e) {
            response.put("message", "Failed to add menus");
            response.put("error", e.getMessage());
            response.put("status", HttpStatus.BAD_REQUEST.value());
        }
        return response;
    }



    public Map<String, Object> getMenuById(Long menuId) {
        Map<String, Object> response = new HashMap<>();
        try {
            RestaurantMenu menu = restaurantMenuRepository.findById(menuId)
                    .orElseThrow(() -> new RuntimeException("Menu not found with ID: " + menuId));

            response.put("message", "Menu fetched successfully");
            response.put("menu", menu);
            response.put("status", HttpStatus.OK.value());
        } catch (Exception e) {
            response.put("message", "Failed to fetch menu");
            response.put("error", e.getMessage());
            response.put("status", HttpStatus.NOT_FOUND.value());
        }
        return response;
    }

}
