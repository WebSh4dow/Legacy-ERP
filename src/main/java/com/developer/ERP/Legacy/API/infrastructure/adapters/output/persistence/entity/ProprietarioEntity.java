package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity;

import com.developer.ERP.Legacy.API.domain.enums.Nacionalidade;
import com.developer.ERP.Legacy.API.domain.model.Honorario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "proprietario")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProprietarioEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;

    private String cpf;

    private String gerenciadoPor;

    private String rg;

    private String dataNascimento;

    private String profissao;

    private String naturalidade;

    private String natura;

    @Enumerated(EnumType.STRING)
    private Nacionalidade nacionalidade;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codigo_honorario")
    private HonorarioEntity honorario;


}
