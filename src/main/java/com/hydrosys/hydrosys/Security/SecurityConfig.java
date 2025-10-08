package com.hydrosys.hydrosys.Security; // Asegúrate de que este sea el paquete correcto para tu SecurityConfig

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Clase de configuración de seguridad para la aplicación.
 * Configura los filtros de autenticación, CORS y las reglas de autorización.
 */

@Configuration
@EnableWebSecurity // Habilita las características de seguridad web de Spring
@EnableMethodSecurity // Habilita la seguridad a nivel de metodo (ej. @PreAuthorize)

public class SecurityConfig {

    private final JwtAuthorizationFilter jwtRequestFilter;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtils;

    public SecurityConfig(JwtAuthorizationFilter jwtRequestFilter,
                          UserDetailsServiceImpl userDetailsService,
                          JwtUtil jwtUtils) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AuthenticationManager authenticationManager) throws Exception {

        // Creamos el filtro de login personalizado
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtUtils);
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/auth/login"); // Establecemos la URL de inicio de sesión personalizada

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //.authorizeHttpRequests(auth -> auth
                  //      .requestMatchers("/api/auth/**").permitAll()
                      //  .requestMatchers(HttpMethod.GET, "/api/productos/**", "/api/categorias/**").permitAll()
                        //.requestMatchers("/api/admin/**").hasRole("ADMIN")
                        //.anyRequest().authenticated()
                //)
                // COMENTAR YA EN PRODUCCION
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll() // Permite todas las rutas
                        .anyRequest().permitAll()
                )
                // Configuramos el AuthenticationManager para que use nuestro AuthenticationProvider
                .authenticationProvider(authenticationProvider())

                // Primero añadimos el filtro que maneja /api/auth/login en el lugar adecuado
                // Este filtro maneja la autenticación de usuario con email y contraseña
                .addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                // Luego el filtro que valida JWT en cada request
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }

    // Configuración CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173/")); // especifica frontend
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

