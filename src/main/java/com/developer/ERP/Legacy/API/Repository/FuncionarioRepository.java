package com.developer.ERP.Legacy.API.Repository;

import com.developer.ERP.Legacy.API.Model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository <Funcionario,Long> {
    @Query(nativeQuery = true,value = "SELECT id," +
            "carteira_trabalho, " +
            "celular," +
            " cpf, " +
            "data_contratacao, " +
            "data_nascimento," +
            " email, " +
            "login, " +
            "nome," +
            " observacoes, " +
            "rg, " +
            "saldo_caixa, " +
            "senha, " +
            "telefone_fixo, " +
            "tipo\n" +
            "FROM funcionario WHERE login = 0 ")

    List <Funcionario> findByInativoContaining(int login);

    @Query(nativeQuery = true,value = "SELECT id," +
            "carteira_trabalho, " +
            "celular," +
            " cpf, " +
            "data_contratacao, " +
            "data_nascimento," +
            " email, " +
            "login, " +
            "nome," +
            " observacoes, " +
            "rg, " +
            "saldo_caixa, " +
            "senha, " +
            "telefone_fixo, " +
            "tipo\n" +
            "FROM funcionario WHERE login = 1 ")
    List <Funcionario> findByAtivoContaining(int login);

    Optional<Funcionario>findByEmail(String email);

    Optional <Funcionario>findByCpf(String cpf);

    Optional <Funcionario>findByLogin(int login);
}
