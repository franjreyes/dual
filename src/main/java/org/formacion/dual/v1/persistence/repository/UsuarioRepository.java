package org.formacion.dual.v1.persistence.repository;

import org.formacion.dual.v1.persistence.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

	Optional<Usuario> findByNombreIgnoreCase(String usuarioName);
}
