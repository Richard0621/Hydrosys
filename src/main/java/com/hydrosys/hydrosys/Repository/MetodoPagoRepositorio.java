package com.hydrosys.hydrosys.Repository;

import com.hydrosys.hydrosys.Model.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetodoPagoRepositorio extends JpaRepository<MetodoPago, Long> {
    boolean existsByNombrePago(String nombre);
}
