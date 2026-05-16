package com.example.ProductApplication.Repository;

import com.example.ProductApplication.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
