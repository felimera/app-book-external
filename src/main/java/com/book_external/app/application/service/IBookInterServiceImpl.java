package com.book_external.app.application.service;

import com.book_external.app.domain.model.external.BookExt;
import com.book_external.app.domain.model.internal.BookInter;
import com.book_external.app.domain.repository.IBookInterRepository;
import com.book_external.app.domain.service.IBookExtService;
import com.book_external.app.domain.service.IBookInterService;
import com.book_external.app.domain.service.IMessageService;
import com.book_external.app.insfraestructure.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
@Slf4j
public class IBookInterServiceImpl implements IBookInterService {
    private IBookInterRepository iBookInterRepository;
    private IBookExtService iBookExtService;
    private IMessageService iMessageService;

    @Autowired
    public IBookInterServiceImpl(IBookInterRepository iBookInterRepository, IBookExtService iBookExtService, IMessageService iMessageService) {
        this.iBookInterRepository = iBookInterRepository;
        this.iBookExtService = iBookExtService;
        this.iMessageService = iMessageService;
    }

    @Override
    public BookInter getBookInterById(Integer id) {
        Optional<BookInter> bookInterOptional = iBookInterRepository.findById(id);
        return bookInterOptional.orElseGet(() -> this.create(id));
    }

    @Override
    public BookInter create(Integer idBookExt) {

        Locale locale = LocaleContextHolder.getLocale();
        String mensaje = iMessageService.getMensaje("infor.not_founds", locale);
        BookExt bookExt = iBookExtService.getBookExtById(idBookExt)
                .orElseThrow(() -> new NotFoundException(mensaje, String.valueOf(HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND));

        BookInter bookInter = new BookInter();
        bookInter.setId(bookExt.getId());
        bookInter.setName(bookExt.getTitle());
        log.info("BookInter =>> "+bookInter);
        return iBookInterRepository.save(bookInter);
    }
}
