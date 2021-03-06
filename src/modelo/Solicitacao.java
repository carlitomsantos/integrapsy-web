package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Solicitacao {

	private String voucher;
	private String tipoExame;
	private String urlVoucher;
	private String urlBoleto;
	private String etiqueta;
	private Paciente paciente;
	private NotaFiscal notaFiscal;
	private String formaPagamento;
	private String usuarioPsy;
	private String senhaPsy;
	private String mensagem;
	
	public Solicitacao(){
		File integraPsyProperties = new File(System.getProperty("user.home") + "\\IntegraPSY\\integrapsy.properties");
		Properties properties = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream(integraPsyProperties);
			properties.load(fis);
			usuarioPsy = properties.getProperty("usuarioPsy");
			senhaPsy = properties.getProperty("senhaPsy");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Solicitacao enviarSolicitacao(Solicitacao s) {

		Client cliente = Client.create();
		JSONObject solicitacao = this.solicitacaoToJSON(s);
		s.setMensagem(null);
		
		WebResource resource = cliente.resource("https://api.exametoxicologico.com.br/ws/v1/create");
		ClientResponse response =  resource.header("Psychemedics-Code", usuarioPsy)
				.header("Psychemedics-Pass", senhaPsy)
				.accept("application/json")
				.type("application/json")
				.post(ClientResponse.class, solicitacao.toString());
		
		String saida = response.getEntity(String.class);
		System.out.println(saida);
		JSONObject saidaJson = new JSONObject(saida);
		
		if(saidaJson.has("message")){
			s.setMensagem(saidaJson.getString("message"));
			return s;
		}
		
		if(saidaJson.has("url_boleto")){
			s.setUrlBoleto(saidaJson.getString("url_boleto"));
		}		
		
		s.setVoucher(saidaJson.getString("voucher"));
		s.setUrlVoucher(saidaJson.getString("url_voucher"));		
		
		return s;
	}
	
	private JSONObject solicitacaoToJSON(Solicitacao s){
		
		JSONObject solicitacao = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		

		solicitacao.put("nome", s.getPaciente().getNome());
		solicitacao.put("tipo_exame", s.getTipoExame());
		solicitacao.put("cpf", s.getPaciente().getCpf());
		solicitacao.put("data_nascimento", sdf.format(s.getPaciente().getDatNascimento()));
		solicitacao.put("email", s.getPaciente().getEmail());
		solicitacao.put("celular", s.getPaciente().getCelular());
		solicitacao.put("vencimento_cnh", sdf.format(s.getPaciente().getVencimentoCnh()));
		solicitacao.put("numero_cnh", s.getPaciente().getNumCnh());
		solicitacao.put("numero_declaracao", s.getPaciente().getNumDeclara��o());
		solicitacao.put("forma_pagamento", s.getFormaPagamento());
		
		if(s.getNotaFiscal() != null){
			JSONObject notaFiscal = new JSONObject();
			String cep = s.getNotaFiscal().getCep().substring(0, 4) + "-" + s.getNotaFiscal().getCep().substring(5, 7);
			notaFiscal.put("cpf", s.getNotaFiscal().getCpf());
			notaFiscal.put("nome", s.getNotaFiscal().getNome());
			notaFiscal.put("email", s.getNotaFiscal().getEmail());
			notaFiscal.put("cep", cep);
			notaFiscal.put("logradouro", s.getNotaFiscal().getLogradouro());
			notaFiscal.put("numero", s.getNotaFiscal().getNumero());
			notaFiscal.put("complemento", s.getNotaFiscal().getComplemento());
			notaFiscal.put("bairro", s.getNotaFiscal().getBairro());
			notaFiscal.put("cidade", s.getNotaFiscal().getCidade());
			notaFiscal.put("uf", s.getNotaFiscal().getUf());

			solicitacao.put("nota_fiscal", notaFiscal);
		}
		
		
		return solicitacao;
	}
	
	
	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	public String getTipoExame() {
		return tipoExame;
	}

	public void setTipoExame(String tipoExame) {
		this.tipoExame = tipoExame;
	}

	public String getUrlVoucher() {
		return urlVoucher;
	}

	public void setUrlVoucher(String urlVoucher) {
		this.urlVoucher = urlVoucher;
	}

	public String getUrlBoleto() {
		return urlBoleto;
	}

	public void setUrlBoleto(String urlBoleto) {
		this.urlBoleto = urlBoleto;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
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

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
