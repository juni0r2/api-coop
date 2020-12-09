package br.com.cooperativa.api.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class CpfApiDTO {

    private String number;

    private String number_formatted;

    private String message;

}
