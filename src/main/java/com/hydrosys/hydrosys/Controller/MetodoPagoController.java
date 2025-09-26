package com.hydrosys.hydrosys.Controller;
import com.hydrosys.hydrosys.DTOs.MetodoPagoDTO;
import com.hydrosys.hydrosys.Model.MetodoPago;
import com.hydrosys.hydrosys.Service.MetodoPagoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metodospago")
public class MetodoPagoController {

    private final MetodoPagoServiceImpl metodoPagoService;

    public MetodoPagoController(MetodoPagoServiceImpl metodoPagoService) {
        this.metodoPagoService = metodoPagoService;
    }

    @PostMapping
    public ResponseEntity<MetodoPago> crearMetodo(@Valid @RequestBody MetodoPagoDTO dto) {
        MetodoPago metodo = metodoPagoService.crearMetodoPago(dto.getNombrePago());
        return ResponseEntity.status(HttpStatus.CREATED).body(metodo);
    }

    @GetMapping
    public ResponseEntity<List<MetodoPago>> listar() {
        return ResponseEntity.ok(metodoPagoService.listarMetodosPago());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(metodoPagoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPago> actualizar(@PathVariable Long id,
                                                 @Valid @RequestBody MetodoPagoDTO dto) {
        return ResponseEntity.ok(metodoPagoService.actualizarMetodoPago(id, dto.getNombrePago()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        metodoPagoService.eliminarMetodoPago(id);
        return ResponseEntity.noContent().build();
    }
}
