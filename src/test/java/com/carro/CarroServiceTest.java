package com.carro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.carro.api.exception.ObjectNotFoundException;
import com.carro.domain.Carro;
import com.carro.domain.CarroService;
import com.carro.domain.dto.CarroDTO;

@SpringBootTest
class CarroServiceTest {
	
	@Autowired
	private CarroService service;

	@Test
	public void testSave() {
		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("esportivos");
		
		CarroDTO c = service.save(carro);
		
		assertNotNull(c);
		
		Long id = c.getId();
		assertNotNull(id);
		
		// Find object
		c = service.getCarrosById(id);
		assertNotNull(c);
		
		assertEquals("Ferrari", c.getNome());
		assertEquals("esportivos", c.getTipo());
		
		// Delete object
		service.delete(id);
		
		try {
			assertNull(service.getCarrosById(id));
			fail("O carro não foi excluído");
		} catch (ObjectNotFoundException e) {
			// Ok
		}
	}
	
	@Test
	public void testLista() {
		List<CarroDTO> carros = service.getCarros();
		
		assertEquals(30, carros.size());
	}
	
	@Test
	public void testListaPorTipo() {
		assertEquals(10, service.getCarrosByTipo("classicos").size());
		assertEquals(10, service.getCarrosByTipo("esportivos").size());
		assertEquals(10, service.getCarrosByTipo("luxo").size());
	}
	
	@Test
	public void testGet() {
		CarroDTO c = service.getCarrosById(11L);
		
		assertNotNull(c);
		
		assertEquals("Ferrari FF", c.getNome());
	}

}
