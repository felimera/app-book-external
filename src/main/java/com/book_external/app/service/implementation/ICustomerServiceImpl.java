package com.book_external.app.service.implementation;

import com.book_external.app.model.dto.CustomerDto;
import com.book_external.app.model.external.Customer;
import com.book_external.app.service.ICustomerService;
import com.book_external.app.service.mapper.CustomerMapper;
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
        return Arrays.stream(response).map(CustomerMapper.INSTANCE::toDto).toList();
    }
}
