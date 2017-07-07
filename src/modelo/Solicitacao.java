package modelo;

public class Solicitacao {
	
	private String voucher;
	private String tipoExame;
	private String urlVoucher;
	private String urlBoleto;
	private String etiqueta;
	private Paciente paciente;
	private NotaFiscal notaFiscal;
	private String formaPagamento;
	
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
	
	
}
