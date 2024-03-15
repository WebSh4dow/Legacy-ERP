package com.developer.ERP.Legacy.API.domain.enums;

public enum Nacionalidade {
    BRASILEIRO("Brasileiro"),
    AMERICANO("Americano"),
    CANADENSE("Canadense"),
    FRANCES("Francês"),
    INGLES("Inglês"),
    ALEMAO("Alemão"),
    JAPONES("Japonês"),
    CHINES("Chinês"),
    RUSSO("Russo"),
    ITALIANO("Italiano"),
    ESPANHOL("Espanhol"),
    MEXICANO("Mexicano"),
    ARGENTINO("Argentino"),
    AUSTRALIANO("Australiano"),
    SUL_AFRICANO("Sul-africano");

    private final String descricao;

    Nacionalidade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Nacionalidade fromDescricao(String descricao) {
        for (Nacionalidade nacionalidade : values()) {
            if (nacionalidade.getDescricao().equalsIgnoreCase(descricao)) {
                return nacionalidade;
            }
        }
        throw new IllegalArgumentException("Nacionalidade não encontrada para a descrição: " + descricao);
    }
}