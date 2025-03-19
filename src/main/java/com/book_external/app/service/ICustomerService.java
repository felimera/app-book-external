package com.book_external.app.service;

import com.book_external.app.model.dto.CustomerDto;

import java.util.List;

public interface ICustomerService {
    List<CustomerDto> getListAll();
}
