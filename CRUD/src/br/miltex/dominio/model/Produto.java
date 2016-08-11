/**
 * 
 */
package br.miltex.dominio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.miltex.dominio.AbstractEntity;
import br.miltex.service.ProdutoService;

/**
 * Definição da entidade.
 * @author Milton Ferreira Lima Filho
 * @version 1
 * @see ProdutoService
 */
@Entity
@Table(name="PRODUTO")
public class Produto implements AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String descricao;
	private Integer quantidade;
	private Double preco;
	
	@Override
	public Long getId() {
		//ver esta questao.
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
		
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Double getPreco() {
		return preco;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
