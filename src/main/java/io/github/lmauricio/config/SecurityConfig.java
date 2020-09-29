package io.github.lmauricio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// classe de configuracao do spring-security
// anotation de ewb security
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${security.enable.csrf}")
    private boolean csrfEnabled;

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
                .passwordEncoder(passwordEncoder())
                .withUser("fulano")
                .password(passwordEncoder().encode("123"))
                .roles("USER", "ADMIN");
    }

    @Override
    // metodo de configurar a permissao dos usuarios
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        /*
         *   PERMISSÕES DE USUÁRIO
         *   - hasAuthority - autorizacao
         *   - hasRole -  perfil
         *   - permitAll - aberta
         *   - authenticated - basta ta logado
         * */

        http
                .authorizeRequests() // autorizacoes dos requests
                .antMatchers("/api/clientes/**")
                .hasAnyRole("USER")
                .antMatchers("/api/produtos/**")
                .hasRole("ADMIN")
                .antMatchers("/api/pedidos/**")
                .hasAnyRole("USER", "ADMIN")
                .and()
                .httpBasic();

        if (csrfEnabled) {
            System.out.println("csrfEnabled: " + csrfEnabled);
            http.csrf().disable();
        }
    }

}
