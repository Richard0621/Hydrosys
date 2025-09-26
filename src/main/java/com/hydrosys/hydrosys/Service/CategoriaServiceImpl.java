package com.hydrosys.hydrosys.Service;

import com.hydrosys.hydrosys.Model.Categoria;
import com.hydrosys.hydrosys.Repository.CategoriaRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaServiceImpl {

    private final CategoriaRepositorio categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepositorio categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    // Crear nueva categoría

    @Transactional
    public Categoria crearCategoria(String nombre) {
        if (categoriaRepository.existsByNombre(nombre)) {
            throw new RuntimeException("La categoría ya existe");
        }

        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setNombre(nombre);

        return categoriaRepository.save(nuevaCategoria);
    }

    // Obtener categoría por ID
    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    // Actualizar categoría
    @Transactional
    public Categoria actualizarCategoria(Long id, String nuevoNombre) {
        // Verificar si la categoría existe
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        if (categoriaRepository.existsByNombre(nuevoNombre)) {
            throw new RuntimeException("La categoría ya existe");
        }

        categoria.setNombre(nuevoNombre);
        return categoriaRepository.save(categoria);
    }

    // Eliminar categoría
    @Transactional
    public void eliminarCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoría no encontrada");
        }
        categoriaRepository.deleteById(id);
    }

    // Listar todas las categorías
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

}
