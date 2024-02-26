package org.formacion.dual.v1.dto;

public class UsuarioDto {
    private String nombre;

    public UsuarioDto() {

    }

    public UsuarioDto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
