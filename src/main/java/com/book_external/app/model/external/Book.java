package com.book_external.app.model.external;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Book {
    private Integer id;
    private Integer userId;
    private String title;
    private Boolean completed;
}
