package com.hydrosys.hydrosys.DTOs;

import jakarta.validation.constraints.NotBlank;

public class RolDTO {
    @NotBlank(message = "El nombre del rol no puede estar vacío")
    private String nombre;

    // Getter y setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
