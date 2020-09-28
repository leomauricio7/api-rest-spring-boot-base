package io.github.lmauricio.rest.controller;

import io.github.lmauricio.domain.entity.Pedido;
import io.github.lmauricio.rest.dto.PedidoDTO;
import io.github.lmauricio.service.PedidoService;
import org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
     private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

 }
