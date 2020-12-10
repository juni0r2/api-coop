package br.com.cooperativa.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoVotacaoDTO {

    private int sim;

    private int nao;

    private int total;
}
