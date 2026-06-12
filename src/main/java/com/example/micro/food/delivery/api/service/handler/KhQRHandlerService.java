package com.example.micro.food.delivery.api.service.handler;

import com.example.micro.food.delivery.api.dto.PaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class KhQRHandlerService {

    private final RestTemplate restTemplate;

    public KhQRHandlerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String postingToKhQR(PaymentRequest paymentRequest) {

        try {

            final String url = "https://khqr.example.com/transactions";

            final String token =
                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
                            + "9WCoS75YN9mtSaWGhZqBQMHeWIq2HDwgy2YGAlwrjIM";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);

            HttpEntity<PaymentRequest> httpEntity =
                    new HttpEntity<>(paymentRequest, headers);

            log.info("POSTING TO KH QR URL: {}", url);

            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            log.info("Response from KhQR API: {}", response.getBody());

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }

        } catch (Exception ex) {
            log.error("KhQR Service response error: {}", ex.getMessage(), ex);
        }

        return null;
    }
}