package com.book_external.app.controller;

import com.book_external.app.model.dto.BookExtDto;
import com.book_external.app.service.IBookExtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "BookExt", description = "Operations related to books.")
@RestController
@RequestMapping(path = "/api/v1/bookext")
@Slf4j
public class BookExtController {

    private IBookExtService iBookExtService;

    @Autowired
    public BookExtController(IBookExtService iBookExtService) {
        this.iBookExtService = iBookExtService;
    }

    @Operation(summary = "Get all records from external books.")
    @GetMapping
    public ResponseEntity<List<BookExtDto>> getListAll() {
        return ResponseEntity.ok(iBookExtService.getListAll());
    }
}
