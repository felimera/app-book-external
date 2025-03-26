package com.book_external.app.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CompanyDto {
    @Schema(description = "Name of the company.", example = "Deckow-Crist")
    private String nombre;
    @Schema(description = "Relevant words and codes.", example = "Proactive didactic contingency")
    private String fraseClave;
    @Schema(description = "Value of bs.", example = "synergize scalable supply-chains")
    private String bs;
}
