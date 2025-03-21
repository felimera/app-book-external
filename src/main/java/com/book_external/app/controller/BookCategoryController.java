package com.book_external.app.controller;

import com.book_external.app.model.component.TypesStatus;
import com.book_external.app.model.dto.BookCategoryDto;
import com.book_external.app.model.exception.BadRequestException;
import com.book_external.app.model.response.Meta;
import com.book_external.app.model.response.Pagination;
import com.book_external.app.model.response.Response;
import com.book_external.app.service.IBookCategoryService;
import com.book_external.app.utils.BuildErrorUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Tag(name = "BookCategory", description = "Operations related to book and categories.")
@RestController
@RequestMapping(path = "/api/v1/bookcategory")
@Slf4j
public class BookCategoryController {

    private IBookCategoryService iBookCategoryService;

    @Autowired
    public BookCategoryController(IBookCategoryService iBookCategoryService) {
        this.iBookCategoryService = iBookCategoryService;
    }

    @Operation(summary = "Get all book and category records.")
    @GetMapping(path = "/list")
    public ResponseEntity<Response> getListAll() {

        Response response = new Response();
        response.setMeta(Meta.builder().build().toMetaBuilder(TypesStatus.SUCCESS.name()));
        response.setPagination(Pagination.builder().build().toPaginationBuilder());
        response.setData(iBookCategoryService.getListAll());

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Record the relationship between a book and a category.")
    @PostMapping
    public ResponseEntity<Response> create(@Valid @RequestBody BookCategoryDto dto, BindingResult bindingResult) {
        log.info("Creating BookCategory: {}", dto);
        if (bindingResult.hasErrors())
            throw new BadRequestException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Error creating store.", BuildErrorUtil.formatMessage(bindingResult), HttpStatus.BAD_REQUEST);

        Response response = new Response();
        response.setMeta(Meta.builder().build().toMetaBuilder(TypesStatus.SUCCESS.name()));
        response.setPagination(Pagination.builder().build().toPaginationBuilder());
        response.setData(iBookCategoryService.create(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
