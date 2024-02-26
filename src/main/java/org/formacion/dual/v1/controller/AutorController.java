package org.formacion.dual.v1.controller;

import jakarta.annotation.Nullable;
import org.formacion.dual.v1.dto.AutorDto;
import org.formacion.dual.v1.dto.LibroDto;
import org.formacion.dual.v1.dto.mapper.AutorEntityDtoMapper;
import org.formacion.dual.v1.dto.mapper.LibroEntityDtoMapper;
import org.formacion.dual.v1.persistence.model.Autor;
import org.formacion.dual.v1.persistence.model.Libro;
import org.formacion.dual.v1.service.AutorService;
import org.formacion.dual.v1.service.LibroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/autor")
public class AutorController {

    private final AutorService servicio;

    public AutorController(AutorService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public Page<AutorDto> getAutores(Pageable pageable) {
        return servicio.getAll(pageable)
                .map(AutorEntityDtoMapper::toFullDto);
    }

    @GetMapping("{id}")
    public AutorDto getAutor(@PathVariable UUID id) {
        return AutorEntityDtoMapper.toFullDto(servicio.get(id));
    }

    @PostMapping
    public AutorDto save(@RequestBody AutorDto autor) {
    	return toDto(servicio.save(toEntity(autor)));
    }

    @PutMapping("{id}")
    public AutorDto update(@RequestBody AutorDto autor, @PathVariable UUID id) throws RuntimeException {
        return toDto(servicio.save(toEntity(autor), id));
    }

    @DeleteMapping("{id}")
    public void deleteAutor(@PathVariable UUID id) {
        servicio.delete(id);
    }
    
    protected AutorDto toDto(Autor autor) {
    	return AutorEntityDtoMapper.toDto(autor);
    }
    
    protected Autor toEntity(AutorDto autor) {
    	return AutorEntityDtoMapper.toEntity(autor);
    }
    
}
