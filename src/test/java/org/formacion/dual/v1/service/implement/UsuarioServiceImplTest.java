package org.formacion.dual.v1.service.implement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import java.util.UUID;

import org.formacion.dual.v1.exception.ImagenException;
import org.formacion.dual.v1.persistence.model.Usuario;
import org.formacion.dual.v1.persistence.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {
	
    @Spy
    @InjectMocks
    UsuarioServiceImpl usuarioServiceImpl;
    
    @Mock
    UsuarioRepository repositorio;
    
    static Usuario usuario;
    
    @BeforeAll
    static void init() {
    	usuario = mock(Usuario.class);
    }

	@Test
	void guargaUsuario() throws ImagenException {
		doReturn(usuario).when(repositorio).save(usuario);
		
		usuarioServiceImpl.save(usuario);
		
		verify(repositorio).save(usuario);
	}
	
	@Test
	void obtenerUsuario() {
		UUID id = UUID.randomUUID();
		Optional<Usuario> optional = Optional.of(usuario);
		
		doReturn(optional).when(repositorio).findById(id);
		
		Usuario resultado = usuarioServiceImpl.get(id);
		
		verify(repositorio).findById(id);
		
		assertEquals(optional.get(), resultado);
	}

    @Test
	void obtenerUsuarios() {
    	Page page = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        doReturn(page).when(repositorio).findAll(pageable);
        
        Page<Usuario> resultado = usuarioServiceImpl.getAll(pageable);
        
        verify(repositorio).findAll(pageable);
        
        assertEquals(page, resultado);
    }
}
