package org.formacion.dual.v1.persistence.repository;

import org.formacion.dual.v1.persistence.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
    Optional<Autor> findByNombreIgnoreCase(String autorName);

    List<Autor> findAllByNombreContaining(String nombre);
}
