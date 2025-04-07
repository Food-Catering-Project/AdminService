package com.example.AdminService.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MenuId;

    @Column(nullable = false)
    private String menuName;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String category;

//    @ManyToOne
//    @JoinColumn(name = "restaurant_id", nullable = false)
//    private OwnerRestaurant  ownerRestaurant;

    @Column(nullable = false)
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private OwnerRestaurant ownerRestaurant;

    public Long getMenuId() {
        return MenuId;
    }

    public void setMenuId(Long menuId) {
        MenuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public OwnerRestaurant getOwnerRestaurant() {
        return ownerRestaurant;
    }

    public void setOwnerRestaurant(OwnerRestaurant ownerRestaurant) {
        this.ownerRestaurant = ownerRestaurant;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
