package com.book_external.app.application.service;

import com.book_external.app.application.dto.BookCategoryDto;
import com.book_external.app.application.dto.BookCategoryNodeDto;
import com.book_external.app.application.mapper.IBookCategoryMapper;
import com.book_external.app.domain.model.internal.BookCategory;
import com.book_external.app.domain.model.internal.BookInter;
import com.book_external.app.domain.model.internal.Category;
import com.book_external.app.domain.model.internal.SubCategory;
import com.book_external.app.domain.repository.IBookCategoryCriteriaRepository;
import com.book_external.app.domain.repository.IBookCategoryRepository;
import com.book_external.app.domain.repository.ISubCategoryRepository;
import com.book_external.app.domain.service.IBookCategoryService;
import com.book_external.app.domain.service.IBookInterService;
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

import static java.util.stream.Collectors.groupingBy;

@Service
@Slf4j
public class IBookCategoryServiceImpl implements IBookCategoryService {

    private IBookCategoryRepository iBookCategoryRepository;
    private ICategoryService iCategoryService;
    private IBookInterService iBookInterService;
    private IMessageService iMessageService;
    private IBookCategoryCriteriaRepository iBookCategoryCriteriaRepository;
    private ISubCategoryRepository iSubCategoryRepository;

    @Autowired
    public IBookCategoryServiceImpl(IBookCategoryRepository iBookCategoryRepository, ICategoryService iCategoryService, IBookInterService iBookInterService, IMessageService iMessageService, IBookCategoryCriteriaRepository iBookCategoryCriteriaRepository, ISubCategoryRepository iSubCategoryRepository) {
        this.iBookCategoryRepository = iBookCategoryRepository;
        this.iCategoryService = iCategoryService;
        this.iBookInterService = iBookInterService;
        this.iMessageService = iMessageService;
        this.iBookCategoryCriteriaRepository = iBookCategoryCriteriaRepository;
        this.iSubCategoryRepository = iSubCategoryRepository;
    }

    @Override
    public List<BookCategoryDto> getListAll() {

        List<BookCategory> bookCategories = iBookCategoryRepository.findAll();
        if (bookCategories.isEmpty()) {
            Locale locale = LocaleContextHolder.getLocale();
            String mensaje = iMessageService.getMensaje("infor.not_founds", locale);
            throw new NotFoundException(mensaje, String.valueOf(HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
        }

        return bookCategories.stream().map(IBookCategoryMapper.INSTANCE::toDto).toList();
    }

    @Override
    public BookCategoryDto create(BookCategoryDto dto) {
        if (iBookCategoryRepository.getMatchOnBookAndCategoryIds(dto.getIdBookInter(), dto.getIdCategory()) != 0) {
            Locale locale = LocaleContextHolder.getLocale();
            String mensaje = iMessageService.getMensaje("war.repeated", locale);
            throw new ConflictException(mensaje, String.valueOf(HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT);
        }
        BookCategory bookCategory = new BookCategory();
        Category category = iCategoryService.getCategoryById(dto.getIdCategory());
        BookInter bookInter = iBookInterService.getBookInterById(dto.getIdBookInter());

        bookCategory.setCategory(category);
        bookCategory.setBookInter(bookInter);
        return IBookCategoryMapper.INSTANCE.toDto(iBookCategoryRepository.save(bookCategory));
    }

    @Override
    public List<BookCategoryNodeDto> getListNode(String nameBook, String nameCategory) {
        Map<String, String> map = new HashMap<>();
        map.put(Constant.BOOKCATEGORY_NAMEBOOK, nameBook);
        map.put(Constant.BOOKCATEGORY_NAMECATEGORY, nameCategory);
        List<BookCategory> bookCategories = iBookCategoryCriteriaRepository.getListNode(map);
        log.info("List BookCategory =>> " + bookCategories.toString() + "\n");
        return this.getNodeListDtos(bookCategories);
    }

    private List<BookCategoryNodeDto> getNodeListDtos(List<BookCategory> bookCategories) {
        List<BookCategoryNodeDto> dtoList = new ArrayList<>();
        if (bookCategories.isEmpty())
            return dtoList;

        Map<Integer, List<BookCategory>> mapBookCategory = bookCategories.stream().collect(groupingBy(bc -> bc.getBookInter().getId()));
        log.info("mapBookCategory =>> " + mapBookCategory.toString() + "\n");
        for (Map.Entry<Integer, List<BookCategory>> listEntry : mapBookCategory.entrySet()) {
            log.info("listEntry =>> " + listEntry.toString() + "\n");

            Optional<BookCategory> bookCategoryOptional = bookCategories.stream().filter(bookCategory -> Objects.equals(bookCategory.getBookInter().getId(), listEntry.getKey())).findFirst();
            if (bookCategoryOptional.isPresent()) {
                BookCategoryNodeDto nodeDto = new BookCategoryNodeDto();
                nodeDto.setName(bookCategoryOptional.get().getBookInter().getName());
                nodeDto.setChildren(this.getNodeCategory(listEntry));

                dtoList.add(nodeDto);
            }
        }
        return dtoList;
    }

    private List<BookCategoryNodeDto> getNodeCategory(Map.Entry<Integer, List<BookCategory>> listEntry) {
        List<BookCategoryNodeDto> children = new ArrayList<>();
        for (BookCategory bookCategory : listEntry.getValue()) {

            log.info("bookCategory =>> " + bookCategory.toString() + "\n");

            BookCategoryNodeDto dto = new BookCategoryNodeDto();
            dto.setName(bookCategory.getCategory().getNameCategory());
            dto.setChildren(this.getNodeSubCategory(bookCategory));
            children.add(dto);
        }
        return children;
    }

    private List<BookCategoryNodeDto> getNodeSubCategory(BookCategory bookCategory) {
        List<BookCategoryNodeDto> childrenSub = new ArrayList<>();
        List<SubCategory> subCategoryList = iSubCategoryRepository.getAllByCategoryId(bookCategory.getCategory().getId());
        for (SubCategory subCategory : subCategoryList) {
            childrenSub.add(new BookCategoryNodeDto(subCategory.getNameSubCategory()));
        }
        return childrenSub;
    }
}
