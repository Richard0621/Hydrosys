package com.hydrosys.hydrosys.Security;

import com.hydrosys.hydrosys.Model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    /*
        * Clase que implementa UserDetails para Spring Security, la cual
        * representa los detalles del usuario autenticado.
     */

    private final Usuario usuario;
    //Inyección de usuario por constructor
    public UserDetailsImpl(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Devuelve las autoridades del usuario, que en este caso son los roles.
     * Spring espera que los roles tengan el prefijo "ROLE_".
     *
     * @return Colección de GrantedAuthority con el rol del usuario.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Spring espera roles con "ROLE_" al inicio
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre()));
    }

    /**
     * Devuelve la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    /**
     * Devuelve el nombre de usuario del usuario.
     * En este caso, se usa el email como nombre de usuario.
     *
     * @return El email del usuario.
     */
    @Override
    public String getUsername() {
        return usuario.getCorreo();
    }

    /**
     * Devuelve si la cuenta del usuario ha expirado.
     * En este caso, siempre devuelve true.
     *
     * @return true si la cuenta no ha expirado, false en caso contrario.
     */
    @Override
    public boolean isAccountNonExpired() {
        // Example: Check if account expiration date has passed
        //return usuario.getExpirationDate().isAfter(LocalDateTime.now());
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public Long getId() {
        return usuario.getId();
    }

    public String getRol() {
        return usuario.getRol().getNombre(); // Ej: "ADMIN" o "CLIENTE"
    }
}
