package io.github.lmauricio.rest.controller;

import io.github.lmauricio.domain.entity.ItemPedido;
import io.github.lmauricio.domain.entity.Pedido;
import io.github.lmauricio.domain.enums.StatusPedido;
import io.github.lmauricio.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.lmauricio.rest.dto.InformacaoItemPedidoDTO;
import io.github.lmauricio.rest.dto.InformacoesPedidoDTO;
import io.github.lmauricio.rest.dto.PedidoDTO;
import io.github.lmauricio.service.PedidoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO dto) {
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable("id") Integer id) {
        return service.obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado."));
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable("id") Integer id,
                             @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));

    }

    // conveter  objeto de pedido
    private InformacoesPedidoDTO converter(Pedido pedido) {
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItems()))
                .build();
    }

    // converte a lista de itens do pedido
    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens) {
        // verifica se esta vazio os itens do pedido
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList(); // retorn uma array vazio []
        }
        return itens.stream()
                .map( item -> InformacaoItemPedidoDTO
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .preco(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
                )
                .collect(
                        Collectors.toList() // pega a string retornada e tranforma em uma lista
                );
    }

}
