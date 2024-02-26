package org.formacion.dual.v1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

public class AutorDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID id;

    private String nombre;

    public AutorDto() {
    }

    public AutorDto(String nombre) {
        this.nombre = nombre;
    }

    public AutorDto(UUID id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
}

