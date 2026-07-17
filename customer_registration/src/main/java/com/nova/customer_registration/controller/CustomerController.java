package com.nova.customer_registration.controller;


import com.nova.customer_registration.dto.request.CreateCustomerRequest;
import com.nova.customer_registration.service.CreateCustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/customer")
public class CustomerController {

    private final CreateCustomerService createCustomerService;

    @PostMapping
    public ResponseEntity<String> create(
            @Valid @RequestBody CreateCustomerRequest request
            ) {
        createCustomerService.create(request.nombre());
        return ResponseEntity.ok("Se han dado del alta el usuario");
    }
}
