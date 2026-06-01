package br.com.phamtecnologia.api_usuario.controllers;

import br.com.phamtecnologia.api_usuario.dtos.UsuarioRequestDto;
import br.com.phamtecnologia.api_usuario.entities.Usuario;
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

        var response = usuarioService.criarUsuario(request);
        return ResponseEntity.status(201).body(response);
    }
}
