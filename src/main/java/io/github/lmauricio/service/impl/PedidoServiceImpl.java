package io.github.lmauricio.service.impl;

import io.github.lmauricio.domain.repository.Pedidos;
import io.github.lmauricio.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private Pedidos repository;

    public PedidoServiceImpl(Pedidos repository) {
        this.repository = repository;
    }
}
