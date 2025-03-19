package com.book_external.app.service;

import com.book_external.app.model.dto.SubCategoryDto;

import java.util.List;

public interface ISubCategoryService {
    SubCategoryDto create(SubCategoryDto subCategoryDto, Integer idCategoria);

    SubCategoryDto edit(Integer id, SubCategoryDto subCategoryDto);

    List<SubCategoryDto> getListAll();
}
