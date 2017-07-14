package controle;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
	private Boolean retornoSolicitacao;
	private String urlVoucher;
	private String urlBoleto;
	

	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = (HttpSession) request.getSession();
		solicitacao = new Solicitacao();
		paciente = (Paciente) session.getAttribute("paciente") != null ? (Paciente) session.getAttribute("paciente")
				: new Paciente();
		notaFiscal = new NotaFiscal();
		notaFiscal.setNome(paciente.getNome());
		notaFiscal.setCpf(paciente.getCpf());
		notaFiscal.setEmail(paciente.getEmail());
		session.removeAttribute("paciente");
	}

	public void enviarSolicitacao() {
		FacesContext context = FacesContext.getCurrentInstance();
		String mensagem = null;
		solicitacao.setPaciente(paciente);
		paciente.atualizar(paciente);
		System.out.println(solicitacao.getFormaPagamento());
		if (!solicitacao.getFormaPagamento().equals("F")) {
			solicitacao.setNotaFiscal(notaFiscal);
		} else {
			solicitacao.setNotaFiscal(null);
		}
		
		Solicitacao solicitacao = this.solicitacao.enviarSolicitacao(this.solicitacao);
		
		 mensagem = solicitacao.getMensagem();
		 
		 System.out.println(mensagem);
		
		if(mensagem != null){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,mensagem, null));
			retornoSolicitacao = false;
		}else{
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Solicitação Enviado Com Sucesso", null));
			urlBoleto = solicitacao.getUrlBoleto();
			urlVoucher = solicitacao.getUrlVoucher();
			retornoSolicitacao = true;
		}


	}

	public void onNomeKeyUp() {
		notaFiscal.setNome(paciente.getNome());
	}

	public void onCpfKeyUp() {
		notaFiscal.setCpf(paciente.getCpf());
	}

	public void onEmailKeyUp() {
		notaFiscal.setEmail(paciente.getEmail());
	}

	public void onNotaEmailKeyUp() {
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

	public Boolean getRetornoSolicitacao() {
		return retornoSolicitacao;
	}

	public void setRetornoSolicitacao(Boolean retornoSolicitacao) {
		this.retornoSolicitacao = retornoSolicitacao;
	}

	public String getUrlVoucher() {
		return urlVoucher;
	}

	public void setUrlVoucher(String urlVoucher) {
		this.urlVoucher = urlVoucher;
	}

	public String getUrlBoleto() {
		return urlBoleto;
	}

	public void setUrlBoleto(String urlBoleto) {
		this.urlBoleto = urlBoleto;
	}

}
