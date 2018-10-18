package br.edu.ifcvideira.Classes;

import java.sql.Timestamp;

public class Categoria {
	private int id_categorias;
	private int descricao_categorias;
	private double desconto_categorias;
	
	public int getId_categorias() {
		return id_categorias;
	}
	public void setId_categorias(int id_categorias) {
		this.id_categorias = id_categorias;
	}
	public int getDescricao_categorias() {
		return descricao_categorias;
	}
	public void setDescricao_categorias(int descricao_categorias) {
		this.descricao_categorias = descricao_categorias;
	}
	public double getDesconto_categorias() {
		return desconto_categorias;
	}
	public void setDesconto_categorias(double desconto_categorias) {
		this.desconto_categorias = desconto_categorias;
	}
	
}