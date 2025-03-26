package com.book_external.app.domain.service;

import com.book_external.app.application.dto.BookExtDto;
import com.book_external.app.domain.model.external.BookExt;

import java.util.List;
import java.util.Optional;

public interface IBookExtService {
    List<BookExtDto> getListAll();

    Optional<BookExt> getBookExtById(Integer id);
}
