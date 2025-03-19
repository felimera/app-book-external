package com.book_external.app.service.mapper;

import com.book_external.app.model.dto.BookExtDto;
import com.book_external.app.model.external.BookExt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookExtMapper {

    BookExtMapper INSTANCE = Mappers.getMapper(BookExtMapper.class);

    @Mapping(target = "titulo", source = "title")
    @Mapping(target = "isCompletado", source = "completed")
    BookExtDto toDto(BookExt entity);
}
