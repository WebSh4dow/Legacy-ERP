package com.developer.ERP.Legacy.API.api.v1.controller;

import com.developer.ERP.Legacy.API.domain.model.administrativo.agenda.AgendaCategoriaCompromisso;
import com.developer.ERP.Legacy.API.domain.model.administrativo.agenda.AgendaCompromisso;
import com.developer.ERP.Legacy.API.domain.model.administrativo.agenda.AgendaCompromissoConvidado;
import com.developer.ERP.Legacy.API.domain.model.administrativo.agenda.ReuniaoSala;
import com.developer.ERP.Legacy.API.domain.model.administrativo.cadastros.Colaborador;
import com.developer.ERP.Legacy.API.domain.service.agenda.AgendaCompromissoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/agenda-corporativa")
public class AgendaController {

    @Autowired
    private AgendaCompromissoService agendaCompromissoService;

    @GetMapping("/listar-reuniao-sala/{nome}")
    public List<ReuniaoSala> getListaReuniaoSala(@PathVariable String nome) {
        return agendaCompromissoService.getListaReuniaoSala(nome);
    }

    @GetMapping("/listar-agenda-categoria-compromisso/{nome}")
    List<AgendaCategoriaCompromisso> getListaAgendaCategoriaCompromisso(@PathVariable String nome) {
        return agendaCompromissoService.getListaAgendaCategoriaCompromisso(nome);
    }

    @GetMapping("/listar-colaborador/{nome}")
    List<Colaborador> getListaColaborador(@PathVariable String nome) {
        return agendaCompromissoService.getListaColaborador(nome);
    }

    @PostMapping("/cadastrar-agenda-compromisso-convidado")
    public AgendaCompromissoConvidado cadastrarAgendaCompromissoConvidado(@RequestBody AgendaCompromissoConvidado agendaCompromissoConvidado) throws Exception {
        return agendaCompromissoService.cadastrarAgendaCompromissoConvidado(agendaCompromissoConvidado);
    }

    @PostMapping(value = "/cadastrar-angenda-compromisso", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AgendaCompromisso cadastrarAgendaCompromisso(@RequestBody AgendaCompromisso agendaCompromisso) throws Exception {
        return agendaCompromissoService.cadastrarAgendaCompromisso(agendaCompromisso);
    }
}
