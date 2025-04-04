package com.example.AdminService.Controller;


import com.example.AdminService.Entity.RestaurantMenu;
import com.example.AdminService.Service.RestaurantMenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/RestaurantMenu")
public class RestaurantMenuController {


    private final RestaurantMenuService restaurantMenuService;

    public RestaurantMenuController(RestaurantMenuService restaurantMenuService) {
        this.restaurantMenuService = restaurantMenuService;
    }


    @PostMapping("/addingMenus/{restaurantId}")
    public ResponseEntity<Map<String, Object>> addMenus(@PathVariable Long restaurantId, @RequestBody List<RestaurantMenu> restaurantMenus) {
        try {
           Map<String,Object> savedMenus = restaurantMenuService.addMenus(restaurantMenus, restaurantId);
            Map<String, Object> response = Map.of(
                    "message", "Menus added successfully",
                    "menus", savedMenus
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Failed to add menus", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getMenus/{RestaurantId}")
    public ResponseEntity<Map<String,Object>> getMenusByRestaurantId(@PathVariable Long RestaurantId){
        try{
            Map<String,Object> res = restaurantMenuService.getMenusByRestaurantId(RestaurantId);
            return new ResponseEntity<>(res,HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Failed to fetched menus", "error", e.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }



    @GetMapping("/getAllMenus")
    public ResponseEntity<Map<String, Object>> getAllMenus() {
        try{
            Map<String,Object> res  = restaurantMenuService.getAllMenus();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Menu not fetched successfully", "error", e.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/menu/{menuId}")
    public ResponseEntity<Map<String, Object>> getMenuById(@PathVariable Long menuId) {
        try {
            Map<String, Object> response = restaurantMenuService.getMenuById(menuId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "unable to feched menus using menuid", "error", e.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }


    @DeleteMapping("/deleteMenu/{MenuId}")
    public ResponseEntity<Map<String, Object>> deleteMenu(@PathVariable Long MenuId) {
        try{
            Map<String,Object> res  = restaurantMenuService.deleteMenu(MenuId);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Menu not deleted suucessfully", "error", e.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }
}
