package com.book_external.app.application.service;

import com.book_external.app.application.dto.SubCategoryDto;
import com.book_external.app.application.mapper.ISubCategoryMapper;
import com.book_external.app.insfraestructure.exception.ConflictException;
import com.book_external.app.insfraestructure.exception.NotFoundException;
import com.book_external.app.domain.model.internal.SubCategory;
import com.book_external.app.domain.repository.ISubCategoryRepository;
import com.book_external.app.domain.service.ICategoryService;
import com.book_external.app.domain.service.IMessageService;
import com.book_external.app.domain.service.ISubCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class ISubCategoryServiceImpl implements ISubCategoryService {

    private ISubCategoryRepository iSubCategoryRepository;
    private ICategoryService iCategoryService;
    private IMessageService iMessageService;

    @Autowired
    public ISubCategoryServiceImpl(ISubCategoryRepository iSubCategoryRepository, ICategoryService iCategoryService, IMessageService iMessageService) {
        this.iSubCategoryRepository = iSubCategoryRepository;
        this.iCategoryService = iCategoryService;
        this.iMessageService = iMessageService;
    }

    @Override
    public SubCategoryDto create(SubCategoryDto subCategoryDto, Integer idCategoria) {
        if (isExistsCategory(subCategoryDto)) {
            Locale locale = LocaleContextHolder.getLocale();
            String mensaje = iMessageService.getMensaje("war.repeated", locale);
            throw new ConflictException(mensaje, String.valueOf(HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT);
        }

        SubCategory subCategory = ISubCategoryMapper.INSTANCE.toEntity(subCategoryDto);
        subCategory.setCategory(iCategoryService.getCategoryById(idCategoria));
        return ISubCategoryMapper.INSTANCE.toDto(iSubCategoryRepository.save(subCategory));
    }

    @Override
    public SubCategoryDto edit(Integer id, SubCategoryDto subCategoryDto) {
        Locale locale = LocaleContextHolder.getLocale();
        String mensaje = iMessageService.getMensaje("war.repeated", locale);
        SubCategory subCategory = iSubCategoryRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(mensaje, String.valueOf(HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND));

        subCategory.setNameSubCategory(subCategoryDto.getNombre());
        subCategory.setDescriptionSubCategory(subCategoryDto.getDescripcion());

        return ISubCategoryMapper.INSTANCE.toDto(iSubCategoryRepository.save(subCategory));
    }

    @Override
    public List<SubCategoryDto> getListAll() {
        return iSubCategoryRepository
                .findAll()
                .stream()
                .map(ISubCategoryMapper.INSTANCE::toDto)
                .toList();
    }

    private boolean isExistsCategory(SubCategoryDto subCategoryDto) {
        return iSubCategoryRepository.getNumberMatchesByName(subCategoryDto.getNombre()) > 0 && iSubCategoryRepository.getNumberMatchesByDescription(subCategoryDto.getDescripcion()) > 0;
    }
}
