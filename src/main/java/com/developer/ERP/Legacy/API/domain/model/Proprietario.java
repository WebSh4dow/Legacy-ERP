package com.developer.ERP.Legacy.API.domain.model;

import com.developer.ERP.Legacy.API.domain.enums.Nacionalidade;
import lombok.*;

import javax.persistence.Column;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Proprietario {

    private Long codigo;
    private String nome;
    private String cpf;
    private String gerenciadoPor;
    private String rg;
    private String dataNascimento;
    private String profissao;
    private String natura;
    private Nacionalidade nacionalidade;
    @Column(name = "honorario")
    private Honorario honorario;

}
