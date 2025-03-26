package com.book_external.app.application.repository;

import com.book_external.app.domain.model.internal.Category;
import com.book_external.app.domain.model.internal.Category_;
import com.book_external.app.domain.model.internal.SubCategory;
import com.book_external.app.domain.model.internal.SubCategory_;
import com.book_external.app.domain.repository.ISubCategoryCriteriaRepository;
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
public class SubCategoryCriteriaRepositoryImpl implements ISubCategoryCriteriaRepository {

    private EntityManager entityManager;

    @Autowired
    public SubCategoryCriteriaRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<SubCategory> getConsultSubCategoryForVariousParameters(Map<String, String> map) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SubCategory> criteriaQuery = criteriaBuilder.createQuery(SubCategory.class);

        Root<SubCategory> subCategoryRoot = criteriaQuery.from(SubCategory.class);
        Join<Category, SubCategory> categorySubCategoryJoin = subCategoryRoot.join(SubCategory_.CATEGORY, JoinType.INNER);

        List<Predicate> predicateList = new ArrayList<>();

        if (Objects.nonNull(map.get(Constant.CATEGORY_NAME)) && !map.get(Constant.CATEGORY_NAME).isEmpty()) {
            Expression<String> nameExp = criteriaBuilder.upper(categorySubCategoryJoin.get(Category_.NAME_CATEGORY));
            String nameValue = "%".concat(map.get(Constant.CATEGORY_NAME).toUpperCase()).concat("%");
            predicateList.add(criteriaBuilder.like(nameExp, nameValue));
        }

        if (Objects.nonNull(map.get(Constant.CATEGORY_DESCRIPTION)) && !map.get(Constant.CATEGORY_DESCRIPTION).isEmpty()) {
            Expression<String> descriptionExp = criteriaBuilder.upper(categorySubCategoryJoin.get(Category_.DESCRIPTION_CATEGORY));
            String descriptionValue = "%".concat(map.get(Constant.CATEGORY_DESCRIPTION).toUpperCase()).concat("%");
            predicateList.add(criteriaBuilder.like(descriptionExp, descriptionValue));
        }

        if (!predicateList.isEmpty()) {
            criteriaQuery.where(predicateList.toArray(new Predicate[0]));
            return entityManager.createQuery(criteriaQuery).getResultList();
        }
        return new ArrayList<>();
    }
}
