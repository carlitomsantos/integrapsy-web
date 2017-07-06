package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;

import modelo.Paciente;

@ManagedBean(name="controleBuscaPacientes")
public class ControleBuscaPacientes {
	
	private List<Paciente> pacientes;
	private String nome;
	private Paciente selectedPaciente;
	
	public void buscaPaciente(){
		Paciente paciente = new Paciente();
		pacientes = paciente.busaPacientes(nome);		
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
	
	
	
}
