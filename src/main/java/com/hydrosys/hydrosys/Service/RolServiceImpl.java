package com.hydrosys.hydrosys.Service;

import com.hydrosys.hydrosys.DTOs.RolDTO;
import com.hydrosys.hydrosys.Model.Rol;
import com.hydrosys.hydrosys.Repository.RolRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl {
    private final RolRepositorio rolRepositorio;

    public RolServiceImpl(RolRepositorio rolRepositorio) {
        this.rolRepositorio = rolRepositorio;
    }

    @Transactional
    public Rol crearRol(RolDTO dto) {
        if (rolRepositorio.existsByNombre(dto.getNombre())) {
            throw new RuntimeException("El rol ya existe");
        }

        Rol rol = new Rol();
        rol.setNombre(dto.getNombre());

        return rolRepositorio.save(rol);
    }

    public List<Rol> listarRoles() {
        return rolRepositorio.findAll();
    }

    public Rol obtenerPorId(Long id) {
        return rolRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    }

    @Transactional
    public Rol actualizarRol(Long id, RolDTO dto) {
        Rol rol = rolRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        if (rolRepositorio.existsByNombre(dto.getNombre())) {
            throw new RuntimeException("Ya existe un rol con ese nombre");
        }

        rol.setNombre(dto.getNombre());
        return rolRepositorio.save(rol);
    }

    @Transactional
    public void eliminarRol(Long id) {
        if (!rolRepositorio.existsById(id)) {
            throw new RuntimeException("Rol no encontrado");
        }
        rolRepositorio.deleteById(id);
    }
}
