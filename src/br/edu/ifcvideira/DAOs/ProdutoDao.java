package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import br.edu.ifcvideira.Classes.Produto;
import br.edu.ifcvideira.utils.Conexao;


public class ProdutoDao{
	
	public void CadastrarProduto(Produto pr) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO produtos (nome , valor_unitario , tamanho, id_categoria, id_fornecedor, status ) VALUES (?,?,?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, pr.getNome());
			sqlPrep.setDouble(2, pr.getValor_unitario());
			sqlPrep.setString(3, pr.getTamanho());
			sqlPrep.setInt(4, pr.getId_categoria());
			sqlPrep.setInt(5, pr.getId_fornecedor());
			sqlPrep.setInt(6, pr.getStatus());

			sqlPrep.execute();
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}

	public void AlterarProduto(Produto pr) throws Exception {
		try{
			String sql = "UPDATE produtos SET nome=?, valor_unitario=?, tamanho=?, id_categoria=?, id_fornecedor=?, status=? WHERE id=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, pr.getNome());
			sqlPrep.setDouble(2, pr.getValor_unitario());
			sqlPrep.setString(3, pr.getTamanho());
			sqlPrep.setInt(4, pr.getId_categoria());
			sqlPrep.setInt(5, pr.getId_fornecedor());
			sqlPrep.setInt(6, pr.getStatus());
			sqlPrep.setInt(7, pr.getId());
			sqlPrep.execute();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deletarProduto(Produto pr) throws Exception{
		try{
			String sql = "DELETE FROM produtos WHERE id=? ";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, pr.getId());
			sqlPrep.execute();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscarTodos() throws SQLException, Exception{
		List<Object> Produto = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM produtos";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7)};
				Produto.add(linha);
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return Produto;
	}
	
	public int RetornarProximoidProduto() throws Exception {
		try{
			String sql ="SELECT MAX(id)+1 AS id FROM produtos ";
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