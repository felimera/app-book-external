package com.book_external.app.controller;

import com.book_external.app.model.dto.CategoryDto;
import com.book_external.app.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Category", description = "Operations related to categories.")
@RestController
@RequestMapping(path = "/api/v1/category")
@Slf4j
public class CategoryController {
    private ICategoryService iCategoryService;

    @Autowired
    public CategoryController(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }

    @Operation(summary = "Get a list of categories by multiple parameters.")
    @PostMapping()
    public ResponseEntity<List<CategoryDto>> getCategoryListByMultipleParameter(@RequestBody CategoryDto dto) {
        log.info("Dto =>>" + dto.toString());
        return ResponseEntity.ok(iCategoryService.getCategoryListByMultipleParameter(dto));
    }
}
