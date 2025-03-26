package com.book_external.app.insfraestructure.controller;

import com.book_external.app.domain.model.component.TypesStatus;
import com.book_external.app.application.response.Meta;
import com.book_external.app.application.response.Pagination;
import com.book_external.app.application.response.Response;
import com.book_external.app.domain.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Customer", description = "Operations related to customers.")
@RestController
@RequestMapping(path = "/api/v1/customer")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    private ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Get all clients of the external service.")
    @GetMapping
    public ResponseEntity<Response> getListAll() {
        Response response = new Response();
        response.setMeta(Meta.builder().build().toMetaBuilder(TypesStatus.SUCCESS.name()));
        response.setPagination(Pagination.builder().build().toPaginationBuilder());
        response.setData(customerService.getListAll());

        return ResponseEntity.ok(response);
    }
}
