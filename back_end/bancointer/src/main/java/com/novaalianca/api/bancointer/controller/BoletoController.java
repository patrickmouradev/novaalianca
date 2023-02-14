package com.novaalianca.api.bancointer.controller;

import com.novaalianca.api.bancointer.models.boleto.FiltroBoletoDTO;
import com.novaalianca.api.bancointer.models.boleto.response.PdfBoleto;
import com.novaalianca.api.bancointer.services.BoletoService;
import org.apache.tomcat.util.http.parser.AcceptEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableScheduling
@RequestMapping(("/boletos"))
public class BoletoController {

	@Autowired
	BoletoService boletoService;

	@GetMapping("/listaBoletos")
	// @Scheduled(cron="* */2 * * * *")
	public ResponseEntity<?> getListBoletos(@RequestBody FiltroBoletoDTO filtro) throws Exception {

		return ResponseEntity.ok().body(boletoService.listaBoletosPaginado(filtro));
	}

	@GetMapping("/detalheBoleto")
	// @Scheduled(cron="* */2 * * * *")
	public ResponseEntity<Object> getDetalheBoleto(@RequestBody FiltroBoletoDTO filtro) throws Exception {

		return ResponseEntity.ok().body(boletoService.boletoDetalhe(filtro));
	}

	@GetMapping("/sumario")
	// @Scheduled(cron="* */2 * * * *")
	public ResponseEntity<Object> getSumario(@RequestBody FiltroBoletoDTO filtro) throws Exception {

		return ResponseEntity.ok().body(boletoService.getSumario(filtro));
	}


	@GetMapping("/recuperaPdf")
	//@Scheduled(cron="* */2 * * * *")
	public PdfBoleto getPdf(@RequestBody FiltroBoletoDTO filtro) throws Exception{
		//boletoService.carga(filtro);
		return boletoService.getPdfBase64(filtro);
	}

//	@PostMapping("/geraBoleto/{id}")
//	// @Scheduled(cron="* */2 * * * *")
//	public ResponseEntity<Boleto> geraBoleto(@RequestBody FiltroListagemBoletoDTO filtro, @PathVariable Integer id) throws Exception {
//
//		return ResponseEntity.ok().body(boletoService.geraBoleto(filtro, id));
//	}
//
//	@PostMapping("/geraBoletov2")
//	// @Scheduled(cron="* */2 * * * *")
//	public ResponseEntity<List<ResponseBoletoDTO>> geraBoletov2(@RequestBody FiltroListagemBoletoDTO filtro)
//			throws Exception {
//
//		return ResponseEntity.ok().body(boletoService.geraBoleto2(filtro));
//	}

//	@PostMapping("/geraBoletov3")
//	// @Scheduled(cron="* */2 * * * *")
//	public ResponseEntity<List<ResponseBoletoDTO>> geraBoletov3(@RequestBody FiltroListagemBoletoDTO filtro)
//			throws Exception {
//
//		return ResponseEntity.ok().body(boletoService.geraBoleto3(filtro));
//	}



//
//	@GetMapping("/resgataBoleto")
//	public ResponseEntity<?> resgataBoleto(@RequestBody FiltroListagemBoletoDTO filtro) throws Exception {
//		return ResponseEntity.ok().body(boletoService.downloadBoleto(filtro));
//	}
//
//	@GetMapping("/pegabyte")
//	public ResponseEntity<?> pegabyte(@RequestBody FiltroListagemBoletoDTO filtro) throws Exception {
//		return ResponseEntity.ok().body(boletoService.populaPDF(filtro));
//	}
//
//	@GetMapping("/enviaemaildepois")
//	public  ResponseEntity<List<Boleto>> enviaemaildepois(@RequestBody FiltroListagemBoletoDTO filtro) throws Exception {
//		return ResponseEntity.ok().body(boletoService.enviamaildepois(filtro));
//	}
//
//
//	@GetMapping("/cargaBanco")
//	//@Scheduled(cron="* */2 * * * *")
//	public ResponseEntity<?> cargaInicialBanco(@RequestBody FiltroListagemBoletoDTO filtro ) throws Exception{
//			//boletoService.carga(filtro);
//			return ResponseEntity.ok().body(boletoService.carga(filtro));
//	}
////
//	@GetMapping("/populapdf")
//	//@Scheduled(cron="* */2 * * * *")
//	public List<Boleto> populaPDF(@RequestBody FiltroListagemBoletoDTO filtro ) throws Exception{
//		//boletoService.carga(filtro);
//		return boletoService.populaPDF(filtro);
//	}

//	@GetMapping("/populapdfUnico")
//	//@Scheduled(cron="* */2 * * * *")
//	public byte[] populaPDFUnico(@RequestBody FiltroListagemBoletoDTO filtro ) throws Exception{
//		//boletoService.carga(filtro);
//		return boletoService.populaPdfUnico(filtro);
//	}


}
