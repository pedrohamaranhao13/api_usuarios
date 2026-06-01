package br.com.phamtecnologia.api_usuario.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponseDto(
        UUID id,
        String nome,
        String email,
        LocalDateTime dataHora,
        String perfil
) {
}
