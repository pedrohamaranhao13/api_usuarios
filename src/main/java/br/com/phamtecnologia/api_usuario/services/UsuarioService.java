package br.com.phamtecnologia.api_usuario.services;

import br.com.phamtecnologia.api_usuario.components.CryptoComponent;
import br.com.phamtecnologia.api_usuario.dtos.UsuarioRequestDto;
import br.com.phamtecnologia.api_usuario.dtos.UsuarioResponseDto;
import br.com.phamtecnologia.api_usuario.entities.Usuario;
import br.com.phamtecnologia.api_usuario.enums.Perfil;
import br.com.phamtecnologia.api_usuario.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CryptoComponent cryptoComponent;

    public UsuarioResponseDto criarUsuario (UsuarioRequestDto request) {

        var usuario = new Usuario();

        usuario.setNome((request.nome()));
        usuario.setEmail(request.email());
        usuario.setSenha(cryptoComponent.getSha256(request.senha()));
        usuario.setPerfil(Perfil.OPERADOR);

        usuarioRepository.save(usuario);

        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                LocalDateTime.now(),
                usuario.getPerfil().toString()
        );
    }
}
