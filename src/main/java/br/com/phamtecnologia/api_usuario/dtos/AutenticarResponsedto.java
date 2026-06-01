package br.com.phamtecnologia.api_usuario.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record AutenticarResponsedto(
        UUID id,
        String nome,
        String email,
        String perfil,
        LocalDateTime dataHraAcesso,
        String token
) {
}
