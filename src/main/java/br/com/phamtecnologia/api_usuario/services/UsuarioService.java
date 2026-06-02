package br.com.phamtecnologia.api_usuario.services;

import br.com.phamtecnologia.api_usuario.components.CryptoComponent;
import br.com.phamtecnologia.api_usuario.components.JwtTokenComponent;
import br.com.phamtecnologia.api_usuario.dtos.AutenticarRequestDto;
import br.com.phamtecnologia.api_usuario.dtos.AutenticarResponsedto;
import br.com.phamtecnologia.api_usuario.dtos.UsuarioRequestDto;
import br.com.phamtecnologia.api_usuario.dtos.UsuarioResponseDto;
import br.com.phamtecnologia.api_usuario.entities.Usuario;
import br.com.phamtecnologia.api_usuario.enums.Perfil;
import br.com.phamtecnologia.api_usuario.exceptions.AcessoNegadoException;
import br.com.phamtecnologia.api_usuario.exceptions.EmailJaCadastradoException;
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

    @Autowired
    private JwtTokenComponent jwtTokenComponent;

    public UsuarioResponseDto criarUsuario (UsuarioRequestDto request) {

        if (usuarioRepository.existsByEmail(request.email())) {
            throw new EmailJaCadastradoException();
        }

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

    public AutenticarResponsedto autenticarUsuario (AutenticarRequestDto request) {

        var email = request.email();
        var senha = cryptoComponent.getSha256(request.senha());

        var usuario = usuarioRepository.findByEmailAndSenha(email, senha);

        if (usuario == null) {
            throw new AcessoNegadoException();
        }

        var token = jwtTokenComponent.getToken(usuario.getId(), usuario.getEmail(),
                usuario.getPerfil().toString());

        return new AutenticarResponsedto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil().toString(),
                LocalDateTime.now(),
                token
        );
    }
}
