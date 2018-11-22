package br.edu.ifcvideira.Jframes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.EventHandler;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.border.EmptyBorder;

import javax.swing.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.awt.*;

import br.edu.ifcvideira.Classes.Cliente;
import br.edu.ifcvideira.Classes.Produto;
import br.edu.ifcvideira.Classes.ProdutoVenda;
import br.edu.ifcvideira.Classes.Venda;
import br.edu.ifcvideira.DAOs.CategoriaDao;
import br.edu.ifcvideira.DAOs.ClienteDao;
import br.edu.ifcvideira.DAOs.ProdutoDao;
import br.edu.ifcvideira.DAOs.ProdutoVendaDao;
import br.edu.ifcvideira.DAOs.VendaDao;
import br.edu.ifcvideira.utils.BlocoProdutoVenda;
import br.edu.ifcvideira.utils.Cor;
import br.edu.ifcvideira.utils.Preferencias;

import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.NumberFormatter;
import javax.swing.event.PopupMenuEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.border.MatteBorder;
import org.eclipse.wb.swing.FocusTraversalOnArray;

public class TelaCaixa extends JFrame {

	public static JPanel contentPane;
	
	Color corTexto = new Color(25, 30, 35);
	Color corGeral = new Color(Preferencias.getR(), Preferencias.getG(), Preferencias.getB());
	Color corSecundaria = Cor.corMaisClara(corGeral, 0.2f);
	Color corTerciaria = Cor.corMaisClara(corGeral, 0.4f);
	Color corSeparador = new Color(176, 176, 176);
	Color corVermelho = new Color (230, 20, 20);
	
	public static ClienteDao clDao = new ClienteDao();
	static ProdutoDao prDao = new ProdutoDao();
	CategoriaDao caDao = new CategoriaDao();
	Venda ve = new Venda();
	static VendaDao veDao = new VendaDao();
	ProdutoVendaDao pvDao = new ProdutoVendaDao();
	
	TelaCadastroCliente telaCadastroCliente = new TelaCadastroCliente();
	
	
	public static JComboBox cbPesquisaCliente = new JComboBox<>(new Object[] {""});
	public static JScrollPane scrollProdutosVenda;
	public static int tamanhoScrollProdutosVenda;
	static JTextArea taNota;
	int idVendaAtual;
	int idClienteAtual;
	public static ArrayList<ProdutoVenda> produtosParaComprar = new ArrayList<>();
	public static JCheckBox cboxCpf;
	public static double descontoGeral = Preferencias.getDesconto();
	
	public static JPanel panelPrincipal;
	
	public static String nomeClienteAtual;
	public static String dataCompraAtual;
	public static String cpfAtual;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCaixa frame = new TelaCaixa(1);
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
	public TelaCaixa(int idUsuario) {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setName("Tela Caixa");
		Dimension tela = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, tela.width, tela.height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, corGeral));
		Rectangle area = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		setMaximizedBounds(area);
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
		
		ImageIcon imageIconLogo = new ImageIcon(Preferencias.getImagem());
		Image imagemLogo = imageIconLogo.getImage().getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH);
		setIconImage(imagemLogo);
		
		setExtendedState(MAXIMIZED_BOTH);
		contentPane.setLayout(null);
		
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
	    lblPesquisarClientes.setBounds(45, 57, 173, 32);
	    lblPesquisarClientes.setFont(new Font("Roboto", Font.PLAIN, 18));
	    contentPane.add(lblPesquisarClientes);
	    
	    JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
	    btnCadastrarCliente.setBounds(410, 90, 162, 45);
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
	    contentPane.add(btnCadastrarCliente);
	    
	    JButton btnInformacoes = new JButton("Informa\u00E7\u00F5es");
	    btnInformacoes.setBounds(45, 159, 162, 45);
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
	    		String nomeCliente = cbPesquisaCliente.getSelectedItem().toString();
	    		 
	    		
	    		try {
	    			Object[] dadosCliente = clDao.buscarCliente(nomeCliente);
	    			
	    			Cliente cliente = new Cliente();
	    			
		    		cliente.setId((int) dadosCliente[0]);
		    		cliente.setNome(dadosCliente[1].toString());
		    		cliente.setCpf(dadosCliente[2].toString());
		    		cliente.setTelefone(dadosCliente[3].toString());
		    		cliente.setCelular(dadosCliente[4].toString());
		    		cliente.setDataCadastro((Timestamp) dadosCliente[5]);
		    		cliente.setRua(dadosCliente[6].toString());
		    		cliente.setBairro(dadosCliente[7].toString());
		    		cliente.setCidade(dadosCliente[8].toString());
		    		cliente.setEstado(dadosCliente[9].toString());
		    		
		    		TelaInfoCliente telaInfoCliente = new TelaInfoCliente(cliente);
		    		telaInfoCliente.setVisible(true);
	    		}catch(Exception e) {
	    			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao visualizar informações", JOptionPane.ERROR_MESSAGE);
	    		}
	    		
	    	}
	    });
	    btnInformacoes.setForeground(corTexto);
	    btnInformacoes.setFont(new Font("Roboto", Font.PLAIN, 18));
	    btnInformacoes.setBorder(null);
	    btnInformacoes.setBackground(new Color(200, 200, 200));
	    contentPane.add(btnInformacoes);
	    
	    JButton btnEditar = new JButton("Editar");
	    btnEditar.setBounds(229, 159, 162, 45);
	    btnEditar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		String nomeCliente = cbPesquisaCliente.getSelectedItem().toString();
	    		 
	    		
	    		try {
	    			Object[] dadosCliente = clDao.buscarCliente(nomeCliente);
	    			
	    			Cliente cliente = new Cliente();
	    			
		    		cliente.setId((int) dadosCliente[0]);
		    		cliente.setNome(dadosCliente[1].toString());
		    		cliente.setCpf(dadosCliente[2].toString());
		    		cliente.setTelefone(dadosCliente[3].toString());
		    		cliente.setCelular(dadosCliente[4].toString());
		    		cliente.setDataCadastro((Timestamp) dadosCliente[5]);
		    		cliente.setRua(dadosCliente[6].toString());
		    		cliente.setBairro(dadosCliente[7].toString());
		    		cliente.setCidade(dadosCliente[8].toString());
		    		cliente.setEstado(dadosCliente[9].toString());
		    		
		    		TelaEditarCliente telaEditarCliente = new TelaEditarCliente(cliente);
		    		telaEditarCliente.setVisible(true);
	    		}catch(Exception e) {
	    			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao alterar", JOptionPane.ERROR_MESSAGE);
	    		}
	    		
	    		
	    	}
	    });
	    btnEditar.setEnabled(false);
	    btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(btnEditar.isEnabled()) {
					btnEditar.setBackground(corSecundaria);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(btnEditar.isEnabled()) {
					btnEditar.setBackground(corGeral);
				}
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(btnInformacoes.isEnabled()) {
					btnEditar.setBackground(corTerciaria);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(btnEditar.isEnabled()) {
					btnEditar.setBackground(corGeral);
				}
			}
		});
	    btnEditar.setForeground(corTexto);
	    btnEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
	    btnEditar.setBorder(null);
	    btnEditar.setBackground(new Color(200, 200, 200));
	    contentPane.add(btnEditar);
	    
	    JButton btnCompras = new JButton("Compras");
	    btnCompras.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		//Tela das compras
	    	}
	    });
	    btnCompras.setBounds(410, 159, 162, 45);
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
	    contentPane.add(btnCompras);
	    
	    cbPesquisaCliente = new JComboBox(new Object[]{""});
	    cbPesquisaCliente.setBounds(45, 90, 346, 50);
	    cbPesquisaCliente.addPopupMenuListener(new PopupMenuListener() {
	    	public void popupMenuCanceled(PopupMenuEvent arg0) {
	    	}
	    	public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
	    		if(cbPesquisaCliente.getSelectedItem() != null) {
		    		if(cbPesquisaCliente.getSelectedItem().equals("")) {
		    			btnEditar.setEnabled(false);
						btnCompras.setEnabled(false);
						btnInformacoes.setEnabled(false);
						
						btnEditar.setBackground(new Color(200, 200, 200));
						btnCompras.setBackground(new Color(200, 200, 200));
						btnInformacoes.setBackground(new Color(200, 200, 200));
					}else {
						btnEditar.setEnabled(true);
						btnCompras.setEnabled(true);
						btnInformacoes.setEnabled(true);
						
						btnEditar.setBackground(corGeral);
						btnCompras.setBackground(corGeral);
						btnInformacoes.setBackground(corGeral);
					}
	    		}
	    		
	    	}
	    	public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
	    		atualizarCbPesquisa();
	    	}
	    });
		cbPesquisaCliente.setFont(new Font("Roboto", Font.PLAIN, 18));
		cbPesquisaCliente.setForeground(corTexto);
	    AutoCompleteDecorator.decorate(cbPesquisaCliente);
	    contentPane.add(cbPesquisaCliente);
	    
	    cboxCpf = new JCheckBox("CPF na nota");
	    cboxCpf.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		if(cboxCpf.isSelected()) {
    				atualizarNotaFiscal(nomeClienteAtual, dataCompraAtual, cpfAtual, String.valueOf(descontoGeral));
    			}else {
    				atualizarNotaFiscal(nomeClienteAtual, dataCompraAtual, null, String.valueOf(descontoGeral));
    			}
	    	}
	    });
	    cboxCpf.setForeground(new Color(25, 30, 35));
	    cboxCpf.setFont(new Font("Roboto", Font.PLAIN, 28));
	    cboxCpf.setBackground(Color.WHITE);
	    cboxCpf.setBounds(1461, 821, 402, 45);
	    contentPane.add(cboxCpf);
	    
	    
	    JSeparator spCliente = new JSeparator();
	    spCliente.setBounds(35, 217, 547, 2);
	    spCliente.setBackground(corSeparador);
	    contentPane.add(spCliente);

		JSeparator spCodigoBarras = new JSeparator();
		spCodigoBarras.setBounds(45, 982, 700, 2);
	    spCodigoBarras.setBackground(corSeparador);
	    contentPane.add(spCodigoBarras);
	    
	    JTextField tfCodigoBarras = new JTextField();
	    tfCodigoBarras.setBounds(45, 900, 700, 82);
	    tfCodigoBarras.addFocusListener(new FocusAdapter() {
	    	@Override
	    	public void focusGained(FocusEvent arg0) {
	    		spCodigoBarras.setBackground(corGeral);
	    	}
	    	@Override
	    	public void focusLost(FocusEvent arg0) {
	    		spCodigoBarras.setBackground(corSeparador);
	    	}
	    });
	    tfCodigoBarras.addKeyListener(new KeyAdapter (){
	        public void keyTyped(KeyEvent e) {
	          char c = e.getKeyChar();
	          if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
	            e.consume();
	          }
	        }
	      });
		tfCodigoBarras.setForeground(corTexto);
		tfCodigoBarras.setFont(new Font("Roboto", Font.PLAIN, 40));
		tfCodigoBarras.setBorder(null);
		tfCodigoBarras.setBackground(contentPane.getBackground());
		contentPane.add(tfCodigoBarras);
	    
	    JLabel lblCodigoDeBarras = new JLabel("C\u00F3digo de barras do produto");
	    lblCodigoDeBarras.setBounds(45, 865, 436, 45);
	    lblCodigoDeBarras.setFont(new Font("Roboto", Font.PLAIN, 20));
	    lblCodigoDeBarras.setForeground(corTexto);
	    contentPane.add(lblCodigoDeBarras);
	    
	    
	    JSeparator spQuantidade = new JSeparator();
	    spQuantidade.setBounds(780, 982, 100, 2);
	    spQuantidade.setBackground(corSeparador);
	    contentPane.add(spQuantidade);
	    
	    JTextField tfQuantidade = new JTextField();
	    tfQuantidade.setBounds(780, 900, 100, 82);
	    tfQuantidade.addFocusListener(new FocusAdapter() {
	    	@Override
	    	public void focusGained(FocusEvent arg0) {
	    		spQuantidade.setBackground(corGeral);
	    	}
	    	@Override
	    	public void focusLost(FocusEvent arg0) {
	    		spQuantidade.setBackground(corSeparador);
	    	}
	    });
	    tfQuantidade.addKeyListener(new KeyAdapter (){
	        public void keyTyped(KeyEvent e) {
	          char c = e.getKeyChar();
	          if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
	            e.consume();
	          }
	        }
	      });
		tfQuantidade.setText("1");
		tfQuantidade.setForeground(corTexto);
		tfQuantidade.setFont(new Font("Roboto", Font.PLAIN, 40));
		tfQuantidade.setBorder(null);
		tfQuantidade.setBackground(contentPane.getBackground());
		contentPane.add(tfQuantidade);
	    
	    JLabel lblQuantidade = new JLabel("Quantidade");
	    lblQuantidade.setBounds(780, 865, 436, 45);
	    lblQuantidade.setFont(new Font("Roboto", Font.PLAIN, 20));
	    lblQuantidade.setForeground(corTexto);
	    contentPane.add(lblQuantidade);
	    
	    scrollProdutosVenda = new JScrollPane();
	    scrollProdutosVenda.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollProdutosVenda.getVerticalScrollBar().setUnitIncrement(10);
	    scrollProdutosVenda.setBounds(45, 752, 1098, 100);
	    contentPane.add(scrollProdutosVenda);
	    
	   
	    
	    panelPrincipal = new JPanel();
	    panelPrincipal.addContainerListener(new ContainerAdapter() {
	    	@Override
	    	public void componentAdded(ContainerEvent arg0) {
	    		tamanhoScrollProdutosVenda = 0;
	    		for(int i = 0; i < panelPrincipal.countComponents(); i++) {
	    	    	if(tamanhoScrollProdutosVenda < 500) {
	    	    		tamanhoScrollProdutosVenda += 100;
	    	    	}
	    		}
	    		atualizarTamanhoScroll();
	    	}
	    });
	    panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
	    scrollProdutosVenda.setViewportView(panelPrincipal);
	    scrollProdutosVenda.setBounds(45, 850 - tamanhoScrollProdutosVenda, 1098, tamanhoScrollProdutosVenda);
	    
	    JLabel lblCodigoInvalido = new JLabel("C\u00F3digo de barras inv\u00E1lido");
	    lblCodigoInvalido.setVisible(false);
	    lblCodigoInvalido.setFont(new Font("Roboto", Font.PLAIN, 14));
	    lblCodigoInvalido.setForeground(corVermelho);
	    lblCodigoInvalido.setBounds(45, 850, 321, 16);
	    contentPane.add(lblCodigoInvalido);
	    
	    JLabel lblQuantidadeInvalida = new JLabel("Quantidade inv\u00E1lida");
	    lblQuantidadeInvalida.setVisible(false);
	    lblQuantidadeInvalida.setForeground(corVermelho);
	    lblQuantidadeInvalida.setFont(new Font("Roboto", Font.PLAIN, 14));
	    lblQuantidadeInvalida.setBounds(780, 850, 162, 16);
	    contentPane.add(lblQuantidadeInvalida);
	    
	    JLabel lblClienteInvalido = new JLabel("Cliente não selecionado");
	    lblClienteInvalido.setVisible(false);
	    lblClienteInvalido.setForeground(corVermelho);
	    lblClienteInvalido.setFont(new Font("Roboto", Font.PLAIN, 14));
	    lblClienteInvalido.setBounds(45, 45, 162, 16);
	    contentPane.add(lblClienteInvalido);
	    
	   /* JScrollPane scrollPaneNotaFiscal = new JScrollPane();
	    scrollPaneNotaFiscal.setBounds(1461, 57, 402, 793);
	    scrollPaneNotaFiscal.setOpaque(false);
	    scrollPaneNotaFiscal.setBackground(Color.WHITE);
	    scrollPaneNotaFiscal.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	    contentPane.add(scrollPaneNotaFiscal);
	    
	    JPanel panelNota = new JPanel();
	    panelNota.setBounds(0, 0, 402, 793);
	    panelNota.setSize(new Dimension(402, 793));
	    panelNota.setBackground(Color.WHITE);
	    scrollPaneNotaFiscal.setOpaque(false);
	    panelNota.setLayout(null);
	    scrollPaneNotaFiscal.add(panelNota);*/

	    taNota = new JTextArea();
	    taNota.setEditable(false);
	    taNota.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	    //taNota.setForeground(corTexto);
	    taNota.setFont(new Font("Roboto", Font.PLAIN, 20));
	    taNota.setBackground(Color.WHITE);
	    taNota.setBounds(1461, 57, 402, 742);
	    contentPane.add(taNota);
	    
	    JLabel lblSubTotalTitulo = new JLabel("Subtotal:");
	    lblSubTotalTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	    lblSubTotalTitulo.setForeground(corTexto);
	    lblSubTotalTitulo.setFont(new Font("Roboto", Font.PLAIN, 24));
	    lblSubTotalTitulo.setBounds(45, 246, 366, 45);
	    contentPane.add(lblSubTotalTitulo);
	    
	    JLabel lblSubTotal = new JLabel("R$0.00");
	    lblSubTotal.setHorizontalAlignment(SwingConstants.CENTER);
	    lblSubTotal.setForeground(corTexto);
	    lblSubTotal.setFont(new Font("Roboto", Font.PLAIN, 40));
	    lblSubTotal.setBounds(45, 292, 366, 68);
	    contentPane.add(lblSubTotal);
	    
	    JLabel lblDescontoTitulo = new JLabel("Desconto:");
	    lblDescontoTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	    lblDescontoTitulo.setForeground(corTexto);
	    lblDescontoTitulo.setFont(new Font("Roboto", Font.PLAIN, 24));
	    lblDescontoTitulo.setBounds(411, 246, 366, 45);
	    contentPane.add(lblDescontoTitulo);
	    
	    JLabel lblDesconto = new JLabel(descontoGeral + "%");
	    lblDesconto.setHorizontalAlignment(SwingConstants.CENTER);
	    lblDesconto.setForeground(corTexto);
	    lblDesconto.setFont(new Font("Roboto", Font.PLAIN, 40));
	    lblDesconto.setBounds(411, 292, 366, 68);
	    contentPane.add(lblDesconto);
	    
	    JLabel lblTotalTitulo = new JLabel("Total:");
	    lblTotalTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTotalTitulo.setForeground(corTexto);
	    lblTotalTitulo.setFont(new Font("Roboto", Font.PLAIN, 24));
	    lblTotalTitulo.setBounds(777, 246, 366, 45);
	    contentPane.add(lblTotalTitulo);
	    
	    JLabel lblTotal = new JLabel("R$0.00");
	    lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTotal.setForeground(corTexto);
	    lblTotal.setFont(new Font("Roboto", Font.PLAIN, 40));
	    lblTotal.setBounds(777, 292, 366, 68);
	    contentPane.add(lblTotal);

	    
	    JButton btnAdicionarProduto = new JButton("Adicionar Produto");
	    btnAdicionarProduto.setBounds(950, 915, 195, 68);
	    btnAdicionarProduto.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		String codigoBarras = tfCodigoBarras.getText();
	    		int quantidade = Integer.parseInt(tfQuantidade.getText());
	    		
	    		boolean codigoBarrasEstaCorreto = codigoBarras.length() < 100 && codigoBarras.length() > 1;
	    		boolean quantidadeEstaCorreta = quantidade > 0;
	    		
	    		if(!codigoBarrasEstaCorreto) {
	    			lblCodigoInvalido.setText("C\u00F3digo de barras inv\u00E1lido");
	    			lblCodigoInvalido.setVisible(true);
	    			spCodigoBarras.setBackground(corVermelho);
	    		}else {
	    			lblCodigoInvalido.setVisible(false);
	    			spCodigoBarras.setBackground(corSeparador);
	    		}
	    		
	    		if(!quantidadeEstaCorreta) {
	    			lblQuantidadeInvalida.setVisible(true);
	    			spQuantidade.setBackground(corVermelho);
	    		}else {
	    			lblQuantidadeInvalida.setVisible(false);
	    			spQuantidade.setBackground(corSeparador);
	    		}
	    		
	    		
	    		if(codigoBarrasEstaCorreto && quantidadeEstaCorreta) {
	    			lblCodigoInvalido.setVisible(false);
	    			lblClienteInvalido.setVisible(false);
	    			Produto produto = new Produto();
		    		try {
		    			Object[] dadosProduto = prDao.buscarProduto(codigoBarras);
	
		    			boolean vazio = true;
		    			for(Object o : dadosProduto) {
		    				if(o != null) {
		    					vazio = false;
		    					break;
		    				}
		    			}
		    			if(cbPesquisaCliente.getSelectedIndex() > 0) {
		    				cbPesquisaCliente.setEnabled(false);
		    				
			    			if(!vazio) {
			    				produto.setId((int) dadosProduto[0]);
				    			produto.setNome(String.valueOf(dadosProduto[1]));
				    			produto.setValorUnitario((double) dadosProduto[2]);
				    			produto.setTamanho(String.valueOf(dadosProduto[3]));
				    			produto.setIdCategoria((int) dadosProduto[4]);
				    			produto.setIdFornecedor((int) dadosProduto[5]);
				    			produto.setCodigoBarras(String.valueOf(dadosProduto[6]));
				    			produto.setStatus((int) dadosProduto[7]);
				    			produto.setQuantidade((int) dadosProduto[8]);
				    			
			    				if(produto.getStatus() == 1) {
			    					if(produto.getQuantidade() >= quantidade) {
			    						Object[] dadosCliente = clDao.buscarCliente(String.valueOf(cbPesquisaCliente.getSelectedItem()));
			    						Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
			    						if(produtosParaComprar.isEmpty()) {
			    							veDao.AtualizarID();
			    							
			    							idClienteAtual = (int) dadosCliente[0];
			    							idVendaAtual = veDao.RetornarProximoCodigoVenda();
			    							cpfAtual = (String) dadosCliente[2];
			    							ve.setId(idVendaAtual);
			    							ve.setIdUsuario(idUsuario);
			    							ve.setIdCliente(idClienteAtual);
			    							ve.setStatus(0);
			    							ve.setData(dataDeHoje);
			    							ve.setStatusPagamento(0);
			    					
			    							veDao.CadastrarVenda(ve);
			    						}
			    		    			
			    		    			boolean pvJaExistente = false;
			    		    			
			    		    			for(int i = 0; i < produtosParaComprar.size(); i++) {
			    		    				int idProdutoJaComprado = produtosParaComprar.get(i).getIdProduto();
			    		    				
			    		    				if(produto.getId() == idProdutoJaComprado) {			    		    			
					    		    			//int idProdutoVendaComprado = produtosParaComprar.get(i).getId();
					    		    			int quantidadeAdicional = produtosParaComprar.get(i).getQuantidade() + quantidade;
					    		    			//pvDao.AlterarQuantidadeProdutoVenda(idProdutoVendaComprado, quantidadeAdicional);
					    		    			
			    		    					produtosParaComprar.get(i).setQuantidade(quantidadeAdicional);
			    		    					
			    		    					int q = produtosParaComprar.get(i).getQuantidade();
			    		    					double v = (double) q * produtosParaComprar.get(i).getValorUnitario();
			    		    					((BlocoProdutoVenda) panelPrincipal.getComponent(i)).setQuantidade(q, v);
			    		    					pvJaExistente = true;
			    		    					break;
			    		    				}
			    		    			}
			    		    			
			    		    			if(!pvJaExistente) {
			    		    				ProdutoVenda pv = new ProdutoVenda();
			    		    				pv.setId(pvDao.RetornarProximoCodigoProdutoVenda());
				    		    			pv.setIdProduto(produto.getId());
				    		    			pv.setIdVenda(ve.getId());
				    		    			pv.setQuantidade(quantidade);
				    		    			
				    		    			int idCategoria = produto.getIdCategoria();
				    		    			double desconto = caDao.buscarDesconto(idCategoria);
				    		    			
				    		    			double valor = produto.getValorUnitario() - (produto.getValorUnitario() * desconto / 100);
				    		    			pv.setValorUnitario(valor);
				    		    			
			    		    				
			    		    				//pvDao.CadastrarProdutoVenda(pv);
			    		    				produtosParaComprar.add(pv);
			    		    				
			    		    				BlocoProdutoVenda bloco = new BlocoProdutoVenda(produto.getNome(), pv.getQuantidade(), pv.getValorUnitario() * pv.getQuantidade(), produtosParaComprar.size() - 1);
				    		    			panelPrincipal.add(bloco);
			    		    			}
			    		    			produto.setQuantidade(produto.getQuantidade() - quantidade);
			    		    			
			    		    			nomeClienteAtual = String.valueOf(dadosCliente[1]);
			    		    			dataCompraAtual = dataDeHoje.toString();
			    		    			if(cboxCpf.isSelected()) {
			    		    				atualizarNotaFiscal(nomeClienteAtual, dataCompraAtual, cpfAtual, String.valueOf(descontoGeral));
			    		    			}else {
			    		    				atualizarNotaFiscal(nomeClienteAtual, dataCompraAtual, null, String.valueOf(descontoGeral));
			    		    			}
			    		    			
			    		    			
			    		    			double totalAPagar = 0;
			    		    			
			    		    			for(ProdutoVenda x : produtosParaComprar) {
			    		    				totalAPagar += x.getQuantidade() * x.getValorUnitario();
			    		    			}
			    						lblSubTotal.setText("R$" + totalAPagar);
			    						double totalComDesconto = totalAPagar - (totalAPagar * descontoGeral / 100);
			    						totalComDesconto = (totalComDesconto < 0) ? 0 : totalComDesconto;
			    						lblTotal.setText("R$" + totalComDesconto);
			    						
			    		    			scrollProdutosVenda.getVerticalScrollBar().setValue(scrollProdutosVenda.getVerticalScrollBar().getMaximum());
			    		    			panelPrincipal.revalidate();
			    		    			panelPrincipal.repaint();
			    					}else {
			    						lblCodigoInvalido.setText("Produto não suficiente para esta quantia");
					    				lblCodigoInvalido.setVisible(true);
			    					}
			    				}else {
			    					lblCodigoInvalido.setText("Produto não disponível");
				    				lblCodigoInvalido.setVisible(true);
			    				}
			    			}else {
			    				lblCodigoInvalido.setText("Produto inexistente");
			    				lblCodigoInvalido.setVisible(true);
			    			}
		    			}else {
		    				lblClienteInvalido.setVisible(true);
		    			}
		    			
		    		}catch(Exception e) {
		    			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		    		}
	    		}
	    	}
	    });
	    btnAdicionarProduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    btnAdicionarProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnAdicionarProduto.setBackground(corSecundaria);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAdicionarProduto.setBackground(corGeral);
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				btnAdicionarProduto.setBackground(corTerciaria);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnAdicionarProduto.setBackground(corGeral);
			}
		});
	    btnAdicionarProduto.setForeground(corTexto);
	    btnAdicionarProduto.setFont(new Font("Roboto", Font.PLAIN, 20));
	    btnAdicionarProduto.setBorder(null);
	    btnAdicionarProduto.setBackground(corGeral);
	    contentPane.add(btnAdicionarProduto);
	    
	    JCheckBox cboxPrazo = new JCheckBox("Venda \u00E0 prazo");
	    cboxPrazo.setBackground(Color.WHITE);
	    cboxPrazo.setForeground(corTexto);
	    cboxPrazo.setFont(new Font("Roboto", Font.PLAIN, 28));
	    cboxPrazo.setBounds(1461, 865, 402, 45);
	    contentPane.add(cboxPrazo);
	    
	    JButton btnCancelarVenda = new JButton("Cancelar Venda");
	    btnCancelarVenda.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		if(produtosParaComprar.isEmpty()) {
	    			return;
	    		}
	    		ve.setId(idVendaAtual);
				ve.setIdUsuario(idUsuario);
				ve.setIdCliente(idClienteAtual);
				ve.setStatus(0);
				ve.setData(ve.getData());
				ve.setStatusPagamento(0);
				
				try {
					List<Object> dados = pvDao.buscarProdutosVenda(ve.getId());
					
					veDao.deletarVenda(ve);
					veDao.AtualizarID();
					
					panelPrincipal.removeAll();
					tamanhoScrollProdutosVenda = 0;
					panelPrincipal.setSize(1098, tamanhoScrollProdutosVenda);
					scrollProdutosVenda.setSize(1098, tamanhoScrollProdutosVenda);
					cbPesquisaCliente.setEnabled(true);
					produtosParaComprar.clear();

					atualizarNotaFiscal("Cliente", "", null, String.valueOf(descontoGeral));
					
					lblSubTotal.setText("R$0.00");
					lblTotal.setText("R$0.00");
					
					revalidate();
					repaint();
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
	    	}
	    });
	    btnCancelarVenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    btnCancelarVenda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnCancelarVenda.setBackground(corSecundaria);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCancelarVenda.setBackground(corGeral);
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				btnCancelarVenda.setBackground(corTerciaria);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnCancelarVenda.setBackground(corGeral);
			}
		});
	    btnCancelarVenda.setForeground(corTexto);
	    btnCancelarVenda.setFont(new Font("Roboto", Font.PLAIN, 20));
	    btnCancelarVenda.setBorder(null);
	    btnCancelarVenda.setBackground(corGeral);
	    btnCancelarVenda.setBounds(1461, 916, 195, 68);
	    contentPane.add(btnCancelarVenda);
	    
	    JButton btnFinalizarVenda = new JButton("Finalizar Venda");
	    btnFinalizarVenda.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		if(produtosParaComprar.isEmpty()) {
	    			return;
	    		}
	    		
	    		ve.setId(idVendaAtual);
				ve.setIdUsuario(idUsuario);
				ve.setIdCliente(idClienteAtual);
				ve.setStatus(1);
				ve.setData(ve.getData());
				
				if(cboxPrazo.isSelected()) {
					ve.setStatusPagamento(0);
					
					
				}else {	
					ve.setStatusPagamento(1);
				}
				
				try {
					for(int i = 0; i < produtosParaComprar.size(); i++) {
						pvDao.CadastrarProdutoVenda(produtosParaComprar.get(i));
						
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
					if(!cboxPrazo.isSelected()) {
						taNota.print();
					}
					
					panelPrincipal.removeAll();
					tamanhoScrollProdutosVenda = 0;
					panelPrincipal.setSize(1098, tamanhoScrollProdutosVenda);
					scrollProdutosVenda.setSize(1098, tamanhoScrollProdutosVenda);
					cbPesquisaCliente.setEnabled(true);
					produtosParaComprar.clear();

					atualizarNotaFiscal("Cliente", "", null, String.valueOf(descontoGeral));
					
					revalidate();
					repaint();
					
	    		}catch (Exception e) {
	    			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	    		}
	    	}
	    });
	    btnFinalizarVenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    btnFinalizarVenda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnFinalizarVenda.setBackground(corSecundaria);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnFinalizarVenda.setBackground(corGeral);
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				btnFinalizarVenda.setBackground(corTerciaria);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnFinalizarVenda.setBackground(corGeral);
			}
		});
	    btnFinalizarVenda.setForeground(corTexto);
	    btnFinalizarVenda.setFont(new Font("Roboto", Font.PLAIN, 20));
	    btnFinalizarVenda.setBorder(null);
	    btnFinalizarVenda.setBackground(corGeral);
	    btnFinalizarVenda.setBounds(1668, 916, 195, 68);
	    contentPane.add(btnFinalizarVenda);   
	    
	    setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{cbPesquisaCliente, btnCadastrarCliente, btnInformacoes, btnEditar, btnCompras, tfCodigoBarras, tfQuantidade, btnAdicionarProduto, btnCancelarVenda, btnFinalizarVenda}));
	    
	    atualizarNotaFiscal("Cliente", "", null, String.valueOf(descontoGeral));
	    
	}
	
	public static void atualizarCbPesquisa() {
		List<Object> nomes = new ArrayList<>();	    		
		try {
			nomes = clDao.buscarNomes();
			
			ArrayList<String> nomesTxt = new ArrayList<>();
			for(Object n : nomes) {
				nomesTxt.add(String.valueOf(n));
			}
			Collections.sort(nomesTxt);
			
			cbPesquisaCliente.removeAllItems();
			cbPesquisaCliente.addItem("");
			
			for(Object n : nomesTxt) {
				cbPesquisaCliente.addItem(n);
			}
			
			cbPesquisaCliente.setSelectedIndex(1);
		}catch(Exception e) { }
	}
	
	public static void atualizarNotaFiscal(String nomeCliente, String dataCompra, String cpfCliente, String desconto) {
		try {
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
		}
	}
	
	public static void atualizarTamanhoScroll() {
		scrollProdutosVenda.setBounds(45, 850 - tamanhoScrollProdutosVenda, 1098, tamanhoScrollProdutosVenda);
	}
}
