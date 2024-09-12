package com.malbano.organizai.shared.exception;

public class PerfilAssociadoException extends RuntimeException {
    public PerfilAssociadoException(Long perfilId) {
        super("Perfil com id: " + perfilId + " não pode ser deletado, pois está associado a um ou mais usuários.");
    }
}
