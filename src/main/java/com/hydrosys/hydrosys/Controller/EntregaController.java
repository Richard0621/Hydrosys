package com.hydrosys.hydrosys.Controller;


import com.hydrosys.hydrosys.Service.EntregaService;
import com.hydrosys.hydrosys.DTOs.EntregaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/api/public/entregas")
@RequiredArgsConstructor
public class EntregaController {

    private final EntregaService entregaService;

    @GetMapping
    public List<EntregaDto> listarEntregas() {
        return entregaService.listarEntregasPublicas();
    }
}
