package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.Classes.Cliente;
import br.edu.ifcvideira.utils.Conexao;

public class ClienteDao {
	public void CadastrarCliente(Cliente cl) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO clientes(nome, cpf, rg, telefone, celular, data_cadastro, rua, bairro, cidade, estado) VALUES (?,?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, cl.getNome());
			sqlPrep.setString(2, cl.getCpf());
			sqlPrep.setString(3, cl.getTelefone());
			sqlPrep.setString(4, cl.getCelular());
			sqlPrep.setTimestamp(5, cl.getDataCadastro());
			sqlPrep.setString(6, cl.getRua());
			sqlPrep.setString(7, cl.getBairro());
			sqlPrep.setString(8, cl.getCidade());
			sqlPrep.setString(9, cl.getEstado());
			sqlPrep.execute();
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}

	public void AlterarCliente(Cliente cl) throws Exception {
		try{
			String sql = "UPDATE clientes SET nome=?, cpf=?, telefone=?, celular=?, data_cadastro=?, rua=?, bairro=?, cidade=?, estado=? WHERE id=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, cl.getNome());
			sqlPrep.setString(2, cl.getCpf());
			sqlPrep.setString(3, cl.getTelefone());
			sqlPrep.setString(4, cl.getCelular());
			sqlPrep.setTimestamp(5, cl.getDataCadastro());
			sqlPrep.setString(6, cl.getRua());
			sqlPrep.setString(7, cl.getBairro());
			sqlPrep.setString(8, cl.getCidade());
			sqlPrep.setString(9, cl.getEstado());
			sqlPrep.setInt(10, cl.getId());
			sqlPrep.execute();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deletarCliente(Cliente cl) throws Exception{
		try{
			String sql = "DELETE FROM clientes WHERE id=? ";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, cl.getId());
			sqlPrep.execute();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscarTodos() throws SQLException, Exception{
		List<Object> Cliente = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM clientes";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)};
				Cliente.add(linha);
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return Cliente;
	}
	
	public int RetornarProximoCodigoCliente() throws Exception {
		try{
			String sql ="SELECT MAX(id)+1 AS id FROM cliente ";
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