package br.com.cooperativa.api.model.dto;

import br.com.cooperativa.api.model.dto.CpfApiDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class GeradorCpfDTO {

    private String status;

    private CpfApiDTO data;

}

