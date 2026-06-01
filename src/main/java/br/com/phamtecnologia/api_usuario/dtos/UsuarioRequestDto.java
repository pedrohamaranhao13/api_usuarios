package br.com.phamtecnologia.api_usuario.dtos;

public record UsuarioRequestDto(
        String nome,
        String email,
        String senha
) {
}
