package com.developer.ERP.Legacy.API.Model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Cidade {

    private String codMunicipio;
    private String nomeMunicipio;
    private int codEstado;


}
