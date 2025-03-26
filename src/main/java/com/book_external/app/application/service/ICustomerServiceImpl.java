package com.book_external.app.application.service;

import com.book_external.app.application.dto.CustomerDto;
import com.book_external.app.domain.model.external.Customer;
import com.book_external.app.domain.service.ICustomerService;
import com.book_external.app.application.mapper.ICustomerMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ICustomerServiceImpl implements ICustomerService {

    private static final String URL_EXTERNAL = "https://jsonplaceholder.typicode.com/users";

    @Override
    public List<CustomerDto> getListAll() {
        RestTemplate restTemplate = new RestTemplate();
        Customer[] response = restTemplate.getForObject(URL_EXTERNAL, Customer[].class);
        return Arrays.stream(response).map(ICustomerMapper.INSTANCE::toDto).toList();
    }
}
