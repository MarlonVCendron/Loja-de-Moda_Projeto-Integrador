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
import br.edu.ifcvideira.Classes.Fornecedor;
import br.edu.ifcvideira.Classes.Produto;
import br.edu.ifcvideira.DAOs.CategoriaDao;
import br.edu.ifcvideira.DAOs.FornecedorDao;
import br.edu.ifcvideira.DAOs.ProdutoDao;
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

public class TelaEstoque extends JFrame {

	private JPanel painelPrincipal;
	private JTextField tfNome;
	private JTextField tfEmail;
	private JTextField tfTelefone;
	private JTextField tfBairro;
	private JTextField tfRua;
	private JTextField tfCidade;
	private JTextField tfId;
	JComboBox cbEstado_1 = new JComboBox();
	public static JComboBox cbCategoriaProd = new JComboBox();

	JSeparator spNomePro = new JSeparator();

	JSeparator spTamanhoPro = new JSeparator();
	JSeparator spCdBarras = new JSeparator();

	private static FornecedorDao fornDao = new FornecedorDao();
	private Fornecedor forn = new Fornecedor();

	Color corTexto = new Color(75, 80, 85);
	Color corGeral = new Color(Preferencias.getR(), Preferencias.getG(), Preferencias.getB());
	Color corSecundaria = Cor.corMaisClara(corGeral, 0.2f);
	Color corTerciaria = Cor.corMaisClara(corGeral, 0.4f);
	Color corVermelho = new Color(230, 20, 20);
	Point posMouseInicial;
	private JTextField tfCNPJ;
	private List<Object> fornecedor = new ArrayList<Object>();
	private List<Object> categoria = new ArrayList<Object>();
	private List<Object> produto = new ArrayList<Object>();

	private JTable tabelaFornecedor;
	private JTable tabelaProdutos;

	JSeparator spNome = new JSeparator();

	JSeparator spCNPJ = new JSeparator();

	JSeparator spId = new JSeparator();
	JSeparator spEmail = new JSeparator();
	JSeparator spTelefone = new JSeparator();
	JSeparator spCidade = new JSeparator();
	JSeparator spBairro = new JSeparator();
	JSeparator spRua = new JSeparator();
	JPanel panelImgCNPJ = new JPanel();
	JPanel panelImgDescCat = new JPanel();
	JSeparator spDesc1Cat = new JSeparator();
	JPanel panelImgNomePro = new JPanel();

	JPanel panelImgTamanho = new JPanel();
	JPanel panelImgNome = new JPanel();
	JPanel panelImgEmail = new JPanel();
	JPanel panelImgTelefone = new JPanel();
	JPanel panelImgCidade = new JPanel();
	JPanel panelImgBairro = new JPanel();
	JPanel panelImgRua = new JPanel();
	JPanel panelImgCdBarras = new JPanel();

	boolean[] camposCorretos = { false, false, false, false, false, false, false };
	JButton btCadastrar = new JButton("Cadastrar");
	JButton btnCategoria = new JButton("Categoria");
	public static JComboBox cbFornecedoresProd = new JComboBox();

	JComboBox cbStatusPro = new JComboBox(new Object[] {});

	boolean[] camposCorretosCat = { false, false };
	boolean[] camposCorretosPro = { false, false, false };

	JButton btnProdutos = new JButton("Produtos");

	ImageIcon imageIconX = new ImageIcon(TelaEstoque.class.getResource("/br/edu/ifcvideira/img/xerro.png"));
	Image imagemX = imageIconX.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
	private JTextField tfBuscaNome;
	private JTextField tfBuscaId;
	JButton btnFornecedor = new JButton("Fornecedor");

	JPanel painelFornecedor = new JPanel();

	JPanel painelCategoria = new JPanel();

	JPanel painelProdutos = new JPanel();

	static CategoriaDao catDao = new CategoriaDao();
	Categoria cat = new Categoria();
	private JTextField tfDescontoCat;
	private JTextField tfIdCat;

	ProdutoDao proDao = new ProdutoDao();
	Produto pro = new Produto();
	private JTable tabelaCategoria;
	JSeparator spIdCat = new JSeparator();
	JSeparator spDescontoCat = new JSeparator();
	JPanel panelImgDescontoCat = new JPanel();
	JButton btCadastrarCat = new JButton("Cadastrar");

	JSpinner spinnerQuant = new JSpinner();

	private JTextField tfBuscaNomeCat;
	private JTextField tfBuscaIdCat;
	private JTextField tfDescricaoCat;
	private JTextField tfIdPro;
	private JTextField tfNomePro;
	private JTextField tfBuscaNomePro;
	private JTextField tfBuscaIdPro;
	private JTextField tfPrecoProd;
	private JTextField tfTamanho;
	private JTextField tfCdBarras;

	JButton btnEditar = new JButton("Editar");

	JButton btnExcluir = new JButton("Excluir");

	JButton btnLimpar = new JButton("Limpar");

	JButton btEditarPro = new JButton("Editar");

	JButton btExcluirPro = new JButton("Excluir");

	JButton btLimparPro = new JButton("Limpar");

	JButton btnEditarCat = new JButton("Editar");

	JButton btnExcluirCat = new JButton("Excluir");

	JButton btnLimparCat = new JButton("Limpar");

	JButton btCadastrarPro = new JButton("Cadastrar");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEstoque frame = new TelaEstoque();
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
	public TelaEstoque() {
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent arg0) {
				deixarCerto();
				clickarFornecedor();

			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1386, 886);
		painelPrincipal = new JPanel();
		painelPrincipal.setBackground(Color.WHITE);
		painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painelPrincipal);
		painelPrincipal.setLayout(null);
		
		Dimension tela = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int largura = 1386;
		int altura = 886;
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds((tela.width / 2) - (largura / 2), (tela.height / 2) - (altura / 2), largura, altura);
		mainPanel.setLayout(null);
		painelPrincipal.add(mainPanel);
		
		//PAINEL SUPERIOR
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 0, 1920, 32);
		painelPrincipal.add(panelSuperior);
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

		JPanel btnSuperior = new JPanel();
		btnSuperior.setLayout(null);
		btnSuperior.setBorder(new EmptyBorder(5, 5, 5, 5));
		btnSuperior.setBackground(Color.WHITE);
		btnSuperior.setBounds(0, 0, 1386, 121);
		mainPanel.add(btnSuperior);
		btnFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickarFornecedor();
			}
		});

		btnFornecedor.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnFornecedor.setBorder(null);
		btnFornecedor.setBackground(corGeral);
		btnFornecedor.setBounds(38, 57, 400, 54);
		btnSuperior.add(btnFornecedor);

		btnCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clickarCategoria();
			}
		});

		btnCategoria.setForeground(corTexto);
		btnCategoria.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnCategoria.setBorder(null);
		btnCategoria.setBackground(Color.LIGHT_GRAY);
		btnCategoria.setBounds(482, 57, 400, 54);
		btnSuperior.add(btnCategoria);
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickarProdutos();
			}
		});

		btnProdutos.setForeground(corTexto);
		btnProdutos.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnProdutos.setBorder(null);
		btnProdutos.setBackground(Color.LIGHT_GRAY);
		btnProdutos.setBounds(927, 57, 400, 54);
		btnSuperior.add(btnProdutos);

		/*JButton btSair = new JButton("X");
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
		btnSuperior.add(btMinimizar);*/

		try {
			MaskFormatter tfDescontoCatFormatter = new MaskFormatter("##");
			tfDescontoCatFormatter.setPlaceholderCharacter('0');

		} catch (Exception e) {
		}

		painelProdutos.setLayout(null);
		painelProdutos.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelProdutos.setBackground(Color.WHITE);
		painelProdutos.setBounds(0, 0, 1386, 886);
		mainPanel.add(painelProdutos);

		JLabel lblNomePro = new JLabel("Nome");
		lblNomePro.setForeground(corTexto);
		lblNomePro.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblNomePro.setBounds(38, 136, 60, 42);
		painelProdutos.add(lblNomePro);

		JLabel lblPrecoPro = new JLabel("Pre\u00E7o");
		lblPrecoPro.setForeground(corTexto);
		lblPrecoPro.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblPrecoPro.setBounds(38, 243, 119, 42);
		painelProdutos.add(lblPrecoPro);

		JLabel lblQuantPro = new JLabel("Quantidade");
		lblQuantPro.setForeground(corTexto);
		lblQuantPro.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblQuantPro.setBounds(640, 136, 119, 42);
		painelProdutos.add(lblQuantPro);

		JLabel label_8 = new JLabel("ID");
		label_8.setForeground(corTexto);
		label_8.setFont(new Font("Roboto", Font.PLAIN, 20));
		label_8.setBounds(1187, 136, 42, 42);
		painelProdutos.add(label_8);

		JLabel lblTamanhoPro = new JLabel("Tamanho");
		lblTamanhoPro.setForeground(corTexto);
		lblTamanhoPro.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblTamanhoPro.setBounds(38, 189, 119, 42);
		painelProdutos.add(lblTamanhoPro);

		JLabel lblBuscaPro = new JLabel("Buscar");
		lblBuscaPro.setForeground(corTexto);
		lblBuscaPro.setFont(new Font("Roboto", Font.PLAIN, 24));
		lblBuscaPro.setBounds(38, 427, 119, 42);
		painelProdutos.add(lblBuscaPro);

		JLabel lblBuscaIdPro = new JLabel("ID");
		lblBuscaIdPro.setForeground(corTexto);
		lblBuscaIdPro.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblBuscaIdPro.setBounds(65, 467, 119, 42);
		painelProdutos.add(lblBuscaIdPro);

		JLabel lblBuscaNomePro = new JLabel("Nome");
		lblBuscaNomePro.setForeground(corTexto);
		lblBuscaNomePro.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblBuscaNomePro.setBounds(774, 461, 79, 42);
		painelProdutos.add(lblBuscaNomePro);

		JLabel lblFornecedorPro = new JLabel("Fornecedor");
		lblFornecedorPro.setForeground(corTexto);
		lblFornecedorPro.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblFornecedorPro.setBounds(640, 190, 119, 42);
		painelProdutos.add(lblFornecedorPro);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setForeground(corTexto);
		lblCategoria.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblCategoria.setBounds(640, 243, 119, 42);
		painelProdutos.add(lblCategoria);

		JLabel lblR = new JLabel("R$");
		lblR.setForeground(corTexto);
		lblR.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblR.setBounds(167, 243, 27, 42);
		painelProdutos.add(lblR);

		JLabel lblCodigoDeBarras = new JLabel("Codigo de Barras");
		lblCodigoDeBarras.setForeground(corTexto);
		lblCodigoDeBarras.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblCodigoDeBarras.setBounds(38, 296, 132, 42);
		painelProdutos.add(lblCodigoDeBarras);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setForeground(corTexto);
		lblStatus.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblStatus.setBounds(640, 296, 119, 42);
		painelProdutos.add(lblStatus);

		spNomePro.setBackground(corGeral);
		spNomePro.setBounds(167, 176, 409, 2);
		painelProdutos.add(spNomePro);

		JSeparator spPrecoPro = new JSeparator();
		spPrecoPro.setBackground(corGeral);
		spPrecoPro.setBounds(167, 283, 409, 2);
		painelProdutos.add(spPrecoPro);

		JSeparator spIdPro = new JSeparator();
		spIdPro.setBackground(corGeral);
		spIdPro.setBounds(1230, 176, 55, 2);
		painelProdutos.add(spIdPro);

		spTamanhoPro.setBackground(corGeral);
		spTamanhoPro.setBounds(167, 230, 407, 2);
		painelProdutos.add(spTamanhoPro);

		JSeparator spBuscaNomePro = new JSeparator();
		spBuscaNomePro.setBackground(corGeral);
		spBuscaNomePro.setBounds(867, 500, 418, 2);
		painelProdutos.add(spBuscaNomePro);

		JSeparator spBuscaIdPro = new JSeparator();
		spBuscaIdPro.setBackground(corGeral);
		spBuscaIdPro.setBounds(158, 501, 418, 2);
		painelProdutos.add(spBuscaIdPro);

		spCdBarras.setBackground(corGeral);
		spCdBarras.setBounds(169, 336, 407, 2);
		painelProdutos.add(spCdBarras);

		btCadastrarPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					pro.setCodigoBarras(String.valueOf(tfCdBarras.getText()));
					pro.setId(Integer.parseInt(tfIdPro.getText()));
					atualizaCamposPro();
					if (proDao.RetornarCdBarras(pro)) {
						if (Integer.parseInt(tfIdPro.getText()) == proDao.RetornarProximoidProdutos()) {
							if (camposEstaoCorretosPro(camposCorretosPro)) {

								pro.setNomeFornecedor(String.valueOf(cbFornecedoresProd.getSelectedItem()));
								pro.setNomeCategoria(String.valueOf(cbCategoriaProd.getSelectedItem()));

								pro.setNome(String.valueOf(tfNomePro.getText()));
								pro.setTamanho(String.valueOf(tfTamanho.getText()));
								;
								pro.setValorUnitario(Double.parseDouble(tfPrecoProd.getText()));
								pro.setCodigoBarras(String.valueOf(tfCdBarras.getText()));
								pro.setQuantidade(Integer.parseInt(String.valueOf(spinnerQuant.getValue())));
								pro.setIdFornecedor(proDao.RetornarIdFornecedor(pro));
								pro.setIdCategoria(proDao.RetornarIdCategoria(pro));
								pro.setStatus(Integer.parseInt(String.valueOf(cbStatusPro.getSelectedIndex())));
								proDao.CadastrarProduto(pro);

								limparPro();

							} else {
								JOptionPane.showMessageDialog(null,
										"Um ou mais campos não foram preenchidos corretamente", "Erro",
										JOptionPane.ERROR_MESSAGE);
								atualizaCamposPro();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Este ID já esta cadastrado!!\nResetando... ", "Erro",
									JOptionPane.ERROR_MESSAGE);
							limparPro();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Este codigo de barras já esta cadastrado ", "Erro",
								JOptionPane.ERROR_MESSAGE);
						panelImgCdBarras.setVisible(true);
						spCdBarras.setBackground(corVermelho);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				atualizarTabelaPro();
			}
		});
		btCadastrarPro.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {

				btCadastrarPro.setBackground(corSecundaria);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btCadastrarPro.setBackground(corGeral);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btCadastrarPro.setBackground(corTerciaria);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btCadastrarPro.setBackground(corGeral);

			}

		});


		btCadastrarPro.setForeground(corTexto);
		btCadastrarPro.setFont(new Font("Roboto", Font.PLAIN, 18));
		btCadastrarPro.setBorder(null);
		btCadastrarPro.setBackground(corGeral);
		btCadastrarPro.setBounds(65, 362, 215, 54);
		painelProdutos.add(btCadastrarPro);

		btEditarPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				atualizaCamposPro();

				try {
					pro.setCodigoBarras(String.valueOf(tfCdBarras.getText()));
					pro.setId(Integer.parseInt(tfIdPro.getText()));

					if (proDao.RetornarCdBarras(pro)) {
						if (camposEstaoCorretosPro(camposCorretosPro)) {

							pro.setNomeFornecedor(String.valueOf(cbFornecedoresProd.getSelectedItem()));
							pro.setNomeCategoria(String.valueOf(cbCategoriaProd.getSelectedItem()));

							pro.setId(Integer.parseInt(tfIdPro.getText()));
							pro.setNome(String.valueOf(tfNomePro.getText()));
							pro.setTamanho(String.valueOf(tfTamanho.getText()));

							pro.setValorUnitario(Double.parseDouble(tfPrecoProd.getText()));
							pro.setCodigoBarras(String.valueOf(tfCdBarras.getText()));
							pro.setQuantidade(Integer.parseInt(String.valueOf(spinnerQuant.getValue())));
							pro.setIdFornecedor(proDao.RetornarIdFornecedor(pro));
							pro.setIdCategoria(proDao.RetornarIdCategoria(pro));
							pro.setStatus(Integer.parseInt(String.valueOf(cbStatusPro.getSelectedIndex())));
							proDao.AlterarProduto(pro);

							limparPro();

						} else {
							JOptionPane.showMessageDialog(null, "Um ou mais campos não foram preenchidos corretamente",
									"Erro", JOptionPane.ERROR_MESSAGE);
							atualizaCamposPro();

						}

					} else {
						JOptionPane.showMessageDialog(null, "Este codigo de barras já esta cadastrado ", "Erro",
								JOptionPane.ERROR_MESSAGE);
						panelImgCdBarras.setVisible(true);
						spCdBarras.setBackground(corVermelho);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		btEditarPro.setForeground(corTexto);
		btEditarPro.setFont(new Font("Roboto", Font.PLAIN, 18));
		btEditarPro.setBorder(null);
		btEditarPro.setBackground(corGeral);
		btEditarPro.setBounds(403, 362, 215, 54);
		painelProdutos.add(btEditarPro);

		btExcluirPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tabelaProdutos.getSelectedRow() != -1) {
					Object[] options3 = { "Excluir", "Cancelar" };
					if (JOptionPane.showOptionDialog(null,
							"Tem certeza que deseja excluir o registro:\n>   "
									+ tabelaProdutos.getValueAt(tabelaProdutos.getSelectedRow(), 0) + "   -   "
									+ tabelaProdutos.getValueAt(tabelaProdutos.getSelectedRow(), 1),
							null, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3,
							options3[0]) == 0) {
						try {
							pro.setId(Integer.parseInt(tfIdPro.getText()));

							proDao.deletarProduto(pro);

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
						atualizarTabelaPro();
						limparPro();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btEditarPro.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {

				btEditarPro.setBackground(corSecundaria);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btEditarPro.setBackground(corGeral);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btEditarPro.setBackground(corTerciaria);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btEditarPro.setBackground(corGeral);

			}

		});

		
		
		
		
		
		btExcluirPro.setForeground(corTexto);
		btExcluirPro.setFont(new Font("Roboto", Font.PLAIN, 18));
		btExcluirPro.setBorder(null);
		btExcluirPro.setBackground(corGeral);
		btExcluirPro.setBounds(728, 362, 215, 54);
		painelProdutos.add(btExcluirPro);
		btLimparPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparPro();
			}
		});
		btExcluirPro.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {

				btExcluirPro.setBackground(corSecundaria);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btExcluirPro.setBackground(corGeral);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btExcluirPro.setBackground(corTerciaria);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btExcluirPro.setBackground(corGeral);

			}

		});

		btLimparPro.setForeground(corTexto);
		btLimparPro.setFont(new Font("Roboto", Font.PLAIN, 18));
		btLimparPro.setBorder(null);
		btLimparPro.setBackground(corGeral);
		btLimparPro.setBounds(1048, 362, 215, 54);
		painelProdutos.add(btLimparPro);
		
		
		btLimparPro.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {

				btLimparPro.setBackground(corSecundaria);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btLimparPro.setBackground(corGeral);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btLimparPro.setBackground(corTerciaria);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btLimparPro.setBackground(corGeral);

			}

		});


		panelImgNomePro.setForeground(Color.WHITE);
		panelImgNomePro.setBackground(Color.WHITE);
		panelImgNomePro.setBounds(6, 142, 27, 26);
		painelProdutos.add(panelImgNomePro);

		JLabel lblXNomePro = new JLabel("");
		lblXNomePro.setToolTipText("O nome \u00E9 obrigat\u00F3rio e n\u00E3o deve conter numeros e  simbolos");
		lblXNomePro.setBackground(Color.WHITE);
		lblXNomePro.setForeground(Color.WHITE);
		lblXNomePro.setIcon(new ImageIcon(imagemX));
		panelImgNomePro.add(lblXNomePro);

		panelImgTamanho.setForeground(Color.WHITE);
		panelImgTamanho.setBackground(Color.WHITE);
		panelImgTamanho.setBounds(6, 194, 27, 26);
		painelProdutos.add(panelImgTamanho);

		JLabel lblXTamanhoPro = new JLabel((Icon) null);
		lblXTamanhoPro.setToolTipText("O tamanho \u00E9 obrigat\u00F3rio");
		panelImgTamanho.add(lblXTamanhoPro);
		lblXTamanhoPro.setIcon(new ImageIcon(imagemX));

		tfIdPro = new JTextField();
		tfIdPro.setHorizontalAlignment(SwingConstants.CENTER);
		tfIdPro.setForeground(corTexto);
		tfIdPro.setFont(new Font("Roboto", Font.PLAIN, 20));
		tfIdPro.setEditable(false);
		tfIdPro.setColumns(10);
		tfIdPro.setBorder(null);
		try {
			tfIdPro.setText(String.valueOf(proDao.RetornarProximoidProdutos()));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		panelImgCdBarras.setForeground(Color.WHITE);
		panelImgCdBarras.setBackground(Color.WHITE);
		panelImgCdBarras.setBounds(6, 301, 27, 26);
		painelProdutos.add(panelImgCdBarras);

		JLabel lblXCdBarras = new JLabel((Icon) null);
		lblXCdBarras.setToolTipText("O código de Barras \u00E9 obrigat\u00F3rio");
		panelImgCdBarras.add(lblXCdBarras);
		lblXCdBarras.setIcon(new ImageIcon(imagemX));

		tfIdPro.setBackground(Color.WHITE);
		tfIdPro.setBounds(1230, 141, 55, 32);
		painelProdutos.add(tfIdPro);

		tfNomePro = new JTextField();
		tfNomePro.setForeground(corTexto);
		tfNomePro.setFont(new Font("Roboto", Font.PLAIN, 20));
		tfNomePro.setColumns(10);
		tfNomePro.setBorder(null);
		tfNomePro.setBackground(Color.WHITE);
		tfNomePro.setBounds(167, 141, 409, 32);
		painelProdutos.add(tfNomePro);
		tfNomePro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				camposCorretosPro[0] = (tfNomePro.getText().matches("[\\p{L}\\s]+")) ? true : false;

				if (tfNomePro.getText().length() < 1) {
					camposCorretosPro[0] = false;
				}

				if (camposCorretosPro[0]) {
					spNomePro.setBackground(corGeral);
					panelImgNomePro.setVisible(false);

				} else {
					spNomePro.setBackground(corVermelho);
					panelImgNomePro.setVisible(true);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				spNomePro.setBackground(corGeral);
				panelImgNomePro.setVisible(false);

			}
		});

		Locale currentLocale = Locale.getDefault();
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
		otherSymbols.setDecimalSeparator('.');
		otherSymbols.setGroupingSeparator(',');

		DecimalFormat df = new DecimalFormat("0.00", otherSymbols);
		tfPrecoProd = new JMoneyField();
		tfPrecoProd.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {

			}
		});

		tfPrecoProd.setOpaque(false);
		tfPrecoProd.setBounds(221, 250, 355, 32);
		tfPrecoProd.setForeground(corTexto);
		tfPrecoProd.setFont(new Font("Roboto", Font.PLAIN, 20));
		tfPrecoProd.setBorder(null);
		tfPrecoProd.setBackground(painelFornecedor.getBackground());

		tfTamanho = new JTextField();
		tfTamanho.setOpaque(false);
		tfTamanho.setBounds(167, 199, 409, 32);
		tfTamanho.setForeground(corTexto);
		tfTamanho.setFont(new Font("Roboto", Font.PLAIN, 20));
		tfTamanho.setBorder(null);
		tfTamanho.setBackground(painelFornecedor.getBackground());
		tfTamanho.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				camposCorretosPro[1] = true;
				if (tfTamanho.getText().length() == 0) {
					camposCorretosPro[1] = false;
				}

				if (camposCorretosPro[1]) {
					spTamanhoPro.setBackground(corGeral);
					panelImgTamanho.setVisible(false);

				} else {
					spTamanhoPro.setBackground(corVermelho);
					panelImgTamanho.setVisible(true);

				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				spTamanhoPro.setBackground(corGeral);
				panelImgTamanho.setVisible(false);

			}
		});
		tfTamanho.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (tfTamanho.getText().length() >= 2) {
					tfTamanho.setText(tfTamanho.getText().substring(0, 1));
				}
			}

			public void keyTyped(KeyEvent e) {

				char q = e.getKeyChar();
				if (!((Character.isDigit(q))) && (!(Character.isAlphabetic(q)))) {
					e.consume();
				}
			}
		});
		tfTamanho.setColumns(10);
		painelProdutos.add(tfTamanho);
		tfPrecoProd.setColumns(10);
		painelProdutos.add(tfPrecoProd);

		tfCdBarras = new JTextField();
		tfCdBarras.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				camposCorretosPro[2] = true;
				if (tfCdBarras.getText().length() == 0) {
					camposCorretosPro[2] = false;
				}

				if (camposCorretosPro[2]) {
					spCdBarras.setBackground(corGeral);
					panelImgCdBarras.setVisible(false);

				} else {
					spCdBarras.setBackground(corVermelho);
					panelImgCdBarras.setVisible(true);

				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				spCdBarras.setBackground(corGeral);
				panelImgCdBarras.setVisible(false);

			}
		});
		tfCdBarras.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if ((!(Character.isDigit(c)))) {
					ke.consume();
				}
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
			}
		});
		tfCdBarras.setForeground(corTexto);
		tfCdBarras.setFont(new Font("Roboto", Font.PLAIN, 20));
		tfCdBarras.setColumns(10);
		tfCdBarras.setBorder(null);
		tfCdBarras.setBackground(Color.WHITE);
		tfCdBarras.setBounds(167, 301, 409, 32);
		painelProdutos.add(tfCdBarras);

////ewsdf

		tfBuscaNomePro = new JTextField();
		tfBuscaNomePro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				tfBuscaNomePro.setText(null);
			}

		});
		tfBuscaNomePro.setForeground(corTexto);
		tfBuscaNomePro.setFont(new Font("Roboto", Font.PLAIN, 20));
		tfBuscaNomePro.setColumns(10);
		tfBuscaNomePro.setBorder(null);
		tfBuscaNomePro.setBackground(Color.WHITE);
		tfBuscaNomePro.setBounds(863, 466, 418, 32);

		tfBuscaNomePro.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {

				// atualizar a tabela apenas com valores correspondentes aos digitados no campo
				// de busca por nome
				TableRowSorter<TableModel> filtro = null;
				DefaultTableModel model = (DefaultTableModel) tabelaProdutos.getModel();
				filtro = new TableRowSorter<TableModel>(model);
				tabelaProdutos.setRowSorter(filtro);

				if (tfBuscaNomePro.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" + tfBuscaNomePro.getText(), 1));
				}

			}
		});
		painelProdutos.add(tfBuscaNomePro);

		tfBuscaIdPro = new JTextField();
		tfBuscaIdPro.setFont(new Font("Roboto", Font.PLAIN, 20));
		tfBuscaIdPro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				tfBuscaIdPro.setText(null);
			}
		});
		tfBuscaIdPro.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {

				// atualizar a tabela apenas com valores correspondentes aos digitados no campo
				// de busca por codigo
				TableRowSorter<TableModel> filtro = null;
				DefaultTableModel model = (DefaultTableModel) tabelaProdutos.getModel();
				filtro = new TableRowSorter<TableModel>(model);
				tabelaProdutos.setRowSorter(filtro);

				if (tfBuscaIdPro.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" + tfBuscaIdPro.getText(), 0));
				}
			}
		});
		tfBuscaIdPro.setForeground(corTexto);
		tfBuscaIdPro.setColumns(10);
		tfBuscaIdPro.setBorder(null);
		tfBuscaIdPro.setBounds(158, 468, 418, 31);
		painelProdutos.add(tfBuscaIdPro);

		cbFornecedoresProd = new JComboBox(new Object[] { "" });
		cbFornecedoresProd.setBounds(774, 200, 409, 32);

		cbFornecedoresProd.setFont(new Font("Roboto", Font.PLAIN, 20));
		cbFornecedoresProd.setForeground(corTexto);
		AutoCompleteDecorator.decorate(cbFornecedoresProd);
		painelProdutos.add(cbFornecedoresProd);

		cbCategoriaProd = new JComboBox(new Object[] { "" });
		cbCategoriaProd.setBounds(774, 250, 409, 32);

		cbCategoriaProd.setFont(new Font("Roboto", Font.PLAIN, 20));
		cbCategoriaProd.setForeground(corTexto);
		AutoCompleteDecorator.decorate(cbCategoriaProd);
		painelProdutos.add(cbCategoriaProd);

		cbCategoriaProd.setFont(new Font("Roboto", Font.PLAIN, 20));
		painelProdutos.add(cbCategoriaProd);

		spinnerQuant.setFont(new Font("Roboto", Font.PLAIN, 20));
		spinnerQuant.setBounds(774, 141, 119, 32);
		painelProdutos.add(spinnerQuant);

		cbStatusPro.setModel(new DefaultComboBoxModel(new String[] { "Inativo", "Ativo" }));
		cbStatusPro.setSelectedIndex(1);
		cbStatusPro.setForeground(corTexto);
		cbStatusPro.setFont(new Font("Roboto", Font.PLAIN, 20));
		cbStatusPro.setBounds(774, 306, 409, 32);
		painelProdutos.add(cbStatusPro);
		AutoCompleteDecorator.decorate(cbStatusPro);

		JScrollPane scrollPanePro = new JScrollPane();
		scrollPanePro.setBounds(38, 520, 1301, 208);
		painelProdutos.add(scrollPanePro);
		scrollPanePro.setColumnHeaderView(tabelaProdutos);

		tabelaProdutos = new JTable();
		tabelaProdutos.setFillsViewportHeight(true);
		tabelaProdutos.setAutoCreateRowSorter(true);
		tabelaProdutos.setSelectionBackground(Color.LIGHT_GRAY);
		tabelaProdutos.setIgnoreRepaint(true);
		tabelaProdutos.setEditingRow(0);
		tabelaProdutos.setFont(new Font("Roboto", Font.PLAIN, 14));
		tabelaProdutos.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				setCamposFromTabelaPro();
			}
		});
		scrollPanePro.setViewportView(tabelaProdutos);
		tabelaProdutos.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Nome", "Preço",
				"Tamanho", "ID Categoria", "ID Fornecedor", "Codigo de Barras", "Status", "Quantidade" }));

///PARTE DE CATEGORIA

		painelCategoria.setLayout(null);
		painelCategoria.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelCategoria.setBackground(Color.WHITE);
		painelCategoria.setBounds(0, 0, 1386, 886);
		mainPanel.add(painelCategoria);

		JLabel lblDescontoCat = new JLabel("Desconto");
		lblDescontoCat.setBounds(38, 190, 119, 42);
		lblDescontoCat.setForeground(corTexto);
		lblDescontoCat.setFont(new Font("Roboto", Font.PLAIN, 20));
		painelCategoria.add(lblDescontoCat);

		JLabel lblIdCat = new JLabel("ID");
		lblIdCat.setBounds(38, 250, 42, 42);
		lblIdCat.setForeground(corTexto);
		lblIdCat.setFont(new Font("Roboto", Font.PLAIN, 20));
		painelCategoria.add(lblIdCat);

		JLabel lblDescricaoCat = new JLabel("Descri\u00E7\u00E3o");
		lblDescricaoCat.setBounds(38, 136, 119, 42);
		lblDescricaoCat.setForeground(corTexto);
		lblDescricaoCat.setFont(new Font("Roboto", Font.PLAIN, 20));
		painelCategoria.add(lblDescricaoCat);

		JLabel lblBuscarCat = new JLabel("Buscar");
		lblBuscarCat.setForeground(corTexto);
		lblBuscarCat.setFont(new Font("Roboto", Font.PLAIN, 24));
		lblBuscarCat.setBounds(38, 427, 119, 42);
		painelCategoria.add(lblBuscarCat);

		JLabel lblBuscaIdCat = new JLabel("ID");
		lblBuscaIdCat.setForeground(corTexto);
		lblBuscaIdCat.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblBuscaIdCat.setBounds(65, 467, 119, 42);
		painelCategoria.add(lblBuscaIdCat);

		JLabel lblBuscaNomeCat = new JLabel("Descri\u00E7\u00E3o");
		lblBuscaNomeCat.setForeground(corTexto);
		lblBuscaNomeCat.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblBuscaNomeCat.setBounds(774, 461, 79, 42);
		painelCategoria.add(lblBuscaNomeCat);
		spIdCat.setBounds(167, 290, 55, 2);
		spIdCat.setBackground(corGeral);
		painelCategoria.add(spIdCat);

		spDescontoCat.setBounds(167, 230, 55, 2);
		spDescontoCat.setBackground(corGeral);
		painelCategoria.add(spDescontoCat);

		JSeparator spBuscaNomeCat = new JSeparator();
		spBuscaNomeCat.setBackground(corGeral);
		spBuscaNomeCat.setBounds(867, 500, 418, 2);
		painelCategoria.add(spBuscaNomeCat);
		JSeparator spBuscaIdCat = new JSeparator();

		spBuscaIdCat.setBackground(corGeral);
		spBuscaIdCat.setBounds(158, 501, 418, 2);
		painelCategoria.add(spBuscaIdCat);

		spDesc1Cat.setBackground(corGeral);
		spDesc1Cat.setBounds(167, 176, 407, 2);
		painelCategoria.add(spDesc1Cat);

		//

		JScrollPane scrollPaneCat = new JScrollPane();
		scrollPaneCat.setBounds(38, 520, 1301, 208);
		painelCategoria.add(scrollPaneCat);
		scrollPaneCat.setColumnHeaderView(tabelaCategoria);

		tabelaCategoria = new JTable();
		tabelaCategoria.setFillsViewportHeight(true);
		tabelaCategoria.setAutoCreateRowSorter(true);
		tabelaCategoria.setSelectionBackground(Color.LIGHT_GRAY);
		tabelaCategoria.setIgnoreRepaint(true);
		tabelaCategoria.setEditingRow(0);
		tabelaCategoria.setFont(new Font("Roboto", Font.PLAIN, 14));
		tabelaCategoria.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				setCamposFromTabelaCat();
			}
		});
		scrollPaneCat.setViewportView(tabelaCategoria);
		tabelaCategoria
				.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Descrição", "Desconto" }));
		btCadastrarCat.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {

				btCadastrarCat.setBackground(corSecundaria);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btCadastrarCat.setBackground(corGeral);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btCadastrarCat.setBackground(corTerciaria);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btCadastrarCat.setBackground(corGeral);

			}

		});
		btCadastrarCat.setBounds(76, 362, 215, 54);
		btCadastrarCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizaCamposCat();
				if (camposEstaoCorretosCat(camposCorretosCat)) {

					try {
						cat.setDescricao(String.valueOf(tfDescricaoCat.getText()));
						cat.setDesconto(Double.parseDouble(tfDescontoCat.getText()));

						catDao.CadastrarCategoria(cat);
					} catch (Exception e1) {
					}
				} else {
					JOptionPane.showMessageDialog(null, "Um ou mais campos não foram preenchidos corretamente", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
				atualizarTabelaCat();
				limparCat();
			}

		});
		btCadastrarCat.setForeground(corTexto);
		btCadastrarCat.setFont(new Font("Roboto", Font.PLAIN, 18));
		btCadastrarCat.setBorder(null);
		btCadastrarCat.setBackground(corGeral);
		painelCategoria.add(btCadastrarCat);
		btnEditarCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizaCamposCat();
				if (camposEstaoCorretosCat(camposCorretosCat)) {
					cat.setDescricao(String.valueOf(tfDescricaoCat.getText()));
					cat.setDesconto(Double.parseDouble(tfDescontoCat.getText()));
					cat.setId(Integer.parseInt(tfIdCat.getText()));
					try {

						catDao.AlterarCategoria(cat);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				atualizarTabelaCat();

			}
		});
		btnEditarCat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				btnEditarCat.setBackground(corSecundaria);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnEditarCat.setBackground(corGeral);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnEditarCat.setBackground(corTerciaria);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnEditarCat.setBackground(corGeral);

			}
		});
		btnEditarCat.setBounds(403, 362, 215, 54);
		btnEditarCat.setForeground(corTexto);
		btnEditarCat.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnEditarCat.setBorder(null);
		btnEditarCat.setBackground(corGeral);
		painelCategoria.add(btnEditarCat);

		btnExcluirCat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluirCat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				btnExcluirCat.setBackground(corSecundaria);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnExcluirCat.setBackground(corGeral);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnExcluirCat.setBackground(corTerciaria);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnExcluirCat.setBackground(corGeral);

			}
		});

		btnExcluirCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cat.setId(Integer.parseInt(tfIdCat.getText()));

				try {
					if (catDao.RetornarForeignKey(cat)) {

						if (tabelaCategoria.getSelectedRow() != -1) {
							Object[] options3 = { "Excluir", "Cancelar" };
							if (JOptionPane.showOptionDialog(null, "Tem certeza que deseja excluir o registro:\n>   "
									+ tabelaCategoria.getValueAt(tabelaCategoria.getSelectedRow(), 0) + "   -   "
									+ tabelaCategoria.getValueAt(tabelaCategoria.getSelectedRow(), 1), null,
									JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3,
									options3[0]) == 0) {
								try {
									cat.setId(Integer.parseInt(tfIdCat.getText()));

									catDao.deletarCategoria(cat);

								} catch (Exception e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage());
								}
								atualizarTabelaCat();
								limparCat();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada", "Erro",
									JOptionPane.ERROR_MESSAGE);
						}

					} else {
						JOptionPane.showMessageDialog(null,
								"Não foi possivel excluir esta categoria, pois a mesma se encontra atribuida a um produto",
								"Erro", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
		});
		btnExcluirCat.setBounds(728, 362, 215, 54);
		btnExcluirCat.setForeground(corTexto);
		btnExcluirCat.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnExcluirCat.setBorder(null);
		btnExcluirCat.setBackground(corGeral);
		painelCategoria.add(btnExcluirCat);

		btnLimparCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCat();
			}
		});
		btnLimparCat.setBounds(1048, 362, 215, 54);
		btnLimparCat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				btnLimparCat.setBackground(corSecundaria);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLimparCat.setBackground(corGeral);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnLimparCat.setBackground(corTerciaria);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnLimparCat.setBackground(corGeral);

			}
		});
		btnLimparCat.setForeground(corTexto);
		btnLimparCat.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnLimparCat.setBorder(null);
		btnLimparCat.setBackground(corGeral);
		painelCategoria.add(btnLimparCat);

		panelImgDescontoCat.setBounds(6, 194, 27, 26);
		painelCategoria.add(panelImgDescontoCat);
		panelImgDescontoCat.setForeground(Color.WHITE);
		panelImgDescontoCat.setBackground(Color.WHITE);

		JLabel labelXDescontoCat = new JLabel(new ImageIcon(imagemX));
		labelXDescontoCat.setToolTipText("O email \u00E9 obrigat\u00F3rio");
		panelImgDescontoCat.add(labelXDescontoCat);

		panelImgDescCat.setForeground(Color.WHITE);
		panelImgDescCat.setBackground(Color.WHITE);
		panelImgDescCat.setBounds(6, 142, 27, 26);
		painelCategoria.add(panelImgDescCat);

		JLabel labelXDescCat = new JLabel(new ImageIcon(imagemX));
		labelXDescCat.setToolTipText("O email \u00E9 obrigat\u00F3rio");
		panelImgDescCat.add(labelXDescCat);

		tfIdCat = new JTextField();
		tfIdCat.setBounds(167, 255, 55, 32);
		tfIdCat.setForeground(corTexto);
		tfIdCat.setFont(new Font("Roboto", Font.PLAIN, 20));
		tfIdCat.setBackground(painelCategoria.getBackground());
		tfIdCat.setBorder(null);
		painelCategoria.add(tfIdCat);
		tfIdCat.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			tfIdCat.setText(String.valueOf(catDao.RetornarProximoidCategoria()));
		} catch (Exception e2) {
		}
		tfIdCat.setEditable(false);
		tfIdCat.setColumns(10);

		tfBuscaNomeCat = new JTextField();
		tfBuscaNomeCat.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				tfBuscaNomeCat.setText(null);
			}

		});
		tfBuscaNomeCat.setForeground(corTexto);
		tfBuscaNomeCat.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfBuscaNomeCat.setColumns(10);
		tfBuscaNomeCat.setBorder(null);
		tfBuscaNomeCat.setBackground(Color.WHITE);
		tfBuscaNomeCat.setBounds(863, 466, 418, 32);

		tfBuscaNomeCat.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {

				// atualizar a tabela apenas com valores correspondentes aos digitados no campo
				// de busca por nome
				TableRowSorter<TableModel> filtro = null;
				DefaultTableModel model = (DefaultTableModel) tabelaCategoria.getModel();
				filtro = new TableRowSorter<TableModel>(model);
				tabelaCategoria.setRowSorter(filtro);

				if (tfBuscaNomeCat.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" + tfBuscaNomeCat.getText(), 1));
				}

			}
		});
		painelCategoria.add(tfBuscaNomeCat);

		tfBuscaIdCat = new JTextField();
		tfBuscaIdCat.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				tfBuscaIdCat.setText(null);
			}
		});
		tfBuscaIdCat.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {

				// atualizar a tabela apenas com valores correspondentes aos digitados no campo
				// de busca por codigo
				TableRowSorter<TableModel> filtro = null;
				DefaultTableModel model = (DefaultTableModel) tabelaCategoria.getModel();
				filtro = new TableRowSorter<TableModel>(model);
				tabelaCategoria.setRowSorter(filtro);

				if (tfBuscaIdCat.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" + tfBuscaIdCat.getText(), 0));
				}
			}
		});
		tfBuscaIdCat.setForeground(corTexto);
		tfBuscaIdCat.setColumns(10);
		tfBuscaIdCat.setBorder(null);
		tfBuscaIdCat.setBounds(158, 468, 418, 31);
		painelCategoria.add(tfBuscaIdCat);

		try {
			MaskFormatter tfDescontoCatFormatter = new MaskFormatter("##");
			tfDescontoCatFormatter.setPlaceholderCharacter('0');
			tfDescontoCat = new JFormattedTextField(tfDescontoCatFormatter);
			tfDescontoCat.setForeground(corTexto);
			tfDescontoCat.setFont(new Font("Roboto", Font.PLAIN, 20));
			tfDescontoCat.setBorder(null);
			tfDescontoCat.setBackground(painelCategoria.getBackground());
		} catch (Exception e) {
		}
		tfDescontoCat.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String numerosDescontoCat = tfDescontoCat.getText();
				numerosDescontoCat = numerosDescontoCat.replaceAll("[^\\d]", "");
				camposCorretos[1] = (numerosDescontoCat.length() < 2) ? false : true;
				spDescontoCat.setBackground(corGeral);

				if (camposCorretos[1]) {
					spDescontoCat.setBackground(corGeral);
					panelImgDescontoCat.setVisible(false);

				} else {
					spDescontoCat.setBackground(corVermelho);
					panelImgDescontoCat.setVisible(true);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				spDescontoCat.setBackground(corGeral);
				panelImgDescontoCat.setVisible(false);

			}
		});
		tfDescontoCat.setColumns(10);
		tfDescontoCat.setBounds(177, 195, 45, 32);
		painelCategoria.add(tfDescontoCat);

		tfDescricaoCat = new JTextField();
		tfDescricaoCat.setOpaque(false);
		tfDescricaoCat.setBounds(167, 146, 417, 32);
		tfDescricaoCat.setForeground(corTexto);
		tfDescricaoCat.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfDescricaoCat.setColumns(10);
		tfDescricaoCat.setBorder(null);
		tfDescricaoCat.setBackground(painelFornecedor.getBackground());
		tfDescricaoCat.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				camposCorretosCat[0] = (tfDescricaoCat.getText().matches("[\\p{L}\\s]+")) ? true : false;

				if (tfDescricaoCat.getText().length() < 1) {
					camposCorretosCat[0] = false;
				}

				if (camposCorretosCat[0]) {
					spDesc1Cat.setBackground(corGeral);
					panelImgDescCat.setVisible(false);
				} else {
					spDesc1Cat.setBackground(corVermelho);
					panelImgDescCat.setVisible(true);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				panelImgDescCat.setVisible(false);

				spDesc1Cat.setBackground(corGeral);
			}
		});
		painelCategoria.add(tfDescricaoCat);

		painelFornecedor.setLayout(null);
		painelFornecedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelFornecedor.setBackground(Color.WHITE);
		painelFornecedor.setBounds(0, 0, 1386, 886);
		mainPanel.add(painelFornecedor);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(38, 136, 60, 42);
		lblNome.setForeground(corTexto);
		lblNome.setFont(new Font("Roboto", Font.PLAIN, 20));
		painelFornecedor.add(lblNome);

		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setBounds(38, 243, 119, 42);
		lblCnpj.setForeground(corTexto);
		lblCnpj.setFont(new Font("Roboto", Font.PLAIN, 20));
		painelFornecedor.add(lblCnpj);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(38, 190, 119, 42);
		lblEmail.setForeground(corTexto);
		lblEmail.setFont(new Font("Roboto", Font.PLAIN, 20));
		painelFornecedor.add(lblEmail);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(38, 299, 119, 42);
		lblTelefone.setForeground(corTexto);
		lblTelefone.setFont(new Font("Roboto", Font.PLAIN, 20));
		painelFornecedor.add(lblTelefone);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(774, 148, 69, 42);
		lblEstado.setForeground(corTexto);
		lblEstado.setFont(new Font("Roboto", Font.PLAIN, 18));
		painelFornecedor.add(lblEstado);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(774, 244, 79, 42);
		lblBairro.setForeground(corTexto);
		lblBairro.setFont(new Font("Roboto", Font.PLAIN, 18));
		painelFornecedor.add(lblBairro);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(774, 196, 119, 42);
		lblCidade.setForeground(corTexto);
		lblCidade.setFont(new Font("Roboto", Font.PLAIN, 18));
		painelFornecedor.add(lblCidade);

		JLabel lblRua = new JLabel("Rua");
		lblRua.setBounds(774, 296, 119, 42);
		lblRua.setForeground(corTexto);
		lblRua.setFont(new Font("Roboto", Font.PLAIN, 18));
		painelFornecedor.add(lblRua);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(1187, 136, 42, 42);
		lblId.setForeground(corTexto);
		lblId.setFont(new Font("Roboto", Font.PLAIN, 20));
		painelFornecedor.add(lblId);

		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setBounds(640, 136, 119, 42);
		lblEndereo.setForeground(corTexto);
		lblEndereo.setFont(new Font("Roboto", Font.PLAIN, 20));
		painelFornecedor.add(lblEndereo);

		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setForeground(corTexto);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 24));
		lblBuscar.setBounds(38, 427, 119, 42);
		painelFornecedor.add(lblBuscar);

		JLabel lblBuscaId = new JLabel("ID");
		lblBuscaId.setForeground(corTexto);
		lblBuscaId.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblBuscaId.setBounds(65, 467, 119, 42);
		painelFornecedor.add(lblBuscaId);

		JLabel lblBuscaNome = new JLabel("Nome");
		lblBuscaNome.setForeground(corTexto);
		lblBuscaNome.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblBuscaNome.setBounds(774, 461, 79, 42);
		painelFornecedor.add(lblBuscaNome);

		spNome.setBounds(167, 176, 409, 2);
		spNome.setBackground(corGeral);
		painelFornecedor.add(spNome);
		spCNPJ.setBounds(167, 283, 409, 2);
		spCNPJ.setBackground(corGeral);
		painelFornecedor.add(spCNPJ);
		spId.setBounds(1230, 176, 55, 2);
		spId.setBackground(corGeral);
		painelFornecedor.add(spId);

		spEmail.setBounds(167, 230, 407, 2);
		spEmail.setBackground(corGeral);
		painelFornecedor.add(spEmail);

		spTelefone.setBounds(167, 339, 409, 2);
		spTelefone.setBackground(corGeral);
		painelFornecedor.add(spTelefone);

		spCidade.setBounds(867, 230, 418, 2);
		spCidade.setBackground(corGeral);
		painelFornecedor.add(spCidade);

		spBairro.setBounds(867, 283, 418, 2);
		spBairro.setBackground(corGeral);
		painelFornecedor.add(spBairro);

		spRua.setBounds(868, 339, 417, 2);
		spRua.setBackground(corGeral);
		painelFornecedor.add(spRua);

		JSeparator spBuscaNome = new JSeparator();
		spBuscaNome.setBackground(corGeral);
		spBuscaNome.setBounds(867, 500, 418, 2);
		painelFornecedor.add(spBuscaNome);

		JSeparator spBuscaID = new JSeparator();
		spBuscaID.setBackground(corGeral);
		spBuscaID.setBounds(158, 501, 418, 2);
		painelFornecedor.add(spBuscaID);

		cbEstado_1.setModel(new DefaultComboBoxModel(
				new String[] { "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PR",
						"PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

		cbEstado_1.setBounds(863, 158, 55, 20);
		cbEstado_1.setBackground(Color.WHITE);
		cbEstado_1.setForeground(new Color(0, 0, 0));
		cbEstado_1.setFont(new Font("Roboto", Font.PLAIN, 18));
		painelFornecedor.add(cbEstado_1);

		//

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 520, 1301, 208);
		painelFornecedor.add(scrollPane);
		scrollPane.setColumnHeaderView(tabelaFornecedor);

		tabelaFornecedor = new JTable();
		tabelaFornecedor.setFillsViewportHeight(true);
		tabelaFornecedor.setAutoCreateRowSorter(true);
		tabelaFornecedor.setSelectionBackground(Color.LIGHT_GRAY);
		tabelaFornecedor.setIgnoreRepaint(true);
		tabelaFornecedor.setEditingRow(0);
		tabelaFornecedor.setFont(new Font("Roboto", Font.PLAIN, 14));
		tabelaFornecedor.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				setCamposFromTabela();
				cbEstado_1.setSelectedItem(
						String.valueOf(tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 8)));
			}
		});
		scrollPane.setViewportView(tabelaFornecedor);
		tabelaFornecedor.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Codigo", "Nome", "CNPJ", "Email", "Telefone", "Rua", "Bairro", "Cidade", "Estado" }));
		try {
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		btCadastrar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {

				btCadastrar.setBackground(corSecundaria);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btCadastrar.setBackground(corGeral);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btCadastrar.setBackground(corTerciaria);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btCadastrar.setBackground(corGeral);

			}

		});
		btCadastrar.setBounds(76, 362, 215, 54);
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarCampos();
				try {
					if (Integer.parseInt(tfId.getText()) == fornDao.RetornarProximoidFornecedor()) {
						if (camposEstaoCorretos(camposCorretos)) {

							forn.setNome(tfNome.getText());
							forn.setCnpj(tfCNPJ.getText());
							forn.setTelefone(tfTelefone.getText());
							forn.setEmail(tfEmail.getText());
							forn.setRua(tfRua.getText());
							forn.setCidade(tfCidade.getText());
							forn.setBairro(tfBairro.getText());
							forn.setEstado(cbEstado_1.getSelectedItem().toString());
							forn.setId(fornDao.RetornarProximoidFornecedor());

							fornDao.CadastrarFornecedor(forn);
							limpar();

						} else {
							JOptionPane.showMessageDialog(null,
									"Erro, um ou mais campos não foram preenchidos corretamente", "Erro",
									JOptionPane.ERROR_MESSAGE);

						}
					} else {

						JOptionPane.showMessageDialog(null, "Este ID já esta cadastrado!!\nResetando... ", "Erro",
								JOptionPane.ERROR_MESSAGE);
						limpar();
					}
				} catch (Exception e1) {
				}

				atualizarTabela();
			}

		});
		btCadastrar.setForeground(corTexto);
		btCadastrar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btCadastrar.setBorder(null);
		btCadastrar.setBackground(corGeral);
		painelFornecedor.add(btCadastrar);

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizarCampos();
				if (camposEstaoCorretos(camposCorretos)) {

					forn.setNome(tfNome.getText());
					forn.setCnpj(tfCNPJ.getText());
					forn.setTelefone(tfTelefone.getText());
					forn.setEmail(tfEmail.getText());
					forn.setRua(tfRua.getText());
					forn.setCidade(tfCidade.getText());
					forn.setBairro(tfBairro.getText());
					forn.setEstado(cbEstado_1.getSelectedItem().toString());
					forn.setId(Integer.parseInt(tfId.getText()));

					try {
						fornDao.AlterarFornecedor(forn);
					} catch (Exception e) {
					}
					atualizarTabela();

				}
			}
		});
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				btnEditar.setBackground(corSecundaria);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnEditar.setBackground(corGeral);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnEditar.setBackground(corTerciaria);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnEditar.setBackground(corGeral);

			}
		});
		btnEditar.setBounds(403, 362, 215, 54);
		btnEditar.setForeground(corTexto);
		btnEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnEditar.setBorder(null);
		btnEditar.setBackground(corGeral);
		painelFornecedor.add(btnEditar);

		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				btnExcluir.setBackground(corSecundaria);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnExcluir.setBackground(corGeral);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnExcluir.setBackground(corTerciaria);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnExcluir.setBackground(corGeral);

			}
		});

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				forn.setId(Integer.parseInt(tfId.getText()));

				try {
					if (fornDao.RetornarForeignKey(forn)) {
						if (tabelaFornecedor.getSelectedRow() != -1) {
							Object[] options3 = { "Excluir", "Cancelar" };
							if (JOptionPane.showOptionDialog(null, "Tem certeza que deseja excluir o registro:\n>   "
									+ tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 0) + "   -   "
									+ tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 1), null,
									JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3,
									options3[0]) == 0) {
								try {

									forn.setId(Integer.parseInt(tfId.getText()));

									fornDao.deletarFornecedor(forn);

									atualizarTabela();
									limpar();
								} catch (Exception e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage());
								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada", "Erro",
									JOptionPane.ERROR_MESSAGE);
						}

					} else {
						JOptionPane.showMessageDialog(null,
								"Não foi possivel excluir este Fornecedor, pois o mesmo se encontra atribuido a um produto",
								"Erro", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
		});
		btnExcluir.setBounds(728, 362, 215, 54);
		btnExcluir.setForeground(corTexto);
		btnExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnExcluir.setBorder(null);
		btnExcluir.setBackground(corGeral);
		painelFornecedor.add(btnExcluir);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpar();
				deixarCerto();
			}
		});
		btnLimpar.setBounds(1048, 362, 215, 54);
		btnLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				btnLimpar.setBackground(corSecundaria);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLimpar.setBackground(corGeral);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnLimpar.setBackground(corTerciaria);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnLimpar.setBackground(corGeral);

			}
		});
		btnLimpar.setForeground(corTexto);
		btnLimpar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnLimpar.setBorder(null);
		btnLimpar.setBackground(corGeral);
		painelFornecedor.add(btnLimpar);

		JLabel imgXNome = new JLabel(new ImageIcon(imagemX));
		imgXNome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgXNome.setBackground(new Color(255, 255, 255));
		imgXNome.setToolTipText("O nome é obrigatório e não pode conter símbolos");

		JPanel panelCampos = new JPanel();
		panelCampos.setForeground(Color.WHITE);
		panelCampos.setBackground(corTerciaria);
		painelFornecedor.add(panelCampos);
		panelCampos.setLayout(null);

		panelImgNome.setBounds(6, 142, 27, 26);
		painelFornecedor.add(panelImgNome);
		panelImgNome.setBackground(Color.WHITE);
		panelImgNome.setForeground(Color.WHITE);

		JLabel labelXnome = new JLabel("");
		labelXnome.setToolTipText("O nome \u00E9 obrigat\u00F3rio e n\u00E3o deve conter numeros e  simbolos");
		labelXnome.setBackground(Color.WHITE);
		labelXnome.setForeground(Color.WHITE);
		labelXnome.setIcon(new ImageIcon(imagemX));
		panelImgNome.add(labelXnome);
		labelXnome.setIcon(new ImageIcon(imagemX));

		panelImgEmail.setBounds(6, 194, 27, 26);
		painelFornecedor.add(panelImgEmail);
		panelImgEmail.setForeground(Color.WHITE);
		panelImgEmail.setBackground(Color.WHITE);

		JLabel labelXEmail = new JLabel(new ImageIcon(imagemX));
		labelXEmail.setToolTipText("O email \u00E9 obrigat\u00F3rio");
		panelImgEmail.add(labelXEmail);

		panelImgCNPJ.setBounds(6, 250, 27, 26);
		painelFornecedor.add(panelImgCNPJ);
		panelImgCNPJ.setForeground(Color.WHITE);
		panelImgCNPJ.setBackground(Color.WHITE);

		JLabel labelXCNPJ = new JLabel(new ImageIcon(imagemX));
		labelXCNPJ.setToolTipText("O CNPJ \u00E9 obrigat\u00F3rio");
		panelImgCNPJ.add(labelXCNPJ);

		panelImgTelefone.setBounds(6, 302, 27, 26);
		painelFornecedor.add(panelImgTelefone);
		panelImgTelefone.setForeground(Color.WHITE);
		panelImgTelefone.setBackground(Color.WHITE);

		JLabel labelXTelefone = new JLabel(new ImageIcon(imagemX));
		labelXTelefone.setToolTipText("O telefone \u00E9 obrigat\u00F3rio\r\n");
		panelImgTelefone.add(labelXTelefone);

		panelImgCidade.setBounds(737, 205, 27, 26);
		painelFornecedor.add(panelImgCidade);
		panelImgCidade.setForeground(Color.WHITE);
		panelImgCidade.setBackground(Color.WHITE);

		JLabel lblXCidade = new JLabel(new ImageIcon(imagemX));
		lblXCidade.setToolTipText("A cidade \u00E9 obrigat\u00F3ria e n\u00E3o deve conter numeros e simbolos\r\n");
		panelImgCidade.add(lblXCidade);

		panelImgBairro.setBounds(737, 250, 27, 26);
		painelFornecedor.add(panelImgBairro);
		panelImgBairro.setForeground(Color.WHITE);
		panelImgBairro.setBackground(Color.WHITE);

		JLabel lblXBairro = new JLabel(new ImageIcon(imagemX));
		lblXBairro.setToolTipText("O bairro \u00E9 obrigat\u00F3rio e n\u00E3o deve conter numeros e simbolos");
		panelImgBairro.add(lblXBairro);

		panelImgRua.setBounds(737, 302, 27, 26);
		painelFornecedor.add(panelImgRua);
		panelImgRua.setToolTipText("A rua \u00E9 obrigat\u00F3ria e n\u00E3o deve conter numeros e simbolos\r\n");
		panelImgRua.setForeground(Color.WHITE);
		panelImgRua.setBackground(Color.WHITE);

		JLabel lblXRua = new JLabel(new ImageIcon(imagemX));
		panelImgRua.add(lblXRua);

		tfId = new JTextField();
		tfId.setBounds(1230, 141, 55, 32);
		tfId.setForeground(corTexto);
		tfId.setFont(new Font("Roboto", Font.PLAIN, 20));
		tfId.setBackground(painelFornecedor.getBackground());
		tfId.setBorder(null);
		painelFornecedor.add(tfId);
		tfId.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			tfId.setText(String.valueOf(fornDao.RetornarProximoidFornecedor()));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tfId.setEditable(false);
		tfId.setColumns(10);

		tfNome = new JTextField();
		tfNome.setBounds(167, 141, 409, 32);
		painelFornecedor.add(tfNome);
		tfNome.setForeground(corTexto);
		tfNome.setFont(new Font("Roboto", Font.PLAIN, 20));
		tfNome.setBorder(null);
		tfNome.setBackground(painelFornecedor.getBackground());
		tfNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				camposCorretos[0] = (tfNome.getText().matches("[\\p{L}\\s]+")) ? true : false;

				if (tfNome.getText().length() < 1) {
					camposCorretos[0] = false;
				}

				if (camposCorretos[0]) {
					spNome.setBackground(corGeral);
					panelImgNome.setVisible(false);

				} else {
					spNome.setBackground(corVermelho);
					panelImgNome.setVisible(true);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				spNome.setBackground(corGeral);
				panelImgNome.setVisible(false);

			}
		});
		tfNome.setColumns(10);

		tfEmail = new JTextField();
		tfEmail.setBounds(167, 195, 414, 32);
		painelFornecedor.add(tfEmail);
		tfEmail.setForeground(corTexto);
		tfEmail.setFont(new Font("Roboto", Font.PLAIN, 20));
		tfEmail.setColumns(10);
		tfEmail.setBorder(null);

		tfEmail.setBackground(painelFornecedor.getBackground());
		tfEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				camposCorretos[1] = (tfEmail.getText()
						.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) ? true : false;

				if (tfEmail.getText().length() < 1) {
					camposCorretos[1] = false;
				}

				if (camposCorretos[1]) {
					spEmail.setBackground(corGeral);
					panelImgEmail.setVisible(false);

				} else {
					spEmail.setBackground(corVermelho);
					panelImgEmail.setVisible(true);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				spEmail.setBackground(corGeral);
				panelImgEmail.setVisible(false);

			}
		});

		try {
			MaskFormatter tfCNPJFormatter = new MaskFormatter("##.###.###/####-##");
			tfCNPJFormatter.setPlaceholderCharacter('_');
			tfCNPJ = new JFormattedTextField(tfCNPJFormatter);
			tfCNPJ.setBounds(167, 248, 409, 32);
			tfCNPJ.setForeground(corTexto);
			tfCNPJ.setFont(new Font("Roboto", Font.PLAIN, 20));
			tfCNPJ.setBorder(null);
			tfCNPJ.setBackground(painelFornecedor.getBackground());
		} catch (Exception e) {
		}
		tfCNPJ.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String numerosCNPJ = tfCNPJ.getText();
				numerosCNPJ = numerosCNPJ.replaceAll("[^\\d]", "");
				camposCorretos[2] = (numerosCNPJ.length() < 11) ? false : true;
				spCNPJ.setBackground(corGeral);

				if (camposCorretos[2]) {
					spCNPJ.setBackground(corGeral);
					panelImgCNPJ.setVisible(false);

				} else {
					spCNPJ.setBackground(corVermelho);
					panelImgCNPJ.setVisible(true);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				spCNPJ.setBackground(corGeral);
				panelImgCNPJ.setVisible(false);

			}
		});
		tfCNPJ.setColumns(10);
		painelFornecedor.add(tfCNPJ);

		try {
			MaskFormatter tfTelefoneFormatter = new MaskFormatter("(##) ####-####");
			tfTelefoneFormatter.setPlaceholderCharacter('_');
			tfTelefone = new JFormattedTextField(tfTelefoneFormatter);
			tfTelefone.setBounds(167, 304, 409, 32);
			tfTelefone.setForeground(corTexto);
			tfTelefone.setFont(new Font("Roboto", Font.PLAIN, 20));
			tfTelefone.setBorder(null);
			tfTelefone.setBackground(painelFornecedor.getBackground());
		} catch (Exception e) {
		}
		tfTelefone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String numerosTelefone = tfTelefone.getText();
				numerosTelefone = numerosTelefone.replaceAll("[^\\d]", "");
				camposCorretos[3] = (numerosTelefone.length() < 10) ? false : true;
				spTelefone.setBackground(corGeral);

				if (camposCorretos[3]) {
					spTelefone.setBackground(corGeral);
					panelImgTelefone.setVisible(false);

				} else {
					spTelefone.setBackground(corVermelho);
					panelImgTelefone.setVisible(true);

				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				spTelefone.setBackground(corGeral);
				panelImgTelefone.setVisible(false);

			}
		});
		tfTelefone.setColumns(10);
		painelFornecedor.add(tfTelefone);

		tfCidade = new JTextField();
		tfCidade.setToolTipText("");
		tfCidade.setBounds(867, 197, 418, 31);
		tfCidade.setForeground(corTexto);
		tfCidade.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfCidade.setColumns(10);
		tfCidade.setBorder(null);
		tfCidade.setBackground(painelFornecedor.getBackground());
		tfCidade.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				camposCorretos[4] = (tfCidade.getText().matches("[\\p{L}\\s]+")) ? true : false;

				if (tfCidade.getText().length() < 1) {
					camposCorretos[4] = false;
				}

				if (camposCorretos[4]) {
					spCidade.setBackground(corGeral);
					panelImgCidade.setVisible(false);
				} else {
					spCidade.setBackground(corVermelho);
					panelImgCidade.setVisible(true);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				panelImgCidade.setVisible(false);

				spCidade.setBackground(corGeral);
			}
		});
		painelFornecedor.add(tfCidade);

		tfBairro = new JTextField();
		tfBairro.setBounds(863, 249, 418, 32);
		tfBairro.setForeground(corTexto);
		tfBairro.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfBairro.setColumns(10);
		tfBairro.setBorder(null);
		tfBairro.setBackground(painelFornecedor.getBackground());
		tfBairro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				camposCorretos[5] = (tfBairro.getText().matches("[\\p{L}\\s]+")) ? true : false;

				if (tfBairro.getText().length() < 1) {
					camposCorretos[5] = false;

				}

				if (camposCorretos[5]) {
					spBairro.setBackground(corGeral);
					panelImgBairro.setVisible(false);
				} else {
					spBairro.setBackground(corVermelho);
					panelImgBairro.setVisible(true);

				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				spBairro.setBackground(corGeral);
				panelImgBairro.setVisible(false);

			}
		});

		painelFornecedor.add(tfBairro);

		tfRua = new JTextField();
		tfRua.setBounds(868, 305, 417, 32);
		tfRua.setForeground(corTexto);
		tfRua.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfRua.setColumns(10);
		tfRua.setBorder(null);
		tfRua.setBackground(painelFornecedor.getBackground());
		tfRua.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				camposCorretos[6] = (tfRua.getText().matches("[\\p{L}\\s]+")) ? true : false;

				if (tfRua.getText().length() < 1) {
					camposCorretos[6] = false;
				}

				if (camposCorretos[6]) {
					spRua.setBackground(corGeral);
					panelImgRua.setVisible(false);

				} else {
					spRua.setBackground(corVermelho);
					panelImgRua.setVisible(true);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				spRua.setBackground(corGeral);
				panelImgRua.setVisible(false);

			}
		});
		painelFornecedor.add(tfRua);

		tfBuscaNome = new JTextField();
		tfBuscaNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				tfBuscaNome.setText(null);
			}

		});
		tfBuscaNome.setForeground(corTexto);
		tfBuscaNome.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfBuscaNome.setColumns(10);
		tfBuscaNome.setBorder(null);
		tfBuscaNome.setBackground(Color.WHITE);
		tfBuscaNome.setBounds(863, 466, 418, 32);

		tfBuscaNome.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {

				// atualizar a tabela apenas com valores correspondentes aos digitados no campo
				// de busca por nome
				TableRowSorter<TableModel> filtro = null;
				DefaultTableModel model = (DefaultTableModel) tabelaFornecedor.getModel();
				filtro = new TableRowSorter<TableModel>(model);
				tabelaFornecedor.setRowSorter(filtro);

				if (tfBuscaNome.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" + tfBuscaNome.getText(), 1));
				}

			}
		});
		painelFornecedor.add(tfBuscaNome);

		tfBuscaId = new JTextField();
		tfBuscaId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				tfBuscaId.setText(null);
			}
		});
		tfBuscaId.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {

				// atualizar a tabela apenas com valores correspondentes aos digitados no campo
				// de busca por codigo
				TableRowSorter<TableModel> filtro = null;
				DefaultTableModel model = (DefaultTableModel) tabelaFornecedor.getModel();
				filtro = new TableRowSorter<TableModel>(model);
				tabelaFornecedor.setRowSorter(filtro);

				if (tfBuscaId.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" + tfBuscaId.getText(), 0));
				}
			}
		});
		tfBuscaId.setToolTipText("");
		tfBuscaId.setForeground(corTexto);
		tfBuscaId.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfBuscaId.setColumns(10);
		tfBuscaId.setBorder(null);
		tfBuscaId.setBackground(Color.WHITE);
		tfBuscaId.setBounds(158, 468, 418, 31);
		painelFornecedor.add(tfBuscaId);

		// tela Inicia Maximizada
		Rectangle area = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		setMaximizedBounds(area);
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { tfNome, tfEmail, tfCNPJ, tfTelefone,
				cbEstado_1, tfCidade, tfBairro, tfRua, btCadastrar, btnEditar, btnExcluir, btnLimpar, tfDescricaoCat,
				tfDescontoCat, btCadastrarCat, btnEditarCat, btnExcluirCat, btnLimparCat, tfNomePro, tfTamanho,
				tfPrecoProd, tfCdBarras, spinnerQuant, cbFornecedoresProd, cbCategoriaProd, cbStatusPro, btCadastrarPro,
				btEditarPro, btExcluirPro, btLimparPro }));

		JLabel imgXNomeCat = new JLabel(new ImageIcon(imagemX));
		imgXNomeCat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgXNomeCat.setBackground(new Color(255, 255, 255));
		imgXNomeCat.setToolTipText("O nome é obrigatório e não pode conter símbolos");
		try {
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			MaskFormatter tfCNPJFormatter = new MaskFormatter("##.###.###/####-##");
			tfCNPJFormatter.setPlaceholderCharacter('_');
		} catch (Exception e) {
		}

		try {
			MaskFormatter tfTelefoneFormatter = new MaskFormatter("(##) ####-####");
			tfTelefoneFormatter.setPlaceholderCharacter('_');
		} catch (Exception e) {
		}

	}

	// metodos

	public void deixarCerto() {
		spCNPJ.setBackground(corGeral);
		panelImgCNPJ.setVisible(false);
		spTelefone.setBackground(corGeral);
		panelImgTelefone.setVisible(false);
		spRua.setBackground(corGeral);
		panelImgRua.setVisible(false);
		spNome.setBackground(corGeral);
		panelImgNome.setVisible(false);
		spEmail.setBackground(corGeral);
		panelImgEmail.setVisible(false);
		spCidade.setBackground(corGeral);
		panelImgCidade.setVisible(false);
		spBairro.setBackground(corGeral);
		panelImgBairro.setVisible(false);

	}

	public void limpar() {
		tfCNPJ.setText(null);
		tfNome.setText(null);
		tfCidade.setText(null);
		tfTelefone.setText(null);
		tfRua.setText(null);
		tfBairro.setText(null);
		tfEmail.setText(null);

		try {
			tfId.setText(String.valueOf(fornDao.RetornarProximoidFornecedor()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void atualizarTabela() {
		try {
			fornecedor = fornDao.buscarTodos();
			DefaultTableModel model = (DefaultTableModel) tabelaFornecedor.getModel();
			model.setNumRows(0);
			for (int x = 0; x != fornecedor.size(); x++) {
				model.addRow((Object[]) fornecedor.get(x));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void setCamposFromTabela() {
		tfId.setText(String.valueOf(tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 0)));
		tfNome.setText(String.valueOf(tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 1)));
		tfCNPJ.setText(String.valueOf(tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 2)));
		tfEmail.setText(String.valueOf(tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 3)));
		tfTelefone.setText(String.valueOf(tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 4)));
		tfRua.setText(String.valueOf(tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 5)));
		tfBairro.setText(String.valueOf(tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 6)));
		tfCidade.setText(String.valueOf(tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 7)));
		atualizarCampos();

	}

	boolean camposEstaoCorretos(boolean[] camposCorretos) {
		boolean x = true;
		for (boolean a : camposCorretos) {
			x = x && a;
		}
		return x;
	}

	public void atualizarCampos() {
		camposCorretos[0] = (tfNome.getText().matches("[\\p{L}\\s]+")) ? true : false;

		if (tfNome.getText().length() < 1) {
			camposCorretos[0] = false;
		}

		if (camposCorretos[0]) {
			spNome.setBackground(corGeral);
			panelImgNome.setVisible(false);

		} else {
			spNome.setBackground(corVermelho);
			panelImgNome.setVisible(true);
		}

		camposCorretos[1] = (tfEmail.getText()
				.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) ? true : false;

		if (tfEmail.getText().length() < 1) {
			camposCorretos[1] = false;
		}

		if (camposCorretos[1]) {
			spEmail.setBackground(corGeral);
			panelImgEmail.setVisible(false);

		} else {
			spEmail.setBackground(corVermelho);
			panelImgEmail.setVisible(true);
		}

		String numerosCNPJ = tfCNPJ.getText();
		numerosCNPJ = numerosCNPJ.replaceAll("[^\\d]", "");
		camposCorretos[2] = (numerosCNPJ.length() < 11) ? false : true;
		spCNPJ.setBackground(corGeral);

		if (camposCorretos[2]) {
			spCNPJ.setBackground(corGeral);
			panelImgCNPJ.setVisible(false);

		} else {
			spCNPJ.setBackground(corVermelho);
			panelImgCNPJ.setVisible(true);
		}

		String numerosTelefone = tfTelefone.getText();
		numerosTelefone = numerosTelefone.replaceAll("[^\\d]", "");
		camposCorretos[3] = (numerosTelefone.length() < 10) ? false : true;
		spTelefone.setBackground(corGeral);

		if (camposCorretos[3]) {
			spTelefone.setBackground(corGeral);
			panelImgTelefone.setVisible(false);

		} else {
			spTelefone.setBackground(corVermelho);
			panelImgTelefone.setVisible(true);

		}

		camposCorretos[4] = (tfCidade.getText().matches("[\\p{L}\\s]+")) ? true : false;

		if (tfCidade.getText().length() < 1) {
			camposCorretos[4] = false;
		}

		if (camposCorretos[4]) {
			spCidade.setBackground(corGeral);
			panelImgCidade.setVisible(false);
		} else {
			spCidade.setBackground(corVermelho);
			panelImgCidade.setVisible(true);
		}

		camposCorretos[5] = (tfBairro.getText().matches("[\\p{L}\\s]+")) ? true : false;

		if (tfBairro.getText().length() < 1) {
			camposCorretos[5] = false;

		}

		if (camposCorretos[5]) {
			spBairro.setBackground(corGeral);
			panelImgBairro.setVisible(false);
		} else {
			spBairro.setBackground(corVermelho);
			panelImgBairro.setVisible(true);

		}

		camposCorretos[6] = (tfRua.getText().matches("[\\p{L}\\s]+")) ? true : false;

		if (tfRua.getText().length() < 1) {
			camposCorretos[6] = false;
		}

		if (camposCorretos[6]) {
			spRua.setBackground(corGeral);
			panelImgRua.setVisible(false);

		} else {
			spRua.setBackground(corVermelho);
			panelImgRua.setVisible(true);
		}
	}

	public void clickarFornecedor() {
		limpar();
		
		deixarCerto();

		painelFornecedor.setVisible(true);
		painelCategoria.setVisible(false);
		painelProdutos.setVisible(false);
		atualizarTabela();

		btnCategoria.setBackground(Color.LIGHT_GRAY);
		btnProdutos.setBackground(Color.LIGHT_GRAY);
		btnFornecedor.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {

				btnFornecedor.setBackground(corSecundaria);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnFornecedor.setBackground(corGeral);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnFornecedor.setBackground(corTerciaria);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnFornecedor.setBackground(corGeral);

			}

		});
		btnCategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				btnCategoria.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY, 0.2f));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCategoria.setBackground(Color.LIGHT_GRAY);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnCategoria.setBackground(corTerciaria);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnCategoria.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY, 0.2f));

			}
		});
		btnProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				btnProdutos.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY, 0.2f));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnProdutos.setBackground(Color.LIGHT_GRAY);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnProdutos.setBackground(corTerciaria);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnProdutos.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY, 0.2f));

			}
		});

	}

	public void clickarCategoria() {
		deixarCertoCat();
		limparCat();
		atualizarTabelaCat();
		painelCategoria.setVisible(true);
		painelFornecedor.setVisible(false);
		painelProdutos.setVisible(false);

		btnFornecedor.setBackground(Color.LIGHT_GRAY);
		btnProdutos.setBackground(Color.LIGHT_GRAY);

		btnCategoria.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {

				btnCategoria.setBackground(corSecundaria);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCategoria.setBackground(corGeral);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnCategoria.setBackground(corTerciaria);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnCategoria.setBackground(corGeral);

			}

		});
		btnFornecedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				btnFornecedor.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY, 0.2f));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnFornecedor.setBackground(Color.LIGHT_GRAY);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnFornecedor.setBackground(corTerciaria);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnFornecedor.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY, 0.2f));

			}
		});
		btnProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				btnProdutos.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY, 0.2f));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnProdutos.setBackground(Color.LIGHT_GRAY);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnProdutos.setBackground(corTerciaria);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnProdutos.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY, 0.2f));

			}
		});

	}

	public void clickarProdutos() {

		try {
			if (!((proDao.RetornarCategoria(pro) || (proDao.RetornarFornecedor(pro))))) {

				limparPro();
				painelCategoria.setVisible(false);
				atualizarCbPesquisa();
				atualizarCbPesquisaCat();
				atualizarTabelaPro();
				painelFornecedor.setVisible(false);
				painelProdutos.setVisible(true);
				btnFornecedor.setBackground(Color.LIGHT_GRAY);
				btnCategoria.setBackground(Color.LIGHT_GRAY);
				btnProdutos.setBackground(corGeral);
				tfIdPro.setText(String.valueOf(proDao.RetornarProximoidProdutos()));

				btnProdutos.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseEntered(MouseEvent arg0) {

						btnProdutos.setBackground(corSecundaria);

					}

					@Override
					public void mouseExited(MouseEvent e) {
						btnProdutos.setBackground(corGeral);

					}

					@Override
					public void mousePressed(MouseEvent e) {
						btnProdutos.setBackground(corTerciaria);

					}

					@Override
					public void mouseReleased(MouseEvent e) {
						btnProdutos.setBackground(corGeral);

					}

				});
				btnCategoria.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent arg0) {

						btnCategoria.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY, 0.2f));

					}

					@Override
					public void mouseExited(MouseEvent e) {
						btnCategoria.setBackground(Color.LIGHT_GRAY);

					}

					@Override
					public void mousePressed(MouseEvent e) {
						btnCategoria.setBackground(corTerciaria);

					}

					@Override
					public void mouseReleased(MouseEvent e) {
						btnCategoria.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY, 0.2f));

					}
				});
				btnFornecedor.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent arg0) {

						btnFornecedor.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY, 0.2f));

					}

					@Override
					public void mouseExited(MouseEvent e) {
						btnFornecedor.setBackground(Color.LIGHT_GRAY);

					}

					@Override
					public void mousePressed(MouseEvent e) {
						btnFornecedor.setBackground(corTerciaria);

					}

					@Override
					public void mouseReleased(MouseEvent e) {
						btnFornecedor.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY, 0.2f));

					}
				});
			} else {
				JOptionPane.showMessageDialog(null, "Nenhum Fornecedor ou Categoria Cadastrado", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

	public void atualizarTabelaCat() {
		try {
			categoria = catDao.buscarTodos();
			DefaultTableModel model = (DefaultTableModel) tabelaCategoria.getModel();
			model.setNumRows(0);
			for (int x = 0; x != categoria.size(); x++) {
				model.addRow((Object[]) categoria.get(x));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void limparCat() {

		try {
			tfIdCat.setText(String.valueOf(catDao.RetornarProximoidCategoria()));
			tfDescricaoCat.setText(null);
			tfDescontoCat.setText("0");
		} catch (Exception e) {
		}
		atualizarTabelaCat();
	}

	public void setCamposFromTabelaCat() {
		tfIdCat.setText(String.valueOf(tabelaCategoria.getValueAt(tabelaCategoria.getSelectedRow(), 0)));
		tfDescontoCat.setText(String.valueOf(tabelaCategoria.getValueAt(tabelaCategoria.getSelectedRow(), 2)));
		tfDescricaoCat.setText(String.valueOf(tabelaCategoria.getValueAt(tabelaCategoria.getSelectedRow(), 1)));

	}

	public void deixarCertoCat() {

		spDescontoCat.setBackground(corGeral);
		panelImgDescontoCat.setVisible(false);

		spDesc1Cat.setBackground(corGeral);

		panelImgDescCat.setVisible(false);

	}

	boolean camposEstaoCorretosCat(boolean[] camposCorretosCat) {
		boolean x = true;
		for (boolean a : camposCorretosCat) {
			x = x && a;
		}
		return x;
	}

	private void atualizaCamposCat() {
		camposCorretosCat[0] = true;

		if (tfDescricaoCat.getText().length() < 1) {
			camposCorretosCat[0] = false;
		}

		if (camposCorretosCat[0]) {
			spDesc1Cat.setBackground(corGeral);
			panelImgDescCat.setVisible(false);
		} else {
			spDesc1Cat.setBackground(corVermelho);
			panelImgDescCat.setVisible(true);
		}

		String numerosDescontoCat = tfDescontoCat.getText();
		numerosDescontoCat = numerosDescontoCat.replaceAll("[^\\d]", "");
		camposCorretosCat[1] = (numerosDescontoCat.length() < 2) ? false : true;
		spDescontoCat.setBackground(corGeral);

		if (camposCorretosCat[1]) {
			spDescontoCat.setBackground(corGeral);
			panelImgDescontoCat.setVisible(false);

		} else {
			spDescontoCat.setBackground(corVermelho);
			panelImgDescontoCat.setVisible(true);
		}
	}

	public static void atualizarCbPesquisa() {
		List<Object> nomesForn = new ArrayList<>();
		try {
			nomesForn = fornDao.buscarNomes();

			ArrayList<String> nomesFornTxt = new ArrayList<>();
			for (Object n : nomesForn) {
				nomesFornTxt.add(String.valueOf(n));
			}
			Collections.sort(nomesFornTxt);

			cbFornecedoresProd.removeAllItems();

			for (Object n : nomesFornTxt) {
				cbFornecedoresProd.addItem(n);
			}

			cbFornecedoresProd.setSelectedIndex(0);
		} catch (Exception e) {
		}
	}

	public static void atualizarCbPesquisaCat() {
		List<Object> nomesCat = new ArrayList<>();
		try {
			nomesCat = catDao.buscarNomes();

			ArrayList<String> nomesCatTxt = new ArrayList<>();
			for (Object n : nomesCat) {
				nomesCatTxt.add(String.valueOf(n));
			}
			Collections.sort(nomesCatTxt);

			cbCategoriaProd.removeAllItems();

			for (Object n : nomesCatTxt) {
				cbCategoriaProd.addItem(n);
			}

			cbCategoriaProd.setSelectedIndex(0);
		} catch (Exception e) {
		}
	}

	public void atualizarTabelaPro() {
		try {
			produto = proDao.buscarTodos();
			DefaultTableModel model = (DefaultTableModel) tabelaProdutos.getModel();
			model.setNumRows(0);
			for (int x = 0; x != produto.size(); x++) {
				model.addRow((Object[]) produto.get(x));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void limparPro() {

		try {
			tfIdPro.setText(String.valueOf(proDao.RetornarProximoidProdutos()));
			tfNomePro.setText(null);
			tfTamanho.setText(null);
			tfPrecoProd.setText(null);
			tfCdBarras.setText(null);
			spinnerQuant.setValue(0);
			cbCategoriaProd.setSelectedIndex(0);
			cbFornecedoresProd.setSelectedIndex(0);
			cbStatusPro.setSelectedIndex(1);
			deixarCertoPro();
		} catch (Exception e) {
		}
		atualizarTabelaCat();
	}

	public void setCamposFromTabelaPro() {
		pro.setIdCategoria(Integer.parseInt((String) tabelaProdutos.getValueAt(tabelaProdutos.getSelectedRow(), 4)));
		pro.setIdFornecedor(Integer.parseInt((String) tabelaProdutos.getValueAt(tabelaProdutos.getSelectedRow(), 5)));

		try {
			tfIdPro.setText(String.valueOf(tabelaProdutos.getValueAt(tabelaProdutos.getSelectedRow(), 0)));
			tfNomePro.setText(String.valueOf(tabelaProdutos.getValueAt(tabelaProdutos.getSelectedRow(), 1)));
			tfPrecoProd.setText(String.valueOf(tabelaProdutos.getValueAt(tabelaProdutos.getSelectedRow(), 2)));
			tfTamanho.setText(String.valueOf(tabelaProdutos.getValueAt(tabelaProdutos.getSelectedRow(), 3)));
			cbCategoriaProd.setSelectedItem(proDao.RetornarNomeCategoria(pro));
			cbFornecedoresProd.setSelectedItem(proDao.RetornarNomeFornecedor(pro));
			tfCdBarras.setText(String.valueOf(tabelaProdutos.getValueAt(tabelaProdutos.getSelectedRow(), 6)));
			cbStatusPro.setSelectedIndex(
					Integer.parseInt((String) tabelaProdutos.getValueAt(tabelaProdutos.getSelectedRow(), 7)));
			spinnerQuant
					.setValue(Integer.parseInt((String) tabelaProdutos.getValueAt(tabelaProdutos.getSelectedRow(), 8)));
			deixarCertoPro();
		} catch (Exception e) {
		}
	}

	boolean camposEstaoCorretosPro(boolean[] camposCorretosPro) {
		boolean x = true;
		for (boolean a : camposCorretosPro) {
			x = x && a;
		}
		return x;
	}

	private void atualizaCamposPro() {
		camposCorretosPro[0] = (tfNomePro.getText().matches("[\\p{L}\\s]+")) ? true : false;

		if (tfNomePro.getText().length() < 1) {
			camposCorretosPro[0] = false;
		}

		if (camposCorretosPro[0]) {
			spNomePro.setBackground(corGeral);
			panelImgNomePro.setVisible(false);

		} else {
			spNomePro.setBackground(corVermelho);
			panelImgNomePro.setVisible(true);
		}
		camposCorretosPro[1] = true;
		if (tfTamanho.getText().length() == 0) {
			camposCorretosPro[1] = false;
		}

		if (camposCorretosPro[1]) {
			spTamanhoPro.setBackground(corGeral);
			panelImgTamanho.setVisible(false);

		} else {
			spTamanhoPro.setBackground(corVermelho);
			panelImgTamanho.setVisible(true);

		}

		camposCorretosPro[2] = true;
		if (tfCdBarras.getText().length() == 0) {
			camposCorretosPro[2] = false;
		}

		if (camposCorretosPro[2]) {
			spCdBarras.setBackground(corGeral);
			panelImgCdBarras.setVisible(false);

		} else {
			spCdBarras.setBackground(corVermelho);
			panelImgCdBarras.setVisible(true);

		}

	}

	public void deixarCertoPro() {

		spNomePro.setBackground(corGeral);
		panelImgNomePro.setVisible(false);

		spTamanhoPro.setBackground(corGeral);
		panelImgTamanho.setVisible(false);

		spCdBarras.setBackground(corGeral);
		panelImgCdBarras.setVisible(false);
	}

}
