package com.book_external.app.service.implementation;

import com.book_external.app.model.external.BookExt;
import com.book_external.app.model.internal.BookInter;
import com.book_external.app.repository.IBookInterRepository;
import com.book_external.app.service.IBookExtService;
import com.book_external.app.service.IBookInterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IBookInterServiceImpl implements IBookInterService {
    private IBookInterRepository iBookInterRepository;
    private IBookExtService iBookExtService;

    @Autowired
    public IBookInterServiceImpl(IBookInterRepository iBookInterRepository, IBookExtService iBookExtService) {
        this.iBookInterRepository = iBookInterRepository;
        this.iBookExtService = iBookExtService;
    }

    @Override
    public BookInter getBookInterById(Integer id) {
        Optional<BookInter> bookInterOptional = iBookInterRepository.findById(id);
        return bookInterOptional.orElseGet(() -> this.create(id));
    }

    @Override
    public BookInter create(Integer idBookExt) {
        BookExt bookExt = iBookExtService.getBookExtById(idBookExt).orElseThrow();
        BookInter bookInter = new BookInter();
        bookExt.setId(bookInter.getId());
        return iBookInterRepository.save(bookInter);
    }
}
