package com.developer.ERP.Legacy.API.domain.enums;

public enum SubtipoImovel {
    APARTAMENTO_STANDARD("Standard"),
    APARTAMENTO_LUXO("Luxo"),
    APARTAMENTO_COBERTURA("Cobertura"),
    APARTAMENTO_STUDIO("Studio"),
    CASA_TERREA("Térrea"),
    CASA_SOBRADO("Sobrado"),
    CASA_CONDOMINIO_FECHADO("Condomínio Fechado"),
    TERRENO_URBANO("Urbano"),
    TERRENO_RURAL("Rural"),
    LOJA_SHOPPING("Shopping"),
    LOJA_RUA("Rua"),
    SALA_COMERCIAL_PADRAO("Padrão"),
    SALA_COMERCIAL_EXECUTIVA("Executiva"),
    GALPAO_LOGISTICO("Logístico"),
    GALPAO_INDUSTRIAL("Industrial");

    private final String descricao;

    SubtipoImovel(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
