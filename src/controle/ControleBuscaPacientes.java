package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;

import com.sun.faces.taglib.html_basic.SelectBooleanCheckboxTag;

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
	
	public void buscaPaciente(){
		Paciente paciente = new Paciente();
		pacientes = paciente.buscaPacientes(nome.trim());	
		
	}
	
	public void onPacienteSelect(SelectEvent s){
		selectedPaciente = (Paciente) s.getObject();
		
		pacienteSelecionado = true;
	}
		
	public void novaSolicitacao(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("paciente", selectedPaciente);
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
	
	
	
}
