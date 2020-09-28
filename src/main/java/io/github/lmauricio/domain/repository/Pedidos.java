package io.github.lmauricio.domain.repository;

import io.github.lmauricio.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
}
