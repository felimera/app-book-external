package com.book_external.app.application.mapper;

import com.book_external.app.application.dto.BookCategoryDto;
import com.book_external.app.domain.model.internal.BookCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IBookCategoryMapper {
    IBookCategoryMapper INSTANCE = Mappers.getMapper(IBookCategoryMapper.class);

    @Mapping(target = "idBookInter", source = "bookInter.id")
    @Mapping(target = "idCategory", source = "category.id")
    BookCategoryDto toDto(BookCategory entity);
}
