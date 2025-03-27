package com.book_external.app.domain.repository;

import com.book_external.app.domain.model.internal.BookCategory;

import java.util.List;
import java.util.Map;

public interface IBookCategoryCriteriaRepository {
    List<BookCategory> getListNode(Map<String, String> map);
}
