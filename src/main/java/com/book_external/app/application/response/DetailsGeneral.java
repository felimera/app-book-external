package com.book_external.app.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetailsGeneral {
    @Schema(description = "Response status label.", examples = {"Bad request", "Not found", "Internal server error"})
    public String statusCode;
    @Schema(description = "Response status code.", examples = {"400", "404", "500"})
    public int statusCodeValue;
}
