package inf.nextti.rest.api.controller;

import inf.nextti.rest.api.domain.entity.Cliente;
import inf.nextti.rest.api.domain.repository.Clientes;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable("id") Integer id) {
        Optional<Cliente> cliente = clientes.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "")
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente) {
        Cliente result = clientes.save(cliente);
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Cliente cliente) {
        return clientes.findById(id)
                .map(elm -> {
                    cliente.setId(elm.getId());
                    clientes.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        Optional<Cliente> cliente = clientes.findById(id);
        if (cliente.isPresent()) {
            clientes.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "")
    public ResponseEntity find(Cliente filtro) {
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, exampleMatcher);

        List<Cliente> lista = clientes.findAll(example);
        return ResponseEntity.ok(lista);
    }

}
