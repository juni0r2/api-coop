package br.com.cooperativa.api.repository;

import br.com.cooperativa.api.model.EnumSituacaoPauta;
import br.com.cooperativa.api.model.Pauta;
import br.com.cooperativa.api.model.Voto;
import br.com.cooperativa.api.model.dto.VotoDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VotoRepository extends CrudRepository<Voto, Long> {

    Optional<Voto> findById(Long id);
}
