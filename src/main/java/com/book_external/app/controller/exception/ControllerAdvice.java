package com.book_external.app.controller.exception;

import com.book_external.app.model.component.TypesStatus;
import com.book_external.app.model.exception.BadRequestException;
import com.book_external.app.model.exception.ConflictException;
import com.book_external.app.model.exception.NotFoundException;
import com.book_external.app.model.response.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorGeneral> runtimeExceptionHandler(RuntimeException ex) {
        ErrorGeneral errorGeneral = ErrorGeneral.builder().code("500").message(ex.getMessage()).build();
        return new ResponseEntity<>(errorGeneral, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ResponseErrorGeneral> notFoundExceptionHandler(NotFoundException ex) {
        ResponseErrorGeneral response = new ResponseErrorGeneral();
        DetailsGeneral detailsGeneral = DetailsGeneral.builder().statusCode(ex.getHttpStatus().name()).statusCodeValue(ex.getHttpStatus().value()).build();
        ErrorGeneral errorGeneral = ErrorGeneral
                .builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .details(detailsGeneral)
                .build();
        response.setMeta(Meta.builder().build().toMetaBuilder(TypesStatus.ERROR.name()));
        response.setError(errorGeneral);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(value = ConflictException.class)
    public ResponseEntity<ResponseErrorGeneral> conflictExceptionHandler(ConflictException ex) {
        ResponseErrorGeneral response = new ResponseErrorGeneral();
        DetailsGeneral detailsGeneral = DetailsGeneral.builder().statusCode(ex.getHttpStatus().name()).statusCodeValue(ex.getHttpStatus().value()).build();
        ErrorGeneral errorGeneral = ErrorGeneral
                .builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .details(detailsGeneral)
                .build();

        response.setMeta(Meta.builder().build().toMetaBuilder(TypesStatus.ERROR.name()));
        response.setError(errorGeneral);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ResponseErrorAttribute> badRequestExceptionHandler(BadRequestException ex) {
        ResponseErrorAttribute response = new ResponseErrorAttribute();
        DetailsAttribute details = DetailsAttribute
                .builder()
                .statusCode(ex.getHttpStatus().name())
                .statusCodeValue(ex.getHttpStatus().value())
                .data(ex.getKeyValueErrors())
                .build();
        ErrorAttribute error = ErrorAttribute
                .builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .details(details)
                .build();

        response.setMeta(Meta.builder().build().toMetaBuilder(TypesStatus.ERROR.name()));
        response.setError(error);
        return ResponseEntity.ok(response);
    }
}
