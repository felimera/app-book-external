package com.book_external.app.domain.service;

import com.book_external.app.application.dto.BookCategoryDto;

import java.util.List;

public interface IBookCategoryService {
    List<BookCategoryDto> getListAll();

    BookCategoryDto create(BookCategoryDto dto);
}
