package com.developer.ERP.Legacy.API.domain.Enum;
import lombok.Getter;

@Getter
public enum DepartamentoEnum {
    NAO_INFORMADO(0,"Departamento Não Informado");
    private Integer codigo;
    private String descricao;
    DepartamentoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
}
