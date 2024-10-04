package com.FPV.pensiones.services;

import com.FPV.pensiones.model.DTO.InscripcionDTO;
import com.FPV.pensiones.model.Inscripcion;

import java.util.List;

public interface InscripcionService {
    List<Inscripcion> getAllInscripciones();
    Inscripcion getInscripcionById(Long id);
    Inscripcion saveInscripcion(InscripcionDTO inscripcionDTO);
    Inscripcion updateInscripcion(Long id, InscripcionDTO inscripcionDTO);
    Inscripcion deleteInscripcion(Long id);
}
