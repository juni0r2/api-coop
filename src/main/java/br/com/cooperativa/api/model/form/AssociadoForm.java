package br.com.cooperativa.api.model.form;

import br.com.cooperativa.api.model.Associado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AssociadoForm {

    private String cpf;

    public Associado converte() {
        return Associado.builder()
                .cpf(this.cpf)
                .build();
    }
}
