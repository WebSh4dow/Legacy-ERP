package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.query;

public interface ProprietarioQuery {
    public static String CONSULTAR_PROPRIETARIO_POR_CPF =
            "FROM ProprietarioEntity as p WHERE p.cpf = ?1 ";

    public static String CONSULTAR_PROPRIETARIO_POR_NOME =
            "FROM ProprietarioEntity as p WHERE p.nome LIKE %?1%";

    public static String CONSULTAR_PROPRIETARIO_POR_PROFISSAO =
            "FROM ProprietarioEntity as p WHERE p.profissao = ?1 ";
}
