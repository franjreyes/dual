package org.formacion.dual.v1.service.implement;

import org.formacion.dual.v1.persistence.model.Autor;
import org.formacion.dual.v1.persistence.repository.AutorRepository;
import org.formacion.dual.v1.service.AutorService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AutorServiceImpl implements AutorService {

    private final AutorRepository repositorio;

    public AutorServiceImpl(AutorRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Transactional
    public Autor save(Autor autor) {
        autor.setNombre("elmismo");
        return repositorio.save(autor);
    }

    @Override
    @Transactional
    public Autor save(Autor autor, UUID id) {
        Optional<Autor> dbo = repositorio.findById(id);
        dbo.ifPresent(a -> autor.setId(a.getId()));
        return repositorio.save(autor);
    }

    public Autor get(UUID autor_id) {
        return repositorio.findById(autor_id).orElse(null);
    }

    @Override
    public Page<Autor> getAll(Pageable pageable) {
        return repositorio.findAll(pageable);
    }

    @Override
    public Optional<Autor> getByName(String autorName) {
        return repositorio.findByNombreIgnoreCase(autorName);
    }

    @Override
    @Transactional
    public void saveAll(List<Autor> autores) {
        autores.forEach(this::save);
    }

    @Override
    public void delete(UUID id) {
        repositorio.deleteById(id);
    }

    @Override
    public List<Autor> findAllByNombreContaining(String nombre) throws Throwable {
        return repositorio.findAllByNombreContaining(nombre);
    }
}
