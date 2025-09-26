package com.hydrosys.hydrosys.Config;

import com.hydrosys.hydrosys.Model.Rol;
import com.hydrosys.hydrosys.Model.Usuario;
import com.hydrosys.hydrosys.Repository.RolRepositorio;
import com.hydrosys.hydrosys.Repository.UsuarioRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RolRepositorio rolRepository;
    private final UsuarioRepositorio usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RolRepositorio rolRepository,
                           UsuarioRepositorio usuarioRepository,
                           PasswordEncoder passwordEncoder) {
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // 1. Crear roles si no existen
        crearRolSiNoExiste("ADMIN");
        crearRolSiNoExiste("CLIENTE");

        // 2. Crear primer admin si no existe
        if (usuarioRepository.findByCorreo("admin@miapp.com").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("adm");
            admin.setApellido("Sistema");
            admin.setCorreo("admin@miapp.com");
            admin.setPassword(passwordEncoder.encode("adm0621"));
            admin.setRol(rolRepository.findByNombre("ADMIN").orElseThrow());
            usuarioRepository.save(admin);
        }
    }

    private void crearRolSiNoExiste(String nombreRol) {
        if (rolRepository.findByNombre(nombreRol).isEmpty()) {
            rolRepository.save(new Rol(nombreRol));
        }
    }
}
