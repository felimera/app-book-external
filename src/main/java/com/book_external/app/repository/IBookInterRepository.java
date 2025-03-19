package com.book_external.app.repository;

import com.book_external.app.model.internal.BookInter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookInterRepository extends JpaRepository<BookInter, Integer> {
}
