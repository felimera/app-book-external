package com.book_external.app.application.service;

import com.book_external.app.application.dto.BookCategoryDto;
import com.book_external.app.application.mapper.IBookCategoryMapper;
import com.book_external.app.insfraestructure.exception.ConflictException;
import com.book_external.app.insfraestructure.exception.NotFoundException;
import com.book_external.app.domain.model.internal.BookCategory;
import com.book_external.app.domain.model.internal.BookInter;
import com.book_external.app.domain.model.internal.Category;
import com.book_external.app.domain.repository.IBookCategoryRepository;
import com.book_external.app.domain.service.IBookCategoryService;
import com.book_external.app.domain.service.IBookInterService;
import com.book_external.app.domain.service.ICategoryService;
import com.book_external.app.domain.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class IBookCategoryServiceImpl implements IBookCategoryService {

    private IBookCategoryRepository iBookCategoryRepository;
    private ICategoryService iCategoryService;
    private IBookInterService iBookInterService;
    private IMessageService iMessageService;

    @Autowired
    public IBookCategoryServiceImpl(IBookCategoryRepository iBookCategoryRepository, ICategoryService iCategoryService, IBookInterService iBookInterService, IMessageService iMessageService) {
        this.iBookCategoryRepository = iBookCategoryRepository;
        this.iCategoryService = iCategoryService;
        this.iBookInterService = iBookInterService;
        this.iMessageService = iMessageService;
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
}
