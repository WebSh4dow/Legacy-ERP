package com.developer.ERP.Legacy.API.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Itens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Produto produto;
    @ElementCollection
    @CollectionTable(name = "itens_vendas", joinColumns = @JoinColumn(name = "itens_id"))
    private List <Venda> vendas;

}
