package com.hydrosys.hydrosys.Service;

import com.hydrosys.hydrosys.DTOs.DetalleCarritoDTO;
import com.hydrosys.hydrosys.Model.Carrito;
import com.hydrosys.hydrosys.Model.Producto;
import com.hydrosys.hydrosys.Model.Usuario;
import com.hydrosys.hydrosys.Repository.CarritoRepositorio;
import com.hydrosys.hydrosys.Repository.ProductoRepositorio;
import com.hydrosys.hydrosys.Repository.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DetalleCarritoServiceImpl {

    private final CarritoRepositorio carritoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final ProductoRepositorio productoRepositorio;

    public DetalleCarritoServiceImpl(CarritoRepositorio carritoRepositorio,
                                     UsuarioRepositorio usuarioRepositorio,
                                     ProductoRepositorio productoRepositorio) {
        this.carritoRepositorio = carritoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.productoRepositorio = productoRepositorio;
    }

    @Transactional
    public Carrito agregarDetalleCarrito(DetalleCarritoDTO dto) {
        Usuario usuario = usuarioRepositorio.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Producto producto = productoRepositorio.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);
        carrito.setProducto(producto);
        carrito.setCantidadProducto(dto.getCantidadProducto());
        carrito.setPrecioUnitario(dto.getPrecioUnitario());

        // Calcular subtotal
        BigDecimal subtotal = dto.getPrecioUnitario().multiply(BigDecimal.valueOf(dto.getCantidadProducto()));
        carrito.setSubtotal(subtotal);

        return carritoRepositorio.save(carrito);
    }

    public List<Carrito> obtenerCarritoPorUsuario(Long usuarioId) {
        return carritoRepositorio.findByUsuarioId(usuarioId);
    }

    @Transactional
    public void eliminarItem(Long id) {
        if (!carritoRepositorio.existsById(id)) {
            throw new RuntimeException("Elemento no encontrado");
        }
        carritoRepositorio.deleteById(id);
    }

    //Ver todos los items del carrito de un usuario
    public List<Carrito> todosItems(Long usuarioId) {
        return carritoRepositorio.findByUsuarioId(usuarioId);
    }

    @Transactional
    public void vaciarCarrito(Long usuarioId) {
        carritoRepositorio.deleteByUsuarioId(usuarioId);
    }
}