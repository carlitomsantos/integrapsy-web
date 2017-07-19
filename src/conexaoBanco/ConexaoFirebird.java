package conexaoBanco;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.util.Properties;

public class ConexaoFirebird {

	private String servidor;
	private String caminhoBanco;
	private String usuario;
	private String senha;
	
	public ConexaoFirebird(){
		File diretorio = new File(System.getProperty("user.home") + "\\IntegraPSY");
		if (diretorio.exists()) {
			File integraPsyProperties = new File(diretorio.toString() + "\\integrapsy.properties");
			if (integraPsyProperties.exists()) {
				Properties properties = new Properties();
				FileInputStream fis;
				try {
					fis = new FileInputStream(integraPsyProperties);
					properties.load(fis);
					servidor = properties.getProperty("servidor");
					caminhoBanco = properties.getProperty("caminhoBanco");
					usuario = properties.getProperty("usuario");
					senha = properties.getProperty("senha");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public ConexaoFirebird(String servidor, String caminhoBanco, String usuario, String senha){
		this.servidor = servidor;		
		this.caminhoBanco = caminhoBanco;
		this.usuario = usuario;
		this.senha = senha;
	}

	public Connection getConnection() throws Exception {
		try {
			Class.forName("org.firebirdsql.jdbc.FBDriver");
			Connection con = DriverManager
					.getConnection("jdbc:firebirdsql:" + servidor + ":" + caminhoBanco+"?encoding=WIN1252", usuario ,  senha);
			return con;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			throw e;

		}
	}

	public void close(Connection con) {
		try {
			con.close();
		} catch (Exception ex) {
		}
	}

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public String getCaminhoBanco() {
		return caminhoBanco;
	}

	public void setCaminhoBanco(String caminhoBanco) {
		this.caminhoBanco = caminhoBanco;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
