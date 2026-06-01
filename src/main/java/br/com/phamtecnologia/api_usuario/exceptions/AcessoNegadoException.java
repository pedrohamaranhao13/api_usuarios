package br.com.phamtecnologia.api_usuario.exceptions;

public class AcessoNegadoException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Acesso negado. Usuário inválido.";
    }
}
