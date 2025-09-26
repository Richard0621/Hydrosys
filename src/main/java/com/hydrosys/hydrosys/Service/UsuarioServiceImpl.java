package com.hydrosys.hydrosys.Service;

import com.hydrosys.hydrosys.DTOs.RegistroUsuarioDTO;
import com.hydrosys.hydrosys.Model.Rol;
import com.hydrosys.hydrosys.Model.Usuario;
import com.hydrosys.hydrosys.Repository.RolRepositorio;
import com.hydrosys.hydrosys.Repository.UsuarioRepositorio;
import com.hydrosys.hydrosys.Security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl {

    private final UsuarioRepositorio usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolRepositorio rolRepositorio;

    public UsuarioServiceImpl(UsuarioRepositorio usuarioRepository, PasswordEncoder passwordEncoder, RolRepositorio rolRepositorio) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolRepositorio = rolRepositorio;
    }

    // Registro de nuevo usuario cliente
    @Transactional
    public Usuario registrarUsuario(RegistroUsuarioDTO registroDTO) {
        if (usuarioRepository.existsByCorreo(registroDTO.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        // Buscar el rol al que se asignará el usuario
        // Si el rol no existe, lanzar una excepción
        Rol rolCliente = rolRepositorio.findByNombre(registroDTO.getRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + registroDTO.getRol()));


        Usuario usuario = new Usuario();
        usuario.setNombre(registroDTO.getNombre());
        usuario.setApellido(registroDTO.getApellido());
        usuario.setCorreo(registroDTO.getCorreo());
        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
        usuario.setRol(rolCliente); // Asignar el objeto Rol completo

        return usuarioRepository.save(usuario);
    }


    // Obtener usuario por ID
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Obtener usuario por email
    public Optional<Usuario> obtenerPorEmail(String email) {
        return usuarioRepository.findByCorreo(email);
    }

    // Verificar si existe un usuario por email
    public boolean existsByCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    // Listar todos los usuarios (solo para administradores)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // Cargar usuario para Spring Security
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        return new UserDetailsImpl(usuario);
    }
}