package com.novaalianca.api.bancointer.services;

import com.novaalianca.api.bancointer.config.BancoInterPropertiesConfig;
import com.novaalianca.api.bancointer.models.boleto.BoletoDTO;
import com.novaalianca.api.bancointer.models.boleto.FiltroBoletoDTO;
import com.novaalianca.api.bancointer.models.boleto.response.PdfBoleto;
import com.novaalianca.api.bancointer.models.boleto.response.listagempaginado.ResponseListPageBoletosDTO;
import com.novaalianca.api.bancointer.models.boleto.response.sumarizados.SumaryDTO;
import com.novaalianca.api.bancointer.models.token.TokenResponseDTO;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.apache.tomcat.util.http.parser.AcceptEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Service
public class BoletoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoletoService.class);

    @Autowired
    private BancoInterPropertiesConfig bancoInterPropertiesConfig;


    @Autowired
    private TokenService tokenService;

    public BoletoDTO boletoDetalhe(FiltroBoletoDTO filtro) throws Exception {
        String urlListaBoletos = bancoInterPropertiesConfig.getUrlBancoInterBoletos() + "/" + filtro.getNossoNumero();
        String hostNameServer = InetAddress.getLocalHost().getHostName();
        LOGGER.info("Inicio Chamada de Detalhe de Boletos");
        LOGGER.info("Url de Chamado da Api de Boletos: {}", urlListaBoletos);
        LOGGER.info("Hostname Server a ser passado : {}", hostNameServer);


        TokenResponseDTO token = tokenService.getToken();
        HttpResponse<BoletoDTO> response = Unirest.get(urlListaBoletos)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + token.getAccess_token())
                .header("Host", hostNameServer)
                .asObject(BoletoDTO.class);

        LOGGER.info("Fim Chamada de Detalhe de Boletos");


        return response.getBody();
    }



    public ResponseListPageBoletosDTO listaBoletosPaginado(FiltroBoletoDTO filtro) throws Exception {
        String urlListaBoletos = bancoInterPropertiesConfig.getUrlBancoInterBoletos();
        String hostNameServer = InetAddress.getLocalHost().getHostName();
        LOGGER.info("Inicio Listagem de Boletos");
        LOGGER.info("Url de Chamado da Api de Boletos: {}", urlListaBoletos);
        LOGGER.info("Hostname Server a ser passado : {}", hostNameServer);


        TokenResponseDTO token = tokenService.getToken();
        HttpResponse<ResponseListPageBoletosDTO> response = Unirest.get(urlListaBoletos)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + token.getAccess_token())
                .header("Host", hostNameServer)
                .queryString("dataInicial", filtro.getDataInicial())
                .queryString("dataFinal", filtro.getDataFinal())
                .asObject(ResponseListPageBoletosDTO.class);

        LOGGER.info("Fim Listagem de Boletos");

        return response.getBody();
    }

    public SumaryDTO getSumario(FiltroBoletoDTO filtro) throws Exception {
        String urlListaBoletos = bancoInterPropertiesConfig.getUrlBancoInterBoletos() + "/sumario";
        String hostNameServer = InetAddress.getLocalHost().getHostName();
        LOGGER.info("Inicio Listagem de Boletos");
        LOGGER.info("Url de Chamado da Api de Boletos: {}", urlListaBoletos);
        LOGGER.info("Hostname Server a ser passado : {}", hostNameServer);


        TokenResponseDTO token = tokenService.getToken();
        HttpResponse<SumaryDTO> response = Unirest.get(urlListaBoletos)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + token.getAccess_token())
                .header("Host", hostNameServer)
                .queryString("dataInicial", filtro.getDataInicial())
                .queryString("dataFinal", filtro.getDataFinal())
                .asObject(SumaryDTO.class);

        LOGGER.info("Fim Listagem de Boletos");

        return response.getBody();
    }


    public PdfBoleto getPdfBase64(FiltroBoletoDTO filtro) throws Exception {

        String urlListaBoletos = bancoInterPropertiesConfig.getUrlBancoInterBoletos() +"/" + filtro.getNossoNumero() + "/pdf";
        String hostNameServer = InetAddress.getLocalHost().getHostName();
        LOGGER.info("Inicio Listagem de Boletos");
        LOGGER.info("Url de Chamado da Api de Boletos: {}", urlListaBoletos);
        LOGGER.info("Hostname Server a ser passado : {}", hostNameServer);


        TokenResponseDTO token = tokenService.getToken();
        HttpResponse<byte[]> response = Unirest.get(urlListaBoletos)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + token.getAccess_token())
                .header("Host", hostNameServer)
                //.queryString("dataInicial", filtro.getDataInicial())
                //.queryString("dataFinal", filtro.getDataFinal())
                .asBytes();

        LOGGER.info("Fim Listagem de Boletos");
        LOGGER.info("Base 64: {}", response.getBody());
        //Base64.getDecoder().decode(response.getBody().toString());


//        try (FileOutputStream fos = new FileOutputStream("c:/temp/teste.pdf")) {
//            fos.write(response.getBody());
//            fos.flush();
//            fos.close();
//        }

        PdfBoleto pdf = new PdfBoleto();
        pdf.setPdf(response.getBody());
        return pdf;



        //byte[] decoder = Base64.getDecoder().decode(jsonNodeHttpResponse.getBody());

       // return this.salvaPdf(boleto, decoder);

    }

//    public Boleto geraBoleto(FiltroListagemBoletoDTO filtro, Integer idUsuario) throws Exception {
//        ResponseBoletoDTO responseBoleto = new ResponseBoletoDTO();
//        Boleto boleto = new Boleto();
//        Unirest.config().clientCertificateStore(propertiesConfig.getCaminhoCertificado(), propertiesConfig.getSenhaCertificado());
//        HttpResponse<ResponseBoletoDTO> jsonNodeHttpResponse = Unirest.post(propertiesConfig.getUrlBancoInter())
//                .header("x-inter-conta-corrente", filtro.getNumConta())
//                .header("Content-Type", "application/json")
//                .body(boletoDTO).asObject(ResponseBoletoDTO.class);
//        responseBoleto = jsonNodeHttpResponse.getBody();
//
//        if (responseBoleto.getCodigoBarras() != null) {
//
//            boleto = this.salvaBoleto(responseBoleto, usuarioRepository.findById(idUsuario).get(), boletoDTO);
//        } else {
//            throw new Exception("NULO");
//        }
//        if (boleto.getIdBoleto() != null) {
//            filtro.setNossoNumero(boleto.getNossoNumero());
//            boleto = downloadPDF(filtro, boleto);
//
//        }
//        if (boleto.getArquivopdf() != null) {
//
//            EmailDTO emailDTO = new EmailDTO();
//
//            DateTimeFormatter formatterReferencia = DateTimeFormatter.ofPattern("MM-yyyy");
//
//            emailDTO.setAnexo(boleto.getArquivopdf());
//            emailDTO.setNumeroUnidade(usuarioRepository.findById(idUsuario).get().getUnidade().getNumeroUnidade());
//            emailDTO.setTo(usuarioRepository.findById(idUsuario).get().getEmailUsuario());
//            emailDTO.setFrom("condominionovaaliancasbc@gmail.com");
//            emailDTO.setSubject("Cobrança Condomínio - " + LocalDate.now().format(formatterReferencia).toString());
//            emailDTO.setValorBoleto(boleto.getValor().toString());
//            emailDTO.setDtVencimento(boleto.getDtVencimento().toString());
//
//            try {
//                emailService.sendMail(emailDTO);
//                boleto.setDtEnvio(LocalDateTime.now());
//                this.updateBoleto(boleto);
//
//            } catch (Exception e) {
//                System.err.println(e);
//            }
//
//        }
//
//        return boleto;
//    }
//
//    public List<ResponseBoletoDTO> geraBoleto2(FiltroListagemBoletoDTO filtro) throws Exception {
//        List<Usuario> listUsuario = usuarioRepository.findByEnviaEmail(Boolean.TRUE);
//        List<ResponseBoletoDTO> listResponseBoleto = new ArrayList<>();
//        Boleto boleto = new Boleto();
//
//        for (Usuario usuario : listUsuario) {
//            ResponseBoletoDTO responseBoleto = new ResponseBoletoDTO();
//            BoletoDTO boletoDTO = boletoBuilder.carregaDadosEmissao(usuario);
//            Unirest.config().clientCertificateStore(propertiesConfig.getCaminhoCertificado(), propertiesConfig.getSenhaCertificado());
//            HttpResponse<ResponseBoletoDTO> jsonNodeHttpResponse = Unirest.post(propertiesConfig.getUrlBancoInter())
//                    .header("x-inter-conta-corrente", filtro.getNumConta()).header("Content-Type", "application/json")
//                    .body(boletoDTO).asObject(ResponseBoletoDTO.class);
//            //System.out.println(jsonNodeHttpResponse.getBody());
//            responseBoleto = jsonNodeHttpResponse.getBody();
//            listResponseBoleto.add(responseBoleto);
//            if (responseBoleto.getCodigoBarras() != null) {
//
//                boleto = this.salvaBoleto(responseBoleto, usuario, boletoDTO);
//            } else {
//                throw new Exception("Boleto Não Gerado");
//            }
//            if (boleto.getIdBoleto() != null) {
//                filtro.setNossoNumero(boleto.getNossoNumero());
//                boleto = downloadPDF(filtro, boleto);
//            }
//
//            if (boleto.getArquivopdf() != null) {
//
//                EmailDTO emailDTO = new EmailDTO();
//
//                DateTimeFormatter formatterReferencia = DateTimeFormatter.ofPattern("MM-yyyy");
//
//                emailDTO.setAnexo(boleto.getArquivopdf());
//                emailDTO.setNumeroUnidade(usuario.getUnidade().getNumeroUnidade());
//                emailDTO.setTo(usuario.getEmailUsuario());
//                emailDTO.setFrom(propertiesConfig.getMailFrom());
//                emailDTO.setSubject("Cobrança Condomínio - " + LocalDate.now().format(formatterReferencia).toString());
//                emailDTO.setValorBoleto(boleto.getValor().toString());
//                emailDTO.setDtVencimento(boleto.getDtVencimento().toString());
//
//                try {
//                    emailService.sendMail(emailDTO);
//                    boleto.setDtEnvio(LocalDateTime.now());
//                    this.updateBoleto(boleto);
//
//                } catch (Exception e) {
//                    System.err.println(e);
//                }
//            }
//        }
//
//        return listResponseBoleto;
//
//    }



//    public ResponseListagemBoletosDTO listaBoletos(FiltroListagemBoletoDTO filtro) throws Exception {
//
//        HttpResponse<String> response = Unirest
//                .get("https://cdpj.partners.bancointer.com.br/cobranca/v2/boletos")
//                //.queryString("filtrarPor", filtro.getFiltrarPor() == null ? "TODOS" : filtro.getFiltrarPor())
////                .queryString("filtrarDataPor",
////                        filtro.getFiltrarDataPor() == null ? "VENCIMENTO" : filtro.getFiltrarDataPor())
////                .queryString("ordenarPor",
////                        filtro.getOrdenarPor() == null ? "DATAVENCIMENTO_DSC" : filtro.getOrdenarPor())
//                // .queryString("size",filtro.getSize() ==null ? 20 : filtro.getSize())
//                //.asObject(ResponseListagemBoletosDTO.class);
//        LOGGER.info("RESPONDE: {}",response.getBody() );
//        return null;
//    }

//    public byte[] downloadBoleto(FiltroListagemBoletoDTO filtro) throws Exception {
//        Unirest.config().clientCertificateStore("E:\\Estudos\\apiBoletoCondominio\\src\\main\\resources\\certs\\Condominio.pfx", propertiesConfig.getSenhaCertificado());
//        HttpResponse<byte[]> jsonNodeHttpResponse = Unirest.get(propertiesConfig.getUrlBancoInter() + "/" + filtro.getNossoNumero() + "/pdf")
//                .header("x-inter-conta-corrente", filtro.getNumConta()).asBytes();
//
//        LOGGER.info("NOSSO NUMERO DO DOWLOAD DO BOLETO {}", filtro.getNossoNumero());
//        LOGGER.info("ARRAY do BOLETO {} ", jsonNodeHttpResponse.getBody());
//
//        byte[] decoder = Base64.getDecoder().decode(jsonNodeHttpResponse.getBody());
//
//        return jsonNodeHttpResponse.getBody();
//    }
//
//    public byte[] populaPdfUnico(FiltroListagemBoletoDTO filtro) throws Exception {
//
//        String urlListaBoletos = "https://cdpj.partners.bancointer.com.br/cobranca/v2/boletos";
//        LOGGER.info("TESTE POPULA PDF UNUCO ");
//        TokenResponseDTO token = tokenService.getToken();
//        HttpResponse<byte[]> response = Unirest.get(urlListaBoletos + "/" + filtro.getNossoNumero() + "/pdf")
//                .header("Accept", "application/json")
//                .header("Authorization", "Bearer " + token.getAccess_token())
//                .header("Host","cdpj.partners.bancointer.com.br")
//                .asBytes();
//
//        LOGGER.info("NOSSO NUMERO DO DOWLOAD DO BOLETO {}", filtro.getNossoNumero());
//        LOGGER.info("ARRAY do BOLETO {} ", response.getBody());
//
//        byte[] decoder = response.getBody();
//
//        try (FileOutputStream fos = new FileOutputStream("c:/temp/teste.pdf")) {
//            fos.write(decoder);
//            fos.close(); // no need, try-with-resources auto close
//        }
//
//
//
//
//
//        return response.getBody();
//    }

//    public Boleto downloadPDF(FiltroListagemBoletoDTO filtro, Boleto boleto) throws Exception {
//        Unirest.config().clientCertificateStore(propertiesConfig.getCaminhoCertificado(), propertiesConfig.getSenhaCertificado());
//        HttpResponse<byte[]> jsonNodeHttpResponse = Unirest.get(propertiesConfig.getUrlBancoInter() + "/" + filtro.getNossoNumero() + "/pdf")
//                .header("x-inter-conta-corrente", filtro.getNumConta()).asBytes();
//
//        byte[] decoder = Base64.getDecoder().decode(jsonNodeHttpResponse.getBody());
//
//        return this.salvaPdf(boleto, decoder);
//
//    }

//    public Boleto salvaBoletos(ResponseBoletoDetalheDTO responseBoletoDetalheDTO) throws Exception {
//        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
//        LOGGER.info("boleto  CPF [{}]", responseBoletoDetalheDTO.getCnpjCpfPagador());
//        Usuario usuario = usuarioRepository.findByCpfUsuario(responseBoletoDetalheDTO.getCnpjCpfPagador());
//
//        Boleto boleto = new Boleto();
//        boleto.setCodBarras(responseBoletoDetalheDTO.getCodigoBarras());
//        boleto.setDtBaixa(LocalDateTime.parse(responseBoletoDetalheDTO.getDataHoraSituacao(), formatoHora));
//        boleto.setDtEnvio((LocalDate.parse(responseBoletoDetalheDTO.getDataEmissao(), formato)).atStartOfDay());
//        boleto.setDtGeracao((LocalDate.parse(responseBoletoDetalheDTO.getDataEmissao(), formato)).atStartOfDay());
//        boleto.setDtPagamento(LocalDateTime.parse(responseBoletoDetalheDTO.getDataHoraSituacao(), formatoHora));
//        boleto.setDtVencimento((LocalDate.parse(responseBoletoDetalheDTO.getDataVencimento(), formato)));
//        boleto.setIdUnidade(usuario.getUnidade());
//        boleto.setLinhaDigital(responseBoletoDetalheDTO.getLinhaDigitavel());
//        boleto.setMesReferencia(responseBoletoDetalheDTO.getDataEmissao().substring(3, 10));
//        boleto.setMotivoBaixa(null);
//        //boleto.setNossoNumero(nossoNumero);
//        boleto.setSeuNumero(responseBoletoDetalheDTO.getSeuNumero().toString());
//        boleto.setStatusBoleto(responseBoletoDetalheDTO.getSituacaoPagamento());
//        boleto.setValor(Double.parseDouble(responseBoletoDetalheDTO.getValorNominal().toString()));
//        boleto.setValorPagamento(responseBoletoDetalheDTO.getValorTotalRecebimento() == null ? 0
//                : Double.parseDouble(responseBoletoDetalheDTO.getValorTotalRecebimento().toString()));
//
//
//        try {
//            boletoRepository.save(boleto);
//            return boleto;//System.out.println("Boleto Salvo Com Sucesso " + nossoNumero);
//        } catch (Exception e) {
//            throw new Exception();
//        }
//    }
//
//    public Boleto salvaBoleto(ResponseBoletoDTO responseBoleto, Usuario usuario, BoletoDTO boletoDTO) {
//        Boleto boleto = new Boleto();
//        DateTimeFormatter formatterMesReferencia = DateTimeFormatter.ofPattern("MM/yyyy");
//        DateTimeFormatter formatterVencimento = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        boleto.setLinhaDigital(responseBoleto.getLinhaDigitavel());
//        boleto.setSeuNumero(responseBoleto.getSeuNumero());
//        boleto.setNossoNumero(responseBoleto.getNossoNumero());
//        boleto.setCodBarras(responseBoleto.getCodigoBarras());
//        boleto.setIdUnidade(usuario.getUnidade());
//        boleto.setMesReferencia((LocalDate.now().format(formatterMesReferencia).toString()));
//        boleto.setDtGeracao(LocalDateTime.now());
//        boleto.setValor(Double.parseDouble(boletoDTO.getValorNominal().toString()));
//        boleto.setDtVencimento(LocalDate.parse(boletoDTO.getDataVencimento(), formatterVencimento));
//
//        return boleto = boletoRepository.save(boleto);
//
//    }
//
//    public Boleto salvaPdf(Boleto boleto, byte[] pdf) {
//
//        boleto.setArquivopdf(pdf);
//
//        return boleto = boletoRepository.save(boleto);
//
//    }
//
//    public void updateBoleto(Boleto boleto) {
//        boletoRepository.save(boleto);
//
//    }
//
//    public List<Boleto> carga(FiltroListagemBoletoDTO filtro) throws Exception {
//        List<Boleto> lisBoleto = new ArrayList<>();
//
//
////        for (String nossoNumero : filtro.getListNossoNumero()) {
////            if (boletoRepository.findByNossoNumero(nossoNumero) == null) {
////                LOGGER.info("Não encontrou boleto na base local com o nosso numero [{}], tentando buscar na API do banco INTER", nossoNumero);
////                filtro.setNossoNumero(nossoNumero);
//                ResponseListagemBoletosDTO detalheboleto = listaBoletos(filtro);
//                if (Objects.isNull(detalheboleto)) {
//                    LOGGER.info("Não conseguiu pegar o detalhe do boleto no banco INTER, tentando de novo");
//                    detalheboleto = listaBoletos(filtro);
//                    Objects.isNull(detalheboleto);
//                    LOGGER.info("Resultado da segunda tentativa conseguiu pegar o detalhe do boleto tentando de novo e nulo [{}]", Objects.isNull(detalheboleto));
//                } else {
//                    LOGGER.info("Entrando no Metodo para Salvar");
//                    detalheboleto.getContent().forEach( boleto -> {
//
//                        try {
//                            Boleto boleto1 = salvaBoletosNewCarga(boleto);
//                            lisBoleto.add(boleto1);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    });
//
//                    //boleto.setArquivopdf(downloadBoleto(filtro));
//                    //boletoRepository.save(boleto)1;
//
//                }
//
////            } else {
////                LOGGER.info("Encontrou boleto na base local com o nosso numero [{}]", nossoNumero);
////            }
////
////        }
//
//        return lisBoleto;
//    }
//
//
//    public List<Boleto> populaPDF(FiltroListagemBoletoDTO filtro) throws Exception {
//
//        List<Boleto> listBoleto = new ArrayList<>();
//        listBoleto = boletoRepository.findByArquivopdfIsNull();
//        for (Boleto boleto : listBoleto) {
//
//            Unirest.config().clientCertificateStore(propertiesConfig.getCaminhoCertificado(), propertiesConfig.getSenhaCertificado());
//            HttpResponse<byte[]> jsonNodeHttpResponse = Unirest.get(propertiesConfig.getUrlBancoInter() + "/" + boleto.getNossoNumero() + "/pdf")
//                    .header("x-inter-conta-corrente", filtro.getNumConta()).asBytes();
//
//            //byte[] decoder = Base64.getDecoder().decode(jsonNodeHttpResponse.getBody());
//            byte[] decoder = jsonNodeHttpResponse.getBody();
//
//            this.salvaPdf(boleto, decoder);
//
//        }
//
//
//        return listBoleto;
//    }
//
//    public void testePDFEnviaEmail(Integer idBoleto) {
//        Boleto boleto = boletoRepository.findById(idBoleto).get();
//
//        EmailDTO emailDTO = new EmailDTO();
//
//        DateTimeFormatter formatterReferencia = DateTimeFormatter.ofPattern("MM-yyyy");
//
//        emailDTO.setAnexo(boleto.getArquivopdf());
//        emailDTO.setNumeroUnidade(boleto.getIdUnidade().getNumeroUnidade());
//        emailDTO.setTo("patrickmoura@gmail.com");
//        emailDTO.setFrom(propertiesConfig.getMailFrom());
//        emailDTO.setSubject("Cobrança Condomínio - " + LocalDate.now().format(formatterReferencia).toString());
//        emailDTO.setValorBoleto(boleto.getValor().toString());
//        emailDTO.setDtVencimento(boleto.getDtVencimento().toString());
//
//        try {
//            emailService.sendMail(emailDTO);
//            boleto.setDtEnvio(LocalDateTime.now());
//            //this.updateBoleto(boleto);
//
//        } catch (Exception e) {
//            System.err.println(e);
//        }
//
//
//    }
//
//
//    public List<ResponseBoletoDTO> geraBoleto3(FiltroListagemBoletoDTO filtro) throws Exception {
//        List<Usuario> listUsuario = usuarioRepository.findByEnviaEmail(Boolean.TRUE);
//        List<ResponseBoletoDTO> listResponseBoleto = new ArrayList<>();
//        Boleto boleto = new Boleto();
//
//        for (Usuario usuario : listUsuario) {
//            ResponseBoletoDTO responseBoleto = new ResponseBoletoDTO();
//            LOGGER.info("USUARIO [{}]", usuario.getNomeUsuario());
//            BoletoDTO boletoDTO = boletoBuilder.carregaDadosEmissao(usuario);
//            Unirest.config().clientCertificateStore(propertiesConfig.getCaminhoCertificado(), propertiesConfig.getSenhaCertificado());
//            HttpResponse<ResponseBoletoDTO> jsonNodeHttpResponse = Unirest.post(propertiesConfig.getUrlBancoInter())
//                    .header("x-inter-conta-corrente", filtro.getNumConta()).header("Content-Type", "application/json")
//                    .body(boletoDTO).asObject(ResponseBoletoDTO.class);
//            //System.out.println(jsonNodeHttpResponse.getBody());
//            responseBoleto = jsonNodeHttpResponse.getBody();
//            listResponseBoleto.add(responseBoleto);
//            if (responseBoleto.getCodigoBarras() != null) {
//
//                boleto = this.salvaBoleto(responseBoleto, usuario, boletoDTO);
//            } else {
//                throw new Exception("Boleto Não Gerado");
//            }
//
//        }
//
//        return listResponseBoleto;
//
//    }
//
//    public void downloadPDFSchedule(FiltroListagemBoletoDTO filtro, Boleto boleto) throws Exception {
//        List<Boleto> litboletos = new ArrayList<>();
//        litboletos = boletoRepository.findByArquivopdfIsNull();
////		for (Boleto bolet : litboletos) {
////			Unirest.config().clientCertificateStore(propertiesConfig.getCaminhoCertificado(), propertiesConfig.getSenhaCertificado());
////			HttpResponse<byte[]> jsonNodeHttpResponse = Unirest.get(propertiesConfig.getUrlBancoInter() + "/" + filtro.getNossoNumero() + "/pdf")
////					.header("x-inter-conta-corrente", filtro.getNumConta()).asBytes();
////
////			byte[] decoder = Base64.getDecoder().decode(jsonNodeHttpResponse.getBody());
////			this.salvaPdf(boleto, decoder);
////		}
//    }
//
//    public List<Boleto> enviamaildepois(FiltroListagemBoletoDTO filtro) throws Exception {
//        List<Boleto> litboletos = boletoRepository.findByDtEnvioIsNull();
//        for (Boleto bolet : litboletos) {
//            if (bolet.getArquivopdf() != null) {
//                //Usuario usuario = usuarioRepository.findByIdUnidade(bolet.getIdUnidade());
//                Usuario usuario = usuarioRepository.findById(5).get();
//
//                EmailDTO emailDTO = new EmailDTO();
//
//                DateTimeFormatter formatterReferencia = DateTimeFormatter.ofPattern("MM-yyyy");
//
//                emailDTO.setAnexo(bolet.getArquivopdf());
//                emailDTO.setNumeroUnidade(usuario.getUnidade().getNumeroUnidade());
//                emailDTO.setTo(usuario.getEmailUsuario());
//                emailDTO.setFrom(propertiesConfig.getMailFrom());
//                emailDTO.setSubject("Cobrança Condomínio - " + LocalDate.now().format(formatterReferencia).toString());
//                emailDTO.setValorBoleto(bolet.getValor().toString());
//                emailDTO.setDtVencimento(bolet.getDtVencimento().toString());
//
//                try {
//                    emailService.sendMail(emailDTO);
//                    bolet.setDtEnvio(LocalDateTime.now());
//                    this.updateBoleto(bolet);
//
//                } catch (Exception e) {
//                    System.err.println(e);
//                }
//            }
//        }
//        return litboletos;
//
//    }
//
//
//    public Boleto salvaBoletosNewCarga(ContentDTO responseBoletoDetalheDTO) throws Exception {
//        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LOGGER.info("boleto  CPF [{}]", responseBoletoDetalheDTO.getPagador().getCpfCnpj());
//
//        Usuario usuario = usuarioRepository.findByCpfUsuario(responseBoletoDetalheDTO.getPagador().getCpfCnpj());
//
//        Boleto boleto = new Boleto();
//        boleto.setCodBarras(responseBoletoDetalheDTO.getCodigoBarras());
//        boleto.setDtBaixa(LocalDateTime.parse(responseBoletoDetalheDTO.getDataHoraSituacao(), formatoHora));
//        boleto.setDtEnvio(responseBoletoDetalheDTO.getDataEmissao().atStartOfDay());
//        boleto.setDtGeracao(responseBoletoDetalheDTO.getDataEmissao().atStartOfDay());
//        boleto.setDtPagamento(LocalDateTime.parse(responseBoletoDetalheDTO.getDataHoraSituacao(), formatoHora));
//        boleto.setDtVencimento(responseBoletoDetalheDTO.getDataVencimento());
//        boleto.setIdUnidade(usuario.getUnidade());
//        boleto.setLinhaDigital(responseBoletoDetalheDTO.getLinhaDigitavel());
//        boleto.setMesReferencia(responseBoletoDetalheDTO.getDataEmissao().toString().substring(4, 7));
//        boleto.setMotivoBaixa(null);
//        boleto.setNossoNumero(responseBoletoDetalheDTO.getNossoNumero());
//        boleto.setSeuNumero(responseBoletoDetalheDTO.getSeuNumero().toString());
//        boleto.setStatusBoleto(responseBoletoDetalheDTO.getSituacao());
//        boleto.setValor(Double.parseDouble(responseBoletoDetalheDTO.getValorNominal().toString()));
//        boleto.setValorPagamento(responseBoletoDetalheDTO.getValorTotalRecebimento() == null ? 0
//                : Double.parseDouble(responseBoletoDetalheDTO.getValorTotalRecebimento().toString()));
//
//
//        try {
//            boletoRepository.save(boleto);
//            return boleto;//System.out.println("Boleto Salvo Com Sucesso " + nossoNumero);
//        } catch (Exception e) {
//            throw new Exception();
//        }
//    }

}
