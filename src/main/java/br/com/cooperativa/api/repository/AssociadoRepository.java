package br.com.cooperativa.api.repository;

import br.com.cooperativa.api.model.Associado;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AssociadoRepository extends CrudRepository<Associado, String> {

    Optional<Associado> findByCpf(String cpf);
}
