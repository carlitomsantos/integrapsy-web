package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

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
	
	public void buscaPaciente(){
		Paciente paciente = new Paciente();
		pacientes = paciente.busaPacientes(nome.trim());	
		
	}
	
	public void onPacienteSelect(SelectEvent s){
		selectedPaciente = (Paciente) s.getObject();
		ControleSolicitacao novaSolicitacao = new ControleSolicitacao();
		novaSolicitacao.novaSolicitacao(selectedPaciente);
		pacienteSelecionado = true;
	}
	
	public void testeClique(){
		System.out.println("Clicou");
		System.out.println(pacienteSelecionado);
		System.out.println(selectedPaciente.getNome());
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
