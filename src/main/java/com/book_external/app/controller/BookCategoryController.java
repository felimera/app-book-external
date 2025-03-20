package com.book_external.app.controller;

import com.book_external.app.model.dto.BookCategoryDto;
import com.book_external.app.service.IBookCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<BookCategoryDto>> getListAll() {
        return ResponseEntity.ok(iBookCategoryService.getListAll());
    }

    @Operation(summary = "Record the relationship between a book and a category.")
    @PostMapping
    public ResponseEntity<BookCategoryDto> create(@RequestBody BookCategoryDto dto) {
        log.info("Dto bookCategory =>>" + dto.toString());
        return ResponseEntity.ok(iBookCategoryService.create(dto));
    }
}
