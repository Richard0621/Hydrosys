package com.hydrosys.hydrosys.Service;

import com.hydrosys.hydrosys.Model.MetodoPago;
import com.hydrosys.hydrosys.Repository.MetodoPagoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MetodoPagoServiceImpl {

    private final MetodoPagoRepositorio metodoPagoRepositorio;

    public MetodoPagoServiceImpl(MetodoPagoRepositorio metodoPagoRepositorio) {
        this.metodoPagoRepositorio = metodoPagoRepositorio;
    }

    // Crear metodo de pago
    @Transactional
    public MetodoPago crearMetodoPago(String nombrePago) {
        if (metodoPagoRepositorio.existsByNombrePago(nombrePago)) {
            throw new RuntimeException("El método de pago ya existe");
        }

        MetodoPago nuevo = new MetodoPago();
        nuevo.setNombrePago(nombrePago);
        return metodoPagoRepositorio.save(nuevo);
    }

    // Listar todos los métodos de pago
    public List<MetodoPago> listarMetodosPago() {
        return metodoPagoRepositorio.findAll();
    }

    // Obtener por ID
    public MetodoPago obtenerPorId(Long id) {
        return metodoPagoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
    }

    // Actualizar
    @Transactional
    public MetodoPago actualizarMetodoPago(Long id, String nuevoNombre) {
        MetodoPago metodo = metodoPagoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));

        if (metodoPagoRepositorio.existsByNombrePago(nuevoNombre)) {
            throw new RuntimeException("Ya existe otro método de pago con ese nombre");
        }

        metodo.setNombrePago(nuevoNombre);
        return metodoPagoRepositorio.save(metodo);
    }

    // Eliminar
    @Transactional
    public void eliminarMetodoPago(Long id) {
        if (!metodoPagoRepositorio.existsById(id)) {
            throw new RuntimeException("Método de pago no encontrado");
        }
        metodoPagoRepositorio.deleteById(id);
    }
}
