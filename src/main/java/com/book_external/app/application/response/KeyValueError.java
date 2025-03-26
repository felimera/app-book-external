package com.book_external.app.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class KeyValueError {
    @Schema(description = "Attribute name.", example = "id")
    private final String attributeName;
    @Schema(description = "Attribute value.", example = "1")
    private final String attributeValue;
}
