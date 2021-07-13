package com.carro.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.carro.domain.dto.CarroDTO;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository rep;
	
	public List<CarroDTO> getCarros() {
		return rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
//		List<Carro> carros = new ArrayList<>();
		
//		List<CarroDTO> list = carros.stream().map(CarroDTO::new).collect(Collectors.toList());
				
//		List<CarroDTO> list = new ArrayList<>();
//		for(Carro c: carros) {
//			list.add(new CarroDTO(c));

		
//		return list;
		}
	
	public Optional<CarroDTO> getCarrosById(Long id) {
//		Optional<Carro> carro = rep.findById(id);
//		if(carro.isPresent()) {
//			return Optional.of(new CarroDTO(carro.get()));
//		}
//		return null; 
		return rep.findById(id).map(CarroDTO::create);
	}

	public List<CarroDTO> getCarrosByTipo(String tipo) {
		return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
	}

	public Carro save(Carro carro) {
		return rep.save(carro);
	}

	public CarroDTO update(Carro carro, Long id) {
		Assert.notNull(id, "Não foi possível encontrar o carro!");
		
		Optional<CarroDTO> optional = getCarrosById(id);
		if(optional.isPresent()) {
			CarroDTO db = optional.get();
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			
			rep.save(db);
			return db;
		} else {
			throw new RuntimeException("Não foi possível atualizar o carro!");
		}
	}

	public String delete(Long id) {
		if(getCarrosById(id).isPresent()) {
			rep.deleteById(id);
			return "Carro deletado com sucesso.";
		} else {
			return "Carro não encontrado.";
		}
	}
	
//	public List<Carro> getCarrosFake() {
//	List<Carro> carros = new ArrayList<>();
//	
//	carros.add(new Carro(1L, "Fusca"));
//	carros.add(new Carro(2L, "Brasilia"));
//	carros.add(new Carro(3L, "Gol"));
//	
//	return carros;
//}
}
