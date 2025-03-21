package com.book_external.app.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class Meta {
    @Schema(description = "Indicates the status of the response.", examples = {"400", "404", "500"})
    private String status;
    @Schema(description = "The API version.", examples = {"0.0.1-SNAPSHOT", "1.0"})
    private String version;
    @Schema(description = "The timestamp of the response.", example = "2025-03-21T15:37:38.535112500Z")
    private String timestamp;

    public Meta toMetaBuilder(String status) {
        return Meta
                .builder()
                .status(status)
                .version("0.0.1-SNAPSHOT")
                .timestamp(Instant.now().toString())
                .build();
    }
}
