package com.FPV.pensiones.services;

import com.FPV.pensiones.model.DTO.SucursalDTO;
import com.FPV.pensiones.model.Sucursal;

import java.util.List;

public interface SucursalService {
    List<Sucursal> getAllSucursales();
    Sucursal getSucursalById(Long id);
    Sucursal saveSucursal(SucursalDTO sucursalDTO);
    Sucursal updateSucursal(Long id, SucursalDTO sucursalDTO);
    Sucursal deleteSucursal(Long id);
}
