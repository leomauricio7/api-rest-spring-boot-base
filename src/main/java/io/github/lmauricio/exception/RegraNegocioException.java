package io.github.lmauricio.exception;

// classe que reprensta um exception e informa qual foi o erro de negocio
public class RegraNegocioException extends RuntimeException{
    public RegraNegocioException(String message) {
        super(message);
    }
}
