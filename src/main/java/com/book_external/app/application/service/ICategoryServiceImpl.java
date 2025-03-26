package com.book_external.app.application.service;

import com.book_external.app.application.dto.CategoryDto;
import com.book_external.app.application.dto.SubCategoryDto;
import com.book_external.app.application.mapper.ICategoryMapper;
import com.book_external.app.application.mapper.ISubCategoryMapper;
import com.book_external.app.domain.model.internal.Category;
import com.book_external.app.domain.model.internal.SubCategory;
import com.book_external.app.domain.repository.ICategoryCriteriaRepository;
import com.book_external.app.domain.repository.ICategoryRepository;
import com.book_external.app.domain.repository.ISubCategoryCriteriaRepository;
import com.book_external.app.domain.repository.ISubCategoryRepository;
import com.book_external.app.domain.service.ICategoryService;
import com.book_external.app.domain.service.IMessageService;
import com.book_external.app.insfraestructure.exception.ConflictException;
import com.book_external.app.insfraestructure.exception.NotFoundException;
import com.book_external.app.insfraestructure.utils.Constant;
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
    private ISubCategoryRepository iSubCategoryRepository;
    private IMessageService iMessageService;

    @Autowired
    public ICategoryServiceImpl(ICategoryRepository iCategoryRepository, ICategoryCriteriaRepository iCategoryCriteriaRepository, ISubCategoryCriteriaRepository iSubCategoryCriteriaRepository, ISubCategoryRepository iSubCategoryRepository, IMessageService iMessageService) {
        this.iCategoryRepository = iCategoryRepository;
        this.iCategoryCriteriaRepository = iCategoryCriteriaRepository;
        this.iSubCategoryCriteriaRepository = iSubCategoryCriteriaRepository;
        this.iSubCategoryRepository = iSubCategoryRepository;
        this.iMessageService = iMessageService;
    }

    @Override
    public List<CategoryDto> getCategoryListByMultipleParameter(CategoryDto dto) {
        Map<String, String> map = new HashMap<>();
        map.put(Constant.CATEGORY_NAME, dto.getNombre());
        map.put(Constant.CATEGORY_DESCRIPTION, dto.getDescripcion());
        log.info("map =>>" + map);

        List<SubCategory> subCategoryList = iSubCategoryCriteriaRepository.getConsultSubCategoryForVariousParameters(map);
        return this.buildDtoList(subCategoryList);
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
                .map(ICategoryMapper.INSTANCE::toDto)
                .toList();
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        if (isExistsCategory(categoryDto)) {
            Locale locale = LocaleContextHolder.getLocale();
            String mensaje = iMessageService.getMensaje("war.repeated", locale);
            throw new ConflictException(mensaje, String.valueOf(HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT);
        }
        Category category = ICategoryMapper.INSTANCE.toEntity(categoryDto);
        Category entity = iCategoryRepository.save(category);
        return ICategoryMapper.INSTANCE.toDto(entity);
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

        return ICategoryMapper.INSTANCE.toDto(entity);
    }

    @Override
    public Category getCategoryById(Integer idCategoria) {
        Locale locale = LocaleContextHolder.getLocale();
        String mensaje = iMessageService.getMensaje("infor.not_founds", locale);
        return iCategoryRepository.findById(idCategoria).orElseThrow(() -> new NotFoundException(mensaje, String.valueOf(HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND));
    }

    @Override
    public List<CategoryDto> getAllList() {
        List<SubCategory> subCategoryList = iSubCategoryRepository.findAll();
        return this.buildDtoList(subCategoryList);
    }

    private boolean isExistsCategory(CategoryDto categoryDto) {
        return iCategoryRepository.getNumberMatchesByName(categoryDto.getNombre()) > 0 && iCategoryRepository.getNumberMatchesByDescription(categoryDto.getDescripcion()) > 0;
    }

    private List<CategoryDto> buildDtoList(List<SubCategory> subCategoryList) {

        List<CategoryDto> categoryDtos = new ArrayList<>();

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
                CategoryDto categoryDto = ICategoryMapper.INSTANCE.toDto(categoryMainOptional.get());

                List<SubCategoryDto> dtoList = subCategoryEntry
                        .getValue()
                        .stream()
                        .filter(subCategory -> Objects.equals(subCategory.getCategory().getId(), categoryMainOptional.get().getId()))
                        .map(ISubCategoryMapper.INSTANCE::toDto)
                        .toList();

                categoryDto.setSubCategoryDtos(dtoList);

                categoryDtos.add(categoryDto);
            }
        }
        log.info("Result DtoGroup =>> " + categoryDtos);

        return categoryDtos;
    }
}
