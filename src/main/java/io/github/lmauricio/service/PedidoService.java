package io.github.lmauricio.service;

import io.github.lmauricio.domain.entity.Pedido;
import io.github.lmauricio.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar (PedidoDTO dto);
}
