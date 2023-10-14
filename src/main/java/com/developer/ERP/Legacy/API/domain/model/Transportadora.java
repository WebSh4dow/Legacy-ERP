package com.developer.ERP.Legacy.API.domain.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Transportadora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String tipo;
    private String inscricao;
    private String placa;
    private String telefone;
    private String celular;
    private String fornecedor;
    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

}
