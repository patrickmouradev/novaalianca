package com.novaalianca.api.bancointer.models.boleto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BoletoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String nomeBeneficiario;
	private String cnpjCPFBeneficiario;
	private String tipoPessoaBeneficiario;
	private String contaCorrente;
	private String nossoNumero;
	private String seuNumero;
	private PagadorDTO pagador;

	private String situacao;
	private String dataHoraSituacao; //Formato aceito: YYYY-MM-DD
	private String dataVencimento; //Formato aceito: YYYY-MM-DD
	private Float valorNominal;
	private String dataEmissao;//Formato aceito: YYYY-MM-DD
	private String dataLimite;//Formato aceito: YYYY-MM-DD
	private String codigoEspecie;
	private String codigoBarras;
	private String linhaDigitavel;

	private String origem; //Origem da requisição que gerou o boleto.

	private MensagemDTO mensagem;
	private DescontoDTO desconto1;
	private DescontoDTO desconto2;
	private DescontoDTO desconto3;
	private MultaDTO multa;
	private MoraDTO mora;

	
	
	public BoletoDTO() {
		pagador = new PagadorDTO();
		mensagem = new MensagemDTO();
		desconto1 = new DescontoDTO();
		desconto2 = new DescontoDTO();
		desconto3 = new DescontoDTO();
		multa = new MultaDTO();
		mora = new MoraDTO();
		
	}
	
	
	
}
