package com.hydrosys.hydrosys.Controller;

import com.hydrosys.hydrosys.DTOs.DetalleCarritoDTO;
import com.hydrosys.hydrosys.Model.Carrito;
import com.hydrosys.hydrosys.Service.DetalleCarritoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
public class DetalleCarritoController {

    private final DetalleCarritoServiceImpl carritoService;

    public DetalleCarritoController(DetalleCarritoServiceImpl carritoService) {
        this.carritoService = carritoService;
    }

    @PostMapping
    public ResponseEntity<Carrito> agregar(@Valid @RequestBody DetalleCarritoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carritoService.agregarDetalleCarrito(dto));
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<Carrito>> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(carritoService.obtenerCarritoPorUsuario(usuarioId));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarItem(@PathVariable Long id) {
        carritoService.eliminarItem(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/vaciar/{usuarioId}")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Long usuarioId) {
        carritoService.vaciarCarrito(usuarioId);
        return ResponseEntity.noContent().build();
    }
}
