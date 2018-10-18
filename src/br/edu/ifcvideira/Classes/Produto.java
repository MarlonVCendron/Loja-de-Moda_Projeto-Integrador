package br.edu.ifcvideira.Classes;

import java.sql.Timestamp;

public class Produto {
	private int id;
	private String nome;
	private double valorUnitario;
	private String tamanho;
	private int idCategoria;
	private int idFornecedor;
	private int status;
	
	
	
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
	public double getValor_unitario() {
		return valorUnitario;
	}
	public void setValor_unitario(double valor_unitario) {
		this.valorUnitario = valor_unitario;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public int getId_categoria() {
		return idCategoria;
	}
	public void setId_categoria(int id_categoria) {
		this.idCategoria = id_categoria;
	}
	public int getId_fornecedor() {
		return idFornecedor;
	}
	public void setId_fornecedor(int id_fornecedor) {
		this.idFornecedor = id_fornecedor;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}