package controle;

import java.io.Serializable;

import utils.CryptoUtil;
import utils.SessionUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import modelo.Usuario;

@ManagedBean
@SessionScoped
public class ControleLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private String senha;
	
	public String login(){
		boolean valido = false;
		
		senha = CryptoUtil.crypt(senha);
		Usuario usuario = Usuario.getUsuario(this.usuario, this.senha);
		
		if(usuario != null){
			HttpSession sessao = SessionUtils.getSession();
			sessao.setAttribute("usuario", usuario);
			return "/app/index?faces-redirect=true";
		}
		
		/*if(usuario.equals("admin") && senha.equals("admin")){
			valido = true;
		}
		
		
		if(valido){
			
		}*/
		
		return "/login/login?faces-redirect=true";
	}
	
	public String logout(){
		HttpSession sessao = SessionUtils.getSession();
		sessao.invalidate();
		return "/login/login?faces-redirect=true";
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
