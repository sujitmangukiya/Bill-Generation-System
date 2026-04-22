package com.example.ProductApplication.Service;

import com.example.ProductApplication.DTO.BillResponseDTO;
import com.example.ProductApplication.DTO.PurchaseRequestDTO;
import com.example.ProductApplication.Model.Bill;
import com.example.ProductApplication.Model.Customer;
import com.example.ProductApplication.Model.Product;
import com.example.ProductApplication.Repository.BillRepository;
import com.example.ProductApplication.Repository.CustomerRepository;
import com.example.ProductApplication.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class BillingService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private BillRepository billRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private WhatsAppService whatsAppService;

    public BillResponseDTO generateBill(PurchaseRequestDTO request) {



        Product product = productRepo.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check stock

//        if (product.getStock() < request.getProductCount()) {
//            throw new RuntimeException("Not enough stock available");
//        }

        // Check stock
        if (product.getStock() < request.getProductCount()) {

            BillResponseDTO response = new BillResponseDTO();
            response.setCustomerName(request.getCustomerName());
            response.setProductName(product.getName());
            response.setQuantity(0);
            response.setPrice(0);
            response.setGst(0);
            response.setTotalAmount(0);
            response.setPaymentStatus("FAILED - PRODUCT OUT OF STOCK");
            return response;
        }

        // Decrease stock (ONLY ONCE)
        product.setStock(product.getStock() - request.getProductCount());
        productRepo.save(product);

        //  Save Customer
        Customer customer = new Customer();
        customer.setName(request.getCustomerName());
        customer.setMobileNo(request.getMobileNo());
        customer.setEmail(request.getEmail());
        customerRepo.save(customer);

        // Price Calculation
        double price = product.getPrice() * request.getProductCount();
        double gst = price * 0.18;
        double total = price + gst;

        //  Save Bill
        Bill bill = new Bill();
        bill.setCustomer(customer);
        bill.setProduct(product);
        bill.setQuantity(request.getProductCount());
        bill.setGst(gst);
        bill.setTotalAmount(total);
        bill.setPaymentStatus("PENDING");
        bill.setBillDate(LocalDateTime.now());

        billRepo.save(bill);


        // SEND NOTIFICATIONS HERE
        String message = "Hello " + customer.getName() +
                "\nProduct: " + product.getName() +
                "\nQuantity: " + request.getProductCount() +
                "\nTotal Amount: ₹" + total +
                "\nStatus: PENDING";

        try {
            smsService.sendSms(customer.getMobileNo(), message);
            whatsAppService.sendWhatsApp(customer.getMobileNo(), message);
            emailService.sendEmail(request.getEmail(), "Bill Generated", message);

        } catch (Exception e) {
            System.out.println("Notification failed but bill generated");
        }


        BillResponseDTO response = new BillResponseDTO();
        response.setCustomerName(customer.getName());
        response.setProductName(product.getName());
        response.setQuantity(request.getProductCount());
        response.setPrice(price);
        response.setGst(gst);
        response.setTotalAmount(total);
        response.setPaymentStatus("PENDING");

        return response;
    }
}
