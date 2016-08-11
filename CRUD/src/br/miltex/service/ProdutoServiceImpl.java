/**
 * 
 */
package br.miltex.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import br.miltex.dominio.AbstractPersistence;
import br.miltex.dominio.model.Produto;

/**
 * Implementa��o do Enterprise Java Bean.
 * @author Milton Ferreira Lima Filho
 * @version 1
 * @see ProdutoService
 */
@Stateless
public class ProdutoServiceImpl extends AbstractPersistence<Long,Produto> 
	implements ProdutoService {

	@PersistenceContext(name="milton-pu")
    private EntityManager em;
	/*
	 * Define o objeto que ser� manipulado pelo JPA
	 */
	public ProdutoServiceImpl() {
		super(Produto.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	@Override
	public int outraRegra() {
		// TODO implemenrar outro servi�o que seja especifico. Pois os servi�os do abstractPersistence s�o herdados.
		return 0;
	}
}
