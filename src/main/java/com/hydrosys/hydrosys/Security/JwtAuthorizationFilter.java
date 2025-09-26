package com.hydrosys.hydrosys.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de autorización JWT que se encarga de verificar la validez del token JWT
 * en cada solicitud HTTP y establecer la autenticación en el contexto de seguridad.
 * OncePerRequestFilter asegura que este filtro se ejecute una vez por solicitud.
 */

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtAuthorizationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Este metodo se encarga de filtrar las solicitudes HTTP para verificar la validez del token JWT.
     * Si el token es válido, establece la autenticación en el contexto de seguridad.
     *
     * @param request  HttpServletRequest que contiene la solicitud del usuario.
     * @param response HttpServletResponse que se utilizará para enviar la respuesta.
     * @param filterChain FilterChain que permite continuar con la cadena de filtros.
     * @throws ServletException si ocurre un error durante el procesamiento del filtro.
     * @throws IOException      si ocurre un error de entrada/salida.
     */
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain)
            throws ServletException, IOException {

        // Extraer el token JWT de la cabecera de autorización
        final String authHeader = request.getHeader("Authorization");

        String email = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7); // quitar "Bearer " del inicio del token
            email = jwtUtil.extractUsername(jwt); // extraer el email del token
        }

        // 3. Verificar condiciones para autenticar el usuario a partir del token
        //    a) 'email != null': Asegura que hemos podido extraer un nombre de usuario del token.
        //                       Esto implica que el token era sintácticamente válido y no estaba vacío.
        //    b) 'SecurityContextHolder.getContext().getAuthentication() == null':
        //       Esta es una comprobación CRÍTICA. Significa:
        //       "Solo procede con la autenticación del token si el usuario AÚN NO ha sido autenticado
        //       en esta petición." Esto evita la autenticación redundante y asegura que si ya hay un
        //       Authentication en el contexto (por ejemplo, de un filtro anterior), no lo sobrescribimos
        //       innecesariamente.
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            // Validar el token JWT
            if (jwtUtil.validateToken(jwt, userDetails)) {
                //    Si el token es válido, autenticar al usuario en el contexto de seguridad
                //    Crea un UsernamePasswordAuthenticationToken. Al usar el constructor con tres argumentos
                //    (principal, credenciales (null aquí porque ya validamos el token), autoridades),
                //    este token se considera "autenticado".
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                //Establecer detalles adicionales de la autenticación (opcional pero buena práctica)
                //Añade detalles sobre la petición web (como la IP del cliente) al objeto de autenticación.
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));
                //    Establecer el objeto de autenticación en el SecurityContextHolder
                //    Esto es lo más importante: le dice a Spring Security que el usuario
                //    representado por 'authToken' está ahora autenticado para el resto de la duración
                //    de esta petición. Cualquier controlador o servicio posterior podrá acceder
                //    a la información de este usuario autenticado.
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
