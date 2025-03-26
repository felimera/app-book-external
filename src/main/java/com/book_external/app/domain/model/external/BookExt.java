package com.book_external.app.domain.model.external;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BookExt {
    private Integer id;
    private Integer userId;
    private String title;
    private Boolean completed;
}
