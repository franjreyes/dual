package org.formacion.dual.v1.service;

import org.formacion.dual.v1.exception.ImagenException;
import org.formacion.dual.v1.persistence.model.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface LibroService {

    Libro save(Libro libro);

    Libro save(Libro libro, UUID isbn);

    Libro get(UUID isbn);

    Page<Libro> getAll(Pageable pageable, String usuario);

	void initDB() throws ImagenException;

    void delete(UUID isbn);
}
