package com.novaalianca.api.bancointer.models.boleto.response.sumarizados;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CanceladosDTO {
	
	private Integer quantidade;
	private Float valor;
}
