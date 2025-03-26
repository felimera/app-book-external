package com.book_external.app.application.mapper;

import com.book_external.app.application.dto.CategoryDto;
import com.book_external.app.domain.model.internal.Category;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ICategoryMapper {
    ICategoryMapper INSTANCE = Mappers.getMapper(ICategoryMapper.class);

    @Mapping(target = "nombre", source = "nameCategory")
    @Mapping(target = "descripcion", source = "descriptionCategory")
    CategoryDto toDto(Category entity);

    @InheritInverseConfiguration
    Category toEntity(CategoryDto dto);
}
