/**
 * 
 */
package br.miltex.ctrl;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import br.miltex.dominio.model.Produto;
import br.miltex.service.ProdutoService;

/**
 * @author Milton Ferreira Lima Filho
 * @version 1
 */
@Named
@RequestScoped
public class ProdutoControl {

	private Long idSelecionado;
	@Inject 
	private ProdutoService service;
	@Inject
	private Produto mercadoria;
	private List<Produto> mercadorias;

	public ProdutoControl() {
	}

	public void setIdSelecionado(Long idSelecionado) {
		this.idSelecionado = idSelecionado;
	}

	public Long getIdSelecionado() {
		return idSelecionado;
	}

	public Produto getMercadoria() {
		return mercadoria;
	}

	public void editar() {
		if (idSelecionado == null) {
			return;
		}
		mercadoria = service.find(idSelecionado);
	}

	public List<Produto> getMercadorias() {
		
		if (mercadorias == null) {
			mercadorias = service.findAll();
		}
		return mercadorias;
	}

	public String salvar() {
		try {
			service.save(mercadoria);
		} catch (Exception ex) {
			addMessage(getMessageFromI18N("msg.erro.salvar.mercadoria"),
					ex.getMessage());
			return "";
		}
		return "listarProdutos";
	}

	public String remover() {
		try {
			service.remove(mercadoria);
		} catch (Exception ex) {
			addMessage(getMessageFromI18N("msg.erro.remover.mercadoria"),
					ex.getMessage());
			return "";
		}
		return "listarProdutos";
	}

	/**
	 * @param key
	 * @return Recupera a mensagem do arquivo properties
	 */
	private String getMessageFromI18N(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_labels",
				getCurrentInstance().getViewRoot().getLocale());
		return bundle.getString(key);
	}

	/**
	 * Adiciona um mensagem no contexto do FacesContex
	 * 
	 * @param summary
	 * @param detail
	 */
	private void addMessage(String summary, String detail) {
		getCurrentInstance().addMessage(
				null,
				new FacesMessage(summary, summary.concat("<br/>")
						.concat(detail)));
	}
}