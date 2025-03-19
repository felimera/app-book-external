package com.book_external.app.service.mapper;

import com.book_external.app.model.dto.BookCategoryDto;
import com.book_external.app.model.internal.BookCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookCategoryMapper {
    BookCategoryMapper INSTANCE = Mappers.getMapper(BookCategoryMapper.class);

    @Mapping(target = "idBookInter", source = "bookInter.id")
    @Mapping(target = "idCategory", source = "category.id")
    BookCategoryDto toDto(BookCategory entity);
}
