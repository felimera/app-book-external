package com.book_external.app.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pagination {
    @Schema(description = "The total number of items available.", example = "200")
    private int totalItems;
    @Schema(description = "The total number of pages.", example = "20")
    private int totalPages;
    @Schema(description = "The current page.", example = "1")
    private int currentPage;
    @Schema(description = "The number of elements per page.", example = "10")
    private int pageSize;

    public Pagination toPaginationBuilder() {
        return Pagination
                .builder()
                .totalItems(200)
                .totalPages(20)
                .currentPage(1)
                .pageSize(10)
                .build();
    }
}
