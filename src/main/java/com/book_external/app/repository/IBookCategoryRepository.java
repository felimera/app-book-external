package com.book_external.app.repository;

import com.book_external.app.model.internal.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookCategoryRepository extends JpaRepository<BookCategory, Integer> {
}
