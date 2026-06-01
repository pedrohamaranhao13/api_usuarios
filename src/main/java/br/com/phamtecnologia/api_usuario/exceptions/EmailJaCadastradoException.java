package br.com.phamtecnologia.api_usuario.exceptions;

public class EmailJaCadastradoException extends RuntimeException {

    @Override
    public String getMessage() {
        return "O e-mail informado já está cadastrado. Tende outro.";
    }

}
