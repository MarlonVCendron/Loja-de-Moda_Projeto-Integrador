package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.Classes.Usuario;
import br.edu.ifcvideira.utils.Conexao;

public class UsuarioDao {
	public void CadastrarUsuario(Usuario us) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO usuario (tipo, nome, senha, cpf, rg, telefone, celular, data_cadastro, status, email) VALUES (?,?,?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			System.out.println("safas");
			sqlPrep.setInt(1, us.getTipo());
			sqlPrep.setString(2, us.getNome());
			sqlPrep.setString(3, us.getSenha());
			sqlPrep.setString(4, us.getCpf());
			sqlPrep.setString(5, us.getRg());
			sqlPrep.setString(6, us.getTelefone());
			sqlPrep.setString(7, us.getCelular());
			sqlPrep.setTimestamp(8, us.getDataCadastro());
			sqlPrep.setTimestamp(9, us.getDataCadastro());
			sqlPrep.setString(10, us.getEmail());
			
			sqlPrep.execute();
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}

	public void AlterarUsuario(Usuario us) throws Exception {
		try{
			String sql = "UPDATE usuario SET tipo=?, nome=?, senha=?, cpf=?, rg=?, telefone=?, celular=?, data_cadastro=?, status=?,email=? WHERE id=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, us.getTipo());
			sqlPrep.setString(2, us.getNome());
			sqlPrep.setString(3, us.getSenha());
			sqlPrep.setString(4, us.getCpf());
			sqlPrep.setString(5, us.getRg());
			sqlPrep.setString(6, us.getTelefone());
			sqlPrep.setString(7, us.getCelular());
			sqlPrep.setTimestamp(8, us.getDataCadastro());
			sqlPrep.setInt(9, us.getStatus());
			sqlPrep.setInt(10, us.getId());
			sqlPrep.setInt(11, us.getId());
			sqlPrep.execute();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deletarUsuario(Usuario us) throws Exception{
		try{
			String sql = "DELETE FROM usuario WHERE id=? ";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, us.getId());
			sqlPrep.execute();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscarTodos() throws SQLException, Exception{
		List<Object> Usuario = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM usuario";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),rs.getString(9),rs.getString(10)};
				Usuario.add(linha);
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return Usuario;
	}
	
	public List<Object> buscarUsuario(String nome) throws SQLException, Exception{
		List<Object> info = new ArrayList<>();
		try {
			String sql = "SELECT id, senha, tipo FROM usuario WHERE nome=?";
			//java.sql.Statement state = Conexao.conectar().createStatement();
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, nome);
			ResultSet rs = sqlPrep.executeQuery();
			
			while (rs.next())
			{
				info.add(rs.getInt(1));
				info.add(rs.getString(2));
				info.add(rs.getString(3));
			}
			sqlPrep.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return info;
	}
	
	public int RetornarProximoCodigoUsuario() throws Exception {
		try{
			String sql ="SELECT MAX(id)+1 AS id FROM usuario ";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = sqlPrep.executeQuery();
			if (rs.next()){
				return rs.getInt("id");
			}else{
				return 1;
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 1;
		}	
	}
	
	public List<Object> buscarNomes() throws SQLException, Exception{
		List<Object> info = new ArrayList<>();
		try {
			String sql = "SELECT nome FROM usuario";
			//java.sql.Statement state = Conexao.conectar().createStatement();
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = sqlPrep.executeQuery();
			
			while (rs.next())
			{
				info.add(rs.getString(1));
			}
			sqlPrep.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return info;
	}
}
