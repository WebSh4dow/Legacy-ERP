package com.developer.ERP.Legacy.API.domain.service;

import com.developer.ERP.Legacy.API.domain.enumerated.IndicadorIE;
import com.developer.ERP.Legacy.API.domain.exceptions.BussinesException;
import com.developer.ERP.Legacy.API.domain.exceptions.HandlerClienteCadastro;
import com.developer.ERP.Legacy.API.domain.exceptions.HandlerNotFoundException;
import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.developer.ERP.Legacy.API.domain.model.Contratos;
import com.developer.ERP.Legacy.API.domain.model.Outros;
import com.developer.ERP.Legacy.API.domain.model.PessoaFisica;
import com.developer.ERP.Legacy.API.domain.model.PessoaJuridica;
import com.developer.ERP.Legacy.API.domain.repository.criteriaFilter.ClienteCriteriaFilter;
import com.developer.ERP.Legacy.API.domain.repository.filter.ClienteFilter;
import com.developer.ERP.Legacy.API.domain.repository.ClienteRepository;
import com.developer.ERP.Legacy.API.domain.repository.EnderecoRepository;
import com.developer.ERP.Legacy.API.infrastructure.config.RepositoryCustomImpl;
import com.developer.ERP.Legacy.API.infrastructure.repositoryImpl.ClienteRepositoryImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static com.developer.ERP.Legacy.API.infrastructure.message.ClienteMessage.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

@Service
public class ClienteService extends RepositoryCustomImpl {

	private final ClienteRepository clienteRepository;

	private final ClienteRepositoryImpl clienteRepositoryImpl;

	public ClienteService(EnderecoRepository enderecoRepository, ClienteRepository clienteRepository,
			ClienteRepositoryImpl clienteRepositoryImpl) {
		this.clienteRepository = clienteRepository;
		this.clienteRepositoryImpl = clienteRepositoryImpl;
	}

	@Transactional
	public Page<Cliente> pesquisar(ClienteFilter clienteFilter, ClienteCriteriaFilter clienteCriteriaFilter) {
		return clienteRepositoryImpl.buscarClientes(clienteFilter, clienteCriteriaFilter);
	}

	public List<Cliente> buscaSimplesClienteby(String cpf) {
		return clienteRepositoryImpl.buscarClienteCpf(cpf);
	}

	public List<Cliente> buscaSimplesClienteBy(String cnpj) {
		return clienteRepositoryImpl.buscarClienteCnpj(cnpj);
	}

	public Page<Cliente> buscarClienteCnpjPageable(ClienteFilter clienteFilter,
			ClienteCriteriaFilter clienteCriteriaFilter, String cnpj) {
		return clienteRepositoryImpl.buscarClienteCnpjPageable(clienteFilter, clienteCriteriaFilter, cnpj);
	}

	public Page<Cliente> buscarClienteCpfPageable(ClienteFilter clienteFilter,
			ClienteCriteriaFilter clienteCriteriaFilter, String cpf) {
		return clienteRepositoryImpl.buscarClienteCpfPageable(clienteFilter, clienteCriteriaFilter, cpf);
	}

	public Page<Cliente> buscarClientesPorIdPageable(ClienteFilter clienteFilter,
			ClienteCriteriaFilter clienteCriteriaFilter, Long id) {
		return clienteRepositoryImpl.buscarClientesPorIdPageable(clienteFilter, clienteCriteriaFilter, id);
	}

	public Cliente cadastrarCliente(Cliente cliente) throws Exception {

		try {

			PessoaJuridica pessoaJuridica = cliente.getPessoaJuridica();
			PessoaFisica pessoaFisica = cliente.getPessoaFisica();

			if (pessoaFisica != null) {
				if (!StringUtils.isBlank(pessoaFisica.getInscricaoEstadual())) {
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
				if (StringUtils.isBlank(pessoaJuridica.getInscricaoEstadual())) {
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

	@Transactional
	public Cliente validarCadastroCliente(Cliente cliente) {
		this.validarCadastroOutros(cliente);
		this.validarClienteMesmoDocumento(cliente);
		return clienteRepository.save(cliente);
	}

	public void validarClienteMesmoDocumento(Cliente cliente) {
		Cliente clinteComMesmoDocumento = clienteRepositoryImpl.verificarCpfCnpjCliente(cliente);
		if (clinteComMesmoDocumento != null) {
			throw new BussinesException("Documento atual já está vinculado a outro cadastro de cliente.");
		}
	}
	
	public void validarExclusaoCliente(Cliente cliente) {
		List<Contratos> clientePossuiContratoDataExpirada = clienteRepositoryImpl.clientePossuiContratosExpirados(cliente.getId());
		if (!clientePossuiContratoDataExpirada.isEmpty()) {
			throw new BussinesException(MSG_CLIENTE_CONTRATOS_EXPIRADOS);
		}
		else {
			clienteRepository.deleteById(cliente.getId());
		}
	}

	public void validarCadastroOutros(Cliente cliente) {
		Outros terceiros = cliente.getOutros();
		if (terceiros != null) {
			if (StringUtils.isBlank(terceiros.getAgencia())) {
				throw new HandlerClienteCadastro(MSG_CLIENTE_AGENCIA_NAO_INFORMADA);
			}

			if (StringUtils.isBlank(terceiros.getBanco())) {
				throw new HandlerClienteCadastro(MSG_CLIENTE_CONTA_BANCO_NAO_INFORMADO);
			}

			if (StringUtils.isBlank(terceiros.getFormaPagamento())) {
				throw new HandlerClienteCadastro(MSG_CLIENTE_FORMA_PAGAMENTO_NAO_INFORMADO);
			}
		}

		if (terceiros == null) {
			throw new HandlerClienteCadastro(MSG_CLIENTE_OUTROS_NAO_INFORMADO);
		}
	}
	
	
	public Cliente remover(Long idCliente) {
		try {
			Cliente cliente = clienteRepository.findById(idCliente).get();
			if (idCliente != null)
				this.validarExclusaoCliente(cliente);
		} catch (Exception e) {
			Cliente clienteExcluido = clienteRepository.findById(idCliente).get();
			if (clienteExcluido.getId() == null) {
				throw new NoSuchElementException();
			}
		}
		return null;
		
	}

}
