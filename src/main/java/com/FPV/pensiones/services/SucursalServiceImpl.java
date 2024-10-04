package com.FPV.pensiones.services;

import com.FPV.pensiones.exceptions.RequestException;
import com.FPV.pensiones.model.DTO.SucursalDTO;
import com.FPV.pensiones.model.Sucursal;
import com.FPV.pensiones.repository.SucursalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SucursalServiceImpl implements SucursalService {
    private final SucursalRepository sucursalRepository;

    @Override
    public List<Sucursal> getAllSucursales() {
        return sucursalRepository.findAll();
    }

    @Override
    public Sucursal getSucursalById(Long id) {
        return sucursalRepository.findById(id)
                .orElseThrow(() -> new RequestException("Sucursal no encontrada", HttpStatus.NOT_FOUND));
    }

    @Override
    public Sucursal saveSucursal(SucursalDTO sucursalDTO) {
        Sucursal sucursal = Sucursal.builder()
                .nombre(capitalizeFirstLetter(sucursalDTO.getNombre()))
                .ciudad(capitalizeFirstLetter(sucursalDTO.getCiudad()))
                .build();
        return sucursalRepository.save(sucursal);
    }

    @Override
    public Sucursal updateSucursal(Long id, SucursalDTO sucursalDTO) {
        Sucursal existingSucursal = getSucursalById(id);
        Sucursal updatedSucursal = Sucursal.builder()
                .id(existingSucursal.getId())
                .nombre(capitalizeFirstLetter(sucursalDTO.getNombre()))
                .ciudad(capitalizeFirstLetter(sucursalDTO.getCiudad()))
                .build();
        return sucursalRepository.save(updatedSucursal);
    }

    @Override
    public Sucursal deleteSucursal(Long id) {
        Sucursal sucursal = getSucursalById(id);
        sucursalRepository.delete(sucursal);
        return sucursal;
    }

    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
