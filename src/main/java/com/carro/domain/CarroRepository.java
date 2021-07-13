package com.carro.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carro.domain.dto.CarroDTO;

public interface CarroRepository extends JpaRepository<Carro, Long>{
	List<Carro> findByTipo(String tipo);

	void save(CarroDTO db);
}
