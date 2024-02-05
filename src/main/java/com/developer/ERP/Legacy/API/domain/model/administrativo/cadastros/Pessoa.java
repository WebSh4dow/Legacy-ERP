package com.developer.ERP.Legacy.API.domain.model.administrativo.cadastros;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PESSOA")
@Data
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "TIPO")
    private String tipo;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SITE")
    private String site;

    @Column(name = "CLIENTE")
    private String cliente;

    @Column(name = "FORNECEDOR")
    private String fornecedor;

    @Column(name = "COLABORADOR")
    private String colaborador;

    @Column(name = "TRANSPORTADORA")
    private String transportadora;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "pessoa", cascade = CascadeType.ALL)
    private PessoaFisica pessoaFisica;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "pessoa", cascade = CascadeType.ALL)
    private PessoaJuridica pessoaJuridica;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PessoaEndereco> listaPessoaEndereco;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PessoaContato> listaPessoaContato;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PessoaTelefone> listaPessoaTelefone;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "EMPRESA_PESSOA", joinColumns = { @JoinColumn(name = "ID_PESSOA") }, inverseJoinColumns = { @JoinColumn(name = "ID_EMPRESA") })
    @JsonIgnore
    private List<Empresa> listaEmpresa;
}
