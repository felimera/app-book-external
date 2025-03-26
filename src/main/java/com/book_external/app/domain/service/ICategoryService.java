package com.book_external.app.domain.service;

import com.book_external.app.application.dto.CategoryDto;
import com.book_external.app.domain.model.internal.Category;

import java.util.List;

public interface ICategoryService {
    List<CategoryDto> getCategoryListByMultipleParameter(CategoryDto dto);

    List<CategoryDto> getCategoryByNameOrDescription(CategoryDto categoryDto);

    CategoryDto create(CategoryDto categoryDto);

    CategoryDto edit(Integer id, CategoryDto categoryDto);

    Category getCategoryById(Integer idCategoria);

    List<CategoryDto> getAllList();
}
