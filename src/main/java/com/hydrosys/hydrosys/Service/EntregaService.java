package com.hydrosys.hydrosys.Service;

import com.hydrosys.hydrosys.Repository.DetallePedidoRepository;
import com.hydrosys.hydrosys.DTOs.EntregaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntregaService {

    private final DetallePedidoRepository detallePedidoRepository;

    public List<EntregaDto> listarEntregasPublicas() {
        return detallePedidoRepository.findEntregasPublicas();
    }
}
