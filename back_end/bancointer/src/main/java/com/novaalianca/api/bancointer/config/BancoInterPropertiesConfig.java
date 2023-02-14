package com.novaalianca.api.bancointer.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class BancoInterPropertiesConfig {

	@Value("${banco.inter.url.token}")
	private String urlBancoInterToken;
	@Value("${banco.inter.url.boletos}")
	private String urlBancoInterBoletos;
	@Value("${banco.inter.url.extrato}")
	private String urlBancoInterExtrato;
	
	@Value("${banco.inter.caminho.certificado}")
	private String caminhoCertificado;
	
	@Value("${banco.inter.senha.certificado}")
	private String senhaCertificado;

	@Value("${banco.inter.client.id}")
	private String clientId;

	@Value("${banco.inter.client.secret}")
	private String clientSecret;

	@Value("${banco.inter.grant.type}")
	private String grantType;
	

}
