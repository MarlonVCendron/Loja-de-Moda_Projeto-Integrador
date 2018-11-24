package br.edu.ifcvideira.Jframes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.text.MaskFormatter;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import br.edu.ifcvideira.Classes.Cliente;
import br.edu.ifcvideira.Classes.Venda;
import br.edu.ifcvideira.DAOs.ClienteDao;
import br.edu.ifcvideira.DAOs.ProdutoVendaDao;
import br.edu.ifcvideira.DAOs.VendaDao;
import br.edu.ifcvideira.utils.BlocoProdutoVenda;
import br.edu.ifcvideira.utils.BlocoVendaFiado;
import br.edu.ifcvideira.utils.BlocoVendaPaga;
import br.edu.ifcvideira.utils.Cor;
import br.edu.ifcvideira.utils.Preferencias;
import javax.swing.JScrollPane;
public class TelaConsultaDeVendas extends JFrame {

	private JPanel contentPane;
	
	public static JPanel panelFiado;
	public static JPanel panelPaga;
	
	public static JLabel lblLogo;
	
	public static Cliente cl = new Cliente();
	public static ClienteDao daoCl = new ClienteDao();
	
	public static Venda ve = new Venda();
	public static VendaDao veDao = new VendaDao();
	
	Color corTexto = new Color(75, 80, 85);
	Color corGeral = new Color(Preferencias.getR(), Preferencias.getG(), Preferencias.getB());
	Color corSecundaria = Cor.corMaisClara(corGeral, 0.2f);
	Color corTerciaria = Cor.corMaisClara(corGeral, 0.4f);
	Color corSeparador = new Color(176, 176, 176);
	Color corVermelho = new Color (230, 20, 20);
	
	Point posMouseInicial;
	
	ImageIcon imageIconX = new ImageIcon(TelaLogin.class.getResource("/br/edu/ifcvideira/img/xerro.png"));
	Image imagemX = imageIconX.getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
	private JTextField tfNome;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente cl = new Cliente();
					cl.setNome("Josias");
					Timestamp dataCadastro = new Timestamp(System.currentTimeMillis());
					cl.setDataCadastro(dataCadastro);
					TelaConsultaDeVendas frame = new TelaConsultaDeVendas(cl);
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
	public TelaConsultaDeVendas(Cliente cliente) {
		setName("Tela Consulta De Vendas Cliente");
		Dimension tela = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int largura = 1400;
		int altura = 700;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((tela.width / 2) - (largura / 2), (tela.height / 2) - (altura / 2), largura, altura);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(Color.WHITE);
		setUndecorated(true);
		contentPane.setLayout(null);
		setResizable(false);
		getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, corGeral));
		
		ImageIcon imageIconLogo = new ImageIcon(Preferencias.getImagem());
		Image imagemLogo = imageIconLogo.getImage().getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH);
		setIconImage(imagemLogo);
		
		setTitle(Preferencias.getNomeLoja());
		
		
		//Variáveis que cuidam para que os campos sejam preenchidos adequadamente
		boolean[] camposCorretos = {true, true, true, true, true, true, true};
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 0, largura, 32);
		contentPane.add(panelSuperior);
		panelSuperior.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				posMouseInicial = e.getPoint();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				posMouseInicial = null;
			}
		});
		panelSuperior.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				Point posMouseAtual = arg0.getLocationOnScreen();
				setLocation(posMouseAtual.x - posMouseInicial.x, posMouseAtual.y - posMouseInicial.y);
			}
		});
		panelSuperior.setBackground(new Color(255, 255, 255));
		panelSuperior.setLayout(null);
		
		JButton btnX = new JButton("X");
		btnX.setUI((ButtonUI) BasicButtonUI.createUI(btnX));
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
				dispose();	
			}
		});
		btnX.setBounds(1358, 0, 42, 30);
		btnX.setMaximumSize(new Dimension(80, 50));
		btnX.setFont(new Font("Roboto", Font.PLAIN, 13));
		btnX.setBorder(null);
		panelSuperior.add(btnX);
		
		JButton btnMinimizar = new JButton("-");
		btnMinimizar.setUI((ButtonUI) BasicButtonUI.createUI(btnMinimizar));
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
		btnMinimizar.setBounds(1312, 0, 42, 30);
		panelSuperior.add(btnMinimizar);
		
		
		
		
		
		
		tfNome = new JTextField();
		tfNome.setEditable(false);
		tfNome.setText(cliente.getNome().toString());
		tfNome.setBounds(101, 38, 167, 29);
		tfNome.setForeground(corTexto);
		tfNome.setFont(new Font("Roboto", Font.PLAIN, 14));
		tfNome.setBorder(null);
		tfNome.setBackground(Color.WHITE);
		contentPane.add(tfNome);
		
		JSeparator spNome = new JSeparator();
		spNome.setBounds(101, 67, 167, 2);
		spNome.setBackground(corSeparador);
		contentPane.add(spNome);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(38, 35, 51, 42);
		lblNome.setForeground(new Color(75, 80, 85));
		lblNome.setFont(new Font("Roboto", Font.PLAIN, 15));
		contentPane.add(lblNome);
		
		JTextField tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setText(String.valueOf(cliente.getId()));
		tfId.setBounds(334, 35, 15, 29);
		tfId.setForeground(corTexto);
		tfId.setFont(new Font("Roboto", Font.PLAIN, 14));
		tfId.setBorder(null);
		tfId.setBackground(Color.WHITE);
		contentPane.add(tfId);
		
		JSeparator spId = new JSeparator();
		spId.setBounds(334, 64, 15, 2);
		spId.setBackground(corSeparador);
		contentPane.add(spId);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(304, 32, 37, 42);
		lblId.setForeground(new Color(75, 80, 85));
		lblId.setFont(new Font("Roboto", Font.PLAIN, 15));
		contentPane.add(lblId);
		
		JLabel lblNaoPagas = new JLabel("Vendas n\u00E3o pagas");
		lblNaoPagas.setForeground(corTexto);
		lblNaoPagas.setFont(new Font("Roboto", Font.PLAIN, 24));
		lblNaoPagas.setBounds(36, 115, 345, 55);
		contentPane.add(lblNaoPagas);
		
		JLabel lblPagas = new JLabel("Vendas pagas");
		lblPagas.setForeground(corTexto);
		lblPagas.setFont(new Font("Roboto", Font.PLAIN, 24));
		lblPagas.setBounds(747, 115, 345, 55);
		contentPane.add(lblPagas);
		
		
		
		JScrollPane scrollFiado = new JScrollPane();
		scrollFiado.setBounds(35, 183, 620, 482);
		scrollFiado.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollFiado.getVerticalScrollBar().setUnitIncrement(10);
		contentPane.add(scrollFiado);
		
		JScrollPane scrollPaga = new JScrollPane();
		scrollPaga.setBounds(747, 183, 620, 482);
		scrollPaga.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaga.getVerticalScrollBar().setUnitIncrement(10);
		contentPane.add(scrollPaga);
		
		panelFiado = new JPanel();
		panelFiado.setBackground(Color.WHITE);
		panelFiado.setLayout(new BoxLayout(panelFiado, BoxLayout.Y_AXIS));
		scrollFiado.setViewportView(panelFiado);
		
		panelPaga = new JPanel();
		panelPaga.setBackground(Color.WHITE);
		panelPaga.setLayout(new BoxLayout(panelPaga, BoxLayout.Y_AXIS));
		scrollPaga.setViewportView(panelPaga);
		
		
		
		ArrayList<BlocoVendaPaga> blocosVendaPaga = pegarBlocosVendasPagas(cliente.getId());
		for(BlocoVendaPaga bvp : blocosVendaPaga) {
			panelPaga.add(bvp);
		}
		
		ArrayList<BlocoVendaFiado> blocosVendaFiado = pegarBlocosVendasFiado(cliente.getId());
		for(BlocoVendaFiado bvf : blocosVendaFiado) {
			panelFiado.add(bvf);
		}
	}

	
	boolean camposEstaoCorretos(boolean[] camposCorretos) {
		boolean x = true;
		for(boolean a : camposCorretos) {
			x = x && a;
		}
		return x;
	}
	
	ArrayList<BlocoVendaPaga> pegarBlocosVendasPagas(int idCliente) {
		VendaDao veDao = new VendaDao();
		ArrayList<BlocoVendaPaga> blocos = new ArrayList<>();
		try {
			List<Object> dadosTodasVendas = veDao.buscarVendasCliente(idCliente);
			for(Object o : dadosTodasVendas) {
				Object[] dadosVenda = (Object[]) o;
				
				if((int) dadosVenda[5] != 0) {
					int idVenda = (int) dadosVenda[0];
					int idUsuario = (int) dadosVenda[1];
					Timestamp dataVenda = (Timestamp) dadosVenda[3];
					int desconto = (int) dadosVenda[6];
					
					double valorTotal = 0;
					
					ProdutoVendaDao pvDao = new ProdutoVendaDao();
					List<Object> dadosTodosProdutos = pvDao.buscarProdutosVenda(idVenda);
					for(Object p : dadosTodosProdutos) {
						Object[] dadosProdutoVenda = (Object[]) p;
						
						int quantidade = (int) dadosProdutoVenda[4];
						
						valorTotal += (double) dadosProdutoVenda[3] * quantidade;
					}
					valorTotal -= valorTotal * desconto / 100;
					
					BlocoVendaPaga bvp = new BlocoVendaPaga(idVenda, idUsuario, valorTotal, dataVenda);
					blocos.add(bvp);
				}
			}
			
			
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		return blocos;
	}
	
	ArrayList<BlocoVendaFiado> pegarBlocosVendasFiado(int idCliente) {
		VendaDao veDao = new VendaDao();
		ArrayList<BlocoVendaFiado> blocos = new ArrayList<>();
		try {
			List<Object> dadosTodasVendas = veDao.buscarVendasCliente(idCliente);
			for(Object o : dadosTodasVendas) {
				Object[] dadosVenda = (Object[]) o;
				
				if((int) dadosVenda[5] == 0) {
					int idVenda = (int) dadosVenda[0];
					int idUsuario = (int) dadosVenda[1];
					Timestamp dataVenda = (Timestamp) dadosVenda[3];
					int desconto = (int) dadosVenda[6];
					
					double valorTotal = 0;
					
					ProdutoVendaDao pvDao = new ProdutoVendaDao();
					List<Object> dadosTodosProdutos = pvDao.buscarProdutosVenda(idVenda);
					for(Object p : dadosTodosProdutos) {
						Object[] dadosProdutoVenda = (Object[]) p;
						
						int quantidade = (int) dadosProdutoVenda[4];
						
						valorTotal += (double) dadosProdutoVenda[3] * quantidade;
					}
					valorTotal -= valorTotal * desconto / 100;
					
					BlocoVendaFiado bvf = new BlocoVendaFiado(idVenda, idUsuario, valorTotal, dataVenda);
					blocos.add(bvf);
				}
			}
			
			
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		return blocos;
	}
}
