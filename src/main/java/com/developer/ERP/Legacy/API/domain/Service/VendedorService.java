package com.developer.ERP.Legacy.API.domain.Service;

import com.developer.ERP.Legacy.API.domain.Exceptions.HandlerNotFoundException;
import com.developer.ERP.Legacy.API.domain.Model.Vendedor;
import com.developer.ERP.Legacy.API.domain.Repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static com.developer.ERP.Legacy.API.infrastructure.service.Messages.VendedorMessage.MSG_INFORMATION_NOT_FOUND_VENDEDOR;
@Service
public class VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;
    public List <Vendedor>listarTodos(){
        return vendedorRepository.findAll()
                .stream()
                .collect(Collectors.toList());
    }
    public Vendedor listarPorId(Long id){
        return vendedorRepository.findById(id)
                .orElseThrow(()->new HandlerNotFoundException(MSG_INFORMATION_NOT_FOUND_VENDEDOR+id));
    }
    @Transactional
    public ResponseEntity <Vendedor>registrarVendedor(Vendedor vendedor){
        Vendedor registrarVendedor = vendedorRepository.saveAndFlush(vendedor);
        return ResponseEntity.ok().body(registrarVendedor);
    }
    @Transactional
    public void alterarVendedor(Long id, Vendedor vendedor){
        Vendedor alterarVendedor = vendedorRepository.findById(id)
                .map(salvaOuEditar ->{
                    salvaOuEditar.setFuncionario(vendedor.getFuncionario());
                    salvaOuEditar.setPercentualAvista(vendedor.getPercentualAvista());
                    Vendedor editar = vendedorRepository.saveAndFlush(salvaOuEditar);
                    return ResponseEntity.ok().body(editar);
                })
                .orElseThrow(()-> new HandlerNotFoundException(MSG_INFORMATION_NOT_FOUND_VENDEDOR + id)).getBody();

    }
    @Transactional
    public ResponseEntity<Map<String, Boolean>> remover(Long id){
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(()->new HandlerNotFoundException(MSG_INFORMATION_NOT_FOUND_VENDEDOR+id));
        vendedorRepository.delete(vendedor);

        Map<String,Boolean>response = new HashMap<>();
        response.put("removido",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
