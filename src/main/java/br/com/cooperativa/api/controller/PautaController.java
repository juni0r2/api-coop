package br.com.cooperativa.api.controller;

import br.com.cooperativa.api.model.Pauta;
import br.com.cooperativa.api.model.dto.PautaDTO;
import br.com.cooperativa.api.model.dto.ResultadoVotacaoDTO;
import br.com.cooperativa.api.model.dto.VotoDTO;
import br.com.cooperativa.api.model.form.PautaForm;
import br.com.cooperativa.api.model.form.VotoForm;
import br.com.cooperativa.api.repository.PautaRepository;
import br.com.cooperativa.api.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pauta")
public class PautaController {

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    PautaService pautaService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PautaDTO>> listarPautas() {
        Iterable<Pauta> all = this.pautaRepository.findAll();
        if (all != null) {
            Iterator<Pauta> iterator = all.iterator();

            List<PautaDTO> lista = new ArrayList<>();
            while (iterator.hasNext()) {
                Pauta next = iterator.next();
                lista.add(next.converte());
            }
            return ResponseEntity.ok(lista);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<PautaDTO> buscarPauta(@PathVariable("id") Long id) {
        Optional<Pauta> pautaOptional = this.pautaRepository.findById(id);
        if (pautaOptional.isPresent()) {
            PautaDTO pautaDTO = pautaOptional.get().converte();
            return ResponseEntity.ok(pautaDTO);
        }
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

    @PostMapping("/pauta/voto")
    public ResponseEntity<VotoDTO> votar(@RequestBody VotoForm form, UriComponentsBuilder uriBuilder) {
        VotoDTO votoDTO = this.pautaService.confirmarVoto(form);

        if (votoDTO != null) {
            URI uri = uriBuilder.path("/pauta/voto/{id}").buildAndExpand(votoDTO.getId()).toUri();
            return ResponseEntity.created(uri).body(votoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/status-da-votacao/{id}")
    @ResponseBody
    public ResponseEntity verificarSituacaoDaVotacaoDaPauta(@PathVariable("id") Long idPauta) {
        ResultadoVotacaoDTO resultadoVotacaoDTO = pautaService.verificaSituacaoVotacao(idPauta);
        return ResponseEntity.ok(resultadoVotacaoDTO);
    }

    @GetMapping("/fechar-pauta/{id}")
    @ResponseBody
    public ResponseEntity fecharPauta(@PathVariable("id") Long idPauta) {
        PautaDTO pautaDTO = this.pautaService.fecharPauta(idPauta);
        return ResponseEntity.ok(pautaDTO);
    }

}
