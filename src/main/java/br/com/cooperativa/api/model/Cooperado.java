package br.com.cooperativa.api.model;

import br.com.caelum.stella.bean.validation.CPF;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "TB_COOPERADO")
public class Cooperado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CO_ID")
    private Long id;

    @CPF
    @Column(name = "CO_CPF")
    private String cpf;
}
