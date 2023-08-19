package com.developer.ERP.Legacy.API.domain.service;

import com.developer.ERP.Legacy.API.domain.enumerated.IndicadorIE;
import com.developer.ERP.Legacy.API.domain.exceptions.BussinesException;
import com.developer.ERP.Legacy.API.domain.exceptions.HandlerClienteCadastro;
import com.developer.ERP.Legacy.API.domain.model.Cliente;
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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import static com.developer.ERP.Legacy.API.infrastructure.message.ClienteMessage.*;
import javax.transaction.Transactional;


@Service
public class ClienteService extends RepositoryCustomImpl {

	private final ClienteRepository clienteRepository;
	

	private final ClienteRepositoryImpl clienteRepositoryImpl;

	public ClienteService(EnderecoRepository enderecoRepository,
						  ClienteRepository clienteRepository,
						  ClienteRepositoryImpl clienteRepositoryImpl
	) {
		this.clienteRepository = clienteRepository;
		this.clienteRepositoryImpl = clienteRepositoryImpl;
	}
	
	@Transactional
	public Page<Cliente> pesquisar(ClienteFilter clienteFilter, ClienteCriteriaFilter clienteCriteriaFilter) {
		return clienteRepositoryImpl.buscarClientes(clienteFilter, clienteCriteriaFilter);
	}
	
	@Transactional
	public Page<Cliente>buscarClientesByCriteriaFilter(ClienteCriteriaFilter clienteFilter, Pageable pageable){
    	return clienteRepositoryImpl.buscarClienteByCriteriaFilterPaginator(clienteFilter, pageable,true);
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
		
	public void validarCadastroOutros(Cliente cliente) {
		Outros outrosClientes = cliente.getOutros();
		if (outrosClientes != null) {
			if (StringUtils.isBlank(outrosClientes.getAgencia())) {
				throw new HandlerClienteCadastro(MSG_CLIENTE_AGENCIA_NAO_INFORMADA);
			}

			if (StringUtils.isBlank(outrosClientes.getBanco())) {
				throw new HandlerClienteCadastro(MSG_CLIENTE_CONTA_BANCO_NAO_INFORMADO);
			}

			if (StringUtils.isBlank(outrosClientes.getFormaPagamento())) {
				throw new HandlerClienteCadastro(MSG_CLIENTE_FORMA_PAGAMENTO_NAO_INFORMADO);
			}
		}

		if (outrosClientes == null) {
			throw new HandlerClienteCadastro(MSG_CLIENTE_OUTROS_NAO_INFORMADO);
		}
	}

	

}
