package com.book_external.app.domain.service;

import com.book_external.app.domain.model.internal.BookInter;

public interface IBookInterService {
    BookInter getBookInterById(Integer id);

    BookInter create(Integer idBookExt);
}
