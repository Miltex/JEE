/**
 * 
 */
package br.miltex.service;

import java.util.List;

import javax.ejb.Local;

import br.miltex.dominio.model.Produto;

/**
 * Definindo as regras de negócio.
 * @author Milton Ferreira Lima Filho
 * @version 1
 * @see ProdutoService
 */
@Local
public interface ProdutoService {

	public Produto save(Produto mercadoria);
	
	public void remove(Produto mercadoria);
	
	public Produto find(Long id);
	
	public List<Produto> findAll();
	
	public int outraRegra();
}
