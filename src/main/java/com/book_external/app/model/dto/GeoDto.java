package com.book_external.app.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GeoDto {
    @Schema(description = "Numerical value of the angular distance from the equatorial line.", example = "-37.3159")
    private String latitud;
    @Schema(description = "Numerical value of the angular distance based on the Greenwich meridian.", example = "81.1496")
    private String longitud;
}
