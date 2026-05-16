package com.example.ProductApplication.Service;

import com.example.ProductApplication.DTO.PaymentRequestDTO;
import com.example.ProductApplication.Model.Bill;
import com.example.ProductApplication.Repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private BillRepository billRepo;

    public String processPayment(PaymentRequestDTO request) {

        Bill bill = billRepo.findById(request.getBillId())
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        int option = request.getPaymentOption();
        String mode;

        switch (option) {
            case 1:
                mode = "UPI";
                break;
            case 2:
                mode = "CARD";
                break;
            case 3:
                mode = "NET BANKING";
                break;
            case 4:
                mode = "CASH";
                break;
            case 5:
                mode = "WALLET";
                break;
            default:
                bill.setPaymentStatus("FAILED");
                billRepo.save(bill);
                return "Invalid Payment Option. Payment FAILED.";
        }

        // ✅ 1:4 Success Ratio (80% success)
        boolean success = Math.random() < 0.8;

        if (success) {

            String transactionId = UUID.randomUUID().toString();

            bill.setPaymentStatus("SUCCESS");
            bill.setTransactionId(transactionId);  // make sure this field exists in Bill
            billRepo.save(bill);

            return "Payment SUCCESS using " + mode +
                    " | Transaction ID: " + transactionId;

        } else {

            bill.setPaymentStatus("FAILED");
            billRepo.save(bill);

            return "Payment FAILED using " + mode + ". Please try again.";
        }
    }
}