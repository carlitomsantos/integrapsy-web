package controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modelo.NotaFiscal;
import modelo.Paciente;
import modelo.Solicitacao;

@ManagedBean(name="controleSolicitacao")
@ViewScoped
public class ControleSolicitacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Solicitacao solicitacao;
	private Paciente paciente;
	private NotaFiscal notaFiscal;
	
	public void novaSolicitacao(Paciente paciente){
		System.out.println(paciente.getNome());
	}
	
	public Solicitacao getSolicitacao() {
		return solicitacao;
	}
	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}
	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	
	
	
}
