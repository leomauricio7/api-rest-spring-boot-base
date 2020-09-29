package io.github.lmauricio.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")

// anotations do lombok
@Data // nessa anotacao ja encapsula os @Getter, @Setter, @RequiredArgsConstructor, @ToString e @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao")
    @NotEmpty(message = "Campo Descricao é obrigatório.")
    private String descricao;

    @Column(name = "preco", precision = 20, scale = 2)
    @NotNull(message = "Campo Preço é obrigatório.")
    private BigDecimal preco;
}
