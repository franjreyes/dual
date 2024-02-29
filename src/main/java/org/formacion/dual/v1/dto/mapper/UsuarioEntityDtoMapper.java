package org.formacion.dual.v1.dto.mapper;

import org.formacion.dual.v1.dto.UsuarioDto;
import org.formacion.dual.v1.persistence.model.Usuario;

import java.util.Objects;

public class UsuarioEntityDtoMapper {

    public static Usuario toEntity(UsuarioDto dto) {
    	if(Objects.nonNull(dto)) {
	        Usuario entity = new Usuario();
	        entity.setNombre(dto.getNombre());
			entity.setImagen(dto.getImagen());
	        return entity;
    	}
    	return null;
    }

    public static UsuarioDto toDto(Usuario entity) {
    	if(Objects.nonNull(entity)) {
	        UsuarioDto dto = new UsuarioDto();
	        dto.setNombre(entity.getNombre());
			dto.setImagen(entity.getImagen());
	        return dto;
    	}
    	return null;
    }
}
