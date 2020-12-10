package br.com.cooperativa.api.model.form;

import br.com.cooperativa.api.model.Pauta;
import lombok.Data;

@Data
public class PautaForm {

    private String nome;

    public Pauta converte() {
        return Pauta.builder()
                .nome(this.nome)
                .build();
    }
}
