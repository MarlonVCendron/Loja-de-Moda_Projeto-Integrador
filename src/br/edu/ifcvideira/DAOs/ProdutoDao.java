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
			String sql = "INSERT INTO produtos (nome , valor_unitario , tamanho, id_categoria, id_fornecedor, codigo_barras, status, qtde) VALUES (?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, pr.getNome());
			sqlPrep.setDouble(2, pr.getValorUnitario());
			sqlPrep.setString(3, pr.getTamanho());
			sqlPrep.setInt(4, pr.getIdCategoria());
			sqlPrep.setInt(5, pr.getIdFornecedor());
			sqlPrep.setString(6, pr.getCodigoBarras());
			sqlPrep.setInt(7, pr.getStatus());
			sqlPrep.setInt(8, pr.getQuantidade());
			
			sqlPrep.execute();
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}

	public void AlterarProduto(Produto pr) throws Exception {
		try{
			String sql = "UPDATE produtos SET nome=?, valor_unitario=?, tamanho=?, id_categoria=?, id_fornecedor=?, codigo_barras=?, status=?, qtde=? WHERE id=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, pr.getNome());
			sqlPrep.setDouble(2, pr.getValorUnitario());
			sqlPrep.setString(3, pr.getTamanho());
			sqlPrep.setInt(4, pr.getIdCategoria());
			sqlPrep.setInt(5, pr.getIdFornecedor());
			sqlPrep.setString(6, pr.getCodigoBarras());
			sqlPrep.setInt(7, pr.getStatus());
			sqlPrep.setInt(8, pr.getQuantidade());
			sqlPrep.setInt(9, pr.getId());
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
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8), rs.getString(9)};
				Produto.add(linha);
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return Produto;
	}
	
	public Object[] buscarProduto(String codigoBarras) throws SQLException, Exception{
		Object[] info = new Object[9];
		try {
			String sql = "SELECT * FROM produtos WHERE codigo_barras=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, codigoBarras);
			ResultSet rs = sqlPrep.executeQuery();
			
			while (rs.next())
			{
				Object[] linha = {rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getInt(9)};
				info = linha;
			}
			sqlPrep.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return info;
	}
	
	public String NomeProduto(int id) throws SQLException, Exception{
		String nome = "";
		try {
			String sql = "SELECT nome FROM produtos WHERE id=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, id);
			ResultSet rs = sqlPrep.executeQuery();
			
			while (rs.next())
			{
				nome = rs.getString(1);
			}
			sqlPrep.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return nome;
	}
	
	public Object[] buscarProduto(int id) throws SQLException, Exception{
		Object[] info = new Object[9];
		try {
			String sql = "SELECT * FROM produtos WHERE id=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, id);
			ResultSet rs = sqlPrep.executeQuery();
			
			while (rs.next())
			{
				Object[] linha = {rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getInt(9)};
				info = linha;
			}
			sqlPrep.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return info;
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