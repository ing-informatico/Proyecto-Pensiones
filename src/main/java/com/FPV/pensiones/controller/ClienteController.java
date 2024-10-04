package com.FPV.pensiones.controller;


import com.FPV.pensiones.model.Cliente;
import com.FPV.pensiones.model.DTO.ClienteDTO;
import com.FPV.pensiones.model.DTO.ClientePatchDTO;
import com.FPV.pensiones.services.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Cliente", description = "Cliente API endpoints")
public class ClienteController {
    private final ClienteService clienteService;

    //Api Get all clientes: http://localhost:8080/api/v1/clientes
    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.getAllClientes();
        if(clientes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    //Api Get cliente by id: http://localhost:8080/api/v1/clientes/1
    @GetMapping("/clientes/{id}")
    public ResponseEntity<ClienteDTO> getCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.getClienteById(id);
        return new ResponseEntity<>(ClienteDTO.fromCliente(cliente), HttpStatus.OK);
    }


    //Api Add cliente: http://localhost:8080/api/v1/clientes
    @PostMapping("/clientes")
    public ResponseEntity<Cliente> saveCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        return new ResponseEntity<>(clienteService.saveCliente(clienteDTO), HttpStatus.CREATED);
    }

    //Api Patch cliente: http://localhost:8080/api/v1/clientes/1
    // PATCH para actualizar parcialmente los datos del cliente
    @PatchMapping("/clientes/{id}")
    public ResponseEntity<Cliente> patchCliente(
            @PathVariable Long id,
            @RequestBody @Valid ClientePatchDTO clientePatchDTO) {

        Cliente updatedCliente = clienteService.patchCliente(id, clientePatchDTO);
        return new ResponseEntity<>(updatedCliente, HttpStatus.OK);
    }



    //Api Update cliente: http://localhost:8080/api/v1/clientes/1
    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody @Valid ClienteDTO clienteDTO) {
        return new ResponseEntity<>(clienteService.updateCliente(id, clienteDTO), HttpStatus.OK);
    }

    //Api Delete cliente: http://localhost:8080/api/v1/clientes/1
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable Long id) {
        return new ResponseEntity<>(clienteService.deleteCliente(id), HttpStatus.NO_CONTENT);
    }
}
