package io.github.lmauricio.config;

import io.github.lmauricio.security.jwt.JwtAuthFilter;
import io.github.lmauricio.security.jwt.JwtService;
import io.github.lmauricio.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

// classe de configuracao do spring-security
// anotation de web security
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${security.enable.csrf}")
    private boolean csrfEnabled;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private JwtService jwtService;

    // metodos para configurar o spring security

    // bean para criptograr e descriptografar a senha de um usuário
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // bean para filtar o JWT do request
    @Bean
    public OncePerRequestFilter jwtFilter(){
        return  new JwtAuthFilter(jwtService, usuarioService);
    }

    //metodo de configuracao da parte de autenticacao do usuário
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Ex: autenticacao em banco auth basic
        auth.
                userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());
    }

    // metodo de configurar a permissao dos usuarios
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        /*
         *   PERMISSÕES DE USUÁRIO
         *   - hasAuthority - autorizacao
         *   - hasRole -  perfil
         *   - permitAll - aberta
         *   - authenticated - basta ta logado
         *
         * */

        http.csrf().disable();

        http
                .authorizeRequests() // autorizacoes dos requests
                .antMatchers("/api/clientes/**")
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/produtos/**")
                    .hasRole("ADMIN")
                .antMatchers("/api/pedidos/**")
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/usuarios/**")
                    .permitAll()
                //.anyRequest().authenticated()
                .and() // volta pra raiz httpSecurity
                    .sessionManagement() // informa que vai usar sessions
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // sesions do tipo STATELESS
                .and() // volta pra raiz do httpSecurity
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class); // colocar dentro da sessao o usuario do filtro do jwt

//        if (csrfEnabled) {
//            System.out.println("csrfEnabled: " + csrfEnabled);
//            http.csrf().disable();
//        }
    }

}
