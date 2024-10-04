package com.FPV.pensiones.controller;


import com.FPV.pensiones.model.DTO.SucursalDTO;
import com.FPV.pensiones.model.Sucursal;
import com.FPV.pensiones.services.SucursalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Sucursal", description = "Sucursal API endpoints")
public class SucursalController {
    private final SucursalService sucursalService;

    //Api Get all sucursales: http://localhost:8080/api/v1/sucursales
    @GetMapping("/sucursales")
    public ResponseEntity<List<Sucursal>> getAllSucursales() {
        List<Sucursal> sucursales = sucursalService.getAllSucursales();
        if(sucursales.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sucursales, HttpStatus.OK);
    }

    //Api Get sucursal by id: http://localhost:8080/api/v1/sucursales/1
    @GetMapping("/sucursales/{id}")
    public ResponseEntity<Sucursal> getSucursalById(@PathVariable Long id) {
        return new ResponseEntity<>(sucursalService.getSucursalById(id), HttpStatus.FOUND);
    }

    //Api Add sucursal: http://localhost:8080/api/v1/sucursales
    @PostMapping("/sucursales")
    public ResponseEntity<Sucursal> saveSucursal(@RequestBody @Valid SucursalDTO sucursalDTO) {
        return new ResponseEntity<>(sucursalService.saveSucursal(sucursalDTO), HttpStatus.CREATED);
    }

    //Api Update sucursal: http://localhost:8080/api/v1/sucursales/1
    @PutMapping("/sucursales/{id}")
    public ResponseEntity<Sucursal> updateSucursal(@PathVariable Long id, @RequestBody @Valid SucursalDTO sucursalDTO) {
        return new ResponseEntity<>(sucursalService.updateSucursal(id, sucursalDTO), HttpStatus.OK);
    }

    //Api Delete sucursal: http://localhost:8080/api/v1/sucursales/1
    @DeleteMapping("/sucursales/{id}")
    public ResponseEntity<Sucursal> deleteSucursal(@PathVariable Long id) {
        return new ResponseEntity<>(sucursalService.deleteSucursal(id), HttpStatus.NO_CONTENT);
    }
}
