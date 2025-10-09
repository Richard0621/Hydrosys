package com.hydrosys.hydrosys.Service;

import com.hydrosys.hydrosys.DTOs.ProductoDTO;
import com.hydrosys.hydrosys.Model.Categoria;
import com.hydrosys.hydrosys.Model.Lote;
import com.hydrosys.hydrosys.Model.Producto;
import com.hydrosys.hydrosys.Repository.CategoriaRepositorio;
import com.hydrosys.hydrosys.Repository.LoteRepositorio;
import com.hydrosys.hydrosys.Repository.ProductoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoServiceImpl {

    private final ProductoRepositorio productoRepository;
    private final CategoriaRepositorio categoriaRepository;
    private final LoteRepositorio loteRepository; // ← AGREGADO

    public ProductoServiceImpl(ProductoRepositorio productoRepository,
                               CategoriaRepositorio categoriaRepository,
                               LoteRepositorio loteRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.loteRepository = loteRepository;
    }

    //Agrega un nuevo producto
    @Transactional
    public Producto agregarProducto(ProductoDTO dto) {
        // Buscar la categoría por nombre
        Categoria categoria = categoriaRepository.findByNombre(dto.getCategoriaNombre())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + dto.getCategoriaNombre()));

        Lote lote = loteRepository.findById(dto.getIdLote())
                .orElseThrow(() -> new RuntimeException("Lote no encontrado con ID: " + dto.getIdLote()));

        // Crear y mapear la entidad
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setImagenUrl(dto.getImagenUrl());
        producto.setStock(dto.getStock());
        producto.setDescripcion(dto.getDescripcion());
        producto.setCategoria(categoria);
        producto.setLote(lote);

        return productoRepository.save(producto);
    }

    //Listar productos
    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }

    // Obtener producto por ID
    public Producto buscarPorId(Long id){
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    // Actualizar producto
    @Transactional
    public Producto actualizarProducto(Long id, ProductoDTO dto) {
        // Verificar si el producto existe
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        // Actualizar categoría si se proporciona
        if (dto.getCategoriaNombre() != null) {
            Categoria categoria = categoriaRepository.findByNombre(dto.getCategoriaNombre())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + dto.getCategoriaNombre()));
            producto.setCategoria(categoria);
        }

        // Actualizar lote
        if (dto.getIdLote() != null) {
            Lote lote = loteRepository.findById(dto.getIdLote())
                    .orElseThrow(() -> new RuntimeException("Lote no encontrado con ID: " + dto.getIdLote()));
            producto.setLote(lote);
        }

        // Actualizar otros campos
        if (dto.getNombre() != null) producto.setNombre(dto.getNombre());
        if (dto.getImagenUrl() != null) producto.setImagenUrl(dto.getImagenUrl());
        if (dto.getPrecio() != null) producto.setPrecio(dto.getPrecio());
        if (dto.getStock() != null) producto.setStock(dto.getStock());
        if (dto.getDescripcion() != null) producto.setDescripcion(dto.getDescripcion());

        return productoRepository.save(producto);
    }

    // Eliminar producto
    @Transactional
    public void eliminarProducto(Long id){
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }

    //obtener productos por lote
    public List<Producto> obtenerProductosPorLote(Long loteId) {
        return productoRepository.findByLoteId(loteId);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> obtenerProductosConStockBajo(Integer limite) {
        return productoRepository.findByStockLessThan(limite);
    }

    public List<Producto> obtenerProductosPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }
}