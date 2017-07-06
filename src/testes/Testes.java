package testes;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import modelo.Paciente;

public class Testes {

	@Test
	public void testeBusaPacientes(){
		List<Paciente> pacientes = new ArrayList<Paciente>();
		Paciente paciente = new Paciente();
		paciente.busaPacientes("teste");
	}
}
