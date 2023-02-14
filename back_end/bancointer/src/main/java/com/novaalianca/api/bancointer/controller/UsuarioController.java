//package com.novaalianca.api.bancointer.controller;
//
//import com.patrickmoura.apiboleto.entities.Usuario;
//import com.patrickmoura.apiboleto.services.UsuarioService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.List;
//
//@Controller
//@EnableScheduling
//@RequestMapping("/usuarios")
//public class UsuarioController {
//
//	@Autowired
//	UsuarioService usuarioService;
//
//	@GetMapping
//	public ResponseEntity<?> listUsers() {
//		return ResponseEntity.ok().body(usuarioService.findAll());
//
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<?> findUser(@PathVariable Integer id) {
//		return ResponseEntity.ok().body(usuarioService.findByID(id));
//
//	}
//
//	@PutMapping("/save")
//	public ResponseEntity<?> save(@RequestBody Usuario user) {
//		return ResponseEntity.ok().body(usuarioService.updateByUser(user));
//
//	}
//
//	@RequestMapping(value = "/detalheTime", method = RequestMethod.GET)
//	public ModelAndView detalhaboletoPagina() {
//		ModelAndView mv = new ModelAndView("index");
//		List<Usuario> listUsuartios = usuarioService.findAll();
//		mv.addObject("index",listUsuartios );
//		return mv;
//
//	}
//
//}
