package br.edu.ifcvideira.Jframes;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import br.edu.ifcvideira.Classes.Categoria;
import br.edu.ifcvideira.Classes.Cliente;
import br.edu.ifcvideira.Classes.Fornecedor;
import br.edu.ifcvideira.Classes.Produto;
import br.edu.ifcvideira.Classes.Usuario;
import br.edu.ifcvideira.DAOs.CategoriaDao;
import br.edu.ifcvideira.DAOs.ClienteDao;
import br.edu.ifcvideira.DAOs.FornecedorDao;
import br.edu.ifcvideira.DAOs.ProdutoDao;
import br.edu.ifcvideira.DAOs.UsuarioDao;
import br.edu.ifcvideira.utils.Cor;
import br.edu.ifcvideira.utils.JMoneyField;
import br.edu.ifcvideira.utils.Preferencias;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.print.attribute.standard.MediaSize.Engineering;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextPane;
import javax.swing.JSpinner;

public class TelaGerente extends JFrame {

	private JPanel painelPrincipal;

	Cliente cl = new Cliente();
	static ClienteDao clDao = new ClienteDao();
	static UsuarioDao funDao = new UsuarioDao();
	Usuario fun = new Usuario();

	JButton btnInformacoes = new JButton("Informa\u00E7\u00F5es");

	JButton btnEditar = new JButton("Editar");

	JButton btnCompras = new JButton("Compras");

	JButton btnCadastrarCliente = new JButton("Cadastrar");

	static JComboBox cbPesquisaCliente = new JComboBox(new Object[] {});
	TelaCadastroCliente telaCadastroCliente = new TelaCadastroCliente();
	TelaCadastroFuncionario telaCadastroFuncionario = new TelaCadastroFuncionario();
	static JComboBox cbPesquisaFun = new JComboBox(new Object[] {});

	Color corTexto = new Color(0, 0, 0);
	Color corGeral = new Color(Preferencias.getR(), Preferencias.getG(), Preferencias.getB());
	Color corSecundaria = Cor.corMaisClara(corGeral, 0.2f);
	Color corTerciaria = Cor.corMaisClara(corGeral, 0.4f);
	Color corVermelho = new Color(230, 20, 20);
	Point posMouseInicial;

	
	
	JLabel lblLogo = new JLabel("");

	

	boolean[] camposCorretos = { false, false, false, false, false, false, false };

	boolean[] camposCorretosPro = { false, false, false };

	ImageIcon imageIconX = new ImageIcon(TelaGerente.class.getResource("/br/edu/ifcvideira/img/xerro.png"));
	Image imagemX = imageIconX.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);

	JPanel painelCliente = new JPanel();

	static CategoriaDao catDao = new CategoriaDao();
	Categoria cat = new Categoria();

	ProdutoDao proDao = new ProdutoDao();
	Produto pro = new Produto();

	JButton btnInfFun = new JButton("Informa\u00E7\u00F5es");

	JButton btnEditarFun = new JButton("Editar");

	JButton btnCadastrarFuncionario = new JButton("Cadastrar");
	private final JButton btnTelaEstoque = new JButton("Tela Estoque");
	private final JButton btnConfig = new JButton("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGerente frame = new TelaGerente(null);
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
	public TelaGerente(Usuario usuario) {
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent arg0) {

			}
		});

		ImageIcon imageIconLogo = new ImageIcon(Preferencias.getImagem());
		Image imagemLogo = imageIconLogo.getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
		setIconImage(imagemLogo);
		setTitle(Preferencias.getNomeLoja());
		
		ImageIcon imageConf = new ImageIcon(TelaLogin.class.getResource("/br/edu/ifcvideira/img/engrenagem.png"));
		Image imagemConfig = imageConf.getImage().getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
		

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1386, 886);
		painelPrincipal = new JPanel();
		painelPrincipal.setBackground(Color.WHITE);
		painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painelPrincipal);
		painelPrincipal.setLayout(null);
		


		JPanel panelEsquerda = new JPanel();
		panelEsquerda.setBounds(0, 0, 476, 738);
		painelPrincipal.add(panelEsquerda);
		panelEsquerda.setBackground(corGeral);
		panelEsquerda.setLayout(null);
		
		JPanel panelLogo = new JPanel();
		panelLogo.setOpaque(false);
		panelLogo.setBounds(88, 168, 300, 300);
		panelEsquerda.add(panelLogo);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(imagemLogo));
		panelLogo.add(lblLogo);
		
		JLabel lblConfig = new JLabel("");
		lblConfig.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblConfig.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Configuracoes telaConfig =new Configuracoes();
				telaConfig.setVisible(true);
			}
		});
		lblConfig.setIcon(new ImageIcon (imagemConfig));
		lblConfig.setBounds(428, 692, 48, 46);
		panelEsquerda.add(lblConfig);

		try {
			MaskFormatter tfDescontoCatFormatter = new MaskFormatter("##");
			tfDescontoCatFormatter.setPlaceholderCharacter('0');

		} catch (Exception e) {
		}
		try {
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Locale currentLocale = Locale.getDefault();
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
		otherSymbols.setDecimalSeparator('.');
		otherSymbols.setGroupingSeparator(',');

		DecimalFormat df = new DecimalFormat("0.00", otherSymbols);

///PARTE DE C

		painelCliente.setLayout(null);
		painelCliente.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelCliente.setBackground(Color.WHITE);
		painelCliente.setBounds(0, 0, 1365, 738);
		painelPrincipal.add(painelCliente);

		JLabel lblPesquisarCliente = new JLabel("Pesquisar Clientes");
		lblPesquisarCliente.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblPesquisarCliente.setBounds(722, 119, 173, 32);
		painelCliente.add(lblPesquisarCliente);

		cbPesquisaCliente.setForeground(new Color(25, 30, 35));
		cbPesquisaCliente.setFont(new Font("Roboto", Font.PLAIN, 18));
		cbPesquisaCliente.setBounds(722, 152, 346, 50);
		painelCliente.add(cbPesquisaCliente);
		cbPesquisaCliente.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				if (cbPesquisaCliente.getSelectedItem() != null) {
					if (cbPesquisaCliente.getSelectedItem().equals("")) {
						btnEditar.setEnabled(false);
						btnCompras.setEnabled(false);
						btnInformacoes.setEnabled(false);

						btnEditar.setBackground(new Color(200, 200, 200));
						btnCompras.setBackground(new Color(200, 200, 200));
						btnInformacoes.setBackground(new Color(200, 200, 200));
					} else {
						btnEditar.setEnabled(true);
						btnCompras.setEnabled(true);
						btnInformacoes.setEnabled(true);

						btnEditar.setBackground(corGeral);
						btnCompras.setBackground(corGeral);
						btnInformacoes.setBackground(corGeral);
					}
				} else {
					btnEditar.setEnabled(false);
					btnCompras.setEnabled(false);
					btnInformacoes.setEnabled(false);

					btnEditar.setBackground(new Color(200, 200, 200));
					btnCompras.setBackground(new Color(200, 200, 200));
					btnInformacoes.setBackground(new Color(200, 200, 200));
				}

			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				atualizarCbPesquisa();
			}
		});
		cbPesquisaCliente.setFont(new Font("Roboto", Font.PLAIN, 18));
		cbPesquisaCliente.setForeground(corTexto);
		AutoCompleteDecorator.decorate(cbPesquisaCliente);
		painelCliente.add(cbPesquisaCliente);
		
		
		btnInformacoes.setEnabled(false);
		btnInformacoes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInformacoes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (btnInformacoes.isEnabled()) {
					btnInformacoes.setBackground(corSecundaria);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (btnInformacoes.isEnabled()) {
					btnInformacoes.setBackground(corGeral);
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnInformacoes.isEnabled()) {
					btnInformacoes.setBackground(corTerciaria);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnInformacoes.isEnabled()) {
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
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao visualizar informações",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnInformacoes.setForeground(corTexto);
		btnInformacoes.setUI((ButtonUI) BasicButtonUI.createUI(btnInformacoes));

		btnInformacoes.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnInformacoes.setBorder(null);
		btnInformacoes.setBackground(new Color(200, 200, 200));
		btnInformacoes.setBounds(722, 221, 162, 45);
		painelCliente.add(btnInformacoes);

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
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao alterar", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setUI((ButtonUI) BasicButtonUI.createUI(btnEditar));

		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (btnEditar.isEnabled()) {
					btnEditar.setBackground(corSecundaria);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (btnEditar.isEnabled()) {
					btnEditar.setBackground(corGeral);
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnInformacoes.isEnabled()) {
					btnEditar.setBackground(corTerciaria);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnEditar.isEnabled()) {
					btnEditar.setBackground(corGeral);
				}
			}
		});
		btnEditar.setForeground(corTexto);
		btnEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnEditar.setBorder(null);
		btnEditar.setBackground(new Color(200, 200, 200));

		btnEditar.setBounds(906, 221, 162, 45);
		painelCliente.add(btnEditar);
		 btnCompras.setEnabled(false);
		    btnCompras.setUI((ButtonUI) BasicButtonUI.createUI(btnCompras));
		    btnCompras.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		String nomeCliente = cbPesquisaCliente.getSelectedItem().toString();
		    		 
		    		
		    		try {
		    			Object[] dadosCliente = clDao.buscarCliente(nomeCliente);
		    			
		    			Cliente cliente = new Cliente();
		    			
			    		cliente.setId((int) dadosCliente[0]);
			    		cliente.setNome(dadosCliente[1].toString());
			    		cliente.setDataCadastro((Timestamp) dadosCliente[5]);
			    		
			    		TelaConsultaDeVendas telaConsultaDeVendas = new TelaConsultaDeVendas(cliente);
			    		telaConsultaDeVendas.setVisible(true);
		    		}catch(Exception e) {
		    			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao visualizar informações", JOptionPane.ERROR_MESSAGE);
		    		}
		    	}
		    });
		    btnCompras.setBounds(1087, 221, 162, 45);
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
		    painelCliente.add(btnCompras);
		    

		btnCadastrarCliente.setBounds(1087, 155, 162, 45);
		btnCadastrarCliente.setUI((ButtonUI) BasicButtonUI.createUI(btnCadastrarCliente));

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
		painelCliente.add(btnCadastrarCliente);
		btnCadastrarCliente.setBackground(corGeral);
		btnCadastrarCliente.setBorder(null);
		btnCadastrarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrarCliente.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnCadastrarCliente.setForeground(corTexto);

		JLabel lblPesquisarFuncionrio = new JLabel("Pesquisar Funcion\u00E1rio");
		lblPesquisarFuncionrio.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblPesquisarFuncionrio.setBounds(722, 374, 222, 32);
		painelCliente.add(lblPesquisarFuncionrio);

		cbPesquisaFun.setForeground(new Color(25, 30, 35));
		AutoCompleteDecorator.decorate(cbPesquisaFun);
		cbPesquisaFun.setFont(new Font("Roboto", Font.PLAIN, 18));
		cbPesquisaFun.setBounds(722, 407, 346, 50);
		painelCliente.add(cbPesquisaFun);

		cbPesquisaFun.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				if (cbPesquisaFun.getSelectedItem() != null) {
					if (cbPesquisaFun.getSelectedItem().equals("")) {
						btnEditarFun.setEnabled(false);
						btnInfFun.setEnabled(false);
						

						btnEditarFun.setBackground(new Color(200, 200, 200));
						btnInfFun.setBackground(new Color(200, 200, 200));
					} else {
						btnEditarFun.setEnabled(true);
						btnInfFun.setEnabled(true);

						btnEditarFun.setBackground(corGeral);
						btnInfFun.setBackground(corGeral);
					}
				} else {
					btnEditarFun.setEnabled(false);
					btnInfFun.setEnabled(false);

					btnEditarFun.setBackground(new Color(200, 200, 200));
					btnInfFun.setBackground(new Color(200, 200, 200));
				}

			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				atualizarCbPesquisaFun();
			}
		});

		btnInfFun.setForeground(corTexto);
		btnInfFun.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnInfFun.setUI((ButtonUI) BasicButtonUI.createUI(btnInfFun));
		btnInfFun.setEnabled(false);
		btnInfFun.setBorder(null);
		
		btnInfFun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nomeUs = cbPesquisaFun.getSelectedItem().toString();

				try {
					Object[] dadosUsuario = funDao.buscarFun(nomeUs);

					Usuario usuario = new Usuario();

					usuario.setId((int) dadosUsuario[0]);
					usuario.setTipo((int) dadosUsuario[1]);
					usuario.setNome(dadosUsuario[2].toString());
					usuario.setCpf(dadosUsuario[4].toString());
					usuario.setRg(dadosUsuario[5].toString());
					usuario.setTelefone(dadosUsuario[6].toString());
					usuario.setCelular(dadosUsuario[7].toString());
					usuario.setDataCadastro((Timestamp) dadosUsuario[8]);
					usuario.setStatus((int) dadosUsuario[9]);
					usuario.setEmail(dadosUsuario[10].toString());

					TelaInfoFuncionario telaInfFuncionario = new TelaInfoFuncionario(usuario);
					telaInfFuncionario.setVisible(true);
				} catch (Exception e) {
				}

			}
		});
		btnInfFun.setEnabled(false);
		btnInfFun.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInfFun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (btnInfFun.isEnabled()) {
					btnInfFun.setBackground(corSecundaria);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (btnInfFun.isEnabled()) {
					btnInfFun.setBackground(corGeral);
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnInformacoes.isEnabled()) {
					btnInfFun.setBackground(corTerciaria);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnInfFun.isEnabled()) {
					btnInfFun.setBackground(corGeral);
				}
			}
		});
		
		
		btnInfFun.setBackground(SystemColor.scrollbar);
		btnInfFun.setBounds(722, 476, 162, 45);
		painelCliente.add(btnInfFun);
		btnEditarFun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nomeUs = cbPesquisaFun.getSelectedItem().toString();

				try {
					Object[] dadosUsuario = funDao.buscarFun(nomeUs);

					Usuario usuario = new Usuario();

					usuario.setId((int) dadosUsuario[0]);
					usuario.setTipo((int) dadosUsuario[1]);
					usuario.setNome(dadosUsuario[2].toString());
					usuario.setCpf(dadosUsuario[4].toString());
					usuario.setRg(dadosUsuario[5].toString());
					usuario.setTelefone(dadosUsuario[6].toString());
					usuario.setCelular(dadosUsuario[7].toString());
					usuario.setDataCadastro((Timestamp) dadosUsuario[8]);
					usuario.setStatus((int) dadosUsuario[9]);
					usuario.setEmail(dadosUsuario[10].toString());

					TelaEditarFuncionario telaEditarFuncionario = new TelaEditarFuncionario(usuario);
					telaEditarFuncionario.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao alterar", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnEditarFun.setEnabled(false);
		btnEditarFun.setUI((ButtonUI) BasicButtonUI.createUI(btnEditarFun));

		btnEditarFun.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditarFun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (btnEditarFun.isEnabled()) {
					btnEditarFun.setBackground(corSecundaria);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (btnEditarFun.isEnabled()) {
					btnEditarFun.setBackground(corGeral);
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnInformacoes.isEnabled()) {
					btnEditarFun.setBackground(corTerciaria);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnEditarFun.isEnabled()) {
					btnEditarFun.setBackground(corGeral);
				}
			}
		});
		btnEditarFun.setForeground(corTexto);
		btnEditarFun.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnEditarFun.setBorder(null);
		btnEditarFun.setBackground(new Color(200, 200, 200));

		btnEditarFun.setForeground(corTexto);
		btnEditarFun.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnEditarFun.setEnabled(false);
		btnEditarFun.setBorder(null);
		btnEditarFun.setBackground(SystemColor.scrollbar);
		btnEditarFun.setBounds(906, 476, 162, 45);
		painelCliente.add(btnEditarFun);
		btnCadastrarFuncionario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnCadastrarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaCadastroFuncionario.setVisible(true);
			}
		});
		btnCadastrarFuncionario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnCadastrarFuncionario.setBackground(corSecundaria);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCadastrarFuncionario.setBackground(corGeral);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				btnCadastrarFuncionario.setBackground(corTerciaria);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnCadastrarFuncionario.setBackground(corGeral);
			}
		});

		btnCadastrarFuncionario.setForeground(corTexto);
		btnCadastrarFuncionario.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnCadastrarFuncionario.setBorder(null);
		btnCadastrarFuncionario.setBackground(corGeral);
		btnCadastrarFuncionario.setBounds(1087, 410, 162, 45);
		btnCadastrarFuncionario.setUI((ButtonUI) BasicButtonUI.createUI(btnCadastrarFuncionario));

		painelCliente.add(btnCadastrarFuncionario);
		btnTelaEstoque.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTelaEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaEstoque telaEstoque = new TelaEstoque();
				telaEstoque.setVisible(true);
			}
		});
		
		
		
		btnTelaEstoque.setForeground(corTexto);
		btnTelaEstoque.setFont(new Font("Roboto", Font.PLAIN, 30));
		btnTelaEstoque.setBorder(null);
		btnTelaEstoque.setBackground(corGeral);
		btnTelaEstoque.setUI((ButtonUI) BasicButtonUI.createUI(btnTelaEstoque));

		btnTelaEstoque.setBounds(722, 608, 527, 66);
		
		painelCliente.add(btnTelaEstoque);
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(382, 0, 983, 32);
		painelCliente.add(panelSuperior);
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
				System.exit(0);
			}
		});
		btnX.setBounds(940, 0, 42, 30);
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
		btnMinimizar.setBounds(897, 0, 42, 30);
		panelSuperior.add(btnMinimizar);

		// tela Inicia Maximizada
		Rectangle area = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		setMaximizedBounds(area);
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);

	}

	public static void atualizarCbPesquisa() {
		List<Object> nomes = new ArrayList<>();
		try {
			nomes = clDao.buscarNomes();

			ArrayList<String> nomesTxt = new ArrayList<>();
			for (Object n : nomes) {
				nomesTxt.add(String.valueOf(n));
			}
			Collections.sort(nomesTxt);

			cbPesquisaCliente.removeAllItems();
			cbPesquisaCliente.addItem("");

			for (Object n : nomesTxt) {
				cbPesquisaCliente.addItem(n);
			}

			cbPesquisaCliente.setSelectedIndex(1);
		} catch (Exception e) {
		}
	}

	public static void atualizarCbPesquisaFun() {
		List<Object> nomes = new ArrayList<>();
		try {
			nomes = funDao.buscarNomes();

			ArrayList<String> nomesTxt = new ArrayList<>();
			for (Object n : nomes) {
				nomesTxt.add(String.valueOf(n));
			}
			Collections.sort(nomesTxt);

			cbPesquisaFun.removeAllItems();
			cbPesquisaFun.addItem("");

			for (Object n : nomesTxt) {
				cbPesquisaFun.addItem(n);
			}

			cbPesquisaFun.setSelectedIndex(1);
		} catch (Exception e) {
		}
	}
}