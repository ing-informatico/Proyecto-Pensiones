package com.FPV.pensiones.controller;

import com.FPV.pensiones.model.DTO.DisponibilidadDTO;
import com.FPV.pensiones.model.Disponibilidad;
import com.FPV.pensiones.services.DisponibilidadService;
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
@Tag(name = "Disponibilidad", description = "Disponibilidad API endpoints")
public class DisponibilidadController {
    private final DisponibilidadService disponibilidadService;

    //Api Get all disponibilidades: http://localhost:8080/api/v1/disponibilidades
    @GetMapping("/disponibilidades")
    public ResponseEntity<List<Disponibilidad>> getAllDisponibilidades() {
        List<Disponibilidad> disponibilidades = disponibilidadService.getAllDisponibilidades();
        if(disponibilidades.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(disponibilidades, HttpStatus.OK);
    }

    //Api Get disponibilidad by id: http://localhost:8080/api/v1/disponibilidades/1
    @GetMapping("/disponibilidades/{id}")
    public ResponseEntity<Disponibilidad> getDisponibilidadById(@PathVariable Long id) {
        return new ResponseEntity<>(disponibilidadService.getDisponibilidadById(id), HttpStatus.FOUND);
    }

    //Api Add disponibilidad: http://localhost:8080/api/v1/disponibilidades
    @PostMapping("/disponibilidades")
    public ResponseEntity<Disponibilidad> saveDisponibilidad(@RequestBody @Valid DisponibilidadDTO disponibilidadDTO) {
        return new ResponseEntity<>(disponibilidadService.saveDisponibilidad(disponibilidadDTO), HttpStatus.CREATED);
    }

    //Api Update disponibilidad: http://localhost:8080/api/v1/disponibilidades/1
    @PutMapping("/disponibilidades/{id}")
    public ResponseEntity<Disponibilidad> updateDisponibilidad(@PathVariable Long id, @RequestBody @Valid DisponibilidadDTO disponibilidadDTO) {
        return new ResponseEntity<>(disponibilidadService.updateDisponibilidad(id, disponibilidadDTO), HttpStatus.OK);
    }

    //Api Delete disponibilidad: http://localhost:8080/api/v1/disponibilidades/1
    @DeleteMapping("/disponibilidades/{id}")
    public ResponseEntity<Disponibilidad> deleteDisponibilidad(@PathVariable Long id) {
        return new ResponseEntity<>(disponibilidadService.deleteDisponibilidad(id), HttpStatus.NO_CONTENT);
    }
}
