package com.hydrosys.hydrosys.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydrosys.hydrosys.Model.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Filtro de autenticación JWT que se encarga de autenticar al usuario
 * mediante su email y contraseña, generando un token JWT en caso de éxito.
 * Este filtro extiende UsernamePasswordAuthenticationFilter para manejar
 * la autenticación basada en nombre de usuario y contraseña.
 */

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Este metodo se encarga de autenticar al usuario con su email y contraseña.
     * Se lee el cuerpo de la solicitud para obtener las credenciales del usuario.
     *
     * @param request  HttpServletRequest que contiene la solicitud del usuario.
     * @param response HttpServletResponse que se utilizará para enviar la respuesta.
     * @return Authentication que representa el resultado de la autenticación.
     * @throws AuthenticationException si ocurre un error durante la autenticación.
     */

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        // Intentar leer el cuerpo de la solicitud para obtener las credenciales del usuario
        try {
            // Leer el cuerpo de la solicitud y mapearlo a LoginRequest
            LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

            // Crear un objeto UsernamePasswordAuthenticationToken con las credenciales del usuario
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

            // Autenticar al usuario utilizando el AuthenticationManager
            return authenticationManager.authenticate(authToken);

        } catch (IOException e) {
            throw new RuntimeException("Error al leer el login", e);
        }
    }

    /**
     * Este mtodo se llama cuando la autenticación es exitosa.
     * Genera un token JWT y lo devuelve en el cuerpo de la respuesta.
     *
     * @param request  HttpServletRequest que contiene la solicitud del usuario.
     * @param response HttpServletResponse que se utilizará para enviar la respuesta.
     * @param chain    FilterChain que permite continuar con el procesamiento de la solicitud.
     * @param authResult Authentication que representa el resultado de la autenticación exitosa.
     * @throws IOException si ocurre un error al escribir en la respuesta.
     */


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // Obtener el principal autenticado (la clase que implementa UserDetails)
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();

        //Obtener la entidad Usuario (si la tienes anidada dentro de CustomUserDetails)
        Usuario usuario = userDetails.getUsuario();

        // Generar el token JWT usando la clase JwtUtil
        String token = jwtUtil.generateToken(userDetails); // Asumiendo que incluya claims como id y rol

        //Obtener el rol principal del usuario
        String rolPrincipal = userDetails.getRol(); // Asegúrate de tener esto en CustomUserDetails

        // Construir el cuerpo de la respuesta JSON
        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token", token);
        httpResponse.put("message", "Autenticación correcta");
        httpResponse.put("email", usuario.getCorreo());
        httpResponse.put("rol", rolPrincipal);
        httpResponse.put("userId", usuario.getId());

        // Establecer código de estado y tipo de contenido de la respuesta
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        // Escribir la respuesta JSON
        new ObjectMapper().writeValue(response.getWriter(), httpResponse);
        response.getWriter().flush();
    }

    /**
     * Este metodo se llama cuando la autenticación falla.
     * Devuelve un mensaje de error en el cuerpo de la respuesta.
     *
     * @param request  HttpServletRequest que contiene la solicitud del usuario.
     * @param response HttpServletResponse que se utilizará para enviar la respuesta.
     * @param failed   AuthenticationException que representa el error de autenticación.
     * @throws IOException      si ocurre un error al escribir en la respuesta.
     * @throws ServletException si ocurre un error durante el procesamiento del filtro.
     */

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        // Construir una respuesta de error
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", "Correo o contraseña incorrectos.");
        errorResponse.put("status", 401);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        // Escribir la respuesta como JSON
        new ObjectMapper().writeValue(response.getWriter(), errorResponse);
        response.getWriter().flush();
    }


}
