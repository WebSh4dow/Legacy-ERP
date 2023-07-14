package com.developer.ERP.Legacy.API.domain.Service;

import com.developer.ERP.Legacy.API.domain.Enumerated.IndicadorIE;
import com.developer.ERP.Legacy.API.domain.Exceptions.HandlerCadastroOutros;
import com.developer.ERP.Legacy.API.domain.Exceptions.HandlerClienteCadastro;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static com.developer.ERP.Legacy.API.infrastructure.service.Messages.ClienteMessage.*;
import java.util.HashMap;
import java.util.Map;


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

			PessoaJuridica pessoaJuridica = cliente.getPessoaJuridica();
			PessoaFisica pessoaFisica = cliente.getPessoaFisica();
			
			if (pessoaFisica != null) {
				if (pessoaFisica != null && !pessoaFisica.getInscricaoEstadual().isBlank()) {
					pessoaFisica.setIndicadorIe(IndicadorIE.CONTRIBUINTE_ICMS);
					cliente.getPessoaFisica().setIndicadorIe(pessoaFisica.getIndicadorIe());
					return validarCadastroOutros(cliente);
				}
				else {
					pessoaFisica.setIndicadorIe(IndicadorIE.CONTRIBUINTE_ISENTO);
					cliente.getPessoaFisica().setIndicadorIe(pessoaFisica.getIndicadorIe());
					return validarCadastroOutros(cliente);
				}
			}
			
			if (pessoaJuridica != null) {
				if (pessoaJuridica != null && pessoaJuridica.getInscricaoEstadual().isBlank()) {
					pessoaJuridica.setIndicadorIe(IndicadorIE.CONTRIBUINTE_ISENTO);
					cliente.getPessoaJuridica().setIndicadorIe(pessoaJuridica.getIndicadorIe());
					return validarCadastroOutros(cliente);
				}
				else {
					pessoaJuridica.setIndicadorIe(IndicadorIE.CONTRIBUINTE_ICMS);
					cliente.getPessoaJuridica().setIndicadorIe(pessoaJuridica.getIndicadorIe());
					return validarCadastroOutros(cliente);
				}
			}
			
			return validarCadastroOutros(cliente);
		} catch (Exception e) {
			throw new HandlerClienteCadastro("Ocorreu um erro ao tentar salvar o cliente:" + e.getMessage());
		}

	}
	
	public Cliente validarCadastroOutros(Cliente cliente) {

		if (cliente.getOutros() == null)
			throw new HandlerCadastroOutros("O Cadastro de outros também é um campo obrigatório.");
		else
			clienteRepository.save(cliente);
		return null;
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
