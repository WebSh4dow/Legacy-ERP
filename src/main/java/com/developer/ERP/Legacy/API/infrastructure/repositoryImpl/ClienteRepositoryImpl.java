package com.developer.ERP.Legacy.API.infrastructure.repositoryImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.developer.ERP.Legacy.API.domain.repository.criteriaFilter.ClienteCriteriaFilter;
import com.developer.ERP.Legacy.API.domain.repository.filter.ClienteFilter;
import com.developer.ERP.Legacy.API.infrastructure.config.RepositoryCustomImpl;
import com.developer.ERP.Legacy.API.infrastructure.repository.CustomClienteRepository;


@Repository
public class ClienteRepositoryImpl extends RepositoryCustomImpl implements CustomClienteRepository {
	

	private final EntityManager entityManager;
	
    private final CriteriaBuilder criteriaBuilder;
    
    private final static boolean CLIENTE_INATIVO = false;
    
    private final static boolean CLIENTE_ATIVO = true;
    
    public ClienteRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
	}
    
    @Override
    public Long getClienteCount(Predicate predicate) {
    	CriteriaQuery<Long> countCriteriaQuery = criteriaBuilder.createQuery(Long.class);
    	Root<Cliente> countRoot = countCriteriaQuery.from(Cliente.class);
    	countCriteriaQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
    	return entityManager.createQuery(countCriteriaQuery).getSingleResult();
    }
    
    @Override
    public Pageable getPageable(ClienteFilter clienteFilter) {
    	Sort sort = Sort.by(clienteFilter.getSortDirection(),clienteFilter.getSortBy());
    	return PageRequest.of(clienteFilter.getPageNumber(),clienteFilter.getPageSize(),sort);
    }
    
    @Override
    public void setOrder(ClienteFilter clienteFilter,CriteriaQuery<Cliente>criteriaQuery,Root<Cliente>clientesRoot) {
    	if (clienteFilter.getSortDirection().equals(Sort.Direction.ASC)) {
			criteriaQuery.orderBy(criteriaBuilder.asc(clientesRoot.get(clienteFilter.getSortBy())));
		}else{
			criteriaQuery.orderBy(criteriaBuilder.desc(clientesRoot.get(clienteFilter.getSortBy())));
		}
    }
    
    @Override
    public Predicate getPredicate(ClienteCriteriaFilter clienteCriteriaFilter, Root<Cliente>clienteRoot) {
    	List<Predicate> predicates = new ArrayList<>();
    	if (Objects.nonNull(clienteCriteriaFilter.getNome())) {
			predicates.add(
					criteriaBuilder.like(clienteRoot.get("nome"), 
							"%" + clienteCriteriaFilter.getNome() + "%")
			);
		}
    	if (Objects.nonNull(clienteCriteriaFilter.getSobreNome())) {
			predicates.add(
					criteriaBuilder.like(clienteRoot.get("sobreNome"),
						   "%" + clienteCriteriaFilter.getSobreNome() + "%")
			);
		}
    	return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
    
    @Override
    public Page<Cliente> buscarClientes(ClienteFilter clienteFilter,ClienteCriteriaFilter clienteCriteriaFilter) {
    	CriteriaQuery<Cliente>criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
    	Root<Cliente> clienteRoot = criteriaQuery.from(Cliente.class);
    	Predicate predicate = getPredicate(clienteCriteriaFilter, clienteRoot);
    	
    	criteriaQuery.where(predicate);
        setOrder(clienteFilter, criteriaQuery, clienteRoot);
        
    	TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteriaQuery);
    	
    	typedQuery.setFirstResult(clienteFilter.getPageNumber() * clienteFilter.getPageSize());
    	typedQuery.setMaxResults(clienteFilter.getPageSize());
    	
    	Pageable pageable  = getPageable(clienteFilter);
    	long countClientes = getClienteCount(predicate);
    	
    	return new PageImpl<>(typedQuery.getResultList(),pageable,countClientes);
    }
    
    @Override
	public Page<Cliente> buscarClienteCnpjPageable(ClienteFilter clienteFilter,ClienteCriteriaFilter clienteCriteriaFilter, String cnpj) {
    	
    	CriteriaQuery<Cliente>criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
    	Root<Cliente> clienteRoot = criteriaQuery.from(Cliente.class);
    	Predicate predicate = getPredicate(clienteCriteriaFilter, clienteRoot);
    	
    	criteriaQuery.where(predicate);
        setOrder(clienteFilter, criteriaQuery, clienteRoot);
        
    	TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteriaQuery);
    	
    	typedQuery.setFirstResult(clienteFilter.getPageNumber() * clienteFilter.getPageSize());
    	typedQuery.setMaxResults(clienteFilter.getPageSize());
    	
    	Pageable pageable  = getPageable(clienteFilter);
    	List<Cliente> buscarCliente = buscarClienteCnpj(cnpj);
    	
    	long countClientes = getClienteCount(predicate);
    	
    	return new PageImpl<>(buscarCliente,pageable,countClientes);
	}

	@Override
	public Page<Cliente> buscarClienteCpfPageable(ClienteFilter clienteFilter,
			ClienteCriteriaFilter clienteCriteriaFilter, String cpf) {
		
		CriteriaQuery<Cliente>criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
    	Root<Cliente> clienteRoot = criteriaQuery.from(Cliente.class);
    	Predicate predicate = getPredicate(clienteCriteriaFilter, clienteRoot);
    	
    	criteriaQuery.where(predicate);
        setOrder(clienteFilter, criteriaQuery, clienteRoot);
        
    	TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteriaQuery);
    	typedQuery.setFirstResult(clienteFilter.getPageNumber() * clienteFilter.getPageSize());
    	typedQuery.setMaxResults(clienteFilter.getPageSize());
    	
    	Pageable pageable  = getPageable(clienteFilter);
    	List<Cliente> buscarCliente = buscarClienteCpf(cpf);
    	
    	long countClientes = getClienteCount(predicate);
    	
    	return new PageImpl<>(buscarCliente,pageable,countClientes);
	}
    
	@SuppressWarnings("unchecked")
	@Override
	public Cliente verificarCpfCnpjCliente(Cliente cliente) {
		Cliente clientes = cliente;
		if (clientes.getPessoaFisica() != null) {
			Criteria criteria = super.createCriteria(entityManager, Cliente.class);

			List<Cliente> verficarCpfCliente = new ArrayList<Cliente>();

			criteria.createAlias("pessoaFisica", "pf");
			criteria.add(Restrictions.eq("pf.cpf", clientes.getPessoaFisica().getCpf()));

			if (cliente.getId() != null)
				criteria.add(Restrictions.ne("id", cliente.getId()));

			criteria.add(Restrictions.ne("isAtivo", CLIENTE_INATIVO));

			verficarCpfCliente = criteria.list();

			if (verficarCpfCliente.size() != 0)
				return verficarCpfCliente.get(0);

		} else if (clientes.getPessoaJuridica() != null) {
			Criteria criteria = createCriteria(entityManager, Cliente.class);

			List<Cliente> verificarCnpjCliente = new ArrayList<Cliente>();

			criteria.createAlias("pessoaJuridica", "pj");
			criteria.add(Restrictions.eq("pj.cnpj", clientes.getPessoaJuridica().getCnpj()));

			if (cliente.getId() != null)
				criteria.add(Restrictions.ne("id", cliente.getId()));

			criteria.add(Restrictions.neOrIsNotNull("isAtivo", CLIENTE_INATIVO));
			verificarCnpjCliente = criteria.list();

			if (verificarCnpjCliente.size() != 0)
				return verificarCnpjCliente.get(0);

		}
		return null;
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public List<Cliente> buscarClienteCpf(String cpf) {
		Criteria detachedCriteria = super.createCriteria(entityManager, Cliente.class);

		detachedCriteria.add(Restrictions.eq("isAtivo", CLIENTE_ATIVO));
		detachedCriteria.createAlias("pessoaFisica", "pf");
		detachedCriteria.add(Restrictions.eq("pf.cpf", cpf));

		List<Cliente> listarClientes = detachedCriteria.list();
		return listarClientes;
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public List<Cliente> buscarClienteCnpj(String cnpj) {
		Criteria detachedCriteria = super.createCriteria(entityManager, Cliente.class);

		detachedCriteria.add(Restrictions.eq("isAtivo", CLIENTE_ATIVO));
		detachedCriteria.createAlias("pessoaJuridica", "pj");
		detachedCriteria.add(Restrictions.eq("pj.cnpj", cnpj));

		List<Cliente> listarClientes = detachedCriteria.list();
		return listarClientes;
	}

}
