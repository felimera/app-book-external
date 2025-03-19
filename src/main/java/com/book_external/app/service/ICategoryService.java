package com.book_external.app.service;

import com.book_external.app.model.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {
    List<CategoryDto> getCategoryListByMultipleParameter(CategoryDto dto);

    List<CategoryDto> getCategoryByNameOrDescription(CategoryDto categoryDto);

    CategoryDto create(CategoryDto categoryDto);

    CategoryDto edit(Integer id, CategoryDto categoryDto);
}
