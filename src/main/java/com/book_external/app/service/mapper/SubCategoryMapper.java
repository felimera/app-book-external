package com.book_external.app.service.mapper;

import com.book_external.app.model.dto.SubCategoryDto;
import com.book_external.app.model.internal.SubCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubCategoryMapper {
    SubCategoryMapper INSTANCE = Mappers.getMapper(SubCategoryMapper.class);

    @Mapping(target = "nombre", source = "nameSubCategory")
    @Mapping(target = "descripcion", source = "descriptionSubCategory")
    SubCategoryDto toDto(SubCategory entity);
}
