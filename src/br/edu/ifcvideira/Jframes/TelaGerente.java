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

	JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");

	static JComboBox cbPesquisaCliente = new JComboBox(new Object[] {});
	TelaCadastroCliente telaCadastroCliente = new TelaCadastroCliente();
	TelaCadastroFuncionario telaCadastroFuncionario = new TelaCadastroFuncionario();
	static JComboBox cbPesquisaFun = new JComboBox(new Object[] {});

	Color corTexto = new Color(75, 80, 85);
	Color corGeral = new Color(Preferencias.getR(), Preferencias.getG(), Preferencias.getB());
	Color corSecundaria = Cor.corMaisClara(corGeral, 0.2f);
	Color corTerciaria = Cor.corMaisClara(corGeral, 0.4f);
	Color corVermelho = new Color(230, 20, 20);
	Point posMouseInicial;

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGerente frame = new TelaGerente();
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
	public TelaGerente() {
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent arg0) {

			}
		});

		ImageIcon imageIconLogo = new ImageIcon(Preferencias.getImagem());
		Image imagemLogo = imageIconLogo.getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
		setIconImage(imagemLogo);
		setTitle(Preferencias.getNomeLoja());

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1386, 886);
		painelPrincipal = new JPanel();
		painelPrincipal.setBackground(Color.WHITE);
		painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painelPrincipal);
		painelPrincipal.setLayout(null);

		JPanel btnSuperior = new JPanel();
		btnSuperior.setLayout(null);
		btnSuperior.setBorder(new EmptyBorder(5, 5, 5, 5));
		btnSuperior.setBackground(Color.WHITE);
		btnSuperior.setBounds(0, 0, 1386, 43);
		painelPrincipal.add(btnSuperior);

		JButton btSair = new JButton("X");
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btSair.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {

				btSair.setBackground(corSecundaria);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btSair.setBackground(corGeral);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btSair.setBackground(corTerciaria);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btSair.setBackground(corGeral);

			}

		});

		btSair.setMaximumSize(new Dimension(80, 50));
		btSair.setFont(new Font("Roboto", Font.PLAIN, 13));
		btSair.setBorder(null);
		btSair.setBackground(corGeral);
		btSair.setBounds(1326, 0, 42, 42);
		btnSuperior.add(btSair);

		JButton btMinimizar = new JButton("-");
		btMinimizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);

			}
		});

		btMinimizar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {

				btMinimizar.setBackground(corSecundaria);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btMinimizar.setBackground(corGeral);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btMinimizar.setBackground(corTerciaria);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btMinimizar.setBackground(corGeral);

			}

		});

		btMinimizar.setMaximumSize(new Dimension(80, 50));
		btMinimizar.setFont(new Font("Roboto", Font.PLAIN, 15));
		btMinimizar.setBorder(null);
		btMinimizar.setBackground(corGeral);
		btMinimizar.setBounds(1248, 0, 42, 42);
		btnSuperior.add(btMinimizar);

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
		painelCliente.setBounds(0, 0, 1386, 886);
		painelPrincipal.add(painelCliente);

		JLabel lblPesquisarCliente = new JLabel("Pesquisar Clientes");
		lblPesquisarCliente.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblPesquisarCliente.setBounds(10, 198, 173, 32);
		painelCliente.add(lblPesquisarCliente);

		cbPesquisaCliente.setForeground(new Color(25, 30, 35));
		cbPesquisaCliente.setFont(new Font("Roboto", Font.PLAIN, 18));
		cbPesquisaCliente.setBounds(10, 231, 346, 50);
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
		btnInformacoes.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnInformacoes.setBorder(null);
		btnInformacoes.setBackground(new Color(200, 200, 200));
		btnInformacoes.setBounds(10, 300, 162, 45);
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

		btnEditar.setBounds(194, 300, 162, 45);
		painelCliente.add(btnEditar);

		btnCompras.setForeground(new Color(25, 30, 35));
		btnCompras.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnCompras.setEnabled(false);
		btnCompras.setBorder(null);
		btnCompras.setBackground(SystemColor.scrollbar);
		btnCompras.setBounds(375, 300, 162, 45);
		painelCliente.add(btnCompras);

		btnCadastrarCliente.setBounds(375, 234, 162, 45);
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
		lblPesquisarFuncionrio.setBounds(707, 198, 222, 32);
		painelCliente.add(lblPesquisarFuncionrio);

		cbPesquisaFun.setForeground(new Color(25, 30, 35));
		cbPesquisaFun.setFont(new Font("Roboto", Font.PLAIN, 18));
		cbPesquisaFun.setBounds(707, 231, 346, 50);
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

		btnInfFun.setForeground(new Color(75, 80, 85));
		btnInfFun.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnInfFun.setEnabled(false);
		btnInfFun.setBorder(null);
		btnInfFun.setBackground(SystemColor.scrollbar);
		btnInfFun.setBounds(707, 300, 162, 45);
		painelCliente.add(btnInfFun);
		btnEditarFun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nomeUs = cbPesquisaFun.getSelectedItem().toString();

				try {
					Object[] dadosUsuario = funDao.buscarFun(nomeUs);

					Usuario usuario = new Usuario();

					System.out.println(dadosUsuario[0]);
					System.out.println(dadosUsuario[1]);
					System.out.println(dadosUsuario[2]);
					System.out.println(dadosUsuario[3]);
					System.out.println(dadosUsuario[4]);
					System.out.println(dadosUsuario[5]);
					System.out.println(dadosUsuario[6]);
					System.out.println(dadosUsuario[7]);
					System.out.println(dadosUsuario[8]);
					System.out.println(dadosUsuario[9]);
					System.out.println(dadosUsuario[10]);

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

		btnEditarFun.setForeground(new Color(75, 80, 85));
		btnEditarFun.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnEditarFun.setEnabled(false);
		btnEditarFun.setBorder(null);
		btnEditarFun.setBackground(SystemColor.scrollbar);
		btnEditarFun.setBounds(891, 300, 162, 45);
		painelCliente.add(btnEditarFun);

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

		btnCadastrarFuncionario.setForeground(new Color(75, 80, 85));
		btnCadastrarFuncionario.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnCadastrarFuncionario.setBorder(null);
		btnCadastrarFuncionario.setBackground(new Color(0, 153, 255));
		btnCadastrarFuncionario.setBounds(1072, 234, 162, 45);
		painelCliente.add(btnCadastrarFuncionario);

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