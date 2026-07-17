package com.nova.customer_update.controller;


import com.nova.customer_update.dto.response.CreateCustomerRequest;
import com.nova.customer_update.service.CreateCustomerService;
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
