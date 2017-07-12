package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import conexaoBanco.ConexaoFirebird;

public class Paciente {

	private String nome;
	private String cpf;
	private Date datNascimento;
	private String email;
	private String celular;
	private Date vencimentoCnh;
	private String numCnh;
	private String numDeclaração;	
	private String pacCod;
	private NotaFiscal notafiscal;

	public Paciente() {

	}

	public Paciente(String name, Date datNascimento) {

	}

	public Paciente(String nome, String cpf, Date datNascimento, String celular, String email, String pacCod) {

	}

	public List<Paciente> buscaPacientes(String nome) {
		ConexaoFirebird conexao = new ConexaoFirebird();
		Connection con = null;
		PreparedStatement ps = null;
		List<Paciente> pacientes = new ArrayList<Paciente>();

		try {
			con = conexao.getConnection();
			ps = con.prepareStatement( "SELECT PES_NOME, PES_CPF, PES_DTNASCIMENTO, PES_CORREIOPESSOAL, "
					+ "PES_CELULAR, PAC_COD FROM PACIENTE INNER JOIN PESSOA ON PESSOA.PES_COD = PACIENTE.PES_COD "
					+ "WHERE PES_NOME like ? ORDER BY PES_NOME");
			
			ps.setString(1, nome.toUpperCase()+"%");
			
			System.out.println(ps.toString());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setNome(rs.getString("PES_NOME"));
				paciente.setPacCod(rs.getString("PAC_COD"));
				paciente.setEmail(rs.getString("PES_CORREIOPESSOAL"));
				paciente.setDatNascimento(rs.getDate("PES_DTNASCIMENTO"));
				paciente.setCpf(rs.getString("PES_CPF"));
				paciente.setCelular(rs.getString("PES_CELULAR"));
				pacientes.add(paciente);
			}
		} catch (Exception e) {

		}finally {
			conexao.close(con);
		}
			
		return pacientes;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDatNascimento() {
		return datNascimento;
	}

	public void setDatNascimento(Date datNascimento) {
		this.datNascimento = datNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Date getVencimentoCnh() {
		return vencimentoCnh;
	}

	public void setVencimentoCnh(Date vencimentoCnh) {
		this.vencimentoCnh = vencimentoCnh;
	}

	public String getNumCnh() {
		return numCnh;
	}

	public void setNumCnh(String numCnh) {
		this.numCnh = numCnh;
	}

	public String getNumDeclaração() {
		return numDeclaração;
	}

	public void setNumDeclaração(String numDeclaração) {
		this.numDeclaração = numDeclaração;
	}


	public NotaFiscal getNotafiscal() {
		return notafiscal;
	}

	public void setNotafiscal(NotaFiscal notafiscal) {
		this.notafiscal = notafiscal;
	}

	public String getPacCod() {
		return pacCod;
	}

	public void setPacCod(String pacCod) {
		this.pacCod = pacCod;
	}
}
