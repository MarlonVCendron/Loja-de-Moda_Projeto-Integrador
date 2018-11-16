package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.Classes.ProdutoVenda;
import br.edu.ifcvideira.utils.Conexao;

public class ProdutoVendaDao {
	public void CadastrarProdutoVenda(ProdutoVenda pv) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO produtos_venda (id_produto, id_venda, valor_unitario, quantidade) VALUES (?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, pv.getIdProduto());
			sqlPrep.setInt(2, pv.getIdVenda());
			sqlPrep.setDouble(3, pv.getValorUnitario());
			sqlPrep.setInt(4, pv.getQuantidade());
			sqlPrep.execute();
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}

	public void AlterarProdutoVenda(ProdutoVenda pv) throws Exception {
		try{
			String sql = "UPDATE produtos_venda SET id_produto=?, id_venda=?, valor_unitario=?, quantidade=? WHERE id=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, pv.getIdProduto());
			sqlPrep.setInt(2, pv.getIdVenda());
			sqlPrep.setDouble(3, pv.getValorUnitario());
			sqlPrep.setInt(4, pv.getQuantidade());
			sqlPrep.setInt(5, pv.getId());
			sqlPrep.execute();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void AlterarQuantidadeProdutoVenda(int id, int quantidade) throws Exception {
		try{
			String sql = "UPDATE produtos_venda SET quantidade=? WHERE id=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, quantidade);
			sqlPrep.setInt(2, id);
			sqlPrep.execute();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deletarProdutoVenda(ProdutoVenda pv) throws Exception{
		try{
			String sql = "DELETE FROM produtos_venda WHERE id=? ";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, pv.getId());
			sqlPrep.execute();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscarTodos() throws SQLException, Exception{
		List<Object> produtos_venda = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM produtos_venda";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)};
				produtos_venda.add(linha);
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return produtos_venda;
	}
	
	public List<Object> buscarProdutosVenda(int idVenda) throws SQLException, Exception{
		List<Object> produtos_venda = new ArrayList<>();
		try {
			String sql = "SELECT * FROM produtos_venda WHERE id_venda=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, idVenda);
			ResultSet rs = sqlPrep.executeQuery();
			
			while (rs.next())
			{
				Object[] linha = {rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4), rs.getInt(5)};
				produtos_venda.add(linha);
			}
			sqlPrep.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return produtos_venda;
	}
	
	public int RetornarProximoCodigoProdutoVenda() throws Exception {
		try{
			String sql ="SELECT MAX(id)+1 AS id FROM produtos_venda ";
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
