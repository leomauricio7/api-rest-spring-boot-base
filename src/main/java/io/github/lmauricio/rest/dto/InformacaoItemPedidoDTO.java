package io.github.lmauricio.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor // construtor com as propriedades obrigatorias
@NoArgsConstructor // construtor vazio
@Builder  // gera uma classe builder com todas as propriedades da classe
public class InformacaoItemPedidoDTO {
    private String descricaoProduto;
    private BigDecimal preco;
    private Integer quantidade;
}
