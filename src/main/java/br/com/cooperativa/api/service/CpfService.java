package br.com.cooperativa.api.service;

import br.com.cooperativa.api.consumer.GeradorCpfUseCase;
import br.com.cooperativa.api.consumer.ValidaCpfUseCase;
import br.com.cooperativa.api.model.dto.GeradorCpfDTO;
import br.com.cooperativa.api.model.dto.ValidaCpfDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CpfService {

    @Autowired
    GeradorCpfUseCase geradorCpfUseCase;

    @Autowired
    ValidaCpfUseCase validaCpfUseCase;


    public GeradorCpfDTO gerarCpf() {
        return geradorCpfUseCase.executa();
    }

    public ValidaCpfDTO validaCpfDTO(String cpf) {
        return validaCpfUseCase.executa(cpf);
    }
}
