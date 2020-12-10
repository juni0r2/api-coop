package br.com.cooperativa.api.consumer;

import br.com.cooperativa.api.model.dto.ValidaCpfDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class ValidaCpfUseCase {

    private static final Logger log = LoggerFactory.getLogger(ValidaCpfUseCase.class);
    private final static String URI = "https://user-info.herokuapp.com/users/";

    public ValidaCpfDTO executa(String cpf) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ValidaCpfDTO api = restTemplate.getForObject(URI+cpf, ValidaCpfDTO.class);
            return api;
        } catch (HttpClientErrorException e) {
            log.error("O serviço não respondeu corretamente. Status: " +e.toString());
            return null;
        }

    }
}
