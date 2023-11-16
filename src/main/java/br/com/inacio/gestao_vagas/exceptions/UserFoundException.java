package br.com.inacio.gestao_vagas.exceptions;

public class UserFoundException extends RuntimeException {
    
    public UserFoundException() {

        super("Ususario já existe");
    }
}
