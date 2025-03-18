package com.book_external.app.service;

import com.book_external.app.model.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {
    List<CategoryDto> getCategoryListByMultipleParameter(CategoryDto dto);
}
