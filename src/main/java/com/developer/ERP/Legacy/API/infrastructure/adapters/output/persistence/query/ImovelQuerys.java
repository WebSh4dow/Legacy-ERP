package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.query;


public interface ImovelQuerys {
    public static final String HISTORICO_IMOVEIS_QUERY =
            "SELECT * " +
                    "FROM " +
                    "    imovel i " +
                    "INNER JOIN proprietario p ON " +
                    "    i.codigo = p.codigo " +
                    "    AND i.inativo = false " +
                    "    AND i.imovel_ocupado = false;";
}
