package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity;

import com.developer.ERP.Legacy.API.domain.enums.TipoImovel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "imovel")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImovelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String codAnuncio;

    private BigDecimal valorVenda = BigDecimal.ZERO;

    private BigDecimal valorLocalcao = BigDecimal.ZERO;

    private BigDecimal valorIptu = BigDecimal.ZERO;

    private BigDecimal valorCondominio = BigDecimal.ZERO;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codigo_endereco")
    private EnderecoEntity endereco;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "codigo_empreendimentos")
    private List<EmpreendimentoEntity> empreendimentos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "codigo_proprietarios")
    private List<ProprietarioEntity> proprietarios;

    @Enumerated(EnumType.STRING)
    private TipoImovel tipoImovel;

    @DateTimeFormat
    private Date captacaoEm;

    private String exclusividadeAte;

    private boolean fiador;

    private boolean deposito;

    private boolean seguroFianca;

    private boolean imovelOcupado;

    private boolean inativo;

    private boolean possuiPlaca;

    private boolean exclusivo;

    private boolean lancamento;

    private boolean altoPadrao;

    private boolean temporada;


}
