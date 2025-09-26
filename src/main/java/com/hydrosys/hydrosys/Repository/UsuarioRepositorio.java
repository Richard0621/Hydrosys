package com.hydrosys.hydrosys.Repository;

import com.hydrosys.hydrosys.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    /**
     * Busca un usuario por su email.
     *
     * @param correo El email del usuario a buscar.
     * @return Un Optional que contiene el usuario si se encuentra, o vacío si no.
     */
    boolean existsByCorreo(String correo);
    Optional<Usuario> findByCorreo(String correo);
}
