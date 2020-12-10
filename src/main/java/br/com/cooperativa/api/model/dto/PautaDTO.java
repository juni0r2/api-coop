package br.com.cooperativa.api.model.dto;

import br.com.cooperativa.api.model.Associado;
import br.com.cooperativa.api.model.EnumSituacaoPauta;
import br.com.cooperativa.api.model.Pauta;
import br.com.cooperativa.api.model.Voto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class PautaDTO {

    private Long id;

    private String nome;

    private Date dataAbertura;

    private Date dataFechamento;

    private EnumSituacaoPauta situacaoPauta;

    private List<VotoDTO> votos;

    public PautaDTO(Pauta pauta) {
        this.id = pauta.getId();
        this.nome = pauta.getNome();
        this.dataAbertura = pauta.getDataAbertura();
        this.dataFechamento = pauta.getDataFechamento();
        this.situacaoPauta = pauta.getSituacao();

        if (pauta.getVotos() != null && !pauta.getVotos().isEmpty()) {
            ArrayList<VotoDTO> votoDTOS = new ArrayList<>();
            for (Voto v : pauta.getVotos()) {
                votoDTOS.add(new VotoDTO(v));
            }
            this.votos = votoDTOS;
        }
    }
}
