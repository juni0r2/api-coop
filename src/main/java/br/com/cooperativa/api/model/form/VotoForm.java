package br.com.cooperativa.api.model.form;

import br.com.caelum.stella.bean.validation.CPF;
import br.com.cooperativa.api.model.Associado;
import br.com.cooperativa.api.model.Pauta;
import br.com.cooperativa.api.model.Voto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VotoForm {

    @CPF
    private String cpf;

    private String voto;

    private Long idPauta;

    public Voto converte(Pauta pauta, Associado associado) {
        return Voto.builder()
                .voto(this.voto)
                .cpf(this.cpf)
                .associado(associado)
                .pauta(pauta.getId())
                .build();
    }
}
