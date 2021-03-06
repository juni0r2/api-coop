package br.com.cooperativa.api.repository;

import br.com.cooperativa.api.model.EnumSituacaoPauta;
import br.com.cooperativa.api.model.Pauta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PautaRepository extends CrudRepository<Pauta, Long> {

    Optional<Pauta> findBySituacao(EnumSituacaoPauta aberta);
}
