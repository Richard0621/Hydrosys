package com.hydrosys.hydrosys.Controller;

import com.hydrosys.hydrosys.Model.Lote;
import com.hydrosys.hydrosys.Service.LoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lotes")
@CrossOrigin(origins = "*")
public class LoteController {


    private LoteServiceImpl loteService;

    public LoteController(LoteServiceImpl loteService) {
        this.loteService = loteService;
    }

    // Obtener todos los lotes
    @GetMapping
    public ResponseEntity<List<Lote>> obtenerTodosLosLotes() {
        List<Lote> lotes = loteService.obtenerTodosLosLotes();
        return ResponseEntity.ok(lotes);
    }

    // Obtener lote por ID
    @GetMapping("/{id}")
    public ResponseEntity<Lote> obtenerLotePorId(@PathVariable Long id) {
        Optional<Lote> lote = loteService.obtenerLotePorId(id);
        return lote.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear nuevo lote
    @PostMapping
    public ResponseEntity<Lote> crearLote(@RequestBody Lote lote) {
        try {
            Lote nuevoLote = loteService.crearLote(lote);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLote);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Actualizar lote existente
    @PutMapping("/{id}")
    public ResponseEntity<Lote> actualizarLote(@PathVariable Long id, @RequestBody Lote lote) {
        try {
            Lote loteActualizado = loteService.actualizarLote(id, lote);
            return ResponseEntity.ok(loteActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar lote
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLote(@PathVariable Long id) {
        try {
            loteService.eliminarLote(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar por proveedor
    @GetMapping("/proveedor/{proveedor}")
    public ResponseEntity<List<Lote>> obtenerLotesPorProveedor(@PathVariable String proveedor) {
        List<Lote> lotes = loteService.obtenerLotesPorProveedor(proveedor);
        return ResponseEntity.ok(lotes);
    }

    //Buscar lotes próximos a vencer
    @GetMapping("/proximos-vencer")
    public ResponseEntity<List<Lote>> obtenerLotesProximosAVencer(@RequestParam Date fecha) {
        List<Lote> lotes = loteService.obtenerLotesProximosAVencer(fecha);
        return ResponseEntity.ok(lotes);
    }
}