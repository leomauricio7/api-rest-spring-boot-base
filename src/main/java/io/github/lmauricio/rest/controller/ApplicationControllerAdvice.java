package io.github.lmauricio.rest.controller;

import io.github.lmauricio.exception.PedidoNaoEncontradoException;
import io.github.lmauricio.exception.RegraNegocioException;
import io.github.lmauricio.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// classe de controler dos exception handlers= metodos de erros lancados
//@ControllerAdvice
@RestControllerAdvice // a diferenca para o de cima é que o @RestControllerAdvice tem o @ResponseBody
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class) // diz que o metodo é um tratador exception/errors
    @ResponseStatus(HttpStatus.BAD_REQUEST) // status que o metodo ira retornar
    //@ResponseBody
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException( PedidoNaoEncontradoException ex){
        return new ApiErrors(ex.getMessage());
    }

    // metodo para tratar os erros/exeception de validacoes
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex){

        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map( erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErrors(errors);
    }

}
