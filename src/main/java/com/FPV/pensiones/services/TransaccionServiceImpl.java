package com.FPV.pensiones.services;

import com.FPV.pensiones.model.Cliente;
import com.FPV.pensiones.model.DTO.TransaccionDTO;
import com.FPV.pensiones.model.Producto;
import com.FPV.pensiones.model.Transaccion;
import com.FPV.pensiones.repository.TransaccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransaccionServiceImpl implements TransaccionService {

    private final TransaccionRepository transaccionRepository;

    @Override
    public void crearTransaccion(Cliente cliente, Producto producto, String tipoTransaccion, Double monto) {
        Transaccion transaccion = Transaccion.builder()
                .cliente(cliente)
                .producto(producto)
                .tipoTransaccion(tipoTransaccion)
                .monto(monto)
                .fechaTransaccion(LocalDateTime.now()) // Fecha actual de la transacción
                .build();

        transaccionRepository.save(transaccion); // Guardar la transacción en la base de datos
    }

    @Override
    public TransaccionDTO mapearATransaccionDTO(Cliente cliente, Producto producto, String tipoTransaccion, Double monto) {
        return TransaccionDTO.builder()
                .clienteId(cliente.getId())
                .productoId(producto.getId())
                .tipoTransaccion(tipoTransaccion)
                .monto(monto)
                .fechaTransaccion(LocalDateTime.now()) // Fecha actual
                .build();
    }
}
