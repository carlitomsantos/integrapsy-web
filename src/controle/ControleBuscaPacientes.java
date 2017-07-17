package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;


import modelo.Paciente;

@ManagedBean(name="controleBuscaPacientes")
@ViewScoped
public class ControleBuscaPacientes implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Paciente> pacientes;
	private String nome;
	private Paciente selectedPaciente;
	private Boolean pacienteSelecionado = false;
	private String mensagem = " ";
	
	public void buscaPaciente(){
		Paciente paciente = new Paciente();
		pacientes = paciente.buscaPacientes(nome.trim());
		mensagem = pacientes.isEmpty() ? "Nenhum Paciente Encontrado" : " ";
		
	}
	
	public void onPacienteSelect(SelectEvent s){		
		selectedPaciente = (Paciente) s.getObject();
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("paciente", selectedPaciente);
		
		pacienteSelecionado = true;
	}
	
	public void onPaginationChange(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		
		selectedPaciente = (Paciente) session.getAttribute("paciente");
	}
	

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Paciente getSelectedPaciente() {
		return selectedPaciente;
	}

	public void setSelectedPaciente(Paciente selectedPaciente) {
		this.selectedPaciente = selectedPaciente;
	}

	public Boolean getPacienteSelecionado() {
		return pacienteSelecionado;
	}

	public void setPacienteSelecionado(Boolean pacienteSelecionado) {
		this.pacienteSelecionado = pacienteSelecionado;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
	
}
