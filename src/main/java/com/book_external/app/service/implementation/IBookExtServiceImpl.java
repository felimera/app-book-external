package com.book_external.app.service.implementation;

import com.book_external.app.model.dto.BookExtDto;
import com.book_external.app.model.external.BookExt;
import com.book_external.app.service.IBookExtService;
import com.book_external.app.service.mapper.BookExtMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class IBookExtServiceImpl implements IBookExtService {

    private static final String URL_EXTERNAL = "https://jsonplaceholder.typicode.com/todos";

    @Override
    public List<BookExtDto> getListAll() {
        RestTemplate restTemplate = new RestTemplate();
        BookExt[] response = restTemplate.getForObject(URL_EXTERNAL, BookExt[].class);
        return Arrays.stream(response).map(BookExtMapper.INSTANCE::toDto).toList();
    }

    @Override
    public Optional<BookExt> getBookExtById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        BookExt response = restTemplate.getForObject(URL_EXTERNAL.concat("/").concat(String.valueOf(id)), BookExt.class);
        return Optional.of(response);
    }
}
