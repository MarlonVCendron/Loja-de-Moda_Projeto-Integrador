package br.edu.ifcvideira.utils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import br.edu.ifcvideira.Classes.ProdutoVenda;
import br.edu.ifcvideira.DAOs.VendaDao;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BlocoVendaFiado extends JPanel{
	Color corGeral = new Color(Preferencias.getR(), Preferencias.getG(), Preferencias.getB());
	Color corSecundaria = Cor.corMaisClara(corGeral, 0.2f);
	Color corTerciaria = Cor.corMaisClara(corGeral, 0.4f);
	Color corTexto = new Color(25, 30, 35);
	
	NumberFormat nf = new DecimalFormat("#.##");
	
	public BlocoVendaFiado(int id, int idUsuario, double valor, Timestamp data) {
		setBackground(corTerciaria);
		setLayout(null);
		setPreferredSize(new Dimension(620, 100));
		setMaximumSize(new Dimension(620, 100));
		setMinimumSize(new Dimension(620, 100));
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, corGeral));
		
		JLabel lblIdTitulo = new JLabel("Id: ");
		lblIdTitulo.setForeground(corTexto);
		lblIdTitulo.setFont(new Font("Roboto", Font.PLAIN, 22));
		lblIdTitulo.setBounds(22, 23, 28, 50);
		add(lblIdTitulo);
		
		JLabel lblIdUsuarioTitulo = new JLabel("Id Usu\u00E1rio: ");
		lblIdUsuarioTitulo.setForeground(new Color(25, 30, 35));
		lblIdUsuarioTitulo.setFont(new Font("Roboto", Font.PLAIN, 22));
		lblIdUsuarioTitulo.setBounds(98, 33, 107, 30);
		add(lblIdUsuarioTitulo);
		
		JLabel lblValorTitulo = new JLabel("Valor: ");
		lblValorTitulo.setForeground(new Color(25, 30, 35));
		lblValorTitulo.setFont(new Font("Roboto", Font.PLAIN, 22));
		lblValorTitulo.setBounds(249, 23, 61, 50);
		add(lblValorTitulo);
		
		JLabel lblId = new JLabel(String.valueOf(id));
		lblId.setForeground(new Color(25, 30, 35));
		lblId.setFont(new Font("Roboto", Font.PLAIN, 28));
		lblId.setBounds(50, 23, 76, 50);
		add(lblId);
		
		JLabel lblIdUsuario = new JLabel(String.valueOf(idUsuario));
		lblIdUsuario.setForeground(new Color(25, 30, 35));
		lblIdUsuario.setFont(new Font("Roboto", Font.PLAIN, 28));
		lblIdUsuario.setBounds(208, 23, 86, 50);
		add(lblIdUsuario);
		
		JLabel lblValor = new JLabel("R$" + nf.format(valor));
		lblValor.setForeground(new Color(25, 30, 35));
		lblValor.setFont(new Font("Roboto", Font.PLAIN, 28));
		lblValor.setBounds(310, 23, 140, 50);
		add(lblValor);
		
		String dataVenda = data.getDay() + "/" + data.getMonth() + "/" + (data.getYear() + 1900);
		JLabel lblData = new JLabel(dataVenda);
		lblData.setForeground(new Color(25, 30, 35));
		lblData.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblData.setBounds(22, 73, 96, 24);
		add(lblData);
		
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnFinalizar.setBackground(corSecundaria);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnFinalizar.setBackground(corGeral);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				btnFinalizar.setBackground(corTerciaria);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnFinalizar.setBackground(corGeral);
			}
		});
		btnFinalizar.setBackground(corGeral);
		btnFinalizar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
			}
		});
		btnFinalizar.setFont(new Font("Roboto", Font.PLAIN, 15));
		btnFinalizar.setBorder(null);
		btnFinalizar.setBounds(468, 26, 140, 50);
		add(btnFinalizar);
	}
	
	public static void atualizarNotaFiscal(String nomeCliente, String dataCompra, String cpfCliente, String desconto) {
		JTextArea taNota = new JTextArea();
		
/*		try {
			NumberFormat nf = new DecimalFormat("#.##");
			double totalAPagar = 0;
			taNota.setText(                   "  ---------------------------------------------------------------\n");
			taNota.setText(taNota.getText() + "\n");
			taNota.setText(taNota.getText() + "  LOJA DE MODA" + "\n");
			taNota.setText(taNota.getText() + "\n");
			taNota.setText(taNota.getText() + "  ---------------------------------------------------------------\n");
			taNota.setText(taNota.getText() + "  Venda número: " + veDao.RetornarProximoCodigoVenda() + "\n");
			if(nomeCliente != null) {
				taNota.setText(taNota.getText() + "  Cliente: " + nomeCliente + "\n");	
			}
			if(cpfCliente != null) {
				taNota.setText(taNota.getText() + "  Cpf: " + cpfCliente + "\n");
			}
			if(dataCompra != null) {
				taNota.setText(taNota.getText() + "  " + dataCompra + "\n");
			}
			taNota.setText(taNota.getText() + "  ---------------------------------------------------------------\n");
			taNota.setText(taNota.getText() + "\n");
			taNota.setText(taNota.getText() + "  Código     Descrição   \n");
			taNota.setText(taNota.getText() + "   -->    Qtd      Valor unit.     Valor total\n");
			taNota.setText(taNota.getText() + "  ---------------------------------------------------------------\n");
			for(ProdutoVenda x : produtosParaComprar) {
				taNota.setText(taNota.getText() + "  " + x.getIdProduto() + "         " + prDao.NomeProduto(x.getIdProduto()) + "\n");
				taNota.setText(taNota.getText() + "            " + x.getQuantidade() + "         R$" + nf.format(x.getValorUnitario()) + "         R$" + nf.format(x.getQuantidade() * x.getValorUnitario()) +"\n");
				taNota.setText(taNota.getText() + "  ---------------------------------------------------------------\n");
				totalAPagar += x.getQuantidade() * x.getValorUnitario();
			}
			taNota.setText(taNota.getText() + "  Desconto................................. " + desconto + "%\n");
			taNota.setText(taNota.getText() + "  Total a pagar.......................... R$" + nf.format(totalAPagar) + "\n");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}*/
	}
	
}
