package com.book_external.app.service.implementation;

import com.book_external.app.model.dto.SubCategoryDto;
import com.book_external.app.model.internal.SubCategory;
import com.book_external.app.repository.ISubCategoryRepository;
import com.book_external.app.service.ICategoryService;
import com.book_external.app.service.ISubCategoryService;
import com.book_external.app.service.mapper.SubCategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ISubCategoryServiceImpl implements ISubCategoryService {

    private ISubCategoryRepository iSubCategoryRepository;
    private ICategoryService iCategoryService;

    @Autowired
    public ISubCategoryServiceImpl(ISubCategoryRepository iSubCategoryRepository, ICategoryService iCategoryService) {
        this.iSubCategoryRepository = iSubCategoryRepository;
        this.iCategoryService = iCategoryService;
    }

    @Override
    public SubCategoryDto create(SubCategoryDto subCategoryDto, Integer idCategoria) {
        if (isExistsCategory(subCategoryDto)) {
            SubCategory subCategory = SubCategoryMapper.INSTANCE.toEntity(subCategoryDto);
            subCategory.setCategory(iCategoryService.getCategoryById(idCategoria));
            return SubCategoryMapper.INSTANCE.toDto(iSubCategoryRepository.save(subCategory));
        }
        return subCategoryDto;
    }

    @Override
    public SubCategoryDto edit(Integer id, SubCategoryDto subCategoryDto) {
        SubCategory subCategory = iSubCategoryRepository.findById(id).orElseThrow();
        subCategory.setNameSubCategory(subCategoryDto.getNombre());
        subCategory.setDescriptionSubCategory(subCategoryDto.getDescripcion());

        return SubCategoryMapper.INSTANCE.toDto(iSubCategoryRepository.save(subCategory));
    }

    @Override
    public List<SubCategoryDto> getListAll() {
        return iSubCategoryRepository
                .findAll()
                .stream()
                .map(SubCategoryMapper.INSTANCE::toDto)
                .toList();
    }

    private boolean isExistsCategory(SubCategoryDto subCategoryDto) {
        return iSubCategoryRepository.getNumberMatchesByName(subCategoryDto.getNombre()) == 0 && iSubCategoryRepository.getNumberMatchesByDescription(subCategoryDto.getDescripcion()) == 0;
    }
}
