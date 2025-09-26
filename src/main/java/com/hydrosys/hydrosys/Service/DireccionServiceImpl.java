package com.hydrosys.hydrosys.Service;

import com.hydrosys.hydrosys.DTOs.DireccionDTO;
import com.hydrosys.hydrosys.Model.Direccion;
import com.hydrosys.hydrosys.Model.Usuario;
import com.hydrosys.hydrosys.Repository.DireccionRepositorio;
import com.hydrosys.hydrosys.Repository.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DireccionServiceImpl {
    private final DireccionRepositorio direccionRepository;
    private final UsuarioRepositorio usuarioRepository;

    public DireccionServiceImpl(DireccionRepositorio direccionRepository, UsuarioRepositorio usuarioRepository) {
        this.direccionRepository = direccionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Direccion crearDireccion(DireccionDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Direccion direccion = new Direccion();
        direccion.setUsuario(usuario);
        direccion.setDepartamento(dto.getDepartamento());
        direccion.setCiudad(dto.getCiudad());
        direccion.setPais(dto.getPais());
        direccion.setDireccion(dto.getDireccion());

        return direccionRepository.save(direccion);
    }

    public List<Direccion> listarDirecciones() {
        return direccionRepository.findAll();
    }

    public Direccion obtenerDireccionPorId(Long id) {
        return direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada"));
    }

    @Transactional
    public Direccion actualizarDireccion(Long id, DireccionDTO dto) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada"));

        direccion.setDepartamento(dto.getDepartamento());
        direccion.setCiudad(dto.getCiudad());
        direccion.setPais(dto.getPais());
        direccion.setDireccion(dto.getDireccion());

        // Si deseas permitir cambiar de usuario (opcional)
        if (dto.getIdUsuario() != null && !dto.getIdUsuario().equals(direccion.getUsuario().getId())) {
            Usuario nuevoUsuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            direccion.setUsuario(nuevoUsuario);
        }

        return direccionRepository.save(direccion);
    }

    @Transactional
    public void eliminarDireccion(Long id) {
        if (!direccionRepository.existsById(id)) {
            throw new RuntimeException("Dirección no encontrada");
        }
        direccionRepository.deleteById(id);
    }
}
