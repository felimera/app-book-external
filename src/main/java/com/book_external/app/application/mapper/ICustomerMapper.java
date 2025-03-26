package com.book_external.app.application.mapper;

import com.book_external.app.application.dto.CustomerDto;
import com.book_external.app.domain.model.external.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ICustomerMapper {
    ICustomerMapper INSTANCE = Mappers.getMapper(ICustomerMapper.class);

    @Mapping(target = "nombre", source = "name")
    @Mapping(target = "nombreUsuario", source = "username")
    @Mapping(target = "correo", source = "email")
    @Mapping(target = "telefono", source = "phone")
    @Mapping(target = "sitioWeb", source = "website")
    @Mapping(target = "addressDto.calle", source = "address.street")
    @Mapping(target = "addressDto.suite", source = "address.suite")
    @Mapping(target = "addressDto.ciudad", source = "address.city")
    @Mapping(target = "addressDto.codigoPostal", source = "address.zipcode")
    @Mapping(target = "addressDto.geoDto.latitud", source = "address.geo.lat")
    @Mapping(target = "addressDto.geoDto.longitud", source = "address.geo.lng")
    @Mapping(target = "companyDto.nombre", source = "company.name")
    @Mapping(target = "companyDto.fraseClave", source = "company.catchPhrase")
    @Mapping(target = "companyDto.bs", source = "company.bs")
    CustomerDto toDto(Customer entity);
}
