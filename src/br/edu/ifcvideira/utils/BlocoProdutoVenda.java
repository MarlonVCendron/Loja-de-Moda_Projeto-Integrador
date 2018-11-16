package br.edu.ifcvideira.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.edu.ifcvideira.Jframes.TelaLogin;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class BlocoProdutoVenda extends JPanel{
	Color corGeral = new Color(Preferencias.getR(), Preferencias.getG(), Preferencias.getB());
	Color corSecundaria = Cor.corMaisClara(corGeral, 0.2f);
	Color corTerciaria = Cor.corMaisClara(corGeral, 0.4f);
	Color corTexto = new Color(25, 30, 35);
	
	ImageIcon imageIconMais = new ImageIcon(TelaLogin.class.getResource("/br/edu/ifcvideira/img/mais.png"));
	Image imagemMais = imageIconMais.getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
	
	ImageIcon imageIconLixeira = new ImageIcon(TelaLogin.class.getResource("/br/edu/ifcvideira/img/lixeira.png"));
	Image imagemLixeira= imageIconLixeira.getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
	
	JLabel lblQuantidade;
	JLabel lblValor;
	
	NumberFormat nf = new DecimalFormat("#.##");
	
	public BlocoProdutoVenda(String nomeProduto, int quantidade, double valor) {
		setBackground(corTerciaria);
		setPreferredSize(new Dimension(1095, 100));
		setMaximumSize(new Dimension(1095, 100));
		setMinimumSize(new Dimension(1095, 100));
		setLayout(null);
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, corGeral));
		
		JLabel lblProduto = new JLabel(nomeProduto);
		lblProduto.setForeground(corTexto);
		lblProduto.setFont(new Font("Roboto", Font.PLAIN, 30));
		lblProduto.setBounds(25, 25, 385, 50);
		add(lblProduto);
		
		JLabel lblQuantia = new JLabel("Quantia:");
		lblQuantia.setForeground(corTexto);
		lblQuantia.setFont(new Font("Roboto", Font.PLAIN, 22));
		lblQuantia.setBounds(422, 25, 86, 50);
		add(lblQuantia);
		
		lblQuantidade = new JLabel(String.valueOf(quantidade));
		lblQuantidade.setForeground(corTexto);
		lblQuantidade.setFont(new Font("Roboto", Font.PLAIN, 30));
		lblQuantidade.setBounds(509, 25, 147, 50);
		add(lblQuantidade);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setForeground(corTexto);
		lblTotal.setFont(new Font("Roboto", Font.PLAIN, 22));
		lblTotal.setBounds(660, 25, 68, 50);
		add(lblTotal);
		
		lblValor = new JLabel("R$" + nf.format(valor));
		lblValor.setForeground(corTexto);
		lblValor.setFont(new Font("Roboto", Font.PLAIN, 30));
		lblValor.setBounds(721, 23, 188, 50);
		add(lblValor);
		
		JLabel buttonMais = new JLabel("");
		buttonMais.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		buttonMais.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonMais.setIcon(new ImageIcon(imagemMais));
		buttonMais.setBounds(918, 25, 50, 50);
		add(buttonMais);
		
		JLabel buttonLixeira = new JLabel("");
		buttonLixeira.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		buttonLixeira.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonLixeira.setIcon(new ImageIcon(imagemLixeira));
		buttonLixeira.setBounds(1003, 25, 50, 50);
		add(buttonLixeira);
	}
	
	public void setQuantidade(int quantidade, double valor) {
		this.lblQuantidade.setText(String.valueOf(quantidade));
		this.lblValor.setText("R$" + nf.format(valor));
	}
}
