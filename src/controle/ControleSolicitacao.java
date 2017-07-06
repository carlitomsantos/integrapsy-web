package controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="controleSolicitacao")
@ViewScoped
public class ControleSolicitacao implements Serializable {
	
	private String texto;
	
	

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
}
