package org.formacion.dual.v1.persistence.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class AutorTest {
	
	@Test
	void constructorNoArgs() {
		Autor autor = new Autor();
		
		assertNull(autor.getId());
		assertNull(autor.getNombre());
		assertNull(autor.getLibros());
	}

	@Test
	void constructorAllArgs() {
		UUID id = UUID.randomUUID();

		Autor autor = new Autor(id, "Test", Collections.emptyList());
		
		assertEquals(id, autor.getId());
		assertEquals("Test", autor.getNombre());
		assertTrue(autor.getLibros().isEmpty());
	}
	
	@Test
	void constructorNombre() {
		Autor autor = new Autor("Test");
		
		assertNull(autor.getId());
		assertEquals("Test", autor.getNombre());
		assertNull(autor.getLibros());
	}

}
