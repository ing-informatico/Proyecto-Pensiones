package com.FPV.pensiones.services;

import com.FPV.pensiones.model.DTO.DisponibilidadDTO;
import com.FPV.pensiones.model.Disponibilidad;

import java.util.List;

public interface DisponibilidadService {
    List<Disponibilidad> getAllDisponibilidades();
    Disponibilidad getDisponibilidadById(Long id);
    Disponibilidad saveDisponibilidad(DisponibilidadDTO disponibilidadDTO);
    Disponibilidad updateDisponibilidad(Long id, DisponibilidadDTO disponibilidadDTO);
    Disponibilidad deleteDisponibilidad(Long id);
}
