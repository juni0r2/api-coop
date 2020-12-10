package br.com.cooperativa.api.model;

import br.com.caelum.stella.bean.validation.CPF;
import br.com.cooperativa.api.model.dto.AssociadoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "TB_ASSOCIADO")
public class Associado {

    @Id
    @CPF
    @Column(name = "CO_CPF")
    private String cpf;

    @ManyToMany
    private List<Pauta> pautas;

    public AssociadoDTO converte() {
        return AssociadoDTO.builder()
                .cpf(this.cpf)
                .build();
    }
}
