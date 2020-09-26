package inf.nextti.rest.api;

import inf.nextti.rest.api.domain.entity.Cliente;
import inf.nextti.rest.api.domain.entity.Pedido;
import inf.nextti.rest.api.domain.repository.Clientes;
import inf.nextti.rest.api.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos
    ) {
        return args -> {

            System.out.println("salvando cliente");
            Cliente leo = new Cliente("Leonardo");
            clientes.save(leo);

            Pedido p = new Pedido(leo, LocalDate.now(), BigDecimal.valueOf(100));
            pedidos.save(p);

            Cliente c = clientes.findClienteFetchPedidos(leo.getId());
            System.out.println(c);
            System.out.println(c.getPedidos());

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
