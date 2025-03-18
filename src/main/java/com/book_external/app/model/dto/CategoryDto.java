package com.book_external.app.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CategoryDto {
    private Integer id;
    private String nombre;
    private String descripcion;

    private List<SubCategoryDto> subCategoryDtos;
}
