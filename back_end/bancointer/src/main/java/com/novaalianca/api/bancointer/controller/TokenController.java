package com.novaalianca.api.bancointer.controller;


import com.novaalianca.api.bancointer.models.token.TokenResponseDTO;
import com.novaalianca.api.bancointer.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<TokenResponseDTO> getToken(){
        return ResponseEntity.ok().body(tokenService.getToken());
    }
}
