package com.book_external.app.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerDto {
    @Schema(description = "Id of the customer.", example = "1")
    private Integer id;
    @Schema(description = "Name of the customer.", example = "Leanne Graham")
    private String nombre;
    @Schema(description = "Loggin of the customer.", example = "Bret")
    private String nombreUsuario;
    @Schema(description = "Email of the customer.", example = "Sincere@april.biz")
    private String correo;
    @Schema(description = "Object of the addresses.", exampleClasses = AddressDto.class)
    private AddressDto addressDto;
    @Schema(description = "Customer's telephone number.", example = "1-770-736-8031 x56442")
    private String telefono;
    @Schema(description = "Customer website.", example = "hildegard.org")
    private String sitioWeb;
    @Schema(description = "Object of the company.", exampleClasses = CompanyDto.class)
    private CompanyDto companyDto;
}
