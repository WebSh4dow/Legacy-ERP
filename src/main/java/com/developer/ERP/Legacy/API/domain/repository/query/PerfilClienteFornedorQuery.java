package com.developer.ERP.Legacy.API.domain.repository.query;

public interface PerfilClienteFornedorQuery {

    public static final String FILTRAR_POR_TIPO_PERFIL =
            "SELECT p.id, p.descricao_perfil, p.tipo_perfil, p.visivel_site " +
                    "FROM Perfil p " +
                    "WHERE UPPER(p.tipo_perfil) LIKE UPPER(CONCAT('%', ?1, '%'))";
}
