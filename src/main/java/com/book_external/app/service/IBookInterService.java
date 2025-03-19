package com.book_external.app.service;

import com.book_external.app.model.internal.BookInter;

public interface IBookInterService {
    BookInter getBookInterById(Integer id);

    BookInter create(Integer idBookExt);
}
