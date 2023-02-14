package com.novaalianca.api.bancointer.models.boleto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class PagadorDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String cpfCnpj; //CPF/CNPJ do pagador do título
	private String tipoPessoa; //FISICA  ou JURIDICA
	private String nome;
	private String endereco;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;
	private String email;
	private String ddd;
	private String telefone;






	

		
}
