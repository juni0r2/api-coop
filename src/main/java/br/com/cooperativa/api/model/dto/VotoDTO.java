package br.com.cooperativa.api.model.dto;

import br.com.cooperativa.api.model.Pauta;
import br.com.cooperativa.api.model.Voto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class VotoDTO {

    private Long id;

    private Long pauta;

    private String voto;

    private String cpf;

    public VotoDTO(Voto voto) {
        this.id = voto.getId();
        this.pauta = voto.getPauta();
        this.voto = voto.getVoto();
        this.cpf = voto.getCpf();
    }
}


