package org.formacion.dual.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;
import java.util.UUID;

public class LibroDto {
	
	private UUID isbn;
	
    private String titulo;

    @JsonInclude(Include.NON_NULL)
    private List<String> autores;

    @JsonInclude(Include.NON_NULL)
    private UsuarioDto usuario;

    public LibroDto() {
    }

    public LibroDto(UUID isbn, String titulo, List<String> autores, UsuarioDto usuario) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autores = autores;
        this.usuario = usuario;
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

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }
}
