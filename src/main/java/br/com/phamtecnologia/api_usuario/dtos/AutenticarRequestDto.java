package br.com.phamtecnologia.api_usuario.dtos;

public record AutenticarRequestDto(
        String email,
        String senha
) {
}
