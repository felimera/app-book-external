package com.book_external.app.application.mapper;

import com.book_external.app.application.dto.SubCategoryDto;
import com.book_external.app.domain.model.internal.SubCategory;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ISubCategoryMapper {
    ISubCategoryMapper INSTANCE = Mappers.getMapper(ISubCategoryMapper.class);

    @Mapping(target = "nombre", source = "nameSubCategory")
    @Mapping(target = "descripcion", source = "descriptionSubCategory")
    SubCategoryDto toDto(SubCategory entity);

    @InheritInverseConfiguration
    SubCategory toEntity(SubCategoryDto dto);
}
