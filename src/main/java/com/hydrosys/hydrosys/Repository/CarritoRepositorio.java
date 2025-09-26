package com.hydrosys.hydrosys.Repository;

import com.hydrosys.hydrosys.Model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarritoRepositorio extends JpaRepository<Carrito, Long> {
    public List<Carrito> findByUsuarioId(Long usuarioId);
    void deleteByUsuarioId(Long usuarioId);
}
