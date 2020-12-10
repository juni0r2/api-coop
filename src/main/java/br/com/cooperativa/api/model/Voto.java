package br.com.cooperativa.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    @Column(name = "VO_ID_PAUTA")
    private Long IdPauta;

    @ManyToOne
    private Pauta pauta;

    @Column(name = "VO_VOTO")
    private String voto;

    @ManyToOne
    private Associado associado;
}
