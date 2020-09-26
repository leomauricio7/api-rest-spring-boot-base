package inf.nextti.rest.api.domain.repository;

import inf.nextti.rest.api.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
