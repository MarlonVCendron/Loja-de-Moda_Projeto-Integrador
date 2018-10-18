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
			String sql = "INSERT INTO usuario (nome, tipo, nome, senha, cpf, rg, telefone, celular, data_cadastro, status) VALUES (?,?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, us.getTipo());
			sqlPrep.setString(2, us.getNome());
			sqlPrep.setString(3, us.getSenha());
			sqlPrep.setString(4, us.getCpf());
			sqlPrep.setString(5, us.getRg());
			sqlPrep.setString(6, us.getTelefone());
			sqlPrep.setString(7, us.getCelular());
			sqlPrep.setTimestamp(8, us.getDataCadastro());
			sqlPrep.setInt(9, us.getStatus());
			sqlPrep.execute();
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}

	public void AlterarUsuario(Usuario us) throws Exception {
		try{
			String sql = "UPDATE usuario SET tipo=?, nome=?, senha=?, cpf=?, rg=?, telefone=?, celular=?, data_cadastro=?, status=? WHERE id=?";
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
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)};
				Usuario.add(linha);
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return Usuario;
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
}