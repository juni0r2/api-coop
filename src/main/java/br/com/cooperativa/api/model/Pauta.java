package br.com.cooperativa.api.model;

import br.com.cooperativa.api.model.dto.PautaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
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

    @OneToMany(mappedBy = "pauta")
    private List<Associado> associados;

    @Transient
    private List<Voto> votos;

    public PautaDTO converte() {
        return PautaDTO.builder()
                .id(this.id)
                .nome(this.nome)
                .associados(this.associados)
                .build();
    }
}
