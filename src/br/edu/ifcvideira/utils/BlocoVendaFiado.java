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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import br.edu.ifcvideira.Classes.Produto;
import br.edu.ifcvideira.Classes.ProdutoVenda;
import br.edu.ifcvideira.Classes.Venda;
import br.edu.ifcvideira.DAOs.ClienteDao;
import br.edu.ifcvideira.DAOs.ProdutoDao;
import br.edu.ifcvideira.DAOs.ProdutoVendaDao;
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
				ArrayList<ProdutoVenda> produtosParaComprar = new ArrayList<>();
				Venda ve = new Venda();
				VendaDao veDao = new VendaDao();
				ProdutoDao prDao = new ProdutoDao();
				ProdutoVendaDao pvDao = new ProdutoVendaDao();
				
				try {
					Object[] infoVenda = veDao.buscarVenda(id);
					
		    		ve.setId((int) infoVenda[0]);
					ve.setIdUsuario((int) infoVenda[1]);
					ve.setIdCliente((int) infoVenda[2]);
					ve.setStatus(1);
					ve.setData((Timestamp) infoVenda[3]);
					ve.setDesconto((int) infoVenda[6]);
					ve.setStatusPagamento(1);
					
					
					List<Object> dadosTodosProdutos = pvDao.buscarProdutosVenda(id);
					for(Object p : dadosTodosProdutos) {
						Object[] dadosProdutoVenda = (Object[]) p;
						ProdutoVenda pv = new ProdutoVenda();
						
						pv.setId((int) dadosProdutoVenda[0]);
						pv.setIdProduto((int) dadosProdutoVenda[1]);
						pv.setIdVenda((int) dadosProdutoVenda[2]);
						pv.setValorUnitario((double) dadosProdutoVenda[3]);
						pv.setQuantidade((int) dadosProdutoVenda[4]);
						
						produtosParaComprar.add(pv);
					}
					
					for(int i = 0; i < produtosParaComprar.size(); i++) {
						Produto pr = new Produto();
						Object[] dadosProduto = prDao.buscarProduto(produtosParaComprar.get(i).getIdProduto());
						
						pr.setId((int) dadosProduto[0]);
						pr.setNome(String.valueOf(dadosProduto[1]));
						pr.setValorUnitario((double) dadosProduto[2]);
						pr.setTamanho(String.valueOf(dadosProduto[3]));
						pr.setIdCategoria((int) dadosProduto[4]);
						pr.setIdFornecedor((int) dadosProduto[5]);
						pr.setCodigoBarras(String.valueOf(dadosProduto[6]));
						pr.setStatus((int) dadosProduto[7]);
						pr.setQuantidade((int) dadosProduto[8] - produtosParaComprar.get(i).getQuantidade());
						
						prDao.AlterarProduto(pr);
						veDao.AlterarVenda(ve);
					}
					
					ClienteDao clDao = new ClienteDao();
					String[] infoCliente = clDao.buscarInfoCliente(ve.getIdCliente());
					
					imprimirNotaFiscal(infoCliente[0], ve.getData().toString(), infoCliente[1], String.valueOf(ve.getDesconto()), ve.getId(), produtosParaComprar);
					
		    	}catch (Exception err) {
		    		JOptionPane.showMessageDialog(null, err.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		   		}	
			}
		});
		btnFinalizar.setFont(new Font("Roboto", Font.PLAIN, 15));
		btnFinalizar.setBorder(null);
		btnFinalizar.setBounds(468, 26, 140, 50);
		add(btnFinalizar);
	}
	
	public static void imprimirNotaFiscal(String nomeCliente, String dataCompra, String cpfCliente, String desconto, int idVenda, ArrayList<ProdutoVenda> produtosParaComprar) {
		JTextArea taNota = new JTextArea();
		ProdutoDao prDao = new ProdutoDao();
		Timestamp dataPagamento = new Timestamp(System.currentTimeMillis());
		
		 taNota.setForeground(new Color(25, 30, 35));
		 taNota.setFont(new Font("Roboto", Font.PLAIN, 20));
		    
		try {
			NumberFormat nf = new DecimalFormat("#.##");
			double totalAPagar = 0;
			taNota.setText(                   "  ---------------------------------------------------------------\n");
			taNota.setText(taNota.getText() + "\n");
			taNota.setText(taNota.getText() + "  " + Preferencias.getNomeLoja() + "\n");
			taNota.setText(taNota.getText() + "\n");
			if(Preferencias.getEnderecoLoja() != "") {
				taNota.setText(taNota.getText() + "  " + Preferencias.getEnderecoLoja() + "\n");
			}
			if(Preferencias.getTelefoneLoja() != "") {
				taNota.setText(taNota.getText() + "  " + Preferencias.getTelefoneLoja() + "\n");
			}
			taNota.setText(taNota.getText() + "  ---------------------------------------------------------------\n");
			taNota.setText(taNota.getText() + "  Venda número: " + idVenda + "\n");
			if(nomeCliente != null) {
				taNota.setText(taNota.getText() + "  Cliente: " + nomeCliente + "\n");	
			}
			if(cpfCliente != null) {
				taNota.setText(taNota.getText() + "  Cpf: " + cpfCliente + "\n");
			}
			if(dataCompra != null) {
				taNota.setText(taNota.getText() + "  Compra: " + dataCompra + "\n");
			}
			taNota.setText(taNota.getText() + "  Pagamento: " + dataPagamento + "\n");
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
			
			
			taNota.print();
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
