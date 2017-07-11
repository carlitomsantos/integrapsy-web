package controle;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modelo.NotaFiscal;
import modelo.Paciente;
import modelo.Solicitacao;

@ManagedBean(name = "controleSolicitacao")
@ViewScoped
public class ControleSolicitacao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Solicitacao solicitacao;
	private Paciente paciente;
	private NotaFiscal notaFiscal;
	

	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = (HttpSession) request.getSession();
		solicitacao = new Solicitacao();
		paciente = (Paciente) session.getAttribute("paciente") != null? (Paciente) session.getAttribute("paciente") : new Paciente() ;
		notaFiscal = new NotaFiscal();
		notaFiscal.setNome(paciente.getNome());
		notaFiscal.setCpf(paciente.getCpf());
		notaFiscal.setEmail(paciente.getEmail());
		session.setAttribute("paciente", null);
	}
	
	public void teste(){
		System.out.println(solicitacao.getFormaPagamento());
	}
	
	public void enviarSolicitacao(){
		System.out.println(paciente.getNome());
		solicitacao.setNotaFiscal(notaFiscal);
		solicitacao.setPaciente(paciente);
		
		
		Solicitacao solicitacao = this.solicitacao.enviarSolicitacao(this.solicitacao);
		
		System.out.println(solicitacao.getUrlVoucher());
		
		/*solicitacao = solicitacao.enviarSolicitacao(solicitacao);
		
		System.out.println(solicitacao.getUrlBoleto());*/
		
	}

	public void onCpfKeyUp(){
		notaFiscal.setCpf(paciente.getCpf());
	}
	
	public void onEmailKeyUp(){
		notaFiscal.setEmail(paciente.getEmail());
	}
	
	public void onNotaEmailKeyUp(){
		paciente.setEmail(notaFiscal.getEmail());
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
