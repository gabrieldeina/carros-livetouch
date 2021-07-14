package com.carro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
		Optional<CarroDTO> op = service.getCarrosById(id);
		assertTrue(op.isPresent());
		
		c = op.get();
		assertEquals("Ferrari", c.getNome());
		assertEquals("esportivos", c.getTipo());
		
		// Delete object
		service.delete(id);
		
		assertFalse(service.getCarrosById(id).isPresent());
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
		Optional<CarroDTO> op = service.getCarrosById(11L);
		
		assertTrue(op.isPresent());
		
		CarroDTO c = op.get();
		
		assertEquals("Ferrari FF", c.getNome());
	}

}
