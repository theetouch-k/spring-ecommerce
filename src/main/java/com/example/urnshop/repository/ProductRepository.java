package com.example.urnshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.urnshop.model.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}