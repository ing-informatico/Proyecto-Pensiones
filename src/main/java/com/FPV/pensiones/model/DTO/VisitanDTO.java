package com.FPV.pensiones.model.DTO;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitanDTO {
    @NotNull(message = "El ID de la sucursal es obligatorio")
    private Long idSucursal;

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long idCliente;

    @NotNull(message = "La fecha de visita es obligatoria")
    @Past(message = "La fecha de visita debe ser en el pasado")
    private Date fechaVisita;
}
