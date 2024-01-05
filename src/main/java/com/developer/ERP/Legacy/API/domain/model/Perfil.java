package com.developer.ERP.Legacy.API.domain.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
@SequenceGenerator(name = "seq_perfil_cliente_fornecedor", sequenceName = "seq_perfil_cliente_fornecedor",allocationSize = 1, initialValue = 1)
public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_perfil_cliente_fornecedor")
    private Long id;

    @NotBlank(message = "O tipo de perfil é um campo obrigatório")
    private String tipoPerfil;

    private String descricaoPerfil;

    private boolean visivelSite;


}
