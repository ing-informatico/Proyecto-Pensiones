package com.FPV.pensiones.repository;

import com.FPV.pensiones.model.Cliente;
import com.FPV.pensiones.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
    void deleteByCliente(Cliente cliente);
    List<Transaccion> findByCliente(Cliente cliente);
}
