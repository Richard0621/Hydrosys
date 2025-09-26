package com.hydrosys.hydrosys.Repository;

import com.hydrosys.hydrosys.Model.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetallePedidoRepositorio extends JpaRepository<DetallePedido, Long> {
    public List<DetallePedido> findByPedidoId(Long pedidoId);
}
