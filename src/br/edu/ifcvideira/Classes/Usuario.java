package br.edu.ifcvideira.Classes;

import java.sql.Timestamp;
public class Usuario {
	private int id_usuario;
	private int tipo_usuario;
	private String nome_usuario;
	private String senha_usuario;
	private String cpf_usuario;
	private String rg_usuario;
	private String telefone_usuario;
	private String celular_usuario;
	private Timestamp data_cadastro_usuario;
	private int status_usuario;
	
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public int getTipo_usuario() {
		return tipo_usuario;
	}
	public void setTipo_usuario(int tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}
	public String getNome_usuario() {
		return nome_usuario;
	}
	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}
	public String getSenha_usuario() {
		return senha_usuario;
	}
	public void setSenha_usuario(String senha_usuario) {
		this.senha_usuario = senha_usuario;
	}
	public String getCpf_usuario() {
		return cpf_usuario;
	}
	public void setCpf_usuario(String cpf_usuario) {
		this.cpf_usuario = cpf_usuario;
	}
	public String getRg_usuario() {
		return rg_usuario;
	}
	public void setRg_usuario(String rg_usuario) {
		this.rg_usuario = rg_usuario;
	}
	public String getTelefone_usuario() {
		return telefone_usuario;
	}
	public void setTelefone_usuario(String telefone_usuario) {
		this.telefone_usuario = telefone_usuario;
	}
	public String getCelular_usuario() {
		return celular_usuario;
	}
	public void setCelular_usuario(String celular_usuario) {
		this.celular_usuario = celular_usuario;
	}
	public Timestamp getData_cadastro_usuario() {
		return data_cadastro_usuario;
	}
	public void setData_cadastro_usuario(Timestamp data_cadastro_usuario) {
		this.data_cadastro_usuario = data_cadastro_usuario;
	}
	public int getStatus_usuario() {
		return status_usuario;
	}
	public void setStatus_usuario(int status_usuario) {
		this.status_usuario = status_usuario;
	}
	
	
}
