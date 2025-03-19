package com.book_external.app.service;

import com.book_external.app.model.dto.BookExtDto;
import com.book_external.app.model.external.BookExt;

import java.util.List;
import java.util.Optional;

public interface IBookExtService {
    List<BookExtDto> getListAll();

    Optional<BookExt> getBookExtById(Integer id);
}
