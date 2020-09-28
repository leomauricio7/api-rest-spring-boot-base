package io.github.lmauricio.rest.controller;

import io.github.lmauricio.service.PedidoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
     private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }
}
