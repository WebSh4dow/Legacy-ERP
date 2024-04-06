package com.developer.ERP.Legacy.API.domain.enums;

public enum FilaLocacoes {
    VENDAS("Vendas"),
    LOCACOES("Locações"),
    MANUTENCAO("Manutenção"),
    SUPORTE_TECNICO("Suporte Técnico"),
    ATENDIMENTO_AO_CLIENTE("Atendimento ao Cliente"),
    FINANCEIRO("Financeiro"),
    RELATORIOS("Relatórios");

    private final String descricao;

    FilaLocacoes(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
