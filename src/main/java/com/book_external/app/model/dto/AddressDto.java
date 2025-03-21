package com.book_external.app.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressDto {
    @Schema(description = "Description of the location of the public road.", example = "Kulas Light")
    private String calle;
    @Schema(description = "Description of the location of a luxury hotel room.", example = "Apt. 556")
    private String suite;
    @Schema(description = "Name of the town where you live.", example = "Gwenborough")
    private String ciudad;
    @Schema(description = "Code that identifies a geographic area.", example = "92998-3874")
    private String codigoPostal;
    @Schema(description = "Definition of geographic positioning.", exampleClasses = GeoDto.class)
    private GeoDto geoDto;
}
