package com.FPV.pensiones.services;

import com.FPV.pensiones.exceptions.RequestException;
import com.FPV.pensiones.model.Cliente;
import com.FPV.pensiones.model.DTO.ClienteDTO;
import com.FPV.pensiones.model.DTO.ClientePatchDTO;
import com.FPV.pensiones.model.Transaccion;
import com.FPV.pensiones.repository.ClienteRepository;
import com.FPV.pensiones.repository.TransaccionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private final TransaccionRepository transaccionRepository;

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RequestException("Cliente no encontrado", HttpStatus.NOT_FOUND));
    }

    @Override
    public Cliente saveCliente(ClienteDTO clienteDTO) {
        // Aquí se asigna el saldo inicial de 500,000 COP automáticamente.
        Cliente cliente = Cliente.builder()
                .nombre(capitalizeFirstLetter(clienteDTO.getNombre()))
                .apellidos(capitalizeFirstLetter(clienteDTO.getApellidos()))
                .ciudad(capitalizeFirstLetter(clienteDTO.getCiudad()))
                .email(clienteDTO.getEmail())
                .saldo(500000.0) // Asigna saldo inicial de COP $500,000
                .build();
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente patchCliente(Long id, ClientePatchDTO clientePatchDTO) {
        Cliente existingCliente = getClienteById(id); // Verifica que el cliente existe

        // Solo actualizamos los campos que no son null
        if (clientePatchDTO.getNombre() != null) {
            existingCliente.setNombre(capitalizeFirstLetter(clientePatchDTO.getNombre()));
        }

        if (clientePatchDTO.getApellidos() != null) {
            existingCliente.setApellidos(capitalizeFirstLetter(clientePatchDTO.getApellidos()));
        }

//        if (clientePatchDTO.getEmail() != null) {
//            existingCliente.setEmail(clientePatchDTO.getEmail());
//        }

        if (clientePatchDTO.getCiudad() != null) {
            existingCliente.setCiudad(capitalizeFirstLetter(clientePatchDTO.getCiudad()));
        }

        // Guardamos los cambios en la base de datos
        return clienteRepository.save(existingCliente);
    }



    @Override
    public Cliente updateCliente(Long id, ClienteDTO clienteDTO) {
        Cliente existingCliente = getClienteById(id); // Obtiene el cliente existente

        // Actualiza solo los campos que deseas modificar
        existingCliente.setNombre(capitalizeFirstLetter(clienteDTO.getNombre()));
        existingCliente.setApellidos(capitalizeFirstLetter(clienteDTO.getApellidos()));
        existingCliente.setCiudad(capitalizeFirstLetter(clienteDTO.getCiudad()));
        existingCliente.setEmail(clienteDTO.getEmail()); // Actualiza el email si se permite
        // NO actualizamos el saldo, se mantiene igual

        // Guarda el cliente actualizado en la base de datos
        return clienteRepository.save(existingCliente);
    }




    @Override
    public Cliente deleteCliente(Long id) {
        Cliente cliente = getClienteById(id);

        // Verificar si el cliente tiene transacciones asociadas
        List<Transaccion> transacciones = transaccionRepository.findByCliente(cliente);
        if (!transacciones.isEmpty()) {
            throw new RequestException("No se puede eliminar el cliente, ya que tiene transacciones asociadas.", HttpStatus.CONFLICT);
        }

        // Ahora puedes eliminar el cliente
        clienteRepository.delete(cliente);
        return cliente;
    }



    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
