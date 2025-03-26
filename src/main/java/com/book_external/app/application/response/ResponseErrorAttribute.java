package com.book_external.app.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ResponseErrorAttribute {
    @Schema(description = "Meta object.", exampleClasses = Meta.class)
    private Meta meta;
    @Schema(description = "Error object.", exampleClasses = ErrorAttribute.class)
    private ErrorAttribute error;
}
