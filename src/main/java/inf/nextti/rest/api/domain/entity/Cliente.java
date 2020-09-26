package inf.nextti.rest.api.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
// define como uma entity OBS: quando vc adciona o entity o JPA entende que todos os seus atributos são colunas do DB
@Table(name = "cliente") // so coloca essa anotation se o nome da table for direfente do nome da class
public class Cliente {

    @Id //define a primary key da entity
    @GeneratedValue(strategy = GenerationType.AUTO) // define o auto_increment
    @Column(name = "id") //definine as propriedas da coluna
    private Integer id;
    @Column(name = "nome", length = 100)
    private String nome;
    //mapeamento de relacionamento 1:N
    @OneToMany( mappedBy = "cliente", fetch = FetchType.LAZY) // mapeia com o nome do atributo da tabela relacionada, o atributo Feth serve para trazer as informacoes na listagens dos clientes
    private Set<Pedido> pedidos;

    public Cliente() {
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
