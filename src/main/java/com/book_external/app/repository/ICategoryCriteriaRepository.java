package com.book_external.app.repository;

import com.book_external.app.model.internal.Category;

import java.util.List;
import java.util.Map;

public interface ICategoryCriteriaRepository {
    List<Category> getCategoryByNameOrDescription(Map<String, String> map);
}
