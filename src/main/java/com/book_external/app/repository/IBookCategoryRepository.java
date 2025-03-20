package com.book_external.app.repository;

import com.book_external.app.model.internal.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookCategoryRepository extends JpaRepository<BookCategory, Integer> {
    @Query("select count(bc.id) from BookCategory bc where bc.bookInter.id=:idBook and bc.category.id=:idCategory")
    Integer getMatchOnBookAndCategoryIds(@Param("idBook") Integer idBook, @Param("idCategory") Integer idCategory);
}
