package com.developer.ERP.Legacy.API.domain.represetation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "perfilClienteFornecedor")
@Relation (collectionRelation = "perfilClienteFornecedor")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PerfilRepresentationModel extends RepresentationModel<PerfilRepresentationModel> {

    private Long id;

    private String tipoPerfil;

    private String descricaoPerfil;

    private boolean visivelSite;
}
