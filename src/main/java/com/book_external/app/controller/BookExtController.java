package com.book_external.app.controller;

import com.book_external.app.model.component.TypesStatus;
import com.book_external.app.model.response.Meta;
import com.book_external.app.model.response.Pagination;
import com.book_external.app.model.response.Response;
import com.book_external.app.service.IBookExtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "BookExt", description = "Operations related to books.")
@RestController
@RequestMapping(path = "/api/v1/bookext")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class BookExtController {

    private IBookExtService iBookExtService;

    @Autowired
    public BookExtController(IBookExtService iBookExtService) {
        this.iBookExtService = iBookExtService;
    }

    @Operation(summary = "Get all records from external books.")
    @GetMapping
    public ResponseEntity<Response> getListAll() {
        Response response = new Response();
        response.setMeta(Meta.builder().build().toMetaBuilder(TypesStatus.SUCCESS.name()));
        response.setPagination(Pagination.builder().build().toPaginationBuilder());
        response.setData(iBookExtService.getListAll());

        return ResponseEntity.ok(response);
    }
}
