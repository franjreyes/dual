package org.formacion.dual.v1.controller;

import org.formacion.dual.v1.dto.UsuarioDto;
import org.formacion.dual.v1.dto.mapper.UsuarioEntityDtoMapper;
import org.formacion.dual.v1.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/usuario")
public class UsuarioController {

    private final UsuarioService servicio;

    public UsuarioController(UsuarioService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public Page<UsuarioDto> getUsuarios(Pageable pageable) {
        return servicio.getAll(pageable).map(UsuarioEntityDtoMapper::toDto);
    }

    @GetMapping("{id}")
    public UsuarioDto getUsuario(@PathVariable UUID id) {
        return UsuarioEntityDtoMapper.toDto(servicio.get(id));
    }

    @PostMapping
    public UsuarioDto save(@RequestBody UsuarioDto usuario) {
        return UsuarioEntityDtoMapper.toDto(servicio.save(UsuarioEntityDtoMapper.toEntity(usuario)));
    }

    @PutMapping("{id}")
    public UsuarioDto update(@RequestBody UsuarioDto usuario, @PathVariable UUID id) {
        return UsuarioEntityDtoMapper.toDto(servicio.save(UsuarioEntityDtoMapper.toEntity(usuario), id));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable UUID id) {
        servicio.delete(id);
    }

}
