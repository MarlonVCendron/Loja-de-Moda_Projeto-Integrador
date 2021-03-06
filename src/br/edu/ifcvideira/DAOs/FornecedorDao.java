package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.Classes.Categoria;
import br.edu.ifcvideira.Classes.Fornecedor;
import br.edu.ifcvideira.utils.Conexao;


public class FornecedorDao{
	
	public void CadastrarFornecedor(Fornecedor fo) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO fornecedores (nome , cnpj ,email , telefone, rua, bairro ,cidade , estado) VALUES (?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, fo.getNome());
			sqlPrep.setString(2, fo.getCnpj());
			sqlPrep.setString(3, fo.getEmail());
			sqlPrep.setString(4, fo.getTelefone());
			sqlPrep.setString(5, fo.getRua());
			sqlPrep.setString(6, fo.getBairro());
			sqlPrep.setString(7, fo.getCidade());
			sqlPrep.setString(8, fo.getEstado());

			sqlPrep.execute();
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}

	public void AlterarFornecedor(Fornecedor fo) throws Exception {
		try{
			String sql = "UPDATE fornecedores SET nome=?, cnpj=?, email=?, telefone=?, rua=?, bairro=?, cidade=?, estado=? WHERE id=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, fo.getNome());
			sqlPrep.setString(2, fo.getCnpj());
			sqlPrep.setString(3, fo.getEmail());
			sqlPrep.setString(4, fo.getTelefone());
			sqlPrep.setString(5, fo.getRua());
			sqlPrep.setString(6, fo.getBairro());
			sqlPrep.setString(7, fo.getCidade());
			sqlPrep.setString(8, fo.getEstado());
			sqlPrep.setInt(9, fo.getId());
			sqlPrep.execute();
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deletarFornecedor(Fornecedor fo) throws Exception{
		try{
			String sql = "DELETE FROM fornecedores WHERE id=? ";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, fo.getId());
			sqlPrep.execute();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscarTodos() throws SQLException, Exception{
		List<Object> Fornecedor = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM fornecedores";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)};
				Fornecedor.add(linha);
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return Fornecedor;
	}
	
	public int RetornarProximoidFornecedor() throws Exception {
		try{
			String sql ="SELECT MAX(id)+1 AS id FROM fornecedores ";
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
			String sql = "SELECT nome FROM fornecedores";
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
	public Boolean RetornarForeignKey(Fornecedor fo) throws SQLException, Exception{
		try{
			String sql = "SELECT id FROM produtos WHERE id_fornecedor=? ";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);			
			sqlPrep.setInt(1,fo.getId());

			ResultSet rs = sqlPrep.executeQuery(); 
			
			return (rs.next()) ? false : true;
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return true;
		}
	}

	
	
	
}