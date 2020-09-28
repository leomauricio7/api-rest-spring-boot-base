package io.github.lmauricio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
