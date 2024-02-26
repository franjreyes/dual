package org.formacion.dual.v1.dto.mapper;

import org.formacion.dual.v1.dto.AutorDto;
import org.formacion.dual.v1.persistence.model.Autor;
import java.util.Objects;

public class AutorEntityDtoMapper {

    public static Autor toEntity(AutorDto dto) {
    	if(Objects.nonNull(dto)) {
	        Autor entity = new Autor();
	        entity.setId(dto.getId());
	        entity.setNombre(dto.getNombre());
	        return entity;
    	}
    	return null;
    }

	public static AutorDto toDto(Autor entity) {
		return toDto(entity, false);
	}

	public static AutorDto toFullDto(Autor entity) {
		return toDto(entity, true);
	}

    private static AutorDto toDto(Autor entity, boolean conIdentificador) {
    	if(Objects.nonNull(entity)) {
	        AutorDto dto = new AutorDto();
			if(conIdentificador) {
				dto.setId(entity.getId());
			}
	        dto.setNombre(entity.getNombre());
	        return dto;
        }
    	return null;
    }
}
