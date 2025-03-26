package com.book_external.app.domain.service;

import com.book_external.app.application.dto.CustomerDto;

import java.util.List;

public interface ICustomerService {
    List<CustomerDto> getListAll();
}
