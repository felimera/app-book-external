package com.book_external.app.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookExtDto {
    private Integer id;
    private Integer idCliente;
    private String titulo;
    private Boolean isCompletado;
}
