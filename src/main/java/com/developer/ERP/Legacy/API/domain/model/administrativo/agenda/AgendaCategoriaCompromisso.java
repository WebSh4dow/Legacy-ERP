package com.developer.ERP.Legacy.API.domain.model.administrativo.agenda;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AGENDA_CATEGORIA_COMPROMISSO")
@Data
public class AgendaCategoriaCompromisso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "COR")
    private String cor;
}
