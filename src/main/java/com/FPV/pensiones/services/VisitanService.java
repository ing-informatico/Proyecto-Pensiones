package com.FPV.pensiones.services;

import com.FPV.pensiones.model.DTO.VisitanDTO;
import com.FPV.pensiones.model.Visitan;

import java.util.List;

public interface VisitanService {
    List<Visitan> getAllVisitas();
    Visitan getVisitaById(Long id);
    Visitan saveVisita(VisitanDTO visitanDTO);
    Visitan updateVisita(Long id, VisitanDTO visitanDTO);
    Visitan deleteVisita(Long id);
}
