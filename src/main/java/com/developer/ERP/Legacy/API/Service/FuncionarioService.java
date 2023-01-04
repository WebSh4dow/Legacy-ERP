package com.developer.ERP.Legacy.API.Service;

import com.developer.ERP.Legacy.API.Exceptions.HandlerDataIntegrationValidation;
import com.developer.ERP.Legacy.API.Model.Funcionario;
import com.developer.ERP.Legacy.API.Repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import static com.developer.ERP.Legacy.API.Messages.FuncionarioMessage.MSG_INFORMATION_NOT_FOUND_FUNCIONARIO;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    public List<Funcionario> listarTodos(){
        return funcionarioRepository.findAll()
                .stream()
                .collect(Collectors.toList());
    }

    public ResponseEntity <Funcionario>listarPorId(Long id){
       Funcionario pesquisarFuncionario = funcionarioRepository.findById(id)
               .orElse(null);
       return ResponseEntity.ok().body(pesquisarFuncionario);
    }
    @Transactional
    public ResponseEntity <Funcionario>incluir(Funcionario funcionario){
        Funcionario incluirFuncionario = funcionarioRepository.save(funcionario);
        return ResponseEntity.ok().body(incluirFuncionario);
    }
    @Transactional
    public ResponseEntity <Funcionario>alterar(Funcionario funcionario, Long id){
            Funcionario funcionarioSave = funcionarioRepository.findById(id)
                    .orElse(null);

            funcionarioSave.setTipo(funcionario.getTipo());
            funcionarioSave.setNome(funcionario.getNome());
            funcionarioSave.setLogin(funcionario.getLogin());

            funcionarioSave.setSenha(funcionario.getSenha());
            funcionarioSave.setEmail(funcionario.getEmail());
            funcionarioSave.setCpf(funcionario.getCpf());

            funcionarioSave.setRg(funcionario.getRg());
            funcionarioSave.setCarteiraTrabalho(funcionario.getCarteiraTrabalho());
            funcionarioSave.setDataContratacao(funcionario.getDataContratacao());

            funcionarioSave.setTelefoneFixo(funcionario.getTelefoneFixo());
            funcionarioSave.setCelular(funcionario.getCelular());

            funcionarioSave.setDataNascimento(funcionario.getDataNascimento());
            funcionarioSave.setObservacoes(funcionario.getObservacoes());

            funcionarioSave.setSaldoCaixa(funcionario.getSaldoCaixa());
            Funcionario salvarOuAtualizar = funcionarioRepository.save(funcionarioSave);

            return ResponseEntity.ok().body(salvarOuAtualizar);

    }
    @Transactional
    public ResponseEntity<Map<String, Boolean>> remover(Long id){
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElse(null);
        funcionarioRepository.delete(funcionario);

        Map<String,Boolean>response = new HashMap<>();
        response.put("removido",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
