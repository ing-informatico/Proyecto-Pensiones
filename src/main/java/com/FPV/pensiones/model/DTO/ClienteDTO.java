package com.FPV.pensiones.model.DTO;

import com.FPV.pensiones.model.Cliente;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(min = 3, max = 50, message = "Los apellidos deben tener entre 3 y 50 caracteres")
    private String apellidos;

    @Email(message = "El email no es válido")
    private String email;

    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

    private Double saldo; // Incluye el saldo si es necesario

    // Método estático para mapear un Cliente a ClienteDTO
    public static ClienteDTO fromCliente(Cliente cliente) {
        return ClienteDTO.builder()
                .nombre(cliente.getNombre())
                .apellidos(cliente.getApellidos())
                .email(cliente.getEmail())
                .ciudad(cliente.getCiudad())
                .saldo(cliente.getSaldo()) // Asegúrate de incluir el saldo
                .build();
    }
}
