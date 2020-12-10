package br.com.cooperativa.api.model;

import br.com.cooperativa.api.model.dto.PautaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "TB_PAUTA")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PA_ID")
    private Long id;

    @Column(name = "PA_NOME")
    private String nome;

    @Column(name = "PA_DATA_ABERTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAbertura = new Date();

    @Column(name = "PA_DATA_FECHAMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFechamento;

    @Enumerated(EnumType.STRING)
    private EnumSituacaoPauta situacao = EnumSituacaoPauta.ABERTA;

    @OneToMany(mappedBy = "pauta")
    private List<Associado> associados;

    @OneToMany
    private List<Voto> votos;

    public PautaDTO converte() {
        return PautaDTO.builder()
                .id(this.id)
                .nome(this.nome)
                .associados(this.associados)
                .dataAbertura(this.dataAbertura)
                .dataFechamento(this.dataFechamento)
                .situacaoPauta(this.situacao)
                .build();
    }
}
