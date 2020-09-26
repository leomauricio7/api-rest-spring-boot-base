package inf.nextti.rest.api;

import inf.nextti.rest.api.domain.entity.Cliente;
import inf.nextti.rest.api.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasApplication {
    /*
    @Bean
    public CommandLineRunner commandLineRunner(@Autowired Clientes clientes){
        return args -> {
            Cliente c = new Cliente("leo");
            clientes.save(c);
        };
    }
    */
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
