package com.hydrosys.hydrosys.Controller;

import com.hydrosys.hydrosys.DTOs.DetallePedidoDTO;
import com.hydrosys.hydrosys.Model.DetallePedido;
import com.hydrosys.hydrosys.Service.DetallePedidoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles")
public class DetallePedidoController {

    private final DetallePedidoServiceImpl detalleService;

    public DetallePedidoController(DetallePedidoServiceImpl detalleService) {
        this.detalleService = detalleService;
    }

    @PostMapping
    public ResponseEntity<DetallePedido> agregarDetalle(@Valid @RequestBody DetallePedidoDTO dto) {
        DetallePedido detalle = detalleService.agregarDetalle(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalle);
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<DetallePedido>> listarPorPedido(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(detalleService.listarDetallesPorPedido(pedidoId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Long id) {
        detalleService.eliminarDetalle(id);
        return ResponseEntity.noContent().build();
    }
}