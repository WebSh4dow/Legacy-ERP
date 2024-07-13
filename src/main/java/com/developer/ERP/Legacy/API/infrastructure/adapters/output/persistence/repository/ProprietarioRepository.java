package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.repository;

import com.developer.ERP.Legacy.API.domain.model.Proprietario;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity.ProprietarioEntity;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.query.ProprietarioQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProprietarioRepository extends JpaRepository<ProprietarioEntity, Long>, ProprietarioQuery {
    boolean existsProprietarioByCpfAndRgIgnoreCase(String cpf, String rg);

    @Query(value = CONSULTAR_PROPRIETARIO_POR_CPF)
    ProprietarioEntity consultarProprietarioPorCpf(String cpf);

    @Query(value = CONSULTAR_PROPRIETARIO_POR_PROFISSAO)
    List<ProprietarioEntity> consultarProprietariosPorProfissao(String profissao);

    @Query(value = CONSULTAR_PROPRIETARIO_POR_NOME)
    List<ProprietarioEntity> consultarProprietarioPorNome(String nome);


}
