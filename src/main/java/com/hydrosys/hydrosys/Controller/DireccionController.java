package com.hydrosys.hydrosys.Controller;

import com.hydrosys.hydrosys.DTOs.DireccionDTO;
import com.hydrosys.hydrosys.Model.Direccion;
import com.hydrosys.hydrosys.Service.DireccionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {
    private final DireccionServiceImpl direccionService;

    public DireccionController(DireccionServiceImpl direccionService) {
        this.direccionService = direccionService;
    }

    @PostMapping
    public ResponseEntity<Direccion> crearDireccion(@Valid @RequestBody DireccionDTO dto) {
        Direccion direccion = direccionService.crearDireccion(dto);
        return new ResponseEntity<>(direccion, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Direccion>> listarDirecciones() {
        return ResponseEntity.ok(direccionService.listarDirecciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Direccion> obtenerDireccion(@PathVariable Long id) {
        return ResponseEntity.ok(direccionService.obtenerDireccionPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Direccion> actualizarDireccion(@PathVariable Long id, @Valid @RequestBody DireccionDTO dto) {
        Direccion direccionActualizada = direccionService.actualizarDireccion(id, dto);
        return ResponseEntity.ok(direccionActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDireccion(@PathVariable Long id) {
        direccionService.eliminarDireccion(id);
        return ResponseEntity.noContent().build();
    }
}
