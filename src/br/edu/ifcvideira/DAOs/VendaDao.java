package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.Classes.Venda;
import br.edu.ifcvideira.utils.Conexao;

public class VendaDao {
	public void CadastrarVenda(Venda ve) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO venda (id_usuario, id_cliente, data, desconto) VALUES (?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, ve.getIdUsuario());
			sqlPrep.setInt(2, ve.getIdCliente());
			sqlPrep.setTimestamp(3, ve.getData());
			sqlPrep.setDouble(4, ve.getDesconto());
			sqlPrep.execute();
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}

	public void AlterarVenda(Venda ve) throws Exception {
		try{
			String sql = "UPDATE venda SET id_usuario=?, id_cliente=?, data=?, desconto=? WHERE id=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, ve.getIdUsuario());
			sqlPrep.setInt(2, ve.getIdCliente());
			sqlPrep.setTimestamp(3, ve.getData());
			sqlPrep.setDouble(4, ve.getDesconto());
			sqlPrep.setInt(5, ve.getId());
			sqlPrep.execute();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deletarVenda(Venda ve) throws Exception{
		try{
			String sql = "DELETE FROM venda WHERE id=? ";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, ve.getId());
			sqlPrep.execute();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscarTodos() throws SQLException, Exception{
		List<Object> Venda = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM venda";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)};
				Venda.add(linha);
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return Venda;
	}
	
	public int RetornarProximoCodigoVenda() throws Exception {
		try{
			String sql ="SELECT MAX(id)+1 AS id FROM venda ";
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