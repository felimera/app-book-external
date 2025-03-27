package com.book_external.app.application.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookCategoryNodeDto {
    private String name;
    private List<BookCategoryNodeDto> children;

    public BookCategoryNodeDto(String name) {
        this.name = name;
    }
}
