package com.developer.ERP.Legacy.API.domain.service;

import com.developer.ERP.Legacy.API.domain.exceptions.runtime.HandlerNotFoundException;
import com.developer.ERP.Legacy.API.domain.model.Departamentos;
import com.developer.ERP.Legacy.API.domain.model.Setor;
import com.developer.ERP.Legacy.API.domain.repository.SetorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SetorService {
    @Autowired
    private SetorRepository setorRepository;
    @Autowired
    private DepartamentoService departamentoService;

    @Transactional
    public ResponseEntity<Setor> adcionar(Setor setor){
        Setor adcionar = setorRepository.save(setor);
        return ResponseEntity.ok().body(adcionar);
    }
    public List <Setor> setores(){
        return setorRepository.findAll();
    }
    public ResponseEntity <Setor> buscar(Long id){
        Setor buscarSetores = setorRepository.findById(id)
                .orElse(null);
        return ResponseEntity.ok().body(buscarSetores);
    }
    @Transactional
    public Setor alterar(Long id, Setor setor){
        Setor setores = setorRepository.findById(id).get();
        if (setores == null){
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(setor,setores,"id");
        return setorRepository.save(setores);
    }
    @Transactional
    public ResponseEntity<Map<String, Boolean>> remover(Long id){
        Setor setor = setorRepository.findById(id)
                .orElseThrow(()->new HandlerNotFoundException("Setor não encontrado"+id));
        setorRepository.delete(setor);

        Map<String,Boolean>response = new HashMap<>();
        response.put("removido",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    @Transactional
    public boolean vincularDepartamentos(Long setorId,Long departamentoId){
            Setor buscar = buscar(setorId).getBody();
            Departamentos buscarDepartamento = departamentoService.buscar(departamentoId).getBody();
            return buscar.associar(buscarDepartamento);
    }
    @Transactional
    public boolean desvincularDepartamentos(Long setorId,Long departamentoId){
        Setor buscar = buscar(setorId).getBody();
        Departamentos buscarDepartamento = departamentoService.buscar(departamentoId).getBody();
        return buscar.desassociar(buscarDepartamento);
    }
}
