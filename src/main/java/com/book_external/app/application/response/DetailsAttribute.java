package com.book_external.app.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DetailsAttribute {
    @Schema(description = "Response status label.", examples = {"Bad request", "Not found", "Internal server error"})
    public String statusCode;
    @Schema(description = "Response status code.", examples = {"400", "404", "500"})
    public int statusCodeValue;
    @Schema(description = "List of incorrect attributes in the request.", exampleClasses = KeyValueError.class)
    private List<KeyValueError> data;
}
