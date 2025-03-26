package com.book_external.app.application.dto;

import com.book_external.app.application.dto.validation.ICreationGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CategoryDto {
    @Schema(description = "Id of the category.", example = "1")
    private Integer id;
    @Schema(description = "Name of the category.", examples = {"Action","Fiction","Adventure"})
    @NotEmpty(message = "The category name cannot be empty.", groups = ICreationGroup.class)
    @NotNull(message = "The category name cannot be null.", groups = ICreationGroup.class)
    @Size(max = 100, message = "The category name has a maximum of 100 characters.")
    private String nombre;
    @Schema(description = "Descrption of the category.", example = "Proactive didactic contingency")
    @Size(max = 1000, message = "The category description has a maximum of 1000 characters.")
    private String descripcion;
    @Schema(description = "List of categories.", exampleClasses = SubCategoryDto.class)
    private List<SubCategoryDto> subCategoryDtos;
}
