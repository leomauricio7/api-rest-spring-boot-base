package inf.nextti.rest.api.domain.repository;

import inf.nextti.rest.api.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer> {
}
