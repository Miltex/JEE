/**
 * 
 */
package br.miltex.ctrl;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.miltex.service.ProdutoService;

/**
 * @author Milton Ferreira Lima Filho
 * @version 1
 */
@Named
@RequestScoped
public class MenuControl {
	
	public void sair() {
		addMessage("gru gru ie ie...\n pegadinha do malandro");
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
