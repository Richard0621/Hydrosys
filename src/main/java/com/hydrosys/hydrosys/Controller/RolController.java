package com.hydrosys.hydrosys.Controller;

import com.hydrosys.hydrosys.DTOs.RolDTO;
import com.hydrosys.hydrosys.Model.Rol;
import com.hydrosys.hydrosys.Service.RolServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolServiceImpl rolService;

    public RolController(RolServiceImpl rolService) {
        this.rolService = rolService;
    }

    @PostMapping
    public ResponseEntity<Rol> crearRol(@Valid @RequestBody RolDTO dto) {
        Rol rol = rolService.crearRol(dto);
        return new ResponseEntity<>(rol, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Rol>> listarRoles() {
        return ResponseEntity.ok(rolService.listarRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerRolPorId(@PathVariable Long id) {
        return ResponseEntity.ok(rolService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizarRol(@PathVariable Long id, @Valid @RequestBody RolDTO dto) {
        return ResponseEntity.ok(rolService.actualizarRol(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Long id) {
        rolService.eliminarRol(id);
        return ResponseEntity.noContent().build();
    }
}