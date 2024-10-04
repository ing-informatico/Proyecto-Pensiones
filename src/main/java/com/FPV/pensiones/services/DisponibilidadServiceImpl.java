package com.FPV.pensiones.services;


import com.FPV.pensiones.exceptions.RequestException;
import com.FPV.pensiones.model.DTO.DisponibilidadDTO;
import com.FPV.pensiones.model.Disponibilidad;
import com.FPV.pensiones.model.Producto;
import com.FPV.pensiones.model.Sucursal;
import com.FPV.pensiones.repository.DisponibilidadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DisponibilidadServiceImpl implements DisponibilidadService {
    private final DisponibilidadRepository disponibilidadRepository;
    private final SucursalService sucursalService;
    private final ProductoService productoService;

    @Override
    public List<Disponibilidad> getAllDisponibilidades() {
        return disponibilidadRepository.findAll();
    }

    @Override
    public Disponibilidad getDisponibilidadById(Long id) {
        return disponibilidadRepository.findById(id)
                .orElseThrow(() -> new RequestException("Disponibilidad no encontrada", HttpStatus.NOT_FOUND));
    }

    @Override
    public Disponibilidad saveDisponibilidad(DisponibilidadDTO disponibilidadDTO) {
        Sucursal sucursal = sucursalService.getSucursalById(disponibilidadDTO.getIdSucursal());
        Producto producto = productoService.getProductoById(disponibilidadDTO.getIdProducto());

        Disponibilidad disponibilidad = Disponibilidad.builder()
                .sucursal(sucursal)
                .producto(producto)
                .build();
        return disponibilidadRepository.save(disponibilidad);
    }

    @Override
    public Disponibilidad updateDisponibilidad(Long id, DisponibilidadDTO disponibilidadDTO) {
        getDisponibilidadById(id); // Verifica que existe
        Sucursal sucursal = sucursalService.getSucursalById(disponibilidadDTO.getIdSucursal());
        Producto producto = productoService.getProductoById(disponibilidadDTO.getIdProducto());

        Disponibilidad updatedDisponibilidad = Disponibilidad.builder()
                .id(id)
                .sucursal(sucursal)
                .producto(producto)
                .build();
        return disponibilidadRepository.save(updatedDisponibilidad);
    }

    @Override
    public Disponibilidad deleteDisponibilidad(Long id) {
        Disponibilidad disponibilidad = getDisponibilidadById(id);
        disponibilidadRepository.delete(disponibilidad);
        return disponibilidad;
    }
}