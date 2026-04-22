package com.example.ProductApplication.Controller;
import com.example.ProductApplication.DTO.BillResponseDTO;
import com.example.ProductApplication.DTO.PurchaseRequestDTO;
import com.example.ProductApplication.Service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    //http://localhost:8080/billing/generate
    @PostMapping("/generate")
    public BillResponseDTO generate(@RequestBody PurchaseRequestDTO request) {
        return billingService.generateBill(request);
    }
}

/*
{
  "customerName": "Sujit",
  "mobileNo": "+919974529573",
  "email": "sujitmangukiya111@gmail.com",
  "productId": 4,
  "productCount": 4
}
 */