package com.carro.domain.dto;

import org.modelmapper.ModelMapper;

import com.carro.domain.Carro;

import lombok.Data;

@Data
public class CarroDTO {

	private long id;
	private String nome;
	private String tipo;
	private String descricao;
	private String urlFoto;
	private String urlVideo;
	private String latitude;
	private String longitude;

	public static CarroDTO create(Carro c) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(c, CarroDTO.class);
	}

}
