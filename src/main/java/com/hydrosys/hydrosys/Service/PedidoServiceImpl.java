package com.hydrosys.hydrosys.Service;

import com.hydrosys.hydrosys.DTOs.PedidoDTO;
import com.hydrosys.hydrosys.Model.MetodoPago;
import com.hydrosys.hydrosys.Model.Pedido;
import com.hydrosys.hydrosys.Model.Usuario;
import com.hydrosys.hydrosys.Repository.MetodoPagoRepositorio;
import com.hydrosys.hydrosys.Repository.PedidoRepositorio;
import com.hydrosys.hydrosys.Repository.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl {

    private final PedidoRepositorio pedidoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final MetodoPagoRepositorio metodoPagoRepositorio;

    public PedidoServiceImpl(PedidoRepositorio pedidoRepositorio, UsuarioRepositorio usuarioRepositorio, MetodoPagoRepositorio metodoPagoRepositorio) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.metodoPagoRepositorio = metodoPagoRepositorio;
    }

    @Transactional
    public Pedido crearPedido(PedidoDTO dto) {
        Usuario usuario = usuarioRepositorio.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        MetodoPago metodoPago = metodoPagoRepositorio.findById(dto.getMetodoPagoId())
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setMetodoPago(metodoPago);
        pedido.setDireccionEnvio(dto.getDireccionEnvio());
        pedido.setEstadoPedido(dto.getEstadoPedido());
        pedido.setTotalPedido(dto.getTotalPedido());

        return pedidoRepositorio.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepositorio.findAll();
    }

    public Pedido obtenerPedidoPorId(Long id) {
        return pedidoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    @Transactional
    public void eliminarPedido(Long id) {
        if (!pedidoRepositorio.existsById(id)) {
            throw new RuntimeException("Pedido no encontrado");
        }
        pedidoRepositorio.deleteById(id);
    }
}