//package com.novaalianca.api.bancointer.controller;
//
//import com.patrickmoura.apiboleto.models.EmailDTO;
//import com.patrickmoura.apiboleto.services.EmailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class EmailController {
//
//	@Autowired
//	EmailService emailservice;
//
//	@PostMapping("/enviaEmail")
//	public void enviaEmail() throws Exception{
//		 EmailDTO emailDTO = new EmailDTO();
//         //emailDTO.setAnexo(decoder);
//         emailDTO.setBody("TESTE EMAIL");
//         emailDTO.setFrom("condominionovaaliancasbc@gmail.com");
//         emailDTO.setSubject("TESTE EMIAL SUBJECT");
//         emailDTO.setTo("patrickmoura@gmail.com");
//		emailservice.sendMail(emailDTO);
//	}
//	@PostMapping("/enviaEmail/{idBoleto}")
//	public void enviaEmailTeste(@PathVariable Integer idBoleto) {
//		emailservice.enviaEmailTestePDF(idBoleto);
//	}
//
//}
