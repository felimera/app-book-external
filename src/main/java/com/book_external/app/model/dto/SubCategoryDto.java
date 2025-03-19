package com.book_external.app.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubCategoryDto {
    private Integer id;
    private String nombre;
    private String descripcion;

    private Integer idCategory;
}
