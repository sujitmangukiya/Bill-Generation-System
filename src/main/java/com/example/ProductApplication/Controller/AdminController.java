package com.example.ProductApplication.Controller;
import com.example.ProductApplication.Model.Product;
import com.example.ProductApplication.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    //http://localhost:8080/admin/products
    @GetMapping("/products")
    public List<Product> getProducts() {
        return adminService.getAllProducts();
    }

    //http://localhost:8080/admin/add-product
    @PostMapping("/add-product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = adminService.addProduct(product);
        return ResponseEntity.ok(savedProduct);
    }
}


/*
{
  "name": "Printer",
  "price": 9000,
  "stock": 6,
  "threshold": 2
}
 */