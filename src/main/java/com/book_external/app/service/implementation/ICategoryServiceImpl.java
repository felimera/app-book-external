package com.book_external.app.service.implementation;

import com.book_external.app.model.dto.CategoryDto;
import com.book_external.app.model.dto.SubCategoryDto;
import com.book_external.app.model.exception.ConflictException;
import com.book_external.app.model.exception.NotFoundException;
import com.book_external.app.model.internal.Category;
import com.book_external.app.model.internal.SubCategory;
import com.book_external.app.repository.ICategoryCriteriaRepository;
import com.book_external.app.repository.ICategoryRepository;
import com.book_external.app.repository.ISubCategoryCriteriaRepository;
import com.book_external.app.service.ICategoryService;
import com.book_external.app.service.IMessageService;
import com.book_external.app.service.mapper.CategoryMapper;
import com.book_external.app.service.mapper.SubCategoryMapper;
import com.book_external.app.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ICategoryServiceImpl implements ICategoryService {
    private ICategoryRepository iCategoryRepository;

    private ICategoryCriteriaRepository iCategoryCriteriaRepository;
    private ISubCategoryCriteriaRepository iSubCategoryCriteriaRepository;
    private IMessageService iMessageService;

    @Autowired
    public ICategoryServiceImpl(ICategoryRepository iCategoryRepository, ICategoryCriteriaRepository iCategoryCriteriaRepository, ISubCategoryCriteriaRepository iSubCategoryCriteriaRepository, IMessageService iMessageService) {
        this.iCategoryRepository = iCategoryRepository;
        this.iCategoryCriteriaRepository = iCategoryCriteriaRepository;
        this.iSubCategoryCriteriaRepository = iSubCategoryCriteriaRepository;
        this.iMessageService = iMessageService;
    }

    @Override
    public List<CategoryDto> getCategoryListByMultipleParameter(CategoryDto dto) {
        Map<String, String> map = new HashMap<>();
        map.put(Constant.CATEGORY_NAME, dto.getNombre());
        map.put(Constant.CATEGORY_DESCRIPTION, dto.getDescripcion());
        log.info("map =>>" + map);

        List<CategoryDto> categoryDtos = new ArrayList<>();
        List<SubCategory> subCategoryList = iSubCategoryCriteriaRepository.getConsultSubCategoryForVariousParameters(map);

        if (subCategoryList.isEmpty())
            return categoryDtos;

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
            Locale locale = LocaleContextHolder.getLocale();
            String mensaje = iMessageService.getMensaje("war.repeated", locale);
            throw new ConflictException(mensaje, String.valueOf(HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT);
        }
        Category category = CategoryMapper.INSTANCE.toEntity(categoryDto);
        Category entity = iCategoryRepository.save(category);
        return CategoryMapper.INSTANCE.toDto(entity);
    }

    @Override
    public CategoryDto edit(Integer id, CategoryDto categoryDto) {
        Locale locale = LocaleContextHolder.getLocale();
        String mensaje = iMessageService.getMensaje("infor.not_founds", locale);
        Category categoryOld = iCategoryRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(mensaje, String.valueOf(HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND));

        categoryOld.setNameCategory(categoryDto.getNombre());
        categoryOld.setDescriptionCategory(categoryDto.getDescripcion());
        Category entity = iCategoryRepository.save(categoryOld);

        return CategoryMapper.INSTANCE.toDto(entity);
    }

    @Override
    public Category getCategoryById(Integer idCategoria) {
        Locale locale = LocaleContextHolder.getLocale();
        String mensaje = iMessageService.getMensaje("infor.not_founds", locale);
        return iCategoryRepository.findById(idCategoria).orElseThrow(() -> new NotFoundException(mensaje, String.valueOf(HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND));
    }

    private boolean isExistsCategory(CategoryDto categoryDto) {
        return iCategoryRepository.getNumberMatchesByName(categoryDto.getNombre()) > 0 && iCategoryRepository.getNumberMatchesByDescription(categoryDto.getDescripcion()) > 0;
    }
}
