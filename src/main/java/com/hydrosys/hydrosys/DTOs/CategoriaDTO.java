package com.hydrosys.hydrosys.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;


}
