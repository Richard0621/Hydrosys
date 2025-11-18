package com.hydrosys.hydrosys.Repository;

import com.hydrosys.hydrosys.Model.DetallePedido;
import com.hydrosys.hydrosys.DTOs.EntregaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

    @Query("""
        select new com.hydrosys.hydrosys.DTOs.EntregaDto(
            p.id,
            p.nombre,
            u.id,
            concat(u.nombre, ' ', u.apellido),
            d.precioUnitario,
            d.cantidadProducto,
            ped.estadoPedido,
            ped.fechaPedido
        )
        from DetallePedido d
        join d.pedido ped
        join ped.usuario u
        join d.producto p
        """)
    List<EntregaDto> findEntregasPublicas();
}
