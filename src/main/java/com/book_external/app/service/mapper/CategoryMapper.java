package com.book_external.app.service.mapper;

import com.book_external.app.model.dto.CategoryDto;
import com.book_external.app.model.internal.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "nombre", source = "nameCategory")
    @Mapping(target = "descripcion", source = "descriptionCategory")
    CategoryDto toDto(Category entity);
}
