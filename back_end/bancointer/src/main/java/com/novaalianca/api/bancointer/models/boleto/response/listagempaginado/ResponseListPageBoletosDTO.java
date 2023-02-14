package com.novaalianca.api.bancointer.models.boleto.response.listagempaginado;

import com.novaalianca.api.bancointer.models.boleto.BoletoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@AllArgsConstructor
public class ResponseListPageBoletosDTO {

	private int totalPages;
	private int totalElements;
	private boolean last;
	private boolean first;
	private int size;
	private int numberOfElements;
	private List<BoletoDTO> content;

}
