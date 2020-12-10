package br.com.cooperativa.api.model;

import br.com.cooperativa.api.model.dto.PautaDTO;
import br.com.cooperativa.api.model.dto.VotoDTO;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.ArrayList;
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

    @ManyToMany
    private List<Associado> associados;

    @OneToMany(mappedBy = "pauta")
    private List<Voto> votos;

    public PautaDTO converte() {

        List<VotoDTO> votosDTO = new ArrayList<>();

        this.votos.forEach(voto -> {
            VotoDTO converte = voto.converte();
            votosDTO.add(converte);
        });

        return PautaDTO.builder()
                .id(this.id)
                .nome(this.nome)
                .dataAbertura(this.dataAbertura)
                .dataFechamento(this.dataFechamento)
                .situacaoPauta(this.situacao)
                .votos(votosDTO)
                .build();
    }
}
