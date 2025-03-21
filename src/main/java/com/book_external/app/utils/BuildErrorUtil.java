package com.book_external.app.utils;

import com.book_external.app.model.response.KeyValueError;
import org.springframework.validation.BindingResult;

import java.util.List;

public class BuildErrorUtil {
    private BuildErrorUtil() {
        throw new IllegalStateException(BuildErrorUtil.class.toString());
    }

    public static List<KeyValueError> formatMessage(BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
                .stream()
                .map(err -> KeyValueError.builder().attributeName(err.getField()).attributeValue(err.getDefaultMessage()).build())
                .toList();
    }
}
