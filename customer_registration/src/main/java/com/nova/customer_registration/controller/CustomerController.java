package com.nova.customer_registration.controller;


import com.nova.customer_registration.dto.request.CreateCustomerRequest;
import com.nova.customer_registration.dto.response.CustomerResponse;
import com.nova.customer_registration.service.CreateCustomerService;
import com.nova.customer_registration.service.SearchCustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/customer")
public class CustomerController {

    private final CreateCustomerService createCustomerService;
    private  final SearchCustomerService searchCustomerService;

    @PostMapping
    public ResponseEntity<String> create(
            @Valid @RequestBody CreateCustomerRequest request
            ) {
        createCustomerService.create(request.nombre());
        return ResponseEntity.ok("Se han dado del alta el usuario");
    }


    @GetMapping("/search")
    public ResponseEntity<CustomerResponse> search(
            @RequestParam String name
    ){
        return ResponseEntity.ok(searchCustomerService.searchCustomerByName(name));
    }
}
