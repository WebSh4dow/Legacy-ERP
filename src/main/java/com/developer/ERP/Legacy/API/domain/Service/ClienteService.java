package com.developer.ERP.Legacy.API.domain.Service;

import com.developer.ERP.Legacy.API.domain.Enumerated.IndicadorIE;
import com.developer.ERP.Legacy.API.domain.Exceptions.HandlerCadastroOutros;
import com.developer.ERP.Legacy.API.domain.Exceptions.HandlerClienteCadastro;
import com.developer.ERP.Legacy.API.domain.Exceptions.HandlerNotFoundException;
import com.developer.ERP.Legacy.API.domain.Model.Cliente;
import com.developer.ERP.Legacy.API.domain.Model.Outros;
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
import java.util.List;
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
				if (!pessoaFisica.getInscricaoEstadual().isBlank()) {
					pessoaFisica.setIndicadorIe(IndicadorIE.CONTRIBUINTE_ICMS);
					cliente.getPessoaFisica().setIndicadorIe(pessoaFisica.getIndicadorIe());
					return validarCadastroCliente(cliente);
				} else {
					pessoaFisica.setIndicadorIe(IndicadorIE.CONTRIBUINTE_ISENTO);
					cliente.getPessoaFisica().setIndicadorIe(pessoaFisica.getIndicadorIe());
					return validarCadastroCliente(cliente);
				}
			}

			if (pessoaJuridica != null) {
				if (pessoaJuridica.getInscricaoEstadual().isBlank()) {
					pessoaJuridica.setIndicadorIe(IndicadorIE.CONTRIBUINTE_ISENTO);
					cliente.getPessoaJuridica().setIndicadorIe(pessoaJuridica.getIndicadorIe());
					return validarCadastroCliente(cliente);
				} else {
					pessoaJuridica.setIndicadorIe(IndicadorIE.CONTRIBUINTE_ICMS);
					cliente.getPessoaJuridica().setIndicadorIe(pessoaJuridica.getIndicadorIe());
					return validarCadastroCliente(cliente);
				}
			}

			return validarCadastroCliente(cliente);
		} catch (Exception e) {
			throw new HandlerClienteCadastro(MSG_CLIENTE_ERRO_INTERNO + e.getMessage());
		}

	}
	
	public Cliente validarCadastroCliente(Cliente cliente) {
		this.validarCadastroOutros(cliente);
		Cliente clienteSalvo = clienteRepository.save(cliente);
		return clienteSalvo;
	}
	
	public void validarCadastroOutros(Cliente cliente) {
		Outros outrosClientes = cliente.getOutros();
		if (outrosClientes != null) {
			if (outrosClientes.getAgencia().isBlank()) {
				throw new HandlerClienteCadastro(MSG_CLIENTE_AGENCIA_NAO_INFORMADA);
			}

			if (outrosClientes.getBanco().isBlank()) {
				throw new HandlerClienteCadastro(MSG_CLIENTE_CONTA_BANCO_NAO_INFORMADO);
			}

			if (outrosClientes.getFormaPagamento().isBlank()) {
				throw new HandlerClienteCadastro(MSG_CLIENTE_FORMA_PAGAMENTO_NAO_INFORMADO);
			}
		}
		
		if (outrosClientes == null) {
			throw new HandlerClienteCadastro(MSG_CLIENTE_OUTROS_NAO_INFORMADO);
		}
		
		
	}
	
	
	public void adicionarEnderecos(Cliente cliente, Long id) {
		List<Cliente> listarClientesPorId = clienteRepository.findClientesById(id);
		for (Cliente clientes : listarClientesPorId) {
			clientes.getEnderecos().addAll(cliente.getEnderecos());
		}
		clienteRepository.saveAll(listarClientesPorId);
	}
	
	public void adicionarContatos(Cliente cliente, Long id) {
		List<Cliente> listarClientesPorid = clienteRepository.findClientesById(id);
		for (Cliente clientes : listarClientesPorid) {
			clientes.getContatos().addAll(cliente.getContatos());

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
