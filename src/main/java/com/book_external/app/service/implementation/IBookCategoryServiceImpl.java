package com.book_external.app.service.implementation;

import com.book_external.app.model.dto.BookCategoryDto;
import com.book_external.app.model.internal.BookCategory;
import com.book_external.app.model.internal.BookInter;
import com.book_external.app.model.internal.Category;
import com.book_external.app.repository.IBookCategoryRepository;
import com.book_external.app.service.IBookCategoryService;
import com.book_external.app.service.IBookInterService;
import com.book_external.app.service.ICategoryService;
import com.book_external.app.service.mapper.BookCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IBookCategoryServiceImpl implements IBookCategoryService {

    private IBookCategoryRepository iBookCategoryRepository;
    private ICategoryService iCategoryService;
    private IBookInterService iBookInterService;

    @Autowired
    public IBookCategoryServiceImpl(IBookCategoryRepository iBookCategoryRepository, ICategoryService iCategoryService, IBookInterService iBookInterService) {
        this.iBookCategoryRepository = iBookCategoryRepository;
        this.iCategoryService = iCategoryService;
        this.iBookInterService = iBookInterService;
    }

    @Override
    public BookCategoryDto create(BookCategoryDto dto) {
        BookCategory bookCategory = new BookCategory();
        Category category = iCategoryService.getCategoryById(dto.getIdCategory());
        BookInter bookInter = iBookInterService.getBookInterById(dto.getIdBookInter());

        bookCategory.setCategory(category);
        bookCategory.setBookInter(bookInter);
        return BookCategoryMapper.INSTANCE.toDto(iBookCategoryRepository.save(bookCategory));
    }
}
