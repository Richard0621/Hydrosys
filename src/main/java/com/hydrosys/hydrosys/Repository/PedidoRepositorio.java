package com.hydrosys.hydrosys.Repository;

import com.hydrosys.hydrosys.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {
}
