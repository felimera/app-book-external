package com.book_external.app.repository;

import com.book_external.app.model.internal.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISubCategoryRepository extends JpaRepository<SubCategory, Integer> {

    @Query("select count(ca.id) from SubCategory ca where upper(ca.nameSubCategory) = upper(:name) ")
    Integer getNumberMatchesByName(@Param("name") String name);

    @Query("select count(ca.id) from SubCategory ca where upper(ca.descriptionSubCategory) = upper(:description) ")
    Integer getNumberMatchesByDescription(@Param("description") String description);
}
