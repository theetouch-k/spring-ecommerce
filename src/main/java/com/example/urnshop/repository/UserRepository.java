package com.example.urnshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.urnshop.model.User;

import java.util.UUID;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
}