package com.book_external.app.insfraestructure.controller;

import com.book_external.app.domain.model.component.TypesStatus;
import com.book_external.app.application.dto.CategoryDto;
import com.book_external.app.application.dto.validation.ICreationGroup;
import com.book_external.app.insfraestructure.exception.BadRequestException;
import com.book_external.app.application.response.Meta;
import com.book_external.app.application.response.Pagination;
import com.book_external.app.application.response.Response;
import com.book_external.app.domain.service.ICategoryService;
import com.book_external.app.insfraestructure.utils.BuildErrorUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Category", description = "Operations related to categories.")
@RestController
@RequestMapping(path = "/api/v1/category")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    private ICategoryService iCategoryService;

    @Autowired
    public CategoryController(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }

    @Operation(summary = "Get a list of all registered categories with subcategories.")
    @PostMapping(path = "/subcategories")
    public ResponseEntity<Response> getCategoryListByMultipleParameter(@RequestBody CategoryDto dto) {
        log.info("Dto subcategories =>>" + dto.toString());

        Response response = new Response();
        response.setMeta(Meta.builder().build().toMetaBuilder(TypesStatus.SUCCESS.name()));
        response.setPagination(Pagination.builder().build().toPaginationBuilder());
        response.setData(iCategoryService.getCategoryListByMultipleParameter(dto));

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get a list of all categories by name and/or description.")
    @PostMapping(path = "/namedescription")
    public ResponseEntity<Response> getCategoryByNameOrDescription(@RequestBody CategoryDto dto) {
        log.info("Dto namedescription =>>" + dto.toString());

        Response response = new Response();
        response.setMeta(Meta.builder().build().toMetaBuilder(TypesStatus.SUCCESS.name()));
        response.setPagination(Pagination.builder().build().toPaginationBuilder());
        response.setData(iCategoryService.getCategoryByNameOrDescription(dto));

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create a Category record.")
    @PostMapping
    public ResponseEntity<Response> create(@Validated(ICreationGroup.class) @Valid @RequestBody CategoryDto dto, BindingResult bindingResult) {
        log.info("Dto post =>>" + dto.toString());

        if (bindingResult.hasErrors())
            throw new BadRequestException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Error creating store.", BuildErrorUtil.formatMessage(bindingResult), HttpStatus.BAD_REQUEST);

        Response response = new Response();
        response.setMeta(Meta.builder().build().toMetaBuilder(TypesStatus.SUCCESS.name()));
        response.setPagination(Pagination.builder().build().toPaginationBuilder());
        response.setData(iCategoryService.create(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Modify a Category record.")
    @PutMapping(path = "{id}")
    public ResponseEntity<Response> edit(@PathVariable(name = "id") Integer id, @Validated(ICreationGroup.class) @Valid @RequestBody CategoryDto dto) {
        log.info("Dto put =>>" + dto.toString());
        Response response = new Response();
        response.setMeta(Meta.builder().build().toMetaBuilder(TypesStatus.SUCCESS.name()));
        response.setPagination(Pagination.builder().build().toPaginationBuilder());
        response.setData(iCategoryService.edit(id, dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
