package com.hydrosys.hydrosys.DTOs;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter

public class ProductoDTO {
    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombre;

    private Long idLote;

    private String imagenUrl;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que cero")
    private BigDecimal precio;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    private String descripcion;

    @NotBlank(message = "La categoría es obligatoria")
    private String categoriaNombre; // solo se envía el nombre, no el objeto completo

}
