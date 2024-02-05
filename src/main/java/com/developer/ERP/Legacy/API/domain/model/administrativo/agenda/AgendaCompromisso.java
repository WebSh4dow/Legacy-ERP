package com.developer.ERP.Legacy.API.domain.model.administrativo.agenda;

import com.developer.ERP.Legacy.API.domain.model.administrativo.cadastros.Colaborador;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "AGENDA_COMPROMISSO")
@Data
public class AgendaCompromisso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_COMPROMISSO")
    private Date dataCompromisso;

    @Column(name = "HORA")
    private String hora;

    @Column(name = "DURACAO")
    private Integer duracao;

    @Column(name = "ONDE")
    private String onde;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "TIPO")
    private Integer tipo;

    @JoinColumn(name = "ID_AGENDA_CATEGORIA_COMPROMISSO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private AgendaCategoriaCompromisso agendaCategoriaCompromisso;

    @JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Colaborador colaborador;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "agendaCompromisso", cascade = CascadeType.MERGE, orphanRemoval = true)
    private ReuniaoSalaEvento reuniaoSalaEvento;

    @Transient
    private List<AgendaCompromissoConvidado> listaAgendaCompromissoConvidado;

}
