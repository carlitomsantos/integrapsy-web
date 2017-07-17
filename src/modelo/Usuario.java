package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexaoBanco.ConexaoFirebird;
import utils.CryptoUtil;

public class Usuario {
	
	private int usuCod;
	private int funcMatric;
	private String nomeUsuario;
	private String sigla;
	private String senha;
	
	public static Usuario getUsuario(String usuario, String senha){
		Usuario u = null;
		ConexaoFirebird conexao = new ConexaoFirebird();
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = conexao.getConnection();
			ps = con.prepareStatement( "SELECT * FROM USUARIO WHERE USU_NOME_USUARIO = ? AND USU_SENHA = ? ");
			
			ps.setString(1, usuario.toUpperCase());
			ps.setString(2,senha);
			
			
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				u = new Usuario();
				u.setUsuCod(rs.getInt("USU_COD"));
				u.setFuncMatric(rs.getInt("FUN_MATRIC"));
				u.setNomeUsuario(rs.getString("USU_NOME_USUARIO"));
				u.setSigla("USU_COD");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return u;
	}
	
	public int getUsuCod() {
		return usuCod;
	}
	public void setUsuCod(int usuCod) {
		this.usuCod = usuCod;
	}
	public int getFuncMatric() {
		return funcMatric;
	}
	public void setFuncMatric(int funcMatric) {
		this.funcMatric = funcMatric;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
