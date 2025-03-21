package com.book_external.app.model.exception;

import com.book_external.app.model.response.KeyValueError;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class BadRequestException extends RuntimeException {
    private final String code;
    private final String message;
    private final List<KeyValueError> keyValueErrors;
    private final HttpStatus httpStatus;

    public BadRequestException(String code, String message, List<KeyValueError> keyValueErrors, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.keyValueErrors = keyValueErrors;
        this.httpStatus = httpStatus;
    }
}
