package com.hydrosys.hydrosys.Security;

import com.hydrosys.hydrosys.Model.Usuario;
import com.hydrosys.hydrosys.Repository.UsuarioRepositorio;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UsuarioRepositorio usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepositorio usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Carga un usuario por su email.
     *
     * @param email El email del usuario a buscar.
     * @return UserDetails que contiene la información del usuario.
     * @throws UsernameNotFoundException Si no se encuentra el usuario con el email proporcionado.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));
        return new UserDetailsImpl(usuario);
    }
}
