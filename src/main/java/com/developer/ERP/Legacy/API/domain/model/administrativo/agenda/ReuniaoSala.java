package com.developer.ERP.Legacy.API.domain.model.administrativo.agenda;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "REUNIAO_SALA")
@Data
public class ReuniaoSala implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PREDIO")
    private String predio;

    @Column(name = "ANDAR")
    private String andar;

    @Column(name = "NUMERO")
    private String numero;
}
