package com.book_external.app.application.mapper;

import com.book_external.app.application.dto.BookExtDto;
import com.book_external.app.domain.model.external.BookExt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IBookExtMapper {

    IBookExtMapper INSTANCE = Mappers.getMapper(IBookExtMapper.class);

    @Mapping(target = "titulo", source = "title")
    @Mapping(target = "idCliente", source = "userId")
    @Mapping(target = "isCompletado", source = "completed")
    BookExtDto toDto(BookExt entity);
}
