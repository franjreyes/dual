package org.formacion.dual.v1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class UsuarioDto {
    private String nombre;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String imagen;

    public UsuarioDto() {

    }

    public UsuarioDto(String nombre, String imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
