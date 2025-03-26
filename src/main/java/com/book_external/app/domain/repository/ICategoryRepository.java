package com.book_external.app.domain.repository;

import com.book_external.app.domain.model.internal.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select count(ca.id) from Category ca where upper(ca.nameCategory) = upper(:name) ")
    Integer getNumberMatchesByName(@Param("name") String name);

    @Query("select count(ca.id) from Category ca where upper(ca.descriptionCategory) = upper(:description) ")
    Integer getNumberMatchesByDescription(@Param("description") String description);
}
