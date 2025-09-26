package com.hydrosys.hydrosys.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DireccionDTO {
    private Long id;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long idUsuario;

    @NotBlank(message = "El departamento no puede estar vacío")
    private String departamento;

    @NotBlank(message = "La ciudad no puede estar vacía")
    private String ciudad;

    @NotBlank(message = "El país no puede estar vacío")
    private String pais;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;


}
