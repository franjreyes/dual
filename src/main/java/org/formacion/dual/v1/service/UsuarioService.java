package org.formacion.dual.v1.service;

import org.formacion.dual.v1.exception.ImagenException;
import org.formacion.dual.v1.persistence.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {

    Usuario save(Usuario usuario) throws ImagenException;

    Usuario get(UUID usuario_id);

    Page<Usuario> getAll(Pageable pageable);

    Optional<Usuario> getByName(String usuarioName);

    Usuario save(Usuario entity, UUID id) throws ImagenException;

    void delete(UUID id);

}
