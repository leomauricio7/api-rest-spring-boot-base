package io.github.lmauricio.domain.repository;

import io.github.lmauricio.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// a interface JpaRepository recebe dois params a entidade e o tipo de dado do identificador
public interface Clientes extends JpaRepository<Cliente, Integer> {

    // queryMethods
    // o jpaRepository criar um queryMethod de acordo com o nome que vc repassou para o metódo

    // exe: find=Select By=Where Nome=nome do atributo Like=tipo da pesquisa
    // sql gerada = select * from Cliente Where nome like '%nome%'
    //List<Cliente> findByNomeLike(String nome);

    // exe: find=Select By=Where Nome=nome do atributo OR=ou Id=nome do atributo OrderBy=ordenacao
    // sql gerada = select * from Cliente where nome = nome OR id = id order by id
    //List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

    //verifica se existe um ciente com um nome repassado
    //boolean existsByNome(String nome);

    // @Query - formata o queryMethod com o HQL ou SQL nativo
    //@Query(value = " select c from Cliente c where c.nome like :nome ")
    //List<Cliente> encontrarPorNome(@Param("nome") String nome);

    // sempre usar o @Query para metodos != de get deve se usar a annotation @Modifying
    //@Query( value = " delete from Cliente c where c.nome =:nome")
    //@Modifying
    //void deleteByNome(@Param("nome") String nome);

    @Query(value = " select c from Cliente c left join fetch c.pedidos where c.id =:id ")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);

}
