package com.book_external.app.controller;

import com.book_external.app.model.dto.BookCategoryDto;
import com.book_external.app.service.IBookCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "BookCategory", description = "Operations related to book and categories.")
@RestController
@RequestMapping(path = "/api/v1/bookcategory")
@Slf4j
public class IBookCategoryController {

    private IBookCategoryService iBookCategoryService;

    @Autowired
    public IBookCategoryController(IBookCategoryService iBookCategoryService) {
        this.iBookCategoryService = iBookCategoryService;
    }

    @Operation(summary = "Record the relationship between a book and a category.")
    @PostMapping
    public ResponseEntity<BookCategoryDto> create(@RequestBody BookCategoryDto dto) {
        log.info("Dto bookCategory =>>" + dto.toString());
        return ResponseEntity.ok(iBookCategoryService.create(dto));
    }
}
