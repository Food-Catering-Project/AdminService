package com.example.AdminService.Repository;

import com.example.AdminService.Entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner,Long> {
}
