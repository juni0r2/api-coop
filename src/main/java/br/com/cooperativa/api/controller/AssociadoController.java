package br.com.cooperativa.api.controller;

import br.com.cooperativa.api.model.Associado;
import br.com.cooperativa.api.model.Pauta;
import br.com.cooperativa.api.model.dto.AssociadoDTO;
import br.com.cooperativa.api.model.dto.PautaDTO;
import br.com.cooperativa.api.model.form.AssociadoForm;
import br.com.cooperativa.api.model.form.PautaForm;
import br.com.cooperativa.api.repository.AssociadoRepository;
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
@RequestMapping("/associado")
public class AssociadoController {

    @Autowired
    AssociadoRepository associadoRepository;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<AssociadoDTO>> listarAssociados() {
        Iterable<Associado> all = this.associadoRepository.findAll();
        if (all != null) {
            Iterator<Associado> iterator = all.iterator();

            List<AssociadoDTO> lista = new ArrayList<>();
            while (iterator.hasNext()) {
                Associado next = iterator.next();
                lista.add(next.converte());
            }
            return ResponseEntity.ok(lista);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<AssociadoDTO> buscarAssociado(@PathVariable("id") String cpf) {
        Optional<Associado> byId = this.associadoRepository.findByCpf(cpf);
        if (byId.isPresent())
            return ResponseEntity.ok(byId.get().converte());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AssociadoDTO> cadastrar(@RequestBody AssociadoForm form, UriComponentsBuilder uriBuilder) {
        Associado converte = form.converte();
        Associado save = this.associadoRepository.save(converte);

        URI uri = uriBuilder.path("/associado/{id}").buildAndExpand(save.getCpf()).toUri();
        return ResponseEntity.created(uri).body(new AssociadoDTO(save));
    }
}
