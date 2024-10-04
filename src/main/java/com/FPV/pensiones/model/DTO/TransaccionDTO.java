package com.FPV.pensiones.model.DTO;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionDTO {

    @NotNull(message = "Client ID is mandatory")
    private Long clienteId;

    @NotNull(message = "Product ID is mandatory")
    private Long productoId;

    @NotBlank(message = "Transaction type is mandatory")
    @Pattern(regexp = "^(APERTURA|CANCELACION)$", message = "Transaction type must be either 'APERTURA' or 'CANCELACION'")
    private String tipoTransaccion;

    @NotNull(message = "Amount is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    private Double monto;

    @NotNull(message = "Transaction date is mandatory")
    private LocalDateTime fechaTransaccion;
}
