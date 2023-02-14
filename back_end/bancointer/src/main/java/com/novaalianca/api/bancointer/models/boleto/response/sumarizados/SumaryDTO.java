package com.novaalianca.api.bancointer.models.boleto.response.sumarizados;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SumaryDTO {

	private PagosDTO pagos;
	private AbertosDTO abertos;
	private VencidosDTO vencidos;
	private CanceladosDTO cancelados;
	private ExpiradosDTO expirados;

}
