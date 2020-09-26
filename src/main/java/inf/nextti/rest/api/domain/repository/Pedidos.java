package inf.nextti.rest.api.domain.repository;

import inf.nextti.rest.api.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
}
