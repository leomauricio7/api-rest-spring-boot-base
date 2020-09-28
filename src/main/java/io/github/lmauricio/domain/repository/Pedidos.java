package io.github.lmauricio.domain.repository;

import io.github.lmauricio.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

    @Query(" select p from Pedido p left join fetch p.items where p.id = :id ")
    Optional<Pedido> findByIdFetchItems(@Param("id") Integer id);

}
