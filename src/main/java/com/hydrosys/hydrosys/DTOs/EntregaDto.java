package com.hydrosys.hydrosys.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntregaDto {

    @JsonProperty("id_producto")
    private Long idProducto;

    @JsonProperty("nombre_producto")
    private String nombreProducto;

    @JsonProperty("id_cliente")
    private Long idCliente;

    @JsonProperty("nombre_cliente")
    private String nombreCliente;

    @JsonProperty("precio_unitario")
    private BigDecimal precioUnitario;

    @JsonProperty("cantidad")
    private Integer cantidad;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("fecha")
    private LocalDateTime fecha;
}
