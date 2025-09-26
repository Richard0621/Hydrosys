package com.hydrosys.hydrosys.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MetodoPagoDTO {

    @NotBlank(message = "El nombre del método de pago no puede estar vacío")
    private String nombrePago;


}
