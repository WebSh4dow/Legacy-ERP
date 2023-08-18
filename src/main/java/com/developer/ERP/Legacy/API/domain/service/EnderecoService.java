package com.developer.ERP.Legacy.API.domain.service;

import com.developer.ERP.Legacy.API.domain.model.Endereco;
import com.developer.ERP.Legacy.API.domain.repository.criteriaFilter.EnderecoCriteriaFilter;
import com.developer.ERP.Legacy.API.domain.repository.filter.EnderecoFilter;
import com.developer.ERP.Legacy.API.domain.repository.EnderecoRepository;
import com.developer.ERP.Legacy.API.infrastructure.repositoryImpl.EnderecoRepositoryImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class EnderecoService {

	private final EnderecoRepository enderecoRepository;

	private final EnderecoRepositoryImpl enderecoRepositoryImpl;

	public EnderecoService(EnderecoRepository enderecoRepository,EnderecoRepositoryImpl endereoEnderecoRepositoryImpl) {
		this.enderecoRepositoryImpl = endereoEnderecoRepositoryImpl;
		this.enderecoRepository = enderecoRepository;
	}

	public Page<Endereco> listarTodosEnderecos(EnderecoFilter enderecoFilter,
			EnderecoCriteriaFilter enderecoCriteriaFilter) {
		return enderecoRepositoryImpl.buscarTodosEnderecos(enderecoFilter, enderecoCriteriaFilter);
	}

	public ResponseEntity<Endereco> listarPor(Long id) {
		Endereco endereco = enderecoRepository.findById(id).orElse(null);
		return ResponseEntity.ok().body(endereco);
	}

	public Endereco editar(Endereco endereco, Long id) {
		Endereco editarOuSalvar = enderecoRepository.findById(id).get();
		if (editarOuSalvar == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(endereco, editarOuSalvar, "id");
		return enderecoRepository.save(editarOuSalvar);
	}

	@Transactional
	public ResponseEntity<Endereco> salvar(Endereco endereco) {
		Endereco cadastrar = enderecoRepository.save(endereco);
		return ResponseEntity.ok().body(cadastrar);
	}

	@Transactional
	public ResponseEntity<Map<String, Boolean>> remover(Long id) {
		Endereco endereco = enderecoRepository.findById(id).orElse(null);
		enderecoRepository.delete(endereco);

		Map<String, Boolean> response = new HashMap<>();
		response.put("removido", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
