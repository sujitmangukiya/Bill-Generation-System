package com.example.ProductApplication.Repository;

import com.example.ProductApplication.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
