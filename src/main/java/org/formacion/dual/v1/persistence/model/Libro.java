package org.formacion.dual.v1.persistence.model;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(indexes = {
        @Index(name = "libroUniqueIndex", columnList = "titulo", unique = true)
})
public class Libro {
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID isbn;

    private String titulo;

    @ManyToMany
    private List<Autor> autores;

    @ManyToOne
    private Usuario usuario;

    public Libro() {

    }

    public Libro(UUID isbn, String titulo, List<Autor> autores, Usuario usuario) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autores = autores;
        this.usuario = usuario;
    }

    public Libro(Usuario usuario) {
        this(null, null, null, usuario);
    }

    public UUID getIsbn() {
        return isbn;
    }

    public void setIsbn(UUID isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
