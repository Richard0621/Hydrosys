package com.hydrosys.hydrosys.Service;


import com.hydrosys.hydrosys.DTOs.DetallePedidoDTO;
import com.hydrosys.hydrosys.Model.DetallePedido;
import com.hydrosys.hydrosys.Model.Pedido;
import com.hydrosys.hydrosys.Model.Producto;
import com.hydrosys.hydrosys.Repository.DetallePedidoRepositorio;
import com.hydrosys.hydrosys.Repository.PedidoRepositorio;
import com.hydrosys.hydrosys.Repository.ProductoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DetallePedidoServiceImpl {

    private final DetallePedidoRepositorio detallePedidoRepositorio;
    private final PedidoRepositorio pedidoRepositorio;
    private final ProductoRepositorio productoRepositorio;

    public DetallePedidoServiceImpl(DetallePedidoRepositorio detallePedidoRepositorio,
                                    PedidoRepositorio pedidoRepositorio,
                                    ProductoRepositorio productoRepositorio) {
        this.detallePedidoRepositorio = detallePedidoRepositorio;
        this.pedidoRepositorio = pedidoRepositorio;
        this.productoRepositorio = productoRepositorio;
    }

    @Transactional
    public DetallePedido agregarDetalle(DetallePedidoDTO dto) {
        Pedido pedido = pedidoRepositorio.findById(dto.getPedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        Producto producto = productoRepositorio.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        DetallePedido detalle = new DetallePedido();
        detalle.setPedido(pedido);
        detalle.setProducto(producto);
        detalle.setCantidadProducto(dto.getCantidadProducto());
        detalle.setPrecioUnitario(dto.getPrecioUnitario());

        // Calcular subtotal = precioUnitario * cantidad
        BigDecimal subtotal = dto.getPrecioUnitario().multiply(BigDecimal.valueOf(dto.getCantidadProducto()));
        detalle.setSubtotal(subtotal);

        return detallePedidoRepositorio.save(detalle);
    }

    public List<DetallePedido> listarDetallesPorPedido(Long pedidoId) {
        return detallePedidoRepositorio.findByPedidoId(pedidoId);
    }

    @Transactional
    public void eliminarDetalle(Long id) {
        if (!detallePedidoRepositorio.existsById(id)) {
            throw new RuntimeException("Detalle no encontrado");
        }
        detallePedidoRepositorio.deleteById(id);
    }
}