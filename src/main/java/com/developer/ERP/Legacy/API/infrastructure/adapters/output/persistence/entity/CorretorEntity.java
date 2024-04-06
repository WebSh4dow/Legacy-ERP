package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity;

import com.developer.ERP.Legacy.API.domain.enums.AcessoImoveis;
import com.developer.ERP.Legacy.API.domain.enums.Genero;
import com.developer.ERP.Legacy.API.domain.enums.Nacionalidade;
import com.developer.ERP.Legacy.API.domain.model.DadosPessoais;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "corretor")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CorretorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;

    private String cpf;

    private String rg;

    private String apelido;

    private String Creci;

    private String nascimento;

    private String natura;

    private BigDecimal porcentagemLocacao = BigDecimal.ZERO;

    private BigDecimal porcentagemVenda = BigDecimal.ZERO;

    private BigDecimal porcentagemCaptacao = BigDecimal.ZERO;

    private BigDecimal porcentagemIndicacao = BigDecimal.ZERO;

    private BigDecimal ajudaCusto = BigDecimal.ZERO;

    private boolean captador;

    private boolean corretor;

    private boolean indicador;

    private boolean platonista;

    private boolean promotor;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "codigo_contatos")
    private List<DadosPessoaisEntity> contatos;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CorretorEntity> corretores;

    @Enumerated(EnumType.STRING)
    private Nacionalidade nacionalidade;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Enumerated(EnumType.STRING)
    private AcessoImoveis acessoImoveis;


}
