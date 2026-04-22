package com.example.ProductApplication.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppService {

    @Value("${twilio.whatsapp.number}")
    private String fromWhatsapp;

    public void sendWhatsApp(String to, String message) {

        Message.creator(
                new PhoneNumber("whatsapp:" + to),
                new PhoneNumber(fromWhatsapp),
                message
        ).create();
    }
}