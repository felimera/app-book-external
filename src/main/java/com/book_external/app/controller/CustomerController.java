package com.book_external.app.controller;

import com.book_external.app.model.dto.CustomerDto;
import com.book_external.app.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Customer", description = "Operations related to customers.")
@RestController
@RequestMapping(path = "/api/v1/customer")
@Slf4j
public class CustomerController {

    private ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Get all clients of the external service.")
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getListAll() {
        return ResponseEntity.ok(customerService.getListAll());
    }
}
