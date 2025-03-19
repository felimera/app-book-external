package com.book_external.app.repository.implementation;

import com.book_external.app.model.internal.Category;
import com.book_external.app.model.internal.Category_;
import com.book_external.app.repository.ICategoryCriteriaRepository;
import com.book_external.app.utils.Constant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class ICategoryCriteriaRepositoryImpl implements ICategoryCriteriaRepository {

    private EntityManager entityManager;

    @Autowired
    public ICategoryCriteriaRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Category> getCategoryByNameOrDescription(Map<String, String> map) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);

        Root<Category> categoryRoot = criteriaQuery.from(Category.class);

        List<Predicate> predicateList = new ArrayList<>();

        if (!map.isEmpty()) {
            if (Objects.nonNull(map.get(Constant.CATEGORY_NAME)) && !map.get(Constant.CATEGORY_NAME).isEmpty()) {
                Expression<String> nameExp = criteriaBuilder.upper(categoryRoot.get(Category_.NAME_CATEGORY));
                String nameValue = "%".concat(map.get(Constant.CATEGORY_NAME).toUpperCase()).concat("%");
                predicateList.add(criteriaBuilder.like(nameExp, nameValue));
            }

            if (Objects.nonNull(map.get(Constant.CATEGORY_DESCRIPTION)) && !map.get(Constant.CATEGORY_DESCRIPTION).isEmpty()) {
                Expression<String> descriptionExp = criteriaBuilder.upper(categoryRoot.get(Category_.DESCRIPTION_CATEGORY));
                String descriptionValue = "%".concat(map.get(Constant.CATEGORY_DESCRIPTION).toUpperCase()).concat("%");
                predicateList.add(criteriaBuilder.like(descriptionExp, descriptionValue));
            }
        }

        criteriaQuery.where(predicateList.toArray(new Predicate[0]));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
