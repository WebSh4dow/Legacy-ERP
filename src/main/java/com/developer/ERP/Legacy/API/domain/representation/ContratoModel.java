package com.developer.ERP.Legacy.API.domain.representation;

import com.developer.ERP.Legacy.API.domain.enumerated.CentroCusto;
import com.developer.ERP.Legacy.API.domain.enumerated.TipoContrato;
import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.hateoas.RepresentationModel;
import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Relation(collectionRelation = "contratos")
public class ContratoModel extends RepresentationModel<ContratoModel> {

    private Long id;

    private boolean status;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicial;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;

    @Enumerated(EnumType.STRING)
    private CentroCusto centroCusto;

    @Enumerated(EnumType.STRING)
    private TipoContrato tipoContratos;

    private Cliente cliente;
}
