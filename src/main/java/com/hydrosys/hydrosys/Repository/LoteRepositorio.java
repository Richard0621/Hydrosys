package com.hydrosys.hydrosys.Repository;

import com.hydrosys.hydrosys.Model.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface LoteRepositorio extends JpaRepository<Lote, Long> {
    // Buscar por proveedor
    List<Lote> findByProveedor(String proveedor);

    // Buscar lotes por fecha de vencimiento
    List<Lote> findByFechaVencimiento(Date fechaVencimiento);

    // Buscar lotes próximos a vencer
    @Query("SELECT l FROM Lote l WHERE l.fechaVencimiento <= :fecha")
    List<Lote> findLotesProximosAVencer(Date fecha);

}
