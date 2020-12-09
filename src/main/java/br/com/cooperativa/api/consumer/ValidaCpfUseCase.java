package br.com.cooperativa.api.consumer;

import br.com.cooperativa.api.model.dto.ValidaCpfDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ValidaCpfUseCase {

    private static final Logger log = LoggerFactory.getLogger(ValidaCpfUseCase.class);
    private final static String URI = "https://user-info.herokuapp.com/users/";

    public ValidaCpfDTO executa(String cpf) {
        RestTemplate restTemplate = new RestTemplate();
        ValidaCpfDTO api = restTemplate.getForObject(URI+cpf, ValidaCpfDTO.class);
        log.info(api.toString());
        return api;

    }
}
