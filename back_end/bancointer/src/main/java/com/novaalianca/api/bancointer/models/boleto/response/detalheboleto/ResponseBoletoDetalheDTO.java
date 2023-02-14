package com.novaalianca.api.bancointer.models.boleto.response.detalheboleto;

import com.novaalianca.api.bancointer.models.boleto.BoletoDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ResponseBoletoDetalheDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private BoletoDTO boleto;


	public ResponseBoletoDetalheDTO() {
		boleto = new BoletoDTO();

	}
	
}
