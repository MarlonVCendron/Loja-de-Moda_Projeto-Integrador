package br.edu.ifcvideira.Classes;

import java.sql.Timestamp;

public class Produto {
	private int id_produto;
	private String nome_produto;
	private double valor_unitario_produto;
	private String tamanho_produto;
	private int id_categoria_produto;
	private int id_fornecedor_produto;
	private int status_produto;
	
	
	
	public int getId_produto() {
		return id_produto;
	}
	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}
	public String getNome_produto() {
		return nome_produto;
	}
	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}
	public double getValor_unitario_produto() {
		return valor_unitario_produto;
	}
	public void setValor_unitario_produto(double valor_unitario_produto) {
		this.valor_unitario_produto = valor_unitario_produto;
	}
	public String getTamanho_produto() {
		return tamanho_produto;
	}
	public void setTamanho_produto(String tamanho_produto) {
		this.tamanho_produto = tamanho_produto;
	}
	public int getId_categoria_produto() {
		return id_categoria_produto;
	}
	public void setId_categoria_produto(int id_categoria_produto) {
		this.id_categoria_produto = id_categoria_produto;
	}
	public int getId_fornecedor_produto() {
		return id_fornecedor_produto;
	}
	public void setId_fornecedor_produto(int id_fornecedor_produto) {
		this.id_fornecedor_produto = id_fornecedor_produto;
	}
	public int getStatus_produto() {
		return status_produto;
	}
	public void setStatus_produto(int status_produto) {
		this.status_produto = status_produto;
	}
}