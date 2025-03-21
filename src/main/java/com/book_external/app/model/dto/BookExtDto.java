package com.book_external.app.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookExtDto {
    @Schema(description = "Id of the external book.", example = "1")
    private Integer id;
    @Schema(description = "Id of the related client.", example = "1")
    private Integer idCliente;
    @Schema(description = "Name of the book title.", example = "delectus aut autem")
    private String titulo;
    @Schema(description = "Book completed or not completed.", examples = {"true", "false"})
    private Boolean isCompletado;
}
