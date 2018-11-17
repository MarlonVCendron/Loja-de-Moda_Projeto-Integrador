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
	
	public void CadastrarCategoria(Categoria cat) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO categorias (descricao, desconto) VALUES (?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, cat.getDescricao());
			sqlPrep.setDouble(2, cat.getDesconto());
			sqlPrep.execute();
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}

	public void AlterarCategoria(Categoria cat) throws Exception {
		try{
			String sql = "UPDATE categorias SET descricao=?, desconto=?  WHERE id=?";
			System.out.println("Chegou aqui");

			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, cat.getDescricao());
			sqlPrep.setDouble(2, cat.getDesconto());
			sqlPrep.setInt(3, cat.getId());
			sqlPrep.execute();

		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deletarCategoria(Categoria cat) throws Exception{
		try{
			String sql = "DELETE FROM categorias WHERE id=? ";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, cat.getId());
			sqlPrep.execute();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscarTodos() throws SQLException, Exception{
		List<Object> Categoria = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM categorias";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3)};
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
			String sql ="SELECT MAX(id)+1 AS id FROM categorias ";
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
			String sql = "SELECT descricao FROM categorias";
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
	
	public double buscarDesconto(int id) throws SQLException, Exception{
		double desconto = 0;
		try {
			String sql = "SELECT desconto FROM categorias WHERE id=?";
			//java.sql.Statement state = Conexao.conectar().createStatement();
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, id);
			ResultSet rs = sqlPrep.executeQuery();
			
			while (rs.next())
			{
				desconto = rs.getDouble(1);
			}
			sqlPrep.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return desconto;
	}
}