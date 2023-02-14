package com.novaalianca.api.bancointer.services;

import com.google.gson.Gson;
import com.novaalianca.api.bancointer.config.BancoInterPropertiesConfig;
import com.novaalianca.api.bancointer.models.token.TokenResponseDTO;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private BancoInterPropertiesConfig propertiesConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenService.class);

    public TokenResponseDTO getToken(){
        Gson gson = new Gson();
        String urlArquivo = getClass().getResource("../").toString();
        LOGGER.info("urlArquivo1 : {}", urlArquivo);
        String webDir = "bancointer/";
        urlArquivo = urlArquivo.substring(6, urlArquivo.indexOf(webDir)+webDir.length());

        urlArquivo = urlArquivo + propertiesConfig.getCaminhoCertificado();

        urlArquivo = urlArquivo.replace("/", "//");
        LOGGER.info("urlArquivo2 : {}", urlArquivo);

        //Unirest.config().clientCertificateStore("D:\Estudos\Projeto Nova Alianca Refatorado\novaalianca\back_end\bancointer\src\main\resources\certs","140908");
        Unirest.config().clientCertificateStore(urlArquivo ,propertiesConfig.getSenhaCertificado());
        HttpResponse<TokenResponseDTO> response = Unirest.post("https://cdpj.partners.bancointer.com.br/oauth/v2/token")
                .multiPartContent()
                .field("client_id", propertiesConfig.getClientId())
                .field("client_secret", propertiesConfig.getClientSecret())
                .field("grant_type", propertiesConfig.getGrantType())
                .field("scope", "boleto-cobranca.read")
                .asObject(TokenResponseDTO.class);

        LOGGER.info("Response : {}", gson.toJson(response.getBody()));
        return response.getBody();
    }
}
