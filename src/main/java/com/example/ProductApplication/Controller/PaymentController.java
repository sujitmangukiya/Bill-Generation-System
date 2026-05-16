package com.example.ProductApplication.Controller;

import com.example.ProductApplication.DTO.PaymentRequestDTO;
import com.example.ProductApplication.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // http://localhost:8080/payment/confirm
    @PostMapping("/confirm")
    public String confirmPayment(@RequestBody PaymentRequestDTO request) {
        return paymentService.processPayment(request);
    }
}

/*
{
  "billId": 1,
  "paymentOption": 1
}
 */