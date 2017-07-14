package modelo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import conexaoBanco.ConexaoFirebird;

public class Paciente {

	private String nome;
	private Integer pesCod;
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
			ps = con.prepareStatement( "SELECT PESSOA.PES_COD , PES_NOME, PES_CPF, PES_DTNASCIMENTO, PES_CORREIOPESSOAL, "
					+ "PES_CELULAR, PAC_COD FROM PACIENTE INNER JOIN PESSOA ON PESSOA.PES_COD = PACIENTE.PES_COD "
					+ "WHERE PES_NOME like ? ORDER BY PES_NOME");
			
			ps.setString(1, nome.toUpperCase()+"%");
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setNome(rs.getString("PES_NOME"));
				paciente.setPacCod(rs.getString("PAC_COD"));
				paciente.setEmail(rs.getString("PES_CORREIOPESSOAL"));
				paciente.setDatNascimento(rs.getDate("PES_DTNASCIMENTO"));
				paciente.setCpf(rs.getString("PES_CPF"));
				paciente.setCelular(rs.getString("PES_CELULAR"));
				paciente.setPesCod(rs.getInt("PES_COD"));
				pacientes.add(paciente);
			}
		} catch (Exception e) {

		}finally {
			conexao.close(con);
		}
			
		return pacientes;
	}

	public boolean atualizar(Paciente paciente){
		
		ConexaoFirebird conexao = new ConexaoFirebird();
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = conexao.getConnection();
			ps = con.prepareStatement("UPDATE PESSOA SET PES_NOME = ?, PES_CORREIOPESSOAL = ? , PES_DTNASCIMENTO = ? ,"
					+ " PES_CPF = ? , PES_CELULAR = ? WHERE PES_COD = ?   ");
			ps.setString(1, paciente.getNome());
			ps.setString(2, paciente.getEmail());
			ps.setDate(3, new java.sql.Date(paciente.getDatNascimento().getTime()));
			ps.setString(4, paciente.getCpf());
			ps.setString(5, paciente.getCelular());
			System.out.println(paciente.getPesCod());
			ps.setInt(6, paciente.getPesCod());
			
			if(ps.executeUpdate() > 0){
				return true;
			}else{
				return false;
			}
					
		} catch (Exception e) {
			e.printStackTrace();			
			return false;
		}
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

	public Integer getPesCod() {
		return pesCod;
	}

	public void setPesCod(Integer pesCod) {
		this.pesCod = pesCod;
	}
}
