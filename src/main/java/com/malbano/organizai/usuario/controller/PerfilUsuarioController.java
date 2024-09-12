package com.malbano.organizai.usuario.controller;

import com.malbano.organizai.shared.util.UriLocationUtil;
import com.malbano.organizai.usuario.dto.PerfilUsuarioRequest;
import com.malbano.organizai.usuario.entity.PerfilUsuario;
import com.malbano.organizai.usuario.service.PerfilUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfil-usuario")
public class PerfilUsuarioController {

    PerfilUsuarioService service;

    @Autowired
    public PerfilUsuarioController(PerfilUsuarioService service) {
        this.service = service;
    }

    @Operation(summary = "Procurar Perfil Usuario por Id")
    @GetMapping("/{id}")
    public ResponseEntity<PerfilUsuario> buscarPorId(@PathVariable("id") Long id) {
        PerfilUsuario perfilUsuario = service.buscarPerfilUsuarioPorId(id);
        return ResponseEntity.ok(perfilUsuario);
    }

    @Operation(summary = "Listar Perfis Usuario")
    @GetMapping
    public ResponseEntity<List<PerfilUsuario>> listar() {
        List<PerfilUsuario> perfilUsuario = service.listarPerfisUsuario();
        return ResponseEntity.ok(perfilUsuario);
    }

    @Operation(summary = "Cadastra um Perfil Usuario")
    @PostMapping()
    public ResponseEntity<Void> insert(@RequestBody PerfilUsuarioRequest request) {
        Long perfilId = service.cadastrarPerfilUsuario(request).getPerfilId();
        return ResponseEntity.created(UriLocationUtil.buildLocationUri(perfilId)).build();
    }

    @Operation(summary = "Editar um Perfil Usuario")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Long id, @RequestBody PerfilUsuarioRequest request) {
        service.editarPerfilUsuario(id, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletar um Perfil Usuario")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.deletarPerfilUsuario(id);
        return ResponseEntity.accepted().build();
    }
}
