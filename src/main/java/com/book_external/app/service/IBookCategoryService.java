package com.book_external.app.service;

import com.book_external.app.model.dto.BookCategoryDto;

import java.util.List;

public interface IBookCategoryService {
    List<BookCategoryDto> getListAll();

    BookCategoryDto create(BookCategoryDto dto);
}
