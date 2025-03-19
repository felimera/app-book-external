package com.book_external.app.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerDto {
    private Integer id;
    private String nombre;
    private String nombreUsuario;
    private String correo;
    private AddressDto addressDto;
    private String telefono;
    private String sitioWeb;
    private CompanyDto companyDto;
}
