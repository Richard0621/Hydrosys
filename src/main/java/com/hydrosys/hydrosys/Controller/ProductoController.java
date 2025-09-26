package com.hydrosys.hydrosys.Controller;

import com.hydrosys.hydrosys.DTOs.ProductoDTO;
import com.hydrosys.hydrosys.Model.Producto;
import com.hydrosys.hydrosys.Service.ProductoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoServiceImpl productoService;

    public ProductoController(ProductoServiceImpl productoService) {
        this.productoService = productoService;
    }

    // Crear producto
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody ProductoDTO dto) {
        try {
            Producto nuevo = productoService.agregarProducto(dto);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodos() {
        List<Producto> productos = productoService.listarProductos();
        return ResponseEntity.ok(productos);
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        try {
            Producto producto = productoService.buscarPorId(id);
            return ResponseEntity.ok(producto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoDTO dto) {
        try {
            Producto actualizado = productoService.actualizarProducto(id, dto);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        try {
            productoService.eliminarProducto(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener productos por lote
    @GetMapping("/lote/{loteId}")
    public ResponseEntity<List<Producto>> obtenerProductosPorLote(@PathVariable Long loteId) {
        try {
            List<Producto> productos = productoService.obtenerProductosPorLote(loteId);
            return ResponseEntity.ok(productos);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar productos por nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarPorNombre(@RequestParam String nombre) {
        List<Producto> productos = productoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(productos);
    }

    // Obtener productos con stock bajo
    @GetMapping("/stock-bajo")
    public ResponseEntity<List<Producto>> obtenerProductosStockBajo(@RequestParam(defaultValue = "10") Integer limite) {
        List<Producto> productos = productoService.obtenerProductosConStockBajo(limite);
        return ResponseEntity.ok(productos);
    }

    // Obtener productos por categoría
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Producto>> obtenerProductosPorCategoria(@PathVariable Long categoriaId) {
        List<Producto> productos = productoService.obtenerProductosPorCategoria(categoriaId);
        return ResponseEntity.ok(productos);
    }
}