package io.github.lmauricio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// classe de configuracao do spring-security
@EnableWebSecurity // anotation de ewb security
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // metodos para configurar o spring security

    // bean para criptograr e descriptografar a senha de um usuário
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    //metodo de configuracao da parte de autenticacao do usuário
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Ex: autenticacao em memoria
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder()) // metodo de criptografia
                .withUser("admin") // user
                .password(passwordEncoder().encode("admin")) // senha
                .roles("USER", "ADMIN"); // perfil
    }

    @Override
    // metodo de configurar a permissao dos usuarios
    protected void configure(HttpSecurity http) throws Exception {
        /*
         *   PERMISSÕES DE USUÁRIO
         *   - hasAuthority - autorizacao
         *   - hasRole -  perfil
         *   - permitAll - aberta
         *   - authenticated - basta ta logado
         * */
        http
                .csrf().disable() // desabilita uma conf de permisao de seguranca ente uma api ewb e o back end
                .authorizeRequests() // autorizacoes dos requests
                    .antMatchers("/api/clientes/**") // rota
                        .hasAnyRole("USER", "ADMIN") //permissao
                    .antMatchers("/api/produtos/**")
                        .hasRole("ADMIN")
                    .antMatchers("/api/pedidos/**")
                        .hasAnyRole("USER", "ADMIN")
                .and()
                    .formLogin(); // criar o formulario de login default
    }

}
