package com.hydrosys.hydrosys.Service;

import com.hydrosys.hydrosys.Model.Lote;
import com.hydrosys.hydrosys.Repository.LoteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LoteServiceImpl {

    @Autowired
    private LoteRepositorio loteRepository;

    // Obtener todos los lotes
    public List<Lote> obtenerTodosLosLotes() {
        return loteRepository.findAll();
    }

    // Obtener lote por ID
    public Optional<Lote> obtenerLotePorId(Long id) {
        return loteRepository.findById(id);
    }

    // Crear nuevo lote
    public Lote crearLote(Lote lote) {
        return loteRepository.save(lote);
    }

    // Actualizar lote existente
    public Lote actualizarLote(Long id, Lote loteActualizado) {
        return loteRepository.findById(id)
                .map(lote -> {
                    lote.setProveedor(loteActualizado.getProveedor());
                    lote.setFechaVencimiento(   loteActualizado.getFechaVencimiento());
                    return loteRepository.save(lote);
                })
                .orElseThrow(() -> new RuntimeException("Lote no encontrado con id: " + id));
    }

    // Eliminar lote
    public void eliminarLote(Long id) {
        if (loteRepository.existsById(id)) {
            loteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Lote no encontrado con id: " + id);
        }
    }

    // Buscar por proveedor
    public List<Lote> obtenerLotesPorProveedor(String proveedor) {
        return loteRepository.findByProveedor(proveedor);
    }

    // Buscar lotes próximos a vencer
    public List<Lote> obtenerLotesProximosAVencer(Date fecha) {
        return loteRepository.findLotesProximosAVencer(fecha);
    }
}