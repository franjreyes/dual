package org.formacion.dual.v1.persistence.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(indexes = {
        @Index(name = "usuarioUniqueIndex", columnList = "nombre", unique = true)
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nombre;

    @OneToMany(mappedBy = "usuario")
    private List<Libro> libros;

    public Usuario() {

    }

    public Usuario(UUID id, String nombre, List<Libro> libros) {
        this.id = id;
        this.nombre = nombre;
        this.libros = libros;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
