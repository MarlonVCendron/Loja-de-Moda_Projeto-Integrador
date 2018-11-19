package br.edu.ifcvideira.Classes;

public class Produto {
	private int id;
	private String nome;
	private double valorUnitario;
	private String tamanho;
	private int idCategoria;
	private int idFornecedor;
	private String codigoBarras;
	private int status;
	private int quantidade;
	private String nomeFornecedor;
	private String nomeCategoria;
	
	
	
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}
	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(double valor_unitario) {
		this.valorUnitario = valor_unitario;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int id_categoria) {
		this.idCategoria = id_categoria;
	}
	public int getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(int id_fornecedor) {
		this.idFornecedor = id_fornecedor;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}