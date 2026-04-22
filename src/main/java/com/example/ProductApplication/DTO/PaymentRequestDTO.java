package com.example.ProductApplication.DTO;

public class PaymentRequestDTO {

    private Long billId;
    private int paymentOption;  // 1 to 5

    // getters & setters

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public int getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(int paymentOption) {
        this.paymentOption = paymentOption;
    }

}
