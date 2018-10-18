package br.edu.ifcvideira.Classes;

import java.sql.Timestamp;

public class Cliente {
	
	private int codigo;
	private String nome;
	private String cpf;
	private String telefone;
	private double renda;
	private Timestamp data;
	
	
	//encapsulamento dos atributos
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public double getRenda() {
		return renda;
	}
	public void setRenda(double renda) {
		this.renda = renda;
	}
	public Timestamp getData() {
		return data;
	}
	public void setData(java.sql.Timestamp timestamp) {
		this.data = timestamp;
	}
	
	
	
	

}
