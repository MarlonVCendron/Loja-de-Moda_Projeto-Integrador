package br.edu.ifcvideira.Jframes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import javax.swing.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.awt.*;

import br.edu.ifcvideira.Classes.Cliente;
import br.edu.ifcvideira.DAOs.ClienteDao;
import br.edu.ifcvideira.utils.Cor;
import br.edu.ifcvideira.utils.Preferencias;

import javax.swing.JLabel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

public class TelaCaixa extends JFrame {

	private JPanel contentPane;
	
	Color corTexto = new Color(75, 80, 85);
	Color corGeral = new Color(Preferencias.getR(), Preferencias.getG(), Preferencias.getB());
	Color corSecundaria = Cor.corMaisClara(corGeral, 0.2f);
	Color corTerciaria = Cor.corMaisClara(corGeral, 0.4f);
	Color corSeparador = new Color(176, 176, 176);
	Color corVermelho = new Color (230, 20, 20);
	
	ClienteDao clDao = new ClienteDao();
	TelaCadastroCliente telaCadastroCliente = new TelaCadastroCliente();
	TelaEditarCliente telaEditarCliente;
	
	JComboBox cbPesquisaCliente = new JComboBox<>(new Object[] {""});

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCaixa frame = new TelaCaixa();
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCaixa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setName("Tela Caixa");
		Dimension tela = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, tela.width, tela.height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, corGeral));
		Rectangle area = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		setMaximizedBounds(area);
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

		setExtendedState(MAXIMIZED_BOTH);
		
		
		//PAINEL SUPERIOR
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 0, 1920, 32);
		contentPane.add(panelSuperior);
		panelSuperior.setBackground(new Color(255, 255, 255));
		panelSuperior.setLayout(null);
		
		JButton btnX = new JButton("X");
		btnX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnX.setBackground(corSecundaria);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnX.setBackground(corGeral);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				btnX.setBackground(corTerciaria);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnX.setBackground(corGeral);
			}
		});
		btnX.setBackground(corGeral);
		btnX.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnX.setBounds(1878, 0, 42, 30);
		btnX.setMaximumSize(new Dimension(80, 50));
		btnX.setFont(new Font("Roboto", Font.PLAIN, 13));
		btnX.setBorder(null);
		panelSuperior.add(btnX);
		
		JButton btnMinimizar = new JButton("-");
		btnMinimizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnMinimizar.setBackground(corSecundaria);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnMinimizar.setBackground(corGeral);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				btnMinimizar.setBackground(corTerciaria);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnMinimizar.setBackground(corGeral);
			}
		});
		btnMinimizar.setBackground(corGeral);
		btnMinimizar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnMinimizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		btnMinimizar.setMaximumSize(new Dimension(80, 50));
		btnMinimizar.setFont(new Font("Roboto", Font.PLAIN, 15));
		btnMinimizar.setBorder(null);
		btnMinimizar.setBounds(1835, 0, 42, 30);
		panelSuperior.add(btnMinimizar);
		
		
	    
	    JLabel lblPesquisarClientes = new JLabel("Pesquisar Clientes");
	    lblPesquisarClientes.setFont(new Font("Roboto", Font.PLAIN, 18));
	    lblPesquisarClientes.setBounds(45, 57, 173, 32);
	    contentPane.add(lblPesquisarClientes);
	    
	    JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
	    btnCadastrarCliente.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		telaCadastroCliente.setVisible(true);
	    	}
	    });
	    btnCadastrarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnCadastrarCliente.setBackground(corSecundaria);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCadastrarCliente.setBackground(corGeral);
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				btnCadastrarCliente.setBackground(corTerciaria);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnCadastrarCliente.setBackground(corGeral);
			}
		});
		contentPane.add(btnCadastrarCliente);
		btnCadastrarCliente.setBackground(corGeral);
		btnCadastrarCliente.setBorder(null);
		btnCadastrarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrarCliente.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnCadastrarCliente.setForeground(corTexto);
	    btnCadastrarCliente.setBounds(410, 90, 162, 45);
	    contentPane.add(btnCadastrarCliente);
	    
	    JButton btnInformacoes = new JButton("Informa\u00E7\u00F5es");
	    btnInformacoes.setEnabled(false);
	    btnInformacoes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    btnInformacoes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(btnInformacoes.isEnabled()) {
					btnInformacoes.setBackground(corSecundaria);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(btnInformacoes.isEnabled()) {
					btnInformacoes.setBackground(corGeral);
				}
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(btnInformacoes.isEnabled()) {
					btnInformacoes.setBackground(corTerciaria);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(btnInformacoes.isEnabled()) {
					btnInformacoes.setBackground(corGeral);
				}
			}
		});
	    btnInformacoes.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    	}
	    });
	    btnInformacoes.setForeground(corTexto);
	    btnInformacoes.setFont(new Font("Roboto", Font.PLAIN, 18));
	    btnInformacoes.setBorder(null);
	    btnInformacoes.setBackground(new Color(200, 200, 200));
	    btnInformacoes.setBounds(45, 159, 162, 45);
	    contentPane.add(btnInformacoes);
	    
	    JButton btnAlterar = new JButton("Alterar");
	    btnAlterar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		String nomeCliente = cbPesquisaCliente.getSelectedItem().toString();
	    		List<Object> dadosCliente = new ArrayList<>();
	    		
	    		try {
	    			dadosCliente = clDao.buscarCliente(nomeCliente);
	    			
	    			Cliente cliente = new Cliente();
	    			
		    		cliente.setId((int) dadosCliente.get(0));
		    		cliente.setNome(dadosCliente.get(1).toString());
		    		cliente.setCpf(dadosCliente.get(2).toString());
		    		cliente.setTelefone(dadosCliente.get(3).toString());
		    		cliente.setCelular(dadosCliente.get(4).toString());
		    		cliente.setDataCadastro((Timestamp) dadosCliente.get(5));
		    		cliente.setRua(dadosCliente.get(6).toString());
		    		cliente.setBairro(dadosCliente.get(7).toString());
		    		cliente.setCidade(dadosCliente.get(8).toString());
		    		cliente.setEstado(dadosCliente.get(9).toString());
		    		
		    		telaEditarCliente = new TelaEditarCliente(cliente);
		    		telaEditarCliente.setVisible(true);
	    		}catch(Exception e) {
	    			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao alterar", JOptionPane.ERROR_MESSAGE);
	    		}
	    		
	    		
	    	}
	    });
	    btnAlterar.setEnabled(false);
	    btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    btnAlterar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(btnInformacoes.isEnabled()) {
					btnAlterar.setBackground(corSecundaria);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(btnAlterar.isEnabled()) {
					btnAlterar.setBackground(corGeral);
				}
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(btnInformacoes.isEnabled()) {
					btnAlterar.setBackground(corTerciaria);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(btnAlterar.isEnabled()) {
					btnAlterar.setBackground(corGeral);
				}
			}
		});
	    btnAlterar.setForeground(corTexto);
	    btnAlterar.setFont(new Font("Roboto", Font.PLAIN, 18));
	    btnAlterar.setBorder(null);
	    btnAlterar.setBackground(new Color(200, 200, 200));
	    btnAlterar.setBounds(229, 159, 162, 45);
	    contentPane.add(btnAlterar);
	    
	    JButton btnCompras = new JButton("Compras");
	    btnCompras.setEnabled(false);
	    btnCompras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    btnCompras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(btnInformacoes.isEnabled()) {
					btnCompras.setBackground(corSecundaria);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(btnCompras.isEnabled()) {
					btnCompras.setBackground(corGeral);
				}
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(btnCompras.isEnabled()) {
					btnCompras.setBackground(corTerciaria);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(btnCompras.isEnabled()) {
					btnCompras.setBackground(corGeral);
				}
			}
		});
	    btnCompras.setForeground(corTexto);
	    btnCompras.setFont(new Font("Roboto", Font.PLAIN, 18));
	    btnCompras.setBorder(null);
	    btnCompras.setBackground(new Color(200, 200, 200));
	    btnCompras.setBounds(410, 159, 162, 45);
	    contentPane.add(btnCompras);
	    
	    cbPesquisaCliente = new JComboBox(new Object[]{""});
	    cbPesquisaCliente.addPopupMenuListener(new PopupMenuListener() {
	    	public void popupMenuCanceled(PopupMenuEvent arg0) {
	    	}
	    	public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
	    		if(cbPesquisaCliente.getSelectedItem().equals("")) {
	    			btnAlterar.setEnabled(false);
					btnCompras.setEnabled(false);
					btnInformacoes.setEnabled(false);
					
					btnAlterar.setBackground(new Color(200, 200, 200));
					btnCompras.setBackground(new Color(200, 200, 200));
					btnInformacoes.setBackground(new Color(200, 200, 200));
				}else {
					btnAlterar.setEnabled(true);
					btnCompras.setEnabled(true);
					btnInformacoes.setEnabled(true);
					
					btnAlterar.setBackground(corGeral);
					btnCompras.setBackground(corGeral);
					btnInformacoes.setBackground(corGeral);
				}
	    	}
	    	public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
	    		List<Object> nomes = new ArrayList<>();	    		
	    		try {
	    			nomes = clDao.buscarNomes();
	    			cbPesquisaCliente.removeAllItems();
	    			cbPesquisaCliente.addItem("");
	    			for(Object n : nomes) {
	    				cbPesquisaCliente.addItem(n);
	    			}
	    			
	    		}catch(Exception e) { }
	    	}
	    });
		cbPesquisaCliente.setFont(new Font("Roboto", Font.PLAIN, 18));
		cbPesquisaCliente.setForeground(corTexto);
	    AutoCompleteDecorator.decorate(cbPesquisaCliente);
	    cbPesquisaCliente.setBounds(45,90,346,45);
	    contentPane.add(cbPesquisaCliente);
	    
	    JSeparator spCliente = new JSeparator();
	    spCliente.setBackground(corSeparador);
	    spCliente.setBounds(35, 217, 547, 2);
	    contentPane.add(spCliente);
	}
}
