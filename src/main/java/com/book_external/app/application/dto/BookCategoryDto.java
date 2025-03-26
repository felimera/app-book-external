package com.book_external.app.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookCategoryDto {
    @Schema(description = "Id of the book and category relationship.", example = "1")
    private Integer id;
    @Schema(description = "Id of the book to be linked.", example = "1")
    @Min(value = 1, message = "The id must be greater than zero. ( n > 0)")
    private Integer idBookInter;
    @Schema(description = "Id of the category to be linked.", example = "1")
    @Min(value = 1, message = "The id must be greater than zero. ( n > 0)")
    private Integer idCategory;
}
