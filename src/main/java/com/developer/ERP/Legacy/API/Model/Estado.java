package com.developer.ERP.Legacy.API.Model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Estado {
    private String codigoEstado;
    private String nomeEstado;

}
