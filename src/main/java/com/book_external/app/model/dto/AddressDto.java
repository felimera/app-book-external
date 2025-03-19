package com.book_external.app.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressDto {
    private String calle;
    private String suite;
    private String ciudad;
    private String codigoPostal;
    private GeoDto geoDto;
}
