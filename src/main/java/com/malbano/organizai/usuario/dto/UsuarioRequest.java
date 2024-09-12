package com.malbano.organizai.usuario.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UsuarioRequest(
        Long perfilId,
        String nomeUsuario,
        LocalDate dataNascimento,
        String email,
        String senha,
        BigDecimal rendaFixa
) {}
