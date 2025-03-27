package com.book_external.app.domain.service;

import com.book_external.app.application.dto.BookCategoryDto;
import com.book_external.app.application.dto.BookCategoryNodeDto;

import java.util.List;

public interface IBookCategoryService {
    List<BookCategoryDto> getListAll();

    BookCategoryDto create(BookCategoryDto dto);

    List<BookCategoryNodeDto> getListNode(String nameBook, String nameCategory);
}
