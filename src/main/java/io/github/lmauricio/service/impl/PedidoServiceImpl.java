package io.github.lmauricio.service.impl;

import io.github.lmauricio.domain.entity.Cliente;
import io.github.lmauricio.domain.entity.ItemPedido;
import io.github.lmauricio.domain.entity.Pedido;
import io.github.lmauricio.domain.entity.Produto;
import io.github.lmauricio.domain.repository.Clientes;
import io.github.lmauricio.domain.repository.ItemsPedido;
import io.github.lmauricio.domain.repository.Pedidos;
import io.github.lmauricio.domain.repository.Produtos;
import io.github.lmauricio.exception.RegraNegocioException;
import io.github.lmauricio.rest.dto.ItemPedidoDTO;
import io.github.lmauricio.rest.dto.PedidoDTO;
import io.github.lmauricio.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // criar o construtor na compilacao
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedido itemsPedidoRepository;

    @Override
    @Transactional // serce para dar um roll back caso haja alum erro em alguma transacao
    public Pedido salvar(PedidoDTO dto) {
        Integer idCLiente = dto.getCliente();
        Cliente cliente = clientesRepository.findById(idCLiente)
                .orElseThrow(() -> new RegraNegocioException("Id de cliente inválido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemPedidos = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemPedidos);
        pedido.setItems(itemPedidos);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItems(id);
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new RegraNegocioException("Não é possivel realizar o pedido sem itens.");
        }
        return items
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository.findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());

    }
}
