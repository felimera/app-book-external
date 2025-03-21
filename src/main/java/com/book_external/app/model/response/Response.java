package com.book_external.app.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Response {
    @Schema(description = "Meta object.", exampleClasses = Meta.class)
    private Meta meta;
    @Schema(description = "Object.", exampleClasses = Object.class)
    private Object data;
    @Schema(description = "Pagination object.", exampleClasses = Pagination.class)
    private Pagination pagination;
}
