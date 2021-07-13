package com.carro.domain.dto;

import org.modelmapper.ModelMapper;

import com.carro.domain.Carro;

import lombok.Data;

@Data
public class CarroDTO {
	
	private long id;
	private String nome;
	private String tipo;
	
	public static CarroDTO create(Carro c) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(c, CarroDTO.class);
	}

}
