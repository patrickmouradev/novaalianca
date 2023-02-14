package com.novaalianca.api.bancointer.models.boleto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FiltroBoletoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String dataInicial;
	private String dataFinal;

	/*
	*Os filtros de data inicial e data final se aplicarão a:
	*VENCIMENTO - Filtro de data pelo vencimento. (Default)
	*EMISSAO - Filtro de data pela emissão.
	*SITUACAO - Filtro de data pela mudança de situação.
	* */
	private String filtrarDataPor;

	private String situacao; //EXPIRADO,VENCIDO,EMABERTO,PAGO,CANCELADO
	private String nome; //Filtro pelo nome do pagador

	private String email; //Filtro pelo email do pagador
	private String cpfCnpj; //Campo para informar o cpf ou cnpj do pagador
	private String itensPorPagina;
	private String paginaAtual;
	private String ordenarPor; // PAGADOR (Default) ; NOSSONUMERO; SEUNUMERO; DATASITUACAO;DATAVENCIMENTO;VALOR,STATUS

	private String tipoOrdenacao; // ASC - DESC
	private String nossoNumero;


}
