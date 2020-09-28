package io.github.lmauricio.domain.repository;

import io.github.lmauricio.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
