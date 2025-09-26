package com.hydrosys.hydrosys.Controller;


import com.hydrosys.hydrosys.DTOs.RegistroUsuarioDTO;
import com.hydrosys.hydrosys.Model.Usuario;
import com.hydrosys.hydrosys.Security.JwtUtil;
import com.hydrosys.hydrosys.Security.UserDetailsImpl;
import com.hydrosys.hydrosys.Service.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioServiceImpl usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(UsuarioServiceImpl usuarioService,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody RegistroUsuarioDTO registroDTO, BindingResult bindingResult) {
        try {
            // Validar errores de validación del DTO
            // Si hay errores, devolver una respuesta 400 con los errores
            if (bindingResult.hasErrors()) {
                Map<String, String> errores = new HashMap<>();
                bindingResult.getFieldErrors().forEach(error -> {
                    errores.put(error.getField(), error.getDefaultMessage());
                });
                return ResponseEntity.badRequest().body(errores);
            }

            // Crear el usuario y lo guardar en la base de datos
            Usuario usuarioRegistrado = usuarioService.registrarUsuario(registroDTO);

            // Autenticar al usuario
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            registroDTO.getCorreo(),
                            registroDTO.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Usuario registrado exitosamente");
            response.put("token", token);
            response.put("email", usuarioRegistrado.getCorreo());
            response.put("rol", usuarioRegistrado.getRol().getNombre()); // Acceder al nombre del rol
            response.put("userId", usuarioRegistrado.getId());

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}