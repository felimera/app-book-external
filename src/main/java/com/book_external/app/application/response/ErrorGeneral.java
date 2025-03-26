package com.book_external.app.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorGeneral {
    @Schema(description = "Response status code.", examples = {"400", "404", "500"})
    private String code;
    @Schema(description = "Error message.", example = "No se encontraron el recurso.")
    private String message;
    @Schema(description = "Error details.", exampleClasses = DetailsGeneral.class)
    private DetailsGeneral details;
}
