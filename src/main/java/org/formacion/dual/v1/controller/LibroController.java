package org.formacion.dual.v1.controller;

import jakarta.annotation.Nullable;
import org.formacion.dual.v1.dto.LibroDto;
import org.formacion.dual.v1.dto.mapper.LibroEntityDtoMapper;
import org.formacion.dual.v1.persistence.model.Libro;
import org.formacion.dual.v1.service.LibroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/libro")
public class LibroController {

    private final LibroService servicio;

    public LibroController(LibroService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public Page<LibroDto> getLibros(Pageable pageable, @Nullable @Param("usuario") String usuario) {
        return servicio.getAll(pageable, usuario)
                .map(LibroEntityDtoMapper::toListDto);
    }

    @GetMapping("{isbn}")
    public LibroDto getLibro(@PathVariable UUID isbn) {
        return LibroEntityDtoMapper.toDto(servicio.get(isbn));
    }

    @PostMapping
    public LibroDto saveLibro(@RequestBody LibroDto libro) {
        return toDto(servicio.save(toEntity(libro)));
    }

    @PutMapping("{isbn}")
    public LibroDto editLibro(@RequestBody LibroDto libro, @PathVariable UUID isbn) {
        return toDto(servicio.save(toEntity(libro), isbn));
    }

    @DeleteMapping("{isbn}")
    public void deleteLibro(@PathVariable UUID isbn) {
        servicio.delete(isbn);
    }

    protected LibroDto toDto(Libro libro) {
        return LibroEntityDtoMapper.toDto(libro);
    }

    protected Libro toEntity(LibroDto libro) {
        return LibroEntityDtoMapper.toEntity(libro);
    }

    @GetMapping("inicio-db")
    public void inicioDB() {
        servicio.initDB();
    }

}
