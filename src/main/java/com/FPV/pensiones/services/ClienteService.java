package com.FPV.pensiones.services;

import com.FPV.pensiones.model.Cliente;
import com.FPV.pensiones.model.DTO.ClienteDTO;
import com.FPV.pensiones.model.DTO.ClientePatchDTO;

import java.util.List;
import java.util.Map;

public interface ClienteService {
    List<Cliente> getAllClientes();
    Cliente getClienteById(Long id);
    Cliente saveCliente(ClienteDTO clienteDTO);
    Cliente patchCliente(Long id, ClientePatchDTO clientePatchDTO);
    Cliente updateCliente(Long id, ClienteDTO clienteDTO);
    Cliente deleteCliente(Long id);
}
