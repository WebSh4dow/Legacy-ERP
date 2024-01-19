package com.developer.ERP.Legacy.API.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@SequenceGenerator(name = "seq_item_perfil_cliente_fornecedor", sequenceName = "seq_item_perfil_cliente_fornecedor",allocationSize = 1, initialValue = 1)
public class ItemPerfil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
}
