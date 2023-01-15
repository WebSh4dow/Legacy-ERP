package com.developer.ERP.Legacy.API.domain.Model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Cidade {
    private String codMunicipio;
    private String nomeMunicipio;
    private int codEstado;


}
