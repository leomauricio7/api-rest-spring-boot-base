package io.github.lmauricio.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/*
* {
*	"cliente": 1,
*	"total": 100,
*	"items": [
*		{
*			"produto": 1,
*			"quantidade": 1
*		}
*	]
* }
*/
// DTO -> data tranfere object
// padrao para mapear objects de entidades simples

// anotations do lombak
@Data // nessa anotacao ja encapsula os @Getter, @Setter, @RequiredArgsConstructor, @ToString e @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    @NotNull(message = "Campo cliente é obrigatório,")
    private Integer cliente;

    @NotNull(message = "Campo total é obrigatório,")
    private BigDecimal total;

    private List<ItemPedidoDTO> items;
}
