package com.FPV.pensiones.controller;

import com.FPV.pensiones.model.DTO.VisitanDTO;
import com.FPV.pensiones.model.Visitan;
import com.FPV.pensiones.services.VisitanService;
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
@Tag(name = "Visitan", description = "Visitan API endpoints")
public class VisitanController {
    private final VisitanService visitanService;

    //Api Get all visitas: http://localhost:8080/api/v1/visitas
    @GetMapping("/visitas")
    public ResponseEntity<List<Visitan>> getAllVisitas() {
        List<Visitan> visitas = visitanService.getAllVisitas();
        if(visitas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(visitas, HttpStatus.OK);
    }

    //Api Get visita by id: http://localhost:8080/api/v1/visitas/1
    @GetMapping("/visitas/{id}")
    public ResponseEntity<Visitan> getVisitaById(@PathVariable Long id) {
        return new ResponseEntity<>(visitanService.getVisitaById(id), HttpStatus.FOUND);
    }

    //Api Add visita: http://localhost:8080/api/v1/visitas
    @PostMapping("/visitas")
    public ResponseEntity<Visitan> saveVisita(@RequestBody @Valid VisitanDTO visitanDTO) {
        return new ResponseEntity<>(visitanService.saveVisita(visitanDTO), HttpStatus.CREATED);
    }

    //Api Update visita: http://localhost:8080/api/v1/visitas/1
    @PutMapping("/visitas/{id}")
    public ResponseEntity<Visitan> updateVisita(@PathVariable Long id, @RequestBody @Valid VisitanDTO visitanDTO) {
        return new ResponseEntity<>(visitanService.updateVisita(id, visitanDTO), HttpStatus.OK);
    }

    // Api Delete visita: http://localhost:8080/api/v1/visitas/1
    @DeleteMapping("/visitas/{id}")
    public ResponseEntity<Visitan> deleteVisita(@PathVariable Long id) {
        return new ResponseEntity<>(visitanService.deleteVisita(id), HttpStatus.NO_CONTENT);
    }
}
