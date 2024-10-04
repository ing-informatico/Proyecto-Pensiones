package com.FPV.pensiones.services;

import com.FPV.pensiones.exceptions.RequestException;
import com.FPV.pensiones.model.Cliente;
import com.FPV.pensiones.model.DTO.VisitanDTO;
import com.FPV.pensiones.model.Sucursal;
import com.FPV.pensiones.model.Visitan;
import com.FPV.pensiones.repository.VisitanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VisitanServiceImpl implements VisitanService {
    private final VisitanRepository visitanRepository;
    private final ClienteService clienteService;
    private final SucursalService sucursalService;

    @Override
    public List<Visitan> getAllVisitas() {
        return visitanRepository.findAll();
    }

    @Override
    public Visitan getVisitaById(Long id) {
        return visitanRepository.findById(id)
                .orElseThrow(() -> new RequestException("Visita no encontrada", HttpStatus.NOT_FOUND));
    }

    @Override
    public Visitan saveVisita(VisitanDTO visitanDTO) {
        Cliente cliente = clienteService.getClienteById(visitanDTO.getIdCliente());
        Sucursal sucursal = sucursalService.getSucursalById(visitanDTO.getIdSucursal());

        Visitan visita = Visitan.builder()
                .cliente(cliente)
                .sucursal(sucursal)
                .fechaVisita(visitanDTO.getFechaVisita())
                .build();
        return visitanRepository.save(visita);
    }

    @Override
    public Visitan updateVisita(Long id, VisitanDTO visitanDTO) {
        getVisitaById(id); // Verifica que existe
        Cliente cliente = clienteService.getClienteById(visitanDTO.getIdCliente());
        Sucursal sucursal = sucursalService.getSucursalById(visitanDTO.getIdSucursal());

        Visitan updatedVisita = Visitan.builder()
                .id(id)
                .cliente(cliente)
                .sucursal(sucursal)
                .fechaVisita(visitanDTO.getFechaVisita())
                .build();
        return visitanRepository.save(updatedVisita);
    }

    @Override
    public Visitan deleteVisita(Long id) {
        Visitan visita = getVisitaById(id);
        visitanRepository.delete(visita);
        return visita;
    }
}
