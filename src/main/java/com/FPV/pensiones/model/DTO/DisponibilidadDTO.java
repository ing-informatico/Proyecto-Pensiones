package com.FPV.pensiones.model.DTO;


import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisponibilidadDTO {
    @NotNull(message = "El ID de la sucursal es obligatorio")
    private Long idSucursal;

    @NotNull(message = "El ID del producto es obligatorio")
    private Long idProducto;
}
