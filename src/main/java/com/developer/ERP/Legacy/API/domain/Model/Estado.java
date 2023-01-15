package com.developer.ERP.Legacy.API.domain.Model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Estado {
    private String codigoEstado;
    private String nomeEstado;

}
