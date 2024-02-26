package org.formacion.dual.v1.service.implement;

import org.apache.commons.lang3.StringUtils;
import org.formacion.dual.v1.persistence.model.Autor;
import org.formacion.dual.v1.persistence.model.Libro;
import org.formacion.dual.v1.persistence.model.Usuario;
import org.formacion.dual.v1.persistence.repository.LibroRepository;
import org.formacion.dual.v1.service.AutorService;
import org.formacion.dual.v1.service.LibroService;
import org.formacion.dual.v1.service.UsuarioService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository repositorio;
    private final AutorService servicioAutor;
    private final UsuarioService servicioUsuario;

    public LibroServiceImpl(LibroRepository repositorio, AutorService servicioAutor, UsuarioService servicioUsuario) {
        this.repositorio = repositorio;
        this.servicioAutor = servicioAutor;
        this.servicioUsuario = servicioUsuario;
    }

    @Transactional
    public Libro save(Libro libro) {
        libro.getAutores().forEach(autor -> {
            servicioAutor.getByName(autor.getNombre()).ifPresent(resultado -> {
                autor.setId(resultado.getId());
            });
        });
        
        Optional<Usuario> usuario = Optional.ofNullable(libro.getUsuario());
        
        usuario.ifPresent(u -> servicioUsuario.getByName(u.getNombre()).ifPresent(resultado -> u.setId(resultado.getId())));
        
        servicioAutor.saveAll(libro.getAutores());
        return repositorio.save(libro);
    }

    @Override
    public Libro save(Libro libro, UUID isbn) {
        Libro dbo = repositorio.findById(isbn).orElseThrow();
        libro.setIsbn(dbo.getIsbn());
        return repositorio.save(libro);
    }

    public Libro get(UUID isbn) {
        return repositorio.findById(isbn).orElse(null);
    }


    @Override
    public Page<Libro> getAll(Pageable pageable, String usuarioName) {
        Usuario usuario = StringUtils.isNotEmpty(usuarioName) ? servicioUsuario.getByName(usuarioName).orElseThrow() : null;
        Example<Libro> example = Example.of(new Libro(usuario));
        return repositorio.findAll(example, pageable);
    }

    @Override
    public void delete(UUID isbn) {
        repositorio.deleteById(isbn);
    }

	@Override
    @Transactional
	public Integer initDB() {
		Usuario luigi = new Usuario();
		luigi.setNombre("Luigi");
		servicioUsuario.save(luigi);
		Libro libro1 = new Libro();
		libro1.setAutores(Collections.singletonList(new Autor("Miguel de Cervantes")));
		libro1.setTitulo("Don Quijote");
		libro1.setUsuario(luigi);		
		save(libro1);
		
		Libro libro2 = new Libro();
		libro2.setAutores(Collections.singletonList(new Autor("Federico García Lorca")));
		libro2.setTitulo("Romancero gitano");
		libro2.setUsuario(luigi);
		save(libro2);
		
		Libro libro3 = new Libro();
		libro3.setAutores(Collections.singletonList(new Autor("Lope de Vega")));
		libro3.setTitulo("La Dorotea");
		save(libro3);
		
		return 1;
	}
}
