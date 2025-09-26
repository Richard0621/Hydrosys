package com.hydrosys.hydrosys.Repository;

import com.hydrosys.hydrosys.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
    // Metodo para obtener productos por lote
    List<Producto> findByLoteId(Long loteId);

    // Metodo para obtener productos por categoría
    List<Producto> findByCategoriaId(Long categoriaId);

    // Metodo para obtener productos por nombre (búsqueda)
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    // Metodo para obtener productos con stock bajo
    List<Producto> findByStockLessThan(Integer stock);
}
