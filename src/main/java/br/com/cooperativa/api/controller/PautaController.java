package br.com.cooperativa.api.controller;

import br.com.cooperativa.api.consumer.GeradorCpfUseCase;
import br.com.cooperativa.api.consumer.ValidaCpfUseCase;
import br.com.cooperativa.api.model.Pauta;
import br.com.cooperativa.api.model.dto.GeradorCpfDTO;
import br.com.cooperativa.api.model.dto.PautaDTO;
import br.com.cooperativa.api.model.dto.ValidaCpfDTO;
import br.com.cooperativa.api.model.form.PautaForm;
import br.com.cooperativa.api.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/pauta")
public class PautaController {

    @Autowired
    GeradorCpfUseCase consumer;
    
    @Autowired
    ValidaCpfUseCase cpfUseCase;

    @Autowired
    PautaRepository pautaRepository;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<PautaDTO> buscarPauta(@PathVariable("id") Long id) {
        Optional<Pauta> byId = this.pautaRepository.findById(id);
        if (byId.isPresent())
            return ResponseEntity.ok(byId.get().converte());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PautaDTO> cadastrar(@RequestBody PautaForm form, UriComponentsBuilder uriBuilder) {
        Pauta pauta = form.converte();
        Pauta save = this.pautaRepository.save(pauta);

        URI uri = uriBuilder.path("/pauta/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(uri).body(new PautaDTO(save));
    }

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity gerarCpf() {

        GeradorCpfDTO cpfApi = consumer.executa();
        ValidaCpfDTO validaCpfDTO = cpfUseCase.executa(cpfApi.getData().getNumber());
        return ResponseEntity.ok(validaCpfDTO);
    }

//    @GetMapping("/{cpf}")
//    @ResponseBody
//    public ResponseEntity verificaCpfValido(@PathVariable(name = "cpf") String cpf) {
//
//        ValidaCpfDTO validaCpfDTO = cpfUseCase.executa(cpf);
//
//        if (validaCpfDTO != null) {
//            ResponseEntity.ok(validaCpfDTO);
//        }
//        return ResponseEntity.badRequest().build();
//    }
}
