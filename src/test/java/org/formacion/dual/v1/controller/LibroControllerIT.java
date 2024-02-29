package org.formacion.dual.v1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.formacion.dual.v1.dto.LibroDto;
import org.formacion.dual.v1.dto.UsuarioDto;
import org.formacion.dual.v1.exception.ImagenException;
import org.formacion.dual.v1.persistence.model.Usuario;
import org.formacion.dual.v1.service.UsuarioService;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class LibroControllerIT {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void guardaLibroOk() throws Exception, ImagenException {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juana");
        usuarioService.save(usuario);

        LibroDto libro = new LibroDto();
        libro.setTitulo("Lógica de negocio y Spring boot");
        libro.setAutores(Collections.singletonList("Dual"));
        libro.setUsuario(new UsuarioDto("Juana", null));

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/v1/libro")
                                .content(new ObjectMapper().writeValueAsString(libro))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.isbn").exists(),
                        jsonPath("$.titulo").value("Lógica de negocio y Spring boot")
                );
    }

    @Test
    @Order(2)
    void obtenerLibros() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/libro").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk()
                );
    }

}
