package br.com.cooperativa.api.consumer;

import br.com.cooperativa.api.model.dto.GeradorCpfDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeradorCpfUseCase {
    private static final Logger log = LoggerFactory.getLogger(GeradorCpfUseCase.class);
    private final static String URI = "http://geradorapp.com/api/v1/cpf/generate?";
    private final static String TOKEN = "token=0a89e602e913e92b42cb3babea1b8ced";


    public GeradorCpfDTO executa() {
        RestTemplate restTemplate = new RestTemplate();
        GeradorCpfDTO api = restTemplate.getForObject(URI+TOKEN, GeradorCpfDTO.class);
        log.info(api.toString());
        return api;
    }
}
