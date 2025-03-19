package com.book_external.app.service.implementation;

import com.book_external.app.model.dto.CategoryDto;
import com.book_external.app.model.dto.SubCategoryDto;
import com.book_external.app.model.internal.Category;
import com.book_external.app.model.internal.SubCategory;
import com.book_external.app.repository.ICategoryCriteriaRepository;
import com.book_external.app.repository.ICategoryRepository;
import com.book_external.app.repository.ISubCategoryCriteriaRepository;
import com.book_external.app.service.ICategoryService;
import com.book_external.app.service.mapper.CategoryMapper;
import com.book_external.app.service.mapper.SubCategoryMapper;
import com.book_external.app.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ICategoryServiceImpl implements ICategoryService {
    private ICategoryRepository iCategoryRepository;

    private ICategoryCriteriaRepository iCategoryCriteriaRepository;
    private ISubCategoryCriteriaRepository iSubCategoryCriteriaRepository;

    public ICategoryServiceImpl(ICategoryRepository iCategoryRepository, ICategoryCriteriaRepository iCategoryCriteriaRepository, ISubCategoryCriteriaRepository iSubCategoryCriteriaRepository) {
        this.iCategoryRepository = iCategoryRepository;
        this.iCategoryCriteriaRepository = iCategoryCriteriaRepository;
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

    @Override
    public List<CategoryDto> getCategoryByNameOrDescription(CategoryDto categoryDto) {
        Map<String, String> map = new HashMap<>();
        map.put(Constant.CATEGORY_NAME, categoryDto.getNombre());
        map.put(Constant.CATEGORY_DESCRIPTION, categoryDto.getDescripcion());
        log.info("map =>>" + map);

        return iCategoryCriteriaRepository
                .getCategoryByNameOrDescription(map)
                .stream()
                .map(CategoryMapper.INSTANCE::toDto)
                .toList();
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        if (isExistsCategory(categoryDto)) {
            Category category = CategoryMapper.INSTANCE.toEntity(categoryDto);
            Category entity = iCategoryRepository.save(category);
            return CategoryMapper.INSTANCE.toDto(entity);
        }
        return categoryDto;
    }

    @Override
    public CategoryDto edit(Integer id, CategoryDto categoryDto) {
        Category categoryOld = iCategoryRepository.findById(id).orElseThrow();
        categoryOld.setNameCategory(categoryDto.getNombre());
        categoryOld.setDescriptionCategory(categoryDto.getDescripcion());
        Category entity = iCategoryRepository.save(categoryOld);
        return CategoryMapper.INSTANCE.toDto(entity);
    }

    @Override
    public Category getCategoryById(Integer idCategoria) {
        return iCategoryRepository.findById(idCategoria).orElseThrow();
    }

    private boolean isExistsCategory(CategoryDto categoryDto) {
        return iCategoryRepository.getNumberMatchesByName(categoryDto.getNombre()) == 0 && iCategoryRepository.getNumberMatchesByDescription(categoryDto.getDescripcion()) == 0;
    }
}
