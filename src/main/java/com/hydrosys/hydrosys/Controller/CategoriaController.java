package com.hydrosys.hydrosys.Controller;

import com.hydrosys.hydrosys.DTOs.CategoriaDTO;
import com.hydrosys.hydrosys.Model.Categoria;
import com.hydrosys.hydrosys.Service.CategoriaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaServiceImpl categoriaService;

    public CategoriaController(CategoriaServiceImpl categoriaService) {
        this.categoriaService = categoriaService;
    }

    //Crear nueva categoría
    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@Valid @RequestBody CategoriaDTO dto){
        Categoria categoria = categoriaService.crearCategoria(dto.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    // Obtener categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
        return ResponseEntity.ok(categoria);
    }

    // Actualizar categoría
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaDTO dto) {
        Categoria categoriaActualizada = categoriaService.actualizarCategoria(id, dto.getNombre());
        return ResponseEntity.ok(categoriaActualizada);
    }

    // Eliminar categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    // Listar todas las categorías
    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> categorias = categoriaService.listarCategorias();
        return ResponseEntity.ok(categorias);
    }


}
