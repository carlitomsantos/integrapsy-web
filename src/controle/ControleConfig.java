package controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import conexaoBanco.ConexaoFirebird;

@ManagedBean(name = "controleConfig")
@ViewScoped
public class ControleConfig implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String servidor;
	private String caminhoBanco;
	private String usuario;
	private String senha;
	private String usuarioPsy;
	private String senhaPsy;

	@PostConstruct
	public void init() {
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
					usuarioPsy = properties.getProperty("usuarioPsy");
					senhaPsy = properties.getProperty("senhaPsy");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}	

			}
		}

	}

	public void testarConexaoBanco(){
		ConexaoFirebird conexao = new ConexaoFirebird();
		conexao.setServidor(servidor);
		conexao.setCaminhoBanco(caminhoBanco);
		conexao.setUsuario(usuario);
		conexao.setSenha(senha);
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			if(conexao.getConnection() != null){
				context.addMessage(null, new FacesMessage("Banco Conectado com Sucesso!"));
			}
		} catch (Exception e) {
			switch (((SQLException) e).getErrorCode()) {
			case 335544721:
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Não Foi Possível conectar ao servidor :" + servidor, null));
				break;
			case 335544472:
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Banco não encontrado ou Usuário e/ou Senha Incorretos", null));
				break;

			default:
				break;
			}
			
		}
	}
	
	public void salvarConfig(){
		File diretorio = new File(System.getProperty("user.home") + "\\IntegraPSY");
		File integraPsyProperties = new File(diretorio.toString() + "\\integrapsy.properties");
		if(!diretorio.exists()){
			diretorio.mkdir();
			salvaProperties(integraPsyProperties);			
		}else if(!integraPsyProperties.exists()){
			salvaProperties(integraPsyProperties);
		}else{
			alteraProperties(integraPsyProperties);
		}
	}
	
	private void salvaProperties(File integraPsyProperties){
		try {
			FileWriter integra = new FileWriter(integraPsyProperties.toString());
			Properties properties = new Properties();
			FileInputStream fis = new FileInputStream(integraPsyProperties);
			properties.load(fis);
			properties.put("servidor", servidor);
			properties.put("caminhoBanco", caminhoBanco);
			properties.put("usuario", usuario);
			properties.put("senha", senha);
			FileOutputStream fos = new FileOutputStream(integraPsyProperties);
			properties.store(fos, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void alteraProperties(File integraPsyProperties){
		try {
			Properties properties = new Properties();
			FileInputStream fis = new FileInputStream(integraPsyProperties);
			properties.load(fis);
			properties.setProperty("servidor", servidor);
			properties.setProperty("caminhoBanco", caminhoBanco);
			properties.setProperty("usuario", usuario);
			properties.setProperty("senha", senha);
			properties.setProperty("usuarioPsy", usuarioPsy);
			properties.setProperty("senhaPsy", senhaPsy);
			FileOutputStream fos = new FileOutputStream(integraPsyProperties);
			properties.store(fos, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public String getUsuarioPsy() {
		return usuarioPsy;
	}

	public void setUsuarioPsy(String usuarioPsy) {
		this.usuarioPsy = usuarioPsy;
	}

	public String getSenhaPsy() {
		return senhaPsy;
	}

	public void setSenhaPsy(String senhaPsy) {
		this.senhaPsy = senhaPsy;
	}

}
