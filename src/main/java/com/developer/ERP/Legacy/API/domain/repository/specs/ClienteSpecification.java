package com.developer.ERP.Legacy.API.domain.repository.specs;

import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.developer.ERP.Legacy.API.domain.repository.criteriaFilter.ClienteSpecFilter;
import com.developer.ERP.Legacy.API.infrastructure.specification.PesquisarCriteria;
import com.developer.ERP.Legacy.API.infrastructure.specification.abstractions.PesquisarPageSpecification;
import org.apache.commons.lang3.StringUtils;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteSpecification extends PesquisarPageSpecification<PesquisarCriteria<ClienteSpecFilter>, Cliente> {
    public ClienteSpecification(PesquisarCriteria<ClienteSpecFilter> pesquisar) {
        super(pesquisar);
    }

    @Override
    public Predicate toPredicate(Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, PesquisarCriteria<ClienteSpecFilter> search) {

        ClienteSpecFilter clienteSpecificationRequest = search.getSearch();
        List<Predicate> predicates = new ArrayList<Predicate>();

        if (StringUtils.isNotEmpty(clienteSpecificationRequest.getNome())){
            Predicate likeSearchNome = criteriaBuilder.like(root.get("nome"),"%" + clienteSpecificationRequest.getNome());
            predicates.add(likeSearchNome);
        }

        if (StringUtils.isNotEmpty(clienteSpecificationRequest.getSobreNome())){
            Predicate likeSerachBySobreNome = criteriaBuilder.like(root.get("sobreNome"),"%" + clienteSpecificationRequest.getSobreNome());
            predicates.add(likeSerachBySobreNome);
        }

        if (StringUtils.isNotEmpty(clienteSpecificationRequest.getCpf())){
            root.fetch("pessoaFisica");
            predicates.add(criteriaBuilder.equal(root.get("pessoaFisica").get("cpf"),clienteSpecificationRequest.getCpf()));
            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        }

        if(StringUtils.isNotEmpty(clienteSpecificationRequest.getCnpj())){
            root.fetch("pessoaJuridica");
            predicates.add(criteriaBuilder.equal(root.get("pessoaJuridica").get("cnpj"),clienteSpecificationRequest.getCnpj()));
            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    @Override
    protected String sortProperty(String sortField) {
        switch (sortField){
            case "nome" : return "nome";
            case "sobreNome": return "sobreNome";
            case "cpf": return "cpf";
            case "cnpj": return "cnpj";
            default: return sortField;
        }
    }
}

