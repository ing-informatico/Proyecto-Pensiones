package com.FPV.pensiones.model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientePatchDTO {

    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @Size(min = 3, max = 50, message = "Los apellidos deben tener entre 3 y 50 caracteres")
    private String apellidos;

//    @Email(message = "El email no es válido")
//    private String email;

    @Size(min = 2, message = "La ciudad debe tener al menos 2 caracteres")
    private String ciudad;
}
