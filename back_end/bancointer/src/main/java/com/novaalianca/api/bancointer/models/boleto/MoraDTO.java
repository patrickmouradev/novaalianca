package com.novaalianca.api.bancointer.models.boleto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class MoraDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String codigo;
	private String data;
	private Float valor;
	private Float taxa;

	
}
