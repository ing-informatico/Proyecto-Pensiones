package com.FPV.pensiones.services;

import com.FPV.pensiones.exceptions.RequestException;
import com.FPV.pensiones.model.Cliente;
import com.FPV.pensiones.model.DTO.ClienteDTO;
import com.FPV.pensiones.model.DTO.InscripcionDTO;
import com.FPV.pensiones.model.Inscripcion;
import com.FPV.pensiones.model.Producto;
import com.FPV.pensiones.repository.InscripcionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl implements InscripcionService {
    private final InscripcionRepository inscripcionRepository;
    private final ClienteService clienteService;
    private final ProductoService productoService;
    private final TransaccionService transaccionService;
    private final NotificacionService notificacionService;

    @Override
    public List<Inscripcion> getAllInscripciones() {
        return inscripcionRepository.findAll();
    }

    @Override
    public Inscripcion getInscripcionById(Long id) {
        return inscripcionRepository.findById(id)
                .orElseThrow(() -> new RequestException("Inscripción no encontrada", HttpStatus.NOT_FOUND));
    }

    @Override
    public Inscripcion saveInscripcion(InscripcionDTO inscripcionDTO) {
        Cliente cliente = clienteService.getClienteById(inscripcionDTO.getIdCliente());
        Producto producto = productoService.getProductoById(inscripcionDTO.getIdProducto());

        if (cliente.getSaldo() < producto.getMontoMinimo()) {
            throw new RequestException("No tiene saldo disponible para vincularse al fondo " + producto.getNombre(), HttpStatus.BAD_REQUEST);
        }

        cliente.setSaldo(cliente.getSaldo() - producto.getMontoMinimo());
        clienteService.updateCliente(cliente.getId(), ClienteDTO.fromCliente(cliente));

        Inscripcion inscripcion = Inscripcion.builder()
                .cliente(cliente)
                .producto(producto)
                .build();

        Inscripcion savedInscripcion = inscripcionRepository.save(inscripcion);

        // Crear transacción
        transaccionService.crearTransaccion(cliente, producto, "APERTURA", producto.getMontoMinimo());

        // Enviar notificación
        notificacionService.enviarNotificacion(cliente.getEmail(), "Se ha inscrito al fondo " + producto.getNombre(), "EMAIL");

        return savedInscripcion;
    }

    @Override
    public Inscripcion updateInscripcion(Long id, InscripcionDTO inscripcionDTO) {
        getInscripcionById(id); // Verifica que existe
        Cliente cliente = clienteService.getClienteById(inscripcionDTO.getIdCliente());
        Producto producto = productoService.getProductoById(inscripcionDTO.getIdProducto());

        Inscripcion updatedInscripcion = Inscripcion.builder()
                .id(id)
                .cliente(cliente)
                .producto(producto)
                .build();
        return inscripcionRepository.save(updatedInscripcion);
    }

    @Override
    public Inscripcion deleteInscripcion(Long id) {
        Inscripcion inscripcion = getInscripcionById(id);
        Cliente cliente = inscripcion.getCliente();
        Producto producto = inscripcion.getProducto();

        cliente.setSaldo(cliente.getSaldo() + producto.getMontoMinimo());
        clienteService.updateCliente(cliente.getId(), ClienteDTO.fromCliente(cliente));

        inscripcionRepository.delete(inscripcion);

        // Crear transacción
        transaccionService.crearTransaccion(cliente, producto, "CANCELACION", producto.getMontoMinimo());

        // Enviar notificación
        notificacionService.enviarNotificacion(cliente.getEmail(), "Se ha cancelado su inscripción al fondo " + producto.getNombre(), "EMAIL");

        return inscripcion;
    }
}
