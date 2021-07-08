package com.carro.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository rep;
	
	public Optional<Carro> getCarrosById(Long id) {
		return rep.findById(id);
	}

	public Iterable<Carro> getCarrosByTipo(String tipo) {
		return rep.findByTipo(tipo);
	}
	
	public Iterable<Carro> getCarros() {
		return rep.findAll();
	}
	
	public List<Carro> getCarrosFake() {
		List<Carro> carros = new ArrayList<>();
		
		carros.add(new Carro(1L, "Fusca"));
		carros.add(new Carro(2L, "Brasilia"));
		carros.add(new Carro(3L, "Gol"));
		
		return carros;
	}

	public Carro save(Carro carro) {
		return rep.save(carro);
	}

	public Carro update(Carro carro, Long id) {
		Assert.notNull(id, "Não foi possível encontrar o carro!");
		
		Optional<Carro> optional = getCarrosById(id);
		if(optional.isPresent()) {
			Carro db = optional.get();
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			
			rep.save(db);
			return db;
		} else {
			throw new RuntimeException("Não foi possível atualizar o carro!");
		}
	}

	public String delete(Long id) {
		Optional<Carro> carro = getCarrosById(id);
		if(carro.isPresent()) {
			rep.deleteById(id);
			return "Carro deletado com sucesso.";
		} else {
			return "Carro não encontrado.";
		}
	}
	
	
}
