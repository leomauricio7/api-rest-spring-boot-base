package io.github.lmauricio.service;

import io.github.lmauricio.domain.entity.Pedido;
import io.github.lmauricio.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar (PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

}
