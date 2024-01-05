package com.developer.ERP.Legacy.API.domain.repository;

import com.developer.ERP.Legacy.API.domain.model.Perfil;
import com.developer.ERP.Legacy.API.domain.repository.query.PerfilClienteFornedorQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerfilRepository extends JpaRepository<Perfil, Long>, PerfilClienteFornedorQuery {

    @Query(value = FILTRAR_POR_TIPO_PERFIL, nativeQuery = true)
    List<Perfil> filtrarPorTipoPerfil(String tipoPerfil);
}
