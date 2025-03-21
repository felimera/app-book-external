package com.book_external.app.model.dto;

import com.book_external.app.model.dto.validation.ICreationGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubCategoryDto {
    @Schema(description = "Id of the subcategory.", example = "1")
    private Integer id;
    @Schema(description = "Name of the subcategory.", example = "Clementine Bauch")
    @NotEmpty(message = "The subcategory name cannot be empty.", groups = ICreationGroup.class)
    @NotNull(message = "The subcategory name cannot be null.", groups = ICreationGroup.class)
    @Size(max = 100, message = "The subcategory name has a maximum of 100 characters.")
    private String nombre;
    @Schema(description = "Description of the subcategory.", example = "Proactive didactic contingency")
    @Size(max = 1000, message = "The subcategory description has a maximum of 1000 characters.")
    private String descripcion;
    @Schema(description = "Id of the related category.", example = "1")
    private Integer idCategory;
}
