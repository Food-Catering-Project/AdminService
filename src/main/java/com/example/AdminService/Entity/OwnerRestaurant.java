package com.example.AdminService.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRestaurant {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RestaurantId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String number;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String address;

    @Column(nullable = false,columnDefinition = "TEXT")
    private double rating;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    public Long getRestaurantId() {
        return RestaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        RestaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
