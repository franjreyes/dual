package org.formacion.dual.v1.persistence.repository;

import org.formacion.dual.v1.persistence.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LibroRepository extends JpaRepository<Libro, UUID> {
}
