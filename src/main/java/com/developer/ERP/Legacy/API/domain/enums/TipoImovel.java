package com.developer.ERP.Legacy.API.domain.enums;

public enum TipoImovel {
    CASA("Casa"),
    APARTAMENTO("Apartamento"),
    TERRENO("Terreno"),
    CHACARA("Chácara"),
    SITIO("Sítio"),
    LOJA("Loja"),
    SALA_COMERCIAL("Sala Comercial"),
    GALPAO("Galpão"),
    BARRACAO("Barracão"),
    EDIFICIO_COMERCIAL("Edifício Comercial"),
    EDIFICIO_RESIDENCIAL("Edifício Residencial"),
    FLAT("Flat"),
    KITNET("Kitnet"),
    PREDIO("Prédio"),
    CASA_DE_CONDOMINIO("Casa de Condomínio"),
    APARTAMENTO_DE_CONDOMINIO("Apartamento de Condomínio"),
    COBERTURA("Cobertura"),
    LOTEAMENTO("Loteamento"),
    PENTHOUSE("Penthouse"),
    QUITINETE("Quitinete"),
    SOBRADO("Sobrado"),
    VILA("Vila"),
    FAZENDA("Fazenda"),
    PREDIO_COMERCIAL("Prédio Comercial"),
    PREDIO_RESIDENCIAL("Prédio Residencial");

    private final String descricao;

    TipoImovel(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
