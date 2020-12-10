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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

    @Column(name = "CO_VOTO")
    private String voto;

    @ManyToOne
    @JoinColumn(name = "CO_PAUTA_ID")
    private Pauta pauta;

    public AssociadoDTO converte() {
        return AssociadoDTO.builder()
                .cpf(this.cpf)
                .build();
    }
}
