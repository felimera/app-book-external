package com.book_external.app.controller;

import com.book_external.app.model.dto.SubCategoryDto;
import com.book_external.app.service.ISubCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "SubCategory", description = "Operations related to subcategories.")
@RestController
@RequestMapping(path = "/api/v1/subcategory")
@Slf4j
public class SubCategoryController {

    private ISubCategoryService iSubCategoryService;

    @Autowired
    public SubCategoryController(ISubCategoryService iSubCategoryService) {
        this.iSubCategoryService = iSubCategoryService;
    }

    @Operation(summary = "Create a Subcategory record.")
    @PostMapping
    public ResponseEntity<SubCategoryDto> create(@RequestBody SubCategoryDto dto) {
        log.info("Dto post =>>" + dto.toString());
        return ResponseEntity.ok(iSubCategoryService.create(dto, dto.getIdCategory()));
    }

    @Operation(summary = "Modify a Subcategory record.")
    @PutMapping(path = "{id}")
    public ResponseEntity<SubCategoryDto> edit(@PathVariable(name = "id") Integer id, @RequestBody SubCategoryDto dto) {
        log.info("Dto post =>>" + dto.toString());
        return ResponseEntity.ok(iSubCategoryService.edit(id, dto));
    }

    @Operation(summary = "Get a list of all subcategory records.")
    @GetMapping
    public ResponseEntity<List<SubCategoryDto>> getListAll() {
        return ResponseEntity.ok(iSubCategoryService.getListAll());
    }
}
