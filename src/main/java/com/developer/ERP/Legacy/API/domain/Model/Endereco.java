package com.developer.ERP.Legacy.API.domain.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String bairro;
    private String rua;
    private Integer numero;
    private String proximidades;
    @Embedded
    private Estado estado;
    @Embedded
    private Cidade cidade;


}
