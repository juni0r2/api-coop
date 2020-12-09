package br.com.cooperativa.api.controller;

import br.com.cooperativa.api.consumer.GeradorCpfUseCase;
import br.com.cooperativa.api.consumer.ValidaCpfUseCase;
import br.com.cooperativa.api.model.dto.GeradorCpfDTO;
import br.com.cooperativa.api.model.dto.ValidaCpfDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pauta")
public class PautaController {

    @Autowired
    GeradorCpfUseCase consumer;
    
    @Autowired
    ValidaCpfUseCase cpfUseCase;

    @GetMapping("/{cpf}")
    public ResponseEntity verificaSePodeVotar(@PathVariable(name = "cpf") String cpf) {

        GeradorCpfDTO cpfApi = consumer.executa();
        ValidaCpfDTO executa = cpfUseCase.executa("01956544127");
        System.out.println(executa);
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }
}
