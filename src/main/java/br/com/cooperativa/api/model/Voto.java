package br.com.cooperativa.api.model;

import br.com.cooperativa.api.model.dto.PautaDTO;
import br.com.cooperativa.api.model.dto.VotoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "TB_VOTO")
public class Voto {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PA_ID")
    private Long pauta;

    @Column(name = "VO_VOTO")
    private String voto;

    @Column(name = "CO_CPF")
    private String cpf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CO_CPF", updatable = false, insertable = false)
    private Associado associado;

    public VotoDTO converte() {
        return VotoDTO.builder()
                .id(this.id)
                .cpf(this.cpf)
                .voto(this.voto)
                .pauta(this.pauta)
                .build();
    }
}
