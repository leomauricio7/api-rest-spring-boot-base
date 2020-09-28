package io.github.lmauricio.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
// define como uma entity OBS: quando vc adciona o entity o JPA entende que todos os seus atributos são colunas do DB
@Table(name = "cliente") // so coloca essa anotation se o nome da table for direfente do nome da class
// anotations do lombok
@Data // nessa anotacao ja encapsula os @Getter, @Setter, @RequiredArgsConstructor, @ToString e @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id //define a primary key da entity
    @GeneratedValue(strategy = GenerationType.AUTO) // define o auto_increment
    @Column(name = "id") //definine as propriedas da coluna
    private Integer id;
    @Column(name = "nome", length = 100)
    private String nome;
    //mapeamento de relacionamento 1:N
    @JsonIgnore
    @OneToMany( mappedBy = "cliente", fetch = FetchType.LAZY) // mapeia com o nome do atributo da tabela relacionada, o atributo Feth serve para trazer as informacoes na listagens dos clientes
    private Set<Pedido> pedidos;
    @Column(name = "cpf", length = 11)
    private String cpf;
}