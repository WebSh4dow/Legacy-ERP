package com.developer.ERP.Legacy.API.domain.Service;

import com.developer.ERP.Legacy.API.domain.Enumerated.IndicadorIE;
import com.developer.ERP.Legacy.API.domain.Exceptions.HandlerNotFoundException;
import com.developer.ERP.Legacy.API.domain.Model.Cliente;
import com.developer.ERP.Legacy.API.domain.Model.PessoaFisica;
import com.developer.ERP.Legacy.API.domain.Model.PessoaJuridica;
import com.developer.ERP.Legacy.API.domain.Model.CriteriaFilter.ClienteCriteriaFilter;
import com.developer.ERP.Legacy.API.domain.Model.Filter.ClienteFilter;
import com.developer.ERP.Legacy.API.domain.Repository.ClienteRepository;
import com.developer.ERP.Legacy.API.domain.RepositoryImpl.ClienteRepositoryImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.developer.ERP.Legacy.API.infrastructure.service.Messages.ClienteMessage.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;

	private final ClienteRepositoryImpl clienteRepositoryImpl;

	public ClienteService(ClienteRepository clienteRepository, ClienteRepositoryImpl clienteRepositoryImpl) {
		this.clienteRepository = clienteRepository;
		this.clienteRepositoryImpl = clienteRepositoryImpl;
	}

	public Page<Cliente> pesquisar(ClienteFilter clienteFilter, ClienteCriteriaFilter clienteCriteriaFilter) {
		return clienteRepositoryImpl.buscarClientes(clienteFilter, clienteCriteriaFilter);
	}

	public Cliente cadastrarCliente(Cliente cliente) throws Exception {

		try {
			
			Cliente salvarCliente = clienteRepository.save(cliente);
			PessoaJuridica pessoaJuridica = cliente.getPessoaJuridica();
			PessoaFisica pessoaFisica = cliente.getPessoaFisica();

			if (pessoaJuridica != null && !pessoaJuridica.getInscricaoEstadual().isBlank()) {
				pessoaJuridica.setIndicadorIe(IndicadorIE.CONTRIBUINTE_ICMS);
				cliente.getPessoaJuridica().setIndicadorIe(pessoaJuridica.getIndicadorIe());
				return salvarCliente;

			} else if (pessoaJuridica != null && pessoaJuridica.getInscricaoEstadual().isBlank()) {
				pessoaJuridica.setIndicadorIe(IndicadorIE.CONTRIBUINTE_ISENTO);
				cliente.getPessoaJuridica().setIndicadorIe(pessoaJuridica.getIndicadorIe());
				return salvarCliente;
			}

			if (pessoaFisica != null && !pessoaFisica.getInscricaoEstadual().isBlank()) {
				pessoaFisica.setIndicadorIe(IndicadorIE.CONTRIBUINTE_ICMS);
				cliente.getPessoaFisica().setIndicadorIe(pessoaFisica.getIndicadorIe());
				return salvarCliente;

			} else if (pessoaFisica != null && pessoaFisica.getInscricaoEstadual().isBlank()) {
				pessoaFisica.setIndicadorIe(IndicadorIE.CONTRIBUINTE_ISENTO);
				cliente.getPessoaFisica().setIndicadorIe(pessoaFisica.getIndicadorIe());
				return salvarCliente;
			}
			return salvarCliente;
		} catch (Exception e) {
			throw new Exception("Ocorreu um erro ao tentar salvar o cliente:" + e);
		}

	}
	
	
	public ResponseEntity<Cliente> findByCliente(Long id) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new HandlerNotFoundException(MSG_CLIENTE_NAO_ENCONTRADO + id));
		return ResponseEntity.ok().body(cliente);
	}

	public Cliente editar(Cliente cliente, Long id) {
		Cliente editarOuSalvar = clienteRepository.findById(id).get();
		if (editarOuSalvar == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(cliente, editarOuSalvar, "id");
		return clienteRepository.save(editarOuSalvar);
	}

	public ResponseEntity<Map<String, Boolean>> remover(Long id) {
		Cliente cliente = clienteRepository.findById(id).orElse(null);
		clienteRepository.delete(cliente);

		Map<String, Boolean> response = new HashMap<>();
		response.put("removido com sucesso!!!!", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
