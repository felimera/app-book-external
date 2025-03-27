package com.book_external.app.application.repository;

import com.book_external.app.domain.model.internal.*;
import com.book_external.app.domain.repository.IBookCategoryCriteriaRepository;
import com.book_external.app.insfraestructure.utils.Constant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class IBookCategoryCriteriaRepositoryImpl implements IBookCategoryCriteriaRepository {

    private EntityManager entityManager;

    @Autowired
    public IBookCategoryCriteriaRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<BookCategory> getListNode(Map<String, String> map) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookCategory> criteriaQuery = criteriaBuilder.createQuery(BookCategory.class);

        Root<BookCategory> categoryRoot = criteriaQuery.from(BookCategory.class);
        Join<BookCategory, BookInter> bookCategoryBookInterJoin = categoryRoot.join(BookCategory_.BOOK_INTER, JoinType.INNER);
        Join<BookCategory, Category> bookCategoryCategoryJoin = categoryRoot.join(BookCategory_.CATEGORY, JoinType.INNER);

        List<Predicate> predicateList = new ArrayList<>();

        if (!map.isEmpty()) {
            if (Objects.nonNull(map.get(Constant.BOOKCATEGORY_NAMEBOOK)) && !map.get(Constant.BOOKCATEGORY_NAMEBOOK).isEmpty()) {

                Expression<String> nameBookExp = criteriaBuilder.upper(bookCategoryBookInterJoin.get(BookInter_.NAME));
                String nameBookValue = "%".concat(map.get(Constant.BOOKCATEGORY_NAMEBOOK).toUpperCase()).concat("%");
                predicateList.add(criteriaBuilder.like(nameBookExp, nameBookValue));

            }

            if (Objects.nonNull(map.get(Constant.BOOKCATEGORY_NAMECATEGORY)) && !map.get(Constant.BOOKCATEGORY_NAMECATEGORY).isEmpty()) {

                Expression<String> nameCategoryExp = criteriaBuilder.upper(bookCategoryCategoryJoin.get(Category_.NAME_CATEGORY));
                String nameCategoryValue = "%".concat(map.get(Constant.BOOKCATEGORY_NAMECATEGORY).toUpperCase()).concat("%");
                predicateList.add(criteriaBuilder.like(nameCategoryExp, nameCategoryValue));

            }
        }

        criteriaQuery.where(predicateList.toArray(new Predicate[0]));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
