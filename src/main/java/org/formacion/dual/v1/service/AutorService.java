package org.formacion.dual.v1.service;

import org.formacion.dual.v1.persistence.model.Autor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AutorService {

    Autor save(Autor autor);

    Autor save(Autor autor, UUID id) throws RuntimeException;

    Autor get(UUID autor_id);

    Page<Autor> getAll(Pageable pageable);

    Optional<Autor> getByName(String autorName);

    void saveAll(List<Autor> autores);

    void delete(UUID id);

    List<Autor> findAllByNombreContaining(String nombre) throws Throwable;
}
