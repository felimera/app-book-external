package com.book_external.app.controller;

import com.book_external.app.model.component.TypesStatus;
import com.book_external.app.model.dto.SubCategoryDto;
import com.book_external.app.model.exception.BadRequestException;
import com.book_external.app.model.response.Meta;
import com.book_external.app.model.response.Pagination;
import com.book_external.app.model.response.Response;
import com.book_external.app.service.ISubCategoryService;
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
    public ResponseEntity<Response> create(@Valid @RequestBody SubCategoryDto dto, BindingResult bindingResult) {
        log.info("Dto post =>>" + dto.toString());
        if (bindingResult.hasErrors())
            throw new BadRequestException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Error creating store.", BuildErrorUtil.formatMessage(bindingResult), HttpStatus.BAD_REQUEST);

        Response response = new Response();
        response.setMeta(Meta.builder().build().toMetaBuilder(TypesStatus.SUCCESS.name()));
        response.setPagination(Pagination.builder().build().toPaginationBuilder());
        response.setData(iSubCategoryService.create(dto, dto.getIdCategory()));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Modify a Subcategory record.")
    @PutMapping(path = "{id}")
    public ResponseEntity<Response> edit(@PathVariable(name = "id") Integer id, @Valid @RequestBody SubCategoryDto dto, BindingResult bindingResult) {
        log.info("Dto post =>>" + dto.toString());
        if (bindingResult.hasErrors())
            throw new BadRequestException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Error creating store.", BuildErrorUtil.formatMessage(bindingResult), HttpStatus.BAD_REQUEST);

        Response response = new Response();
        response.setMeta(Meta.builder().build().toMetaBuilder(TypesStatus.SUCCESS.name()));
        response.setPagination(Pagination.builder().build().toPaginationBuilder());
        response.setData(iSubCategoryService.edit(id, dto));

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get a list of all subcategory records.")
    @GetMapping
    public ResponseEntity<Response> getListAll() {
        Response response = new Response();
        response.setMeta(Meta.builder().build().toMetaBuilder(TypesStatus.SUCCESS.name()));
        response.setPagination(Pagination.builder().build().toPaginationBuilder());
        response.setData(iSubCategoryService.getListAll());

        return ResponseEntity.ok(response);
    }
}
