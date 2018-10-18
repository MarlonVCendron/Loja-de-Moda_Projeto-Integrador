package br.edu.ifcvideira.Classes;

import java.sql.Timestamp;

public class Venda {
	private int id_venda;
	private int id_usuario;
	private int id_cliente;
	private Timestamp data_venda;
	private double desconto;
	
	public int getId_venda() {
		return id_venda;
	}
	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public Timestamp getData_venda() {
		return data_venda;
	}
	public void setData_venda(Timestamp data_venda) {
		this.data_venda = data_venda;
	}
	public double getDesconto() {
		return desconto;
	}
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	
	
}
