package br.com.cooperativa.api.model.dto;

import br.com.cooperativa.api.model.Associado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoDTO {

    private String cpf;

    public AssociadoDTO(Associado associado) {
        this.cpf = associado.getCpf();
    }
}
