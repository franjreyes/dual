package org.formacion.dual.v1.dto.mapper;

import org.formacion.dual.v1.dto.LibroDto;
import org.formacion.dual.v1.persistence.model.Autor;
import org.formacion.dual.v1.persistence.model.Libro;

import java.util.Objects;
import java.util.stream.Collectors;

public class LibroEntityDtoMapper {

    public static Libro toEntity(LibroDto dto) {
    	if(Objects.nonNull(dto)) {
	        Libro entity = new Libro();
	        entity.setIsbn(dto.getIsbn());
	        entity.setTitulo(dto.getTitulo());
	        entity.setAutores(
	                dto.getAutores()
	                        .stream()
	                        .map(Autor::new)
	                        .collect(Collectors.toList()));
	        entity.setUsuario(UsuarioEntityDtoMapper.toEntity(dto.getUsuario()));
	        return entity;
    	}
    	return null;
    }
	public static LibroDto toListDto(Libro entity) {
		return toDto(entity, false);
	}

	public static LibroDto toDto(Libro entity) {
		return toDto(entity, true);
	}

    public static LibroDto toDto(Libro entity, boolean conAutores) {
    	if(Objects.nonNull(entity)) {
	        LibroDto dto = new LibroDto();
	        dto.setIsbn(entity.getIsbn());
	        dto.setTitulo(entity.getTitulo());
			if(conAutores) {
				dto.setAutores(entity.getAutores().stream().map(Autor::getNombre).collect(Collectors.toList()));
			}
	        dto.setUsuario(UsuarioEntityDtoMapper.toDto(entity.getUsuario()));
	        return dto;
    	}
    	return null;
    }
}
