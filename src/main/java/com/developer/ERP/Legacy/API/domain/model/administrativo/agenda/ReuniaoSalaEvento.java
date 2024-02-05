package com.developer.ERP.Legacy.API.domain.model.administrativo.agenda;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "REUNIAO_SALA_EVENTO")
@Data
public class ReuniaoSalaEvento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_RESERVA")
    private Date dataReserva;

    @JoinColumn(name = "ID_REUNIAO_SALA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ReuniaoSala reuniaoSala;

    @JoinColumn(name = "ID_AGENDA_COMPROMISSO", referencedColumnName = "ID")
    @OneToOne(optional = false)
    private AgendaCompromisso agendaCompromisso;

}
