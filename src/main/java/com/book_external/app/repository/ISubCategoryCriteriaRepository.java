package com.book_external.app.repository;

import com.book_external.app.model.internal.SubCategory;

import java.util.List;
import java.util.Map;


public interface ISubCategoryCriteriaRepository {
    List<SubCategory> getConsultSubCategoryForVariousParameters(Map<String, String> map);
}
