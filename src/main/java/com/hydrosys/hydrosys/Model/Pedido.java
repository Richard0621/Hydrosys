package com.hydrosys.hydrosys.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_pago", nullable = false)
    private MetodoPago metodoPago;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaPedido;

    @Column(nullable = false)
    private String estadoPedido;

    @Column(nullable = false)
    private String direccionEnvio;

    @Column(nullable = false)
    private BigDecimal totalPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DetallePedido> detalles;
}
