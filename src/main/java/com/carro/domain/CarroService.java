package com.carro.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.carro.domain.dto.CarroDTO;
import com.carro.api.exception.ObjectNotFoundException;

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

	public CarroDTO getCarrosById(Long id) {
		Optional<Carro> carro = rep.findById(id);

		return carro.map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
		//		if(carro.isPresent()) {
		//			return Optional.of(new CarroDTO(carro.get()));
		//		}
		//		return null;
	}

	public List<CarroDTO> getCarrosByTipo(String tipo) {
		return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
	}

	public CarroDTO save(Carro carro) {
		Assert.isNull(carro.getId(), "Não foi possível inserir o registro");
		return CarroDTO.create(rep.save(carro));
	}

	public Carro update(Carro carro, Long id) {
		Assert.notNull(id, "Não foi possível encontrar o carro!");

		Optional<Carro> optional = rep.findById(id);
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

	public void delete(Long id) {
		rep.deleteById(id);
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
