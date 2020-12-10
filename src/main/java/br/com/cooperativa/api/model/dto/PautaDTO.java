package br.com.cooperativa.api.model.dto;

import br.com.cooperativa.api.model.Associado;
import br.com.cooperativa.api.model.Pauta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class PautaDTO {

    private Long id;

    private String nome;

    private List<Associado> associados;

    public PautaDTO(Pauta pauta) {
        this.id = pauta.getId();
        this.nome = pauta.getNome();
    }
}
