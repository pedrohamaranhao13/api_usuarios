package br.com.phamtecnologia.api_usuario.controllers;

import br.com.phamtecnologia.api_usuario.dtos.AutenticarRequestDto;
import br.com.phamtecnologia.api_usuario.dtos.UsuarioRequestDto;
import br.com.phamtecnologia.api_usuario.entities.Usuario;
import br.com.phamtecnologia.api_usuario.exceptions.AcessoNegadoException;
import br.com.phamtecnologia.api_usuario.exceptions.EmailJaCadastradoException;
import br.com.phamtecnologia.api_usuario.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("criar")
    public ResponseEntity<?> post(@RequestBody UsuarioRequestDto request) {

        try {
            var response = usuarioService.criarUsuario(request);
            return ResponseEntity.status(201).body(response);
        }
        catch (EmailJaCadastradoException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("autenticar")
    public ResponseEntity<?> autenticar(@RequestBody AutenticarRequestDto request) {

        try {
            var response = usuarioService.autenticarUsuario(request);
            return ResponseEntity.status(200).body(response);
        }
        catch (AcessoNegadoException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
