package com.FPV.pensiones.controller;

import com.FPV.pensiones.model.DTO.InscripcionDTO;
import com.FPV.pensiones.model.Inscripcion;
import com.FPV.pensiones.services.InscripcionService;
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
@Tag(name = "Inscripcion", description = "Inscripcion API endpoints")
public class InscripcionController {
    private final InscripcionService inscripcionService;

    //Api Get all inscripciones: http://localhost:8080/api/v1/inscripciones
    @GetMapping("/inscripciones")
    public ResponseEntity<List<Inscripcion>> getAllInscripciones() {
        List<Inscripcion> inscripciones = inscripcionService.getAllInscripciones();
        if(inscripciones.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(inscripciones, HttpStatus.OK);
    }

    //Api Get inscripcion by id: http://localhost:8080/api/v1/inscripciones/1
    @GetMapping("/inscripciones/{id}")
    public ResponseEntity<Inscripcion> getInscripcionById(@PathVariable Long id) {
        return new ResponseEntity<>(inscripcionService.getInscripcionById(id), HttpStatus.FOUND);
    }

    //Api Add inscripcion: http://localhost:8080/api/v1/inscripciones
    @PostMapping("/inscripciones")
    public ResponseEntity<Inscripcion> saveInscripcion(@RequestBody @Valid InscripcionDTO inscripcionDTO) {
        return new ResponseEntity<>(inscripcionService.saveInscripcion(inscripcionDTO), HttpStatus.CREATED);
    }

    //Api Update inscripcion: http://localhost:8080/api/v1/inscripciones/1
    @PutMapping("/inscripciones/{id}")
    public ResponseEntity<Inscripcion> updateInscripcion(@PathVariable Long id, @RequestBody @Valid InscripcionDTO inscripcionDTO) {
        return new ResponseEntity<>(inscripcionService.updateInscripcion(id, inscripcionDTO), HttpStatus.OK);
    }

    //Api Delete inscripcion: http://localhost:8080/api/v1/inscripciones/1
    @DeleteMapping("/inscripciones/{id}")
    public ResponseEntity<Inscripcion> deleteInscripcion(@PathVariable Long id) {
        return new ResponseEntity<>(inscripcionService.deleteInscripcion(id), HttpStatus.NO_CONTENT);
    }
}
