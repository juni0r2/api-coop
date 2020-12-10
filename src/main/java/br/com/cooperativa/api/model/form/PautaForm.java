package br.com.cooperativa.api.model.form;

import br.com.cooperativa.api.model.EnumSituacaoPauta;
import br.com.cooperativa.api.model.Pauta;
import lombok.Data;

import java.util.Date;

@Data
public class PautaForm {

    private String nome;


    public Pauta converte() {
        return Pauta.builder()
                .nome(this.nome)
                .dataAbertura(new Date())
                .situacao(EnumSituacaoPauta.ABERTA)
                .build();
    }
}
