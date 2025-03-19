package com.book_external.app.service.implementation;

import com.book_external.app.model.internal.BookInter;
import com.book_external.app.repository.IBookInterRepository;
import com.book_external.app.service.IBookInterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IBookInterServiceImpl implements IBookInterService {
    private IBookInterRepository iBookInterRepository;

    @Autowired
    public IBookInterServiceImpl(IBookInterRepository iBookInterRepository) {
        this.iBookInterRepository = iBookInterRepository;
    }

    @Override
    public BookInter getBookInterById(Integer id) {
        return iBookInterRepository.findById(id).orElseThrow();
    }
}
