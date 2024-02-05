package com.developer.ERP.Legacy.API.domain.service.agenda;

import com.developer.ERP.Legacy.API.core.abstraction.AbstractService;
import com.developer.ERP.Legacy.API.domain.exceptions.runtime.BussinesException;
import com.developer.ERP.Legacy.API.domain.model.administrativo.agenda.*;
import com.developer.ERP.Legacy.API.domain.model.administrativo.cadastros.Colaborador;
import com.developer.ERP.Legacy.API.infrastructure.interfaces.CustomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;

@Service
public class AgendaCompromissoService extends AbstractService<AgendaCompromisso> implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(AgendaCompromisso.class);
    @Autowired
    private CustomRepository<ReuniaoSala> reuniaoSalaRepository;

    @Autowired
    private CustomRepository<AgendaCategoriaCompromisso> agendaCategoriaCompromissoRepository;

    @Autowired
    private CustomRepository<AgendaCompromisso> agendaCompromissoRepository;

    @Autowired
    private CustomRepository<AgendaCompromissoConvidado> agendaCompromissoConvidadoRepository;

    @Autowired
    private CustomRepository<Colaborador> colaboradorRepository;

    private ReuniaoSalaEvento reuniaoSalaEvento;

    private AgendaCompromissoConvidado agendaCompromissoConvidado;

    private Integer recorrente;
    private Integer quantidadeOcorrencia;

    public List<ReuniaoSala> getListaReuniaoSala(String nome) {
        List<ReuniaoSala> listaReuniaoSala = new ArrayList<>();
        try {
            listaReuniaoSala = reuniaoSalaRepository.getEntitiesLike(ReuniaoSala.class, "predio", nome);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaReuniaoSala;
    }

    public List<AgendaCategoriaCompromisso> getListaAgendaCategoriaCompromisso(String nome) {
        List<AgendaCategoriaCompromisso> listaAgendaCategoriaCompromisso = new ArrayList<>();
        try {
            listaAgendaCategoriaCompromisso = agendaCategoriaCompromissoRepository.getEntitiesLike(AgendaCategoriaCompromisso.class, "nome", nome);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaAgendaCategoriaCompromisso;
    }

    public List<Colaborador> getListaColaborador(String nome) {
        List<Colaborador> listaColaborador = new ArrayList<>();
        try {
            listaColaborador = colaboradorRepository.getEntitiesLike(Colaborador.class, "pessoa.nome", nome);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaColaborador;
    }

    public void incluirAgendaCompromissoConvidado(AgendaCompromissoConvidado agendaCompromissoConvidado) throws Exception {
        List<AgendaCompromissoConvidado> listaCompromissosConvidado = agendaCompromissoConvidadoRepository.getEntities(AgendaCompromissoConvidado.class);
        if (!listaCompromissosConvidado.contains(agendaCompromissoConvidado)) {
            listaCompromissosConvidado.add(agendaCompromissoConvidado);
        }

    }

    @Transactional
    public AgendaCompromissoConvidado cadastrarAgendaCompromissoConvidado(AgendaCompromissoConvidado novoCompromisso) throws Exception {
        incluirAgendaCompromissoConvidado(novoCompromisso);
        agendaCompromissoConvidadoRepository.persist(novoCompromisso);
        return novoCompromisso;
    }

    @Transactional
    public AgendaCompromisso cadastrarAgendaCompromisso(AgendaCompromisso agendaCompromisso) throws Exception {
        List<AgendaCompromisso> compromissosAgenda = agendaCompromissoRepository.getEntities(AgendaCompromisso.class);

        if (compromissosAgenda.contains(agendaCompromisso)) {
            throw new BussinesException("Já existe um compromisso atualmente cadastrado na data atual");
        }

        for (AgendaCompromisso compromissoExistente : compromissosAgenda) {
            List<AgendaCompromissoConvidado> convidadosExistente = Optional.ofNullable(compromissoExistente.getListaAgendaCompromissoConvidado()).orElse(Collections.emptyList());

            for (AgendaCompromissoConvidado agendaCompromissoConvidado : Optional.ofNullable(agendaCompromisso.getListaAgendaCompromissoConvidado()).orElse(Collections.emptyList())) {
                if (convidadosExistente.contains(agendaCompromissoConvidado)) {
                    throw new BussinesException("O convidado já está agendado para outro compromisso na mesma data");
                }
            }
        }

        if (agendaCompromisso.getId() == null) {
            agendaCompromissoRepository.persist(agendaCompromisso);
        } else {
            agendaCompromissoRepository.merge(agendaCompromisso);
        }

        return agendaCompromisso;
    }

    public void incluirReuniaoSalaEvento(ReuniaoSalaEvento reuniaoSalaEvento) {
        if (getObjeto().getReuniaoSalaEvento() == null) {
            getObjeto().setReuniaoSalaEvento(reuniaoSalaEvento);
            reuniaoSalaEvento.setAgendaCompromisso(getObjeto());
            reuniaoSalaEvento.setDataReserva(getObjeto().getDataCompromisso());
        }
    }


    public void incluiCompromissoRecorrente() throws Exception {
        if (quantidadeOcorrencia != null && recorrente != null) {
            int campoSomar = 0;

            switch (recorrente) {
                case 1: {
                    campoSomar = Calendar.DAY_OF_MONTH;
                    break;
                }
                case 2: {
                    campoSomar = Calendar.WEEK_OF_MONTH;
                    break;
                }
                case 3: {
                    campoSomar = Calendar.MONTH;
                    break;
                }
                case 4: {
                    campoSomar = Calendar.YEAR;
                }
                default: {
                    break;
                }
            }

            if (campoSomar != 0) {
                Calendar dataCompromisso = Calendar.getInstance();
                dataCompromisso.setTime(getObjeto().getDataCompromisso());

                for (int i = 0; i < quantidadeOcorrencia; i++) {
                    AgendaCompromisso compromisso = new AgendaCompromisso();

                    compromisso.setColaborador(getObjeto().getColaborador());
                    compromisso.setAgendaCategoriaCompromisso(getObjeto().getAgendaCategoriaCompromisso());
                    compromisso.setDescricao(getObjeto().getDescricao());
                    compromisso.setDuracao(getObjeto().getDuracao());
                    compromisso.setHora(getObjeto().getHora());
                    compromisso.setOnde(getObjeto().getOnde());
                    compromisso.setTipo(getObjeto().getTipo());

                    dataCompromisso.add(campoSomar, 1);
                    compromisso.setDataCompromisso(dataCompromisso.getTime());

                    agendaCompromissoRepository.persist(compromisso);
                }
            }
        }
    }

    public void salvarReuniaoSalaEvento() {
        try {
            getObjeto().setReuniaoSalaEvento(reuniaoSalaEvento);
            LOGGER.info("sala de reunião definida com sucesso {}", reuniaoSalaEvento);
        } catch (Exception e) {
            LOGGER.error("sala de reunião não foi definida erro atual {}", e.getMessage());
        }
    }

}
