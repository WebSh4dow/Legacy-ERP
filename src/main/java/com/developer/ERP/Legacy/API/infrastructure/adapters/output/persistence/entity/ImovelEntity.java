package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity;

import com.developer.ERP.Legacy.API.domain.enums.TipoImovel;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codigo_endereco")
    private EnderecoEntity endereco;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "codigo_empreendimentos")
    private List<EmpreendimentoEntity> empreendimentos;

    @ManyToMany(mappedBy = "imoveis",cascade = CascadeType.ALL)
    private List<ProprietarioEntity> proprietarios;

    @Enumerated(EnumType.STRING)
    private TipoImovel tipoImovel;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date captacaoEm;

    private String codAnuncio;

    private String exclusividadeAte;

    private BigDecimal valorVenda = BigDecimal.ZERO;

    private BigDecimal valorLocalcao = BigDecimal.ZERO;

    private BigDecimal valorIptu = BigDecimal.ZERO;

    private BigDecimal valorCondominio = BigDecimal.ZERO;

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
