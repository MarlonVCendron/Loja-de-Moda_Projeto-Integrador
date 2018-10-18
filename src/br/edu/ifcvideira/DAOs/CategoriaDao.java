package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import br.edu.ifcvideira.Classes.Categoria;
import br.edu.ifcvideira.utils.Conexao;


public class CategoriaDao{
	
	public void CadastrarCategoria(Categoria fo) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO categoria (descricao, desconto) VALUES (?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, fo.getDescricao());
			sqlPrep.setDouble(2, fo.getDesconto());
			sqlPrep.execute();
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}

	public void AlterarCategoria(Categoria fo) throws Exception {
		try{
			String sql = "UPDATE Categoria SET descricao=?, desconto=?, WHERE id=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, fo.getDescricao());
			sqlPrep.setDouble(2, fo.getDesconto());
			sqlPrep.setInt(3, fo.getId());
			sqlPrep.execute();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deletarCategoria(Categoria fo) throws Exception{
		try{
			String sql = "DELETE FROM Categoria WHERE id=? ";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, fo.getId());
			sqlPrep.execute();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscarTodos() throws SQLException, Exception{
		List<Object> Categoria = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM Categoria";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
				Categoria.add(linha);
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return Categoria;
	}
	
	public int RetornarProximoidCategoria() throws Exception {
		try{
			String sql ="SELECT MAX(id)+1 AS id FROM Categoria ";
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