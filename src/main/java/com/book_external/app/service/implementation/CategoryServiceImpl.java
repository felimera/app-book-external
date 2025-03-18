package com.book_external.app.service.implementation;

import com.book_external.app.model.dto.CategoryDto;
import com.book_external.app.model.dto.SubCategoryDto;
import com.book_external.app.model.internal.Category;
import com.book_external.app.model.internal.SubCategory;
import com.book_external.app.repository.ISubCategoryCriteriaRepository;
import com.book_external.app.service.ICategoryService;
import com.book_external.app.service.mapper.CategoryMapper;
import com.book_external.app.service.mapper.SubCategoryMapper;
import com.book_external.app.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements ICategoryService {
    private ISubCategoryCriteriaRepository iSubCategoryCriteriaRepository;

    @Autowired
    public CategoryServiceImpl(ISubCategoryCriteriaRepository iSubCategoryCriteriaRepository) {
        this.iSubCategoryCriteriaRepository = iSubCategoryCriteriaRepository;
    }

    @Override
    public List<CategoryDto> getCategoryListByMultipleParameter(CategoryDto dto) {
        Map<String, String> map = new HashMap<>();
        map.put(Constant.CATEGORY_NAME, dto.getNombre());
        map.put(Constant.CATEGORY_DESCRIPTION, dto.getDescripcion());
        log.info("map =>>" + map);

        List<CategoryDto> categoryDtos = new ArrayList<>();
        List<SubCategory> subCategoryList = iSubCategoryCriteriaRepository.getConsultSubCategoryForVariousParameters(map);
        log.info("Result List =>> " + subCategoryList);

        Map<Integer, List<SubCategory>> subCategoryMapGrou = subCategoryList
                .stream()
                .collect(Collectors.groupingBy(subCategory -> subCategory.getCategory().getId()));

        log.info("Result Group =>> " + subCategoryMapGrou);

        for (Map.Entry<Integer, List<SubCategory>> subCategoryEntry : subCategoryMapGrou.entrySet()) {
            Optional<Category> categoryMainOptional = subCategoryList
                    .stream()
                    .filter(subCategory -> Objects.equals(subCategory.getCategory().getId(), subCategoryEntry.getKey()))
                    .map(SubCategory::getCategory)
                    .findFirst();

            if (categoryMainOptional.isPresent()) {
                CategoryDto categoryDto = CategoryMapper.INSTANCE.toDto(categoryMainOptional.get());

                List<SubCategoryDto> dtoList = subCategoryEntry
                        .getValue()
                        .stream()
                        .filter(subCategory -> Objects.equals(subCategory.getCategory().getId(), categoryMainOptional.get().getId()))
                        .map(SubCategoryMapper.INSTANCE::toDto)
                        .toList();

                categoryDto.setSubCategoryDtos(dtoList);

                categoryDtos.add(categoryDto);
            }
        }
        log.info("Result DtoGroup =>> " + categoryDtos);
        return categoryDtos;
    }
}
