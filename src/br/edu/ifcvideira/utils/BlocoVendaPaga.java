package br.edu.ifcvideira.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class BlocoVendaPaga extends JPanel{
	Color corGeral = new Color(Preferencias.getR(), Preferencias.getG(), Preferencias.getB());
	Color corSecundaria = Cor.corMaisClara(corGeral, 0.2f);
	Color corTerciaria = Cor.corMaisClara(corGeral, 0.4f);
	Color corTexto = new Color(25, 30, 35);
	
	NumberFormat nf = new DecimalFormat("#.##");
	
	public BlocoVendaPaga(int id, int idUsuario, double valor, Timestamp data) {		
		setBackground(corTerciaria);
		setLayout(null);
		setPreferredSize(new Dimension(620, 100));
		setMaximumSize(new Dimension(620, 100));
		setMinimumSize(new Dimension(620, 100));
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, corGeral));
		
		JLabel lblIdTitulo = new JLabel("Id: ");
		lblIdTitulo.setForeground(corTexto);
		lblIdTitulo.setFont(new Font("Roboto", Font.PLAIN, 22));
		lblIdTitulo.setBounds(68, 23, 28, 50);
		add(lblIdTitulo);
		
		JLabel lblIdUsuarioTitulo = new JLabel("Id Usu\u00E1rio: ");
		lblIdUsuarioTitulo.setForeground(new Color(25, 30, 35));
		lblIdUsuarioTitulo.setFont(new Font("Roboto", Font.PLAIN, 22));
		lblIdUsuarioTitulo.setBounds(199, 33, 107, 30);
		add(lblIdUsuarioTitulo);
		
		JLabel lblValorTitulo = new JLabel("Valor: ");
		lblValorTitulo.setForeground(new Color(25, 30, 35));
		lblValorTitulo.setFont(new Font("Roboto", Font.PLAIN, 22));
		lblValorTitulo.setBounds(407, 23, 61, 50);
		add(lblValorTitulo);
		
		JLabel lblId = new JLabel(String.valueOf(id));
		lblId.setForeground(new Color(25, 30, 35));
		lblId.setFont(new Font("Roboto", Font.PLAIN, 28));
		lblId.setBounds(96, 23, 76, 50);
		add(lblId);
		
		JLabel lblIdUsuario = new JLabel(String.valueOf(idUsuario));
		lblIdUsuario.setForeground(new Color(25, 30, 35));
		lblIdUsuario.setFont(new Font("Roboto", Font.PLAIN, 28));
		lblIdUsuario.setBounds(309, 23, 86, 50);
		add(lblIdUsuario);
		
		JLabel lblValor = new JLabel("R$" + nf.format(valor));
		lblValor.setForeground(new Color(25, 30, 35));
		lblValor.setFont(new Font("Roboto", Font.PLAIN, 28));
		lblValor.setBounds(468, 23, 140, 50);
		add(lblValor);
		
		String dataVenda = data.getDay() + "/" + data.getMonth() + "/" + (data.getYear() + 1900);
		JLabel lblData = new JLabel(dataVenda);
		lblData.setForeground(new Color(25, 30, 35));
		lblData.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblData.setBounds(522, 73, 96, 24);
		add(lblData);
	}
}
