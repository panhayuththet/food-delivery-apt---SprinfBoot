package com.example.micro.food.delivery.api.service.handler;


import com.example.micro.food.delivery.api.constant.Constant;
import com.example.micro.food.delivery.api.dto.PaymentRequest;
import com.example.micro.food.delivery.api.enumeration.PaymentMethod;
import com.example.micro.food.delivery.api.enumeration.PaymentStatus;
import com.example.micro.food.delivery.api.model.Payment;
import com.example.micro.food.delivery.api.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
@Slf4j
public class PaymentHandlerService {

    private final PaymentRepository paymentRepository;
    private final KhQRHandlerService khQRHandlerService;
    private final CashHandlerService cashHandlerService;


    public PaymentHandlerService(

            PaymentRepository paymentRepository,
            KhQRHandlerService khQRHandlerService,
            CashHandlerService cashHandlerService1) {

        this.paymentRepository = paymentRepository;
        this.khQRHandlerService = khQRHandlerService;
        this.cashHandlerService = cashHandlerService1;

    }

    public String postingToPaymentGateway(PaymentRequest paymentRequest) {
        log.info("Posting to payment gateway");

        if (Constant.BANK.equalsIgnoreCase(paymentRequest.getPaymentMethod())) {
            log.info("Payment method is BANK");
            String khQRServerResponse = khQRHandlerService.postingToKhQR(paymentRequest);
            // Verify response make sur it success or fail
            savePaymentTransaction(paymentRequest, khQRServerResponse);
            //Verify Response make sur it success or

            if(StringUtils.hasText(khQRServerResponse)){
                return Constant.SUCCESS;
            }
            return Constant.FAILED;
        }
        if (Constant.CASH.equalsIgnoreCase(paymentRequest.getPaymentMethod())) {
            log.info("Payment method is CASH");
            //Call cash api
            String cashServerResponse = cashHandlerService.postingToCashApi(paymentRequest);

            savePaymentTransaction(paymentRequest, cashServerResponse);
            // Core system logic
            // Verify    response make sur it success or fail
            if(StringUtils.hasText(cashServerResponse)){
                return Constant.SUCCESS;
            }
            return Constant.FAILED;
        }
        if (Constant.CARD.equalsIgnoreCase(paymentRequest.getPaymentMethod())) {
            log.info("Payment method is CARD");
            return Constant.SUCCESS;
        }
        {
             log.info("Payment method is NOT BANK or CASH");
             return Constant.FAILED;
        }
    }

    public void savePaymentTransaction(PaymentRequest paymentRequest, String response) {

        Payment payment = new Payment();
        payment.setPaymentMethod(PaymentMethod.valueOf(paymentRequest.getPaymentMethod()));
        if(StringUtils.hasText(response)){
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
        } else {
            payment.setPaymentStatus(PaymentStatus.FAILED);
        }
        payment.setAmount(paymentRequest.getAmount());
        payment.setCreatedAt(new Date());
        payment.setCreatedBy(Constant.SYSTEM);

        log.info("Saving payment transaction {}", payment);
        paymentRepository.save(payment);

    }
}


