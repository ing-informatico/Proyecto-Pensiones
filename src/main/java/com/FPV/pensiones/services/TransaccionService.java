package com.FPV.pensiones.services;

import com.FPV.pensiones.model.Cliente;
import com.FPV.pensiones.model.DTO.TransaccionDTO;
import com.FPV.pensiones.model.Producto;

public interface TransaccionService {
    void crearTransaccion(Cliente cliente, Producto producto, String tipoTransaccion, Double monto);
    TransaccionDTO mapearATransaccionDTO(Cliente cliente, Producto producto, String tipoTransaccion, Double monto);
}
