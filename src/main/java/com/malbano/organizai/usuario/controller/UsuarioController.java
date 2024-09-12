package com.malbano.organizai.usuario.controller;

import com.malbano.organizai.shared.util.UriLocationUtil;
import com.malbano.organizai.usuario.dto.UsuarioRequest;
import com.malbano.organizai.usuario.entity.Usuario;
import com.malbano.organizai.usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @Operation(summary = "Procurar Usuario por Id")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable("id") Long id) {
        Usuario usuario = service.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @Operation(summary = "Listar Usuarios")
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuario = service.listarUsuarios();
        return ResponseEntity.ok(usuario);
    }

    @Operation(summary = "Cadastra um Usuario")
    @PostMapping()
    public ResponseEntity<Void> insert(@RequestBody UsuarioRequest request) {
        Long usuarioId = service.cadastrarUsuario(request).getUsuarioId();
        return ResponseEntity.created(UriLocationUtil.buildLocationUri(usuarioId)).build();
    }

    @Operation(summary = "Editar um Usuario")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Long id, @RequestBody UsuarioRequest request) {
        service.editarUsuario(id, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Desativa um Usuario")
    @PatchMapping("/desativar/{id}")
    public ResponseEntity<?> desativar(@PathVariable("id") Long id) {
        service.desativarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Ativa um Usuario")
    @PatchMapping("/ativar/{id}")
    public ResponseEntity<?> ativar(@PathVariable("id") Long id) {
        service.ativarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
