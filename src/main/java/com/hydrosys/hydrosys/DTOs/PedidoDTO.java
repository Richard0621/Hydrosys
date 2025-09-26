package com.hydrosys.hydrosys.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class PedidoDTO {

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "El ID del método de pago es obligatorio")
    private Long metodoPagoId;

    @NotBlank(message = "El estado del pedido no puede estar vacío")
    private String estadoPedido;

    @NotBlank(message = "La dirección de envío no puede estar vacía")
    private String direccionEnvio;

    @NotNull(message = "El total del pedido es obligatorio")
    private BigDecimal totalPedido;

}
