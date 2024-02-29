package org.formacion.dual.v1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.formacion.dual.v1.dto.LibroDto;
import org.formacion.dual.v1.dto.UsuarioDto;
import org.formacion.dual.v1.exception.ImagenException;
import org.formacion.dual.v1.persistence.model.Usuario;
import org.formacion.dual.v1.service.UsuarioService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

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

    private static LibroDto libroDto;

    @BeforeAll
    static void init() {
        libroDto = new LibroDto();
    }

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

        MvcResult result =  mockMvc.perform(
                        MockMvcRequestBuilders.post("/v1/libro")
                                .content(new ObjectMapper().writeValueAsString(libro))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.isbn").exists(),
                        jsonPath("$.titulo").value("Lógica de negocio y Spring boot"),
                        jsonPath("$.autores[0]").value("Dual"),
                        jsonPath("$.usuario.nombre").value("Juana")
                ).andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        this.libroDto.setIsbn(new ObjectMapper().readValue(contentAsString, LibroDto.class).getIsbn());
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

    @Test
    @Order(3)
    void obtenerLibro() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/libro/" + this.libroDto.getIsbn()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.isbn").value(this.libroDto.getIsbn().toString()),
                        jsonPath("$.autores[0]").value("Dual"),
                        jsonPath("$.usuario.nombre").value("Juana")
                );
    }

}
