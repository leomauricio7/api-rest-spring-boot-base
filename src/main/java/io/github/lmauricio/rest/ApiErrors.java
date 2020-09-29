package io.github.lmauricio.rest;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

// class que vai receber os erros da api
public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public ApiErrors(String mensagemErro) {
        this.errors = Arrays.asList(mensagemErro);
    }
}
