package br.com.fiap.namastreta.exception;

// @ResponseStatus(code = HttpStatus.NOT_FOUND) tirei para testar no Handler
public class RestNotFoundException extends RuntimeException {

    public RestNotFoundException(String message) {
        super(message);
    }
// mostra as exceptions - aqui vem tudo junto.

}