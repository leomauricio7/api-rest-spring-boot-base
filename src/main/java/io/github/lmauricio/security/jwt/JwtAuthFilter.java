package io.github.lmauricio.security.jwt;

import io.github.lmauricio.service.impl.UsuarioServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UsuarioServiceImpl usuarioService;

    public JwtAuthFilter(JwtService jwtService, UsuarioServiceImpl usuarioService) {
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    @Override
    // intercepta uma requisicao
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String authorization = response.getHeader("Authorization"); // pega o valor enviado no header
        if (authorization != null && authorization.startsWith("Bearer")) { // verifica se o tpken esta no formato correto
            String token = authorization.split(" ")[1]; // pega o token
            boolean isValid = jwtService.tokenValido(token); // status do token
            if (isValid) { // valida o status
                String loginUsuario = jwtService.obterLoginUsuario(token); // pega o login do usuario
                UserDetails usuario = usuarioService.loadUserByUsername(loginUsuario); // pega os dados do usuario de acordo com o login
                // criar uma autenticacao web
                UsernamePasswordAuthenticationToken user =
                        new UsernamePasswordAuthenticationToken(
                                usuario,
                                null,
                                usuario.getAuthorities());
                // seta no contexto do spring security que se trata de uma aplicao web
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(user);
            }
        }
        filterChain.doFilter(request, response);
    }
}
