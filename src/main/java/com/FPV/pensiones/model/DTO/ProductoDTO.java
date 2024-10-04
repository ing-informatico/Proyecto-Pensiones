package com.FPV.pensiones.model.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    @NotBlank(message = "Product name is mandatory")
    @Size(min = 3, max = 50, message = "Product name must be between 3 and 50 characters")
    private String nombre;

    @NotBlank(message = "Product type is mandatory")
    private String tipoProducto;

    @NotNull(message = "Minimum amount is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Minimum amount must be greater than 0")
    private Double montoMinimo;

    @NotBlank(message = "Category is mandatory")
    private String categoria;
}