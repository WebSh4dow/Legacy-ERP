package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity;

import com.developer.ERP.Legacy.API.domain.enums.Genero;
import com.developer.ERP.Legacy.API.domain.enums.Nacionalidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codigo_endereco")
    private EnderecoEntity endereco;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codigo_dados_pessoais")
    private DadosPessoaisEntity dadosPessoais;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "proprietario_codigo")
    private List<ContaCorrenteEntity> contasBancarias;

    @Enumerated(EnumType.STRING)
    private Genero genero;


}
