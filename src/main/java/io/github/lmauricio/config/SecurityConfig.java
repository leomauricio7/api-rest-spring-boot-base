package io.github.lmauricio.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// classe de configuracao do spring-security
@EnableWebSecurity // anotation de ewb security
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // metodos para configurar o spring security

    @Override
    //metodo de configuracao da parte de autenticacao
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    // metodo de configurar a permissao dos usuarios
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

}
