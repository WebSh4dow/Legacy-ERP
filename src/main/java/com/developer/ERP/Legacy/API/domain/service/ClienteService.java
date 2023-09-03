package com.developer.ERP.Legacy.API.domain.service;

import com.developer.ERP.Legacy.API.api.v1.assembler.ClienteAssembler;
import com.developer.ERP.Legacy.API.api.v1.disasembler.ClienteDisassembler;
import com.developer.ERP.Legacy.API.api.v1.helper.ResourceUriHelper;
import com.developer.ERP.Legacy.API.api.v1.request.ClienteRequest;
import com.developer.ERP.Legacy.API.domain.enumerated.IndicadorIE;
import com.developer.ERP.Legacy.API.domain.exceptions.runtime.BussinesException;
import com.developer.ERP.Legacy.API.domain.exceptions.runtime.EntidadeEmUsoException;
import com.developer.ERP.Legacy.API.domain.exceptions.runtime.HandlerClienteCadastro;
import com.developer.ERP.Legacy.API.domain.model.*;
import com.developer.ERP.Legacy.API.domain.repository.criteriaFilter.ClienteCriteriaFilter;
import com.developer.ERP.Legacy.API.domain.repository.filter.ClienteFilter;
import com.developer.ERP.Legacy.API.domain.repository.ClienteRepository;
import com.developer.ERP.Legacy.API.domain.repository.EnderecoRepository;
import com.developer.ERP.Legacy.API.domain.representation.ClienteRepresentationModel;
import com.developer.ERP.Legacy.API.infrastructure.repositoryImpl.RepositoryCustomImpl;
import com.developer.ERP.Legacy.API.infrastructure.repositoryImpl.ClienteRepositoryImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import static com.developer.ERP.Legacy.API.core.validation.message.ClienteMessage.*;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class ClienteService extends RepositoryCustomImpl {

	private final ClienteRepository clienteRepository;

	private final ClienteRepositoryImpl clienteRepositoryImpl;

	private final ClienteDisassembler clienteDisassembler;

	private final ClienteAssembler clienteAssembler;


	public ClienteService(EnderecoRepository enderecoRepository, ClienteRepository clienteRepository,
			ClienteRepositoryImpl clienteRepositoryImpl,ClienteDisassembler clienteDisassembler,ClienteAssembler clienteAssembler
	) {

		this.clienteRepository = clienteRepository;
		this.clienteRepositoryImpl = clienteRepositoryImpl;
		this.clienteDisassembler = clienteDisassembler;
		this.clienteAssembler = clienteAssembler;
	}

	@Transactional
	public Page<Cliente> pesquisar(ClienteFilter clienteFilter, ClienteCriteriaFilter clienteCriteriaFilter) {
		return clienteRepositoryImpl.buscarClientes(clienteFilter, clienteCriteriaFilter);
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
	public Cliente buscarCliente(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new BussinesException(
						String.format(MSG_CLIENTE_NAO_ENCONTRADO, id)));
	}
	public ClienteRepresentationModel cadastrarCliente(ClienteRequest clienteRequest) {

		try {
			Cliente cliente = clienteDisassembler.toDomainObject(clienteRequest);
			validarClienteMesmoDocumento(cliente);

			if (cliente.getPessoaFisica() != null){
				isPessoaFisicaContribuinte(cliente.getPessoaFisica(), cliente);
			}

			isPessoaJuridicaContribuinte(cliente.getPessoaJuridica(), cliente);
			validarCadastroOutros(cliente);

			cliente = clienteRepository.save(cliente);

			ClienteRepresentationModel clienteRepresentationModel = clienteAssembler.toModel(cliente);
			ResourceUriHelper.addUriResponseHeader(clienteRepresentationModel.getId());

			return clienteRepresentationModel;

		}catch (EntityNotFoundException ex){
			throw new BussinesException(ex.getMessage(),ex);
		}
	}
	public ClienteRepresentationModel editarCliente(Long id, ClienteRequest clienteRequest) {
		Cliente clienteAtual = buscarCliente(id);

		validarClienteMesmoDocumento(clienteAtual);
		validarCadastroOutros(clienteAtual);

		clienteDisassembler.copyToDomainObject(clienteRequest,clienteAtual);
		clienteAtual = this.clienteRepository.save(clienteAtual);
		return clienteAssembler.toModel(clienteAtual);
	}

	public void isPessoaFisicaContribuinte(PessoaFisica pessoaFisica, Cliente cliente){
		if (pessoaFisica != null){
			if (!StringUtils.isBlank(pessoaFisica.getInscricaoEstadual())) {
				cliente.getPessoaFisica().setIndicadorIe(pessoaFisica.getIndicadorIe());
				pessoaFisica.setIndicadorIe(IndicadorIE.CONTRIBUINTE_ICMS);
			} else {
				pessoaFisica.setIndicadorIe(IndicadorIE.CONTRIBUINTE_ISENTO);
				cliente.getPessoaFisica().setIndicadorIe(pessoaFisica.getIndicadorIe());
			}
		}
	}
	public void isPessoaJuridicaContribuinte(PessoaJuridica pessoaJuridica, Cliente cliente){
		if (pessoaJuridica != null) {
			if (StringUtils.isBlank(pessoaJuridica.getInscricaoEstadual())) {
				pessoaJuridica.setIndicadorIe(IndicadorIE.CONTRIBUINTE_ISENTO);
				cliente.getPessoaJuridica().setIndicadorIe(pessoaJuridica.getIndicadorIe());
			} else {
				pessoaJuridica.setIndicadorIe(IndicadorIE.CONTRIBUINTE_ICMS);
				cliente.getPessoaJuridica().setIndicadorIe(pessoaJuridica.getIndicadorIe());
			}
		}
	}

	public void validarClienteMesmoDocumento(Cliente cliente) {
		Cliente clinteComMesmoDocumento = clienteRepositoryImpl.verificarCpfCnpjCliente(cliente);
		if (clinteComMesmoDocumento != null) {
			throw new BussinesException("Documento atual já está vinculado a outro cadastro de cliente.");
		}
	}

	public void excluir(Cliente cliente, Long id) {
		try {
			List<Contratos> clientePossuiContratoDataExpirada = clienteRepositoryImpl
					.clientePossuiContratosExpirados(cliente.getId());

			if (!clientePossuiContratoDataExpirada.isEmpty())
				throw new BussinesException(MSG_CLIENTE_CONTRATOS_EXPIRADOS);

			else
				clienteRepository.deleteById(id);

		}catch (EmptyResultDataAccessException e){
			throw new EntityNotFoundException(MSG_CLIENTE_NAO_ENCONTRADO);

		}catch (DataIntegrityViolationException e){
			throw new EntidadeEmUsoException(
					String.format(MSG_CLIENTE_ERRO_REMOVER,id));
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


}
