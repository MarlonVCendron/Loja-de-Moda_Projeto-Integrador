package br.edu.ifcvideira.Jframes;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import br.edu.ifcvideira.Classes.Categoria;
import br.edu.ifcvideira.Classes.Fornecedor;
import br.edu.ifcvideira.DAOs.CategoriaDao;
import br.edu.ifcvideira.DAOs.FornecedorDao;
import br.edu.ifcvideira.utils.Cor;

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


	private FornecedorDao fornDao = new FornecedorDao();
	private Fornecedor forn = new Fornecedor();
	
	Color corTexto = new Color(75, 80, 85);
	Color corGeral = new Color(118, 184, 184);
	Color corSecundaria = Cor.corMaisClara(corGeral, 0.2f);
	Color corTerciaria = Cor.corMaisClara(corGeral, 0.4f);
	Color corVermelho = new Color (230, 20, 20);
	Point posMouseInicial;
	private JTextField tfCNPJ;
	private List<Object> fornecedor = new ArrayList<Object>();
	private List<Object> categoria = new ArrayList<Object>();

	private JTable tabelaFornecedor;
	
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

	JSeparator spDesc3Cat = new JSeparator();

	JSeparator spDesc2Cat = new JSeparator();
	JSeparator spDesc1Cat = new JSeparator();

	JPanel panelImgNome = new JPanel();
	JPanel panelImgEmail = new JPanel();
	JPanel panelImgTelefone = new JPanel();
	JPanel panelImgCidade = new JPanel();
	JPanel panelImgBairro = new JPanel();
	JPanel panelImgRua = new JPanel();
	boolean[] camposCorretos = {false,false, false, false, false, false, false};
	JButton btCadastrar = new JButton("Cadastrar");
	JButton btnCategoria = new JButton("Categoria");

	
	boolean[] camposCorretosCat = {false,false,false};
	boolean[] camposCorretosPro = {false,false,false};

	
	
	JButton btnProdutos = new JButton("Produtos");

	ImageIcon imageIconX = new ImageIcon(TelaEstoque.class.getResource("/br/edu/ifcvideira/img/xerro.png"));
	Image imagemX = imageIconX.getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
	private JTextField tfBuscaNome;
	private JTextField tfBuscaId;
	JButton btnFornecedor = new JButton("Fornecedor");


	JPanel painelFornecedor = new JPanel();

	JPanel painelCategoria = new JPanel();

	JPanel painelProdutos = new JPanel();
	
	CategoriaDao  catDao = 	new CategoriaDao();
	Categoria cat = new Categoria();
	private JTextField tfNomeCat;
	private JTextField tfDescontoCat;
	private JTextField tfIdCat;
	
	JSeparator spNomeCat = new JSeparator();
	private JTable tabelaCategoria;
	JSeparator spIdCat = new JSeparator();
	JSeparator spDescontoCat = new JSeparator();

	JPanel panelImgNomeCat = new JPanel();
	JPanel panelImgDescontoCat = new JPanel();
	JButton btCadastrarCat = new JButton("Cadastrar");


	private JTextField tfBuscaNomeCat;
	private JTextField tfBuscaIdCat;
	private JTextField tfDescricaoCat;
	private JTextField tfIdPro;
	private JTextField tfNomePro;
	private JTextField tfBuscaNomePro;
	private JTextField tfBuscaIdPro;
	private JTextField tfPrecoProd;
	private JTextField tfTamanho;
	
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
				atualizarTabela();
				limpar();
				clickarFornecedor();
				
			}
		});
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1386, 886);
<<<<<<< HEAD
		painelPrincipal = new JPanel();
		painelPrincipal.setBackground(Color.WHITE);
		painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painelPrincipal);
		painelPrincipal.setLayout(null);
			
			JPanel btnSuperior = new JPanel();
			btnSuperior.setLayout(null);
			btnSuperior.setBorder(new EmptyBorder(5, 5, 5, 5));
			btnSuperior.setBackground(Color.WHITE);
			btnSuperior.setBounds(0, 0, 1386, 121);
			painelPrincipal.add(btnSuperior);
			btnFornecedor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				clickarFornecedor();
				}
			});
			
			btnFornecedor.setFont(new Font("Roboto", Font.PLAIN, 18));
			btnFornecedor.setBorder(null);
			btnFornecedor.setBackground(new Color(118, 184, 184));
			btnFornecedor.setBounds(38, 57, 400, 54);
			btnSuperior.add(btnFornecedor);
			
			btnCategoria.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					clickarCategoria();
				}
			});
			
			btnCategoria.setForeground(new Color(75, 80, 85));
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
			
			btnProdutos.setForeground(new Color(75, 80, 85));
			btnProdutos.setFont(new Font("Roboto", Font.PLAIN, 18));
			btnProdutos.setBorder(null);
			btnProdutos.setBackground(Color.LIGHT_GRAY);
			btnProdutos.setBounds(927, 57, 400, 54);
			btnSuperior.add(btnProdutos);
			
			JButton btSair = new JButton("X");
			btSair.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			btSair.setMaximumSize(new Dimension(80, 50));
			btSair.setFont(new Font("Roboto", Font.PLAIN, 13));
			btSair.setBorder(null);
			btSair.setBackground(new Color(118, 184, 184));
			btSair.setBounds(1326, 0, 42, 42);
			btnSuperior.add(btSair);
			
			JButton btMinimizar = new JButton("-");
			btMinimizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setState(JFrame.ICONIFIED);

				}
			});
			btMinimizar.setMaximumSize(new Dimension(80, 50));
			btMinimizar.setFont(new Font("Roboto", Font.PLAIN, 15));
			btMinimizar.setBorder(null);
			btMinimizar.setBackground(new Color(118, 184, 184));
			btMinimizar.setBounds(1248, 0, 42, 42);
			btnSuperior.add(btMinimizar);
			
			painelProdutos.setLayout(null);
			painelProdutos.setBorder(new EmptyBorder(5, 5, 5, 5));
			painelProdutos.setBackground(Color.WHITE);
			painelProdutos.setBounds(0, 0, 1386, 886);
			painelPrincipal.add(painelProdutos);
			
			JLabel lblNomePro = new JLabel("Nome");
			lblNomePro.setForeground(new Color(75, 80, 85));
			lblNomePro.setFont(new Font("Roboto", Font.PLAIN, 20));
			lblNomePro.setBounds(38, 136, 60, 42);
			painelProdutos.add(lblNomePro);
			
			JLabel lblPrecoPro = new JLabel("Pre\u00E7o");
			lblPrecoPro.setForeground(new Color(75, 80, 85));
			lblPrecoPro.setFont(new Font("Roboto", Font.PLAIN, 20));
			lblPrecoPro.setBounds(38, 243, 119, 42);
			painelProdutos.add(lblPrecoPro);
			
			JLabel lblQuantPro = new JLabel("Quantidade");
			lblQuantPro.setForeground(new Color(75, 80, 85));
			lblQuantPro.setFont(new Font("Roboto", Font.PLAIN, 20));
			lblQuantPro.setBounds(640, 136, 119, 42);
			painelProdutos.add(lblQuantPro);
			
			JLabel label_8 = new JLabel("ID");
			label_8.setForeground(new Color(75, 80, 85));
			label_8.setFont(new Font("Roboto", Font.PLAIN, 20));
			label_8.setBounds(1187, 136, 42, 42);
			painelProdutos.add(label_8);
			
			JLabel lblTamanhoPro = new JLabel("Tamanho");
			lblTamanhoPro.setForeground(new Color(75, 80, 85));
			lblTamanhoPro.setFont(new Font("Roboto", Font.PLAIN, 20));
			lblTamanhoPro.setBounds(38, 189, 119, 42);
			painelProdutos.add(lblTamanhoPro);
			
			JLabel lblBuscaPro = new JLabel("Buscar");
			lblBuscaPro.setForeground(new Color(75, 80, 85));
			lblBuscaPro.setFont(new Font("Roboto", Font.PLAIN, 24));
			lblBuscaPro.setBounds(38, 427, 119, 42);
			painelProdutos.add(lblBuscaPro);
			
			JLabel lblBuscaIdPro = new JLabel("ID");
			lblBuscaIdPro.setForeground(new Color(75, 80, 85));
			lblBuscaIdPro.setFont(new Font("Roboto", Font.PLAIN, 18));
			lblBuscaIdPro.setBounds(65, 467, 119, 42);
			painelProdutos.add(lblBuscaIdPro);
			
			JLabel lblBuscaNomePro = new JLabel("Nome");
			lblBuscaNomePro.setForeground(new Color(75, 80, 85));
			lblBuscaNomePro.setFont(new Font("Roboto", Font.PLAIN, 18));
			lblBuscaNomePro.setBounds(774, 461, 79, 42);
			painelProdutos.add(lblBuscaNomePro);
			
			JSeparator spNomePro = new JSeparator();
			spNomePro.setBackground(new Color(176, 176, 176));
			spNomePro.setBounds(167, 176, 409, 2);
			painelProdutos.add(spNomePro);
			
			JSeparator spPrecoPro = new JSeparator();
			spPrecoPro.setBackground(new Color(176, 176, 176));
			spPrecoPro.setBounds(167, 283, 409, 2);
			painelProdutos.add(spPrecoPro);
			
			JSeparator spIdPro = new JSeparator();
			spIdPro.setBackground(new Color(176, 176, 176));
			spIdPro.setBounds(1230, 176, 55, 2);
			painelProdutos.add(spIdPro);
			
			JSeparator spTamanhoPro = new JSeparator();
			spTamanhoPro.setBackground(new Color(176, 176, 176));
			spTamanhoPro.setBounds(167, 230, 407, 2);
			painelProdutos.add(spTamanhoPro);
			
			JSeparator spBuscaNomePro = new JSeparator();
			spBuscaNomePro.setBackground(new Color(176, 176, 176));
			spBuscaNomePro.setBounds(867, 500, 418, 2);
			painelProdutos.add(spBuscaNomePro);
			
			JSeparator spBuscaIdPro = new JSeparator();
			spBuscaIdPro.setBackground(new Color(176, 176, 176));
			spBuscaIdPro.setBounds(158, 501, 418, 2);
			painelProdutos.add(spBuscaIdPro);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(38, 520, 1301, 208);
			painelProdutos.add(scrollPane_1);
			
			JButton btCadastrarPro = new JButton("Cadastrar");
			btCadastrarPro.setForeground(new Color(75, 80, 85));
			btCadastrarPro.setFont(new Font("Roboto", Font.PLAIN, 18));
			btCadastrarPro.setBorder(null);
			btCadastrarPro.setBackground(new Color(118, 184, 184));
			btCadastrarPro.setBounds(65, 362, 215, 54);
			painelProdutos.add(btCadastrarPro);
			
			JButton btEditarPro = new JButton("Editar");
			btEditarPro.setForeground(new Color(75, 80, 85));
			btEditarPro.setFont(new Font("Roboto", Font.PLAIN, 18));
			btEditarPro.setBorder(null);
			btEditarPro.setBackground(new Color(118, 184, 184));
			btEditarPro.setBounds(403, 362, 215, 54);
			painelProdutos.add(btEditarPro);
			
			JButton btExcluirPro = new JButton("Excluir");
			btExcluirPro.setForeground(new Color(75, 80, 85));
			btExcluirPro.setFont(new Font("Roboto", Font.PLAIN, 18));
			btExcluirPro.setBorder(null);
			btExcluirPro.setBackground(new Color(118, 184, 184));
			btExcluirPro.setBounds(728, 362, 215, 54);
			painelProdutos.add(btExcluirPro);
			
			JButton btLimparPro = new JButton("Limpar");
			btLimparPro.setForeground(new Color(75, 80, 85));
			btLimparPro.setFont(new Font("Roboto", Font.PLAIN, 18));
			btLimparPro.setBorder(null);
			btLimparPro.setBackground(new Color(118, 184, 184));
			btLimparPro.setBounds(1048, 362, 215, 54);
			painelProdutos.add(btLimparPro);
			
			JPanel panelImgNomePro = new JPanel();
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
			lblXNomePro.setIcon(new ImageIcon(imagemX));
			
			JPanel painelImgQuantPro = new JPanel();
			painelImgQuantPro.setForeground(Color.WHITE);
			painelImgQuantPro.setBackground(Color.WHITE);
			painelImgQuantPro.setBounds(6, 194, 27, 26);
			painelProdutos.add(painelImgQuantPro);
			
			JLabel label_14 = new JLabel((Icon) null);
			label_14.setToolTipText("O email \u00E9 obrigat\u00F3rio");
			painelImgQuantPro.add(label_14);
			
			JPanel panel_4 = new JPanel();
			panel_4.setForeground(Color.WHITE);
			panel_4.setBackground(Color.WHITE);
			panel_4.setBounds(6, 250, 27, 26);
			painelProdutos.add(panel_4);
			
			JLabel label_15 = new JLabel((Icon) null);
			label_15.setToolTipText("O CNPJ \u00E9 obrigat\u00F3rio");
			panel_4.add(label_15);
			
			tfIdPro = new JTextField();
			tfIdPro.setHorizontalAlignment(SwingConstants.CENTER);
			tfIdPro.setForeground(new Color(75, 80, 85));
			tfIdPro.setFont(new Font("Roboto", Font.PLAIN, 20));
			tfIdPro.setEditable(false);
			tfIdPro.setColumns(10);
			tfIdPro.setBorder(null);
			tfIdPro.setBackground(Color.WHITE);
			tfIdPro.setBounds(1230, 141, 55, 32);
			painelProdutos.add(tfIdPro);
			
			tfNomePro = new JTextField();
			tfNomePro.setForeground(new Color(75, 80, 85));
			tfNomePro.setFont(new Font("Roboto", Font.PLAIN, 20));
			tfNomePro.setColumns(10);
			tfNomePro.setBorder(null);
			tfNomePro.setBackground(Color.WHITE);
			tfNomePro.setBounds(167, 141, 409, 32);
			painelProdutos.add(tfNomePro);
			tfNomePro.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					camposCorretosPro[0] = (tfNomePro.getText().matches("[\\p{L}\\s]+")) ?  true: false;
					
					if(tfNomePro.getText().length() < 1) {
						camposCorretosPro[0] = false;
					}
					
					if(camposCorretosPro[0]) {
						spNomePro.setBackground(corGeral);
						panelImgNomePro.setVisible(false);

						
					}else {
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
			
			
			
			
			
			try {
				MaskFormatter tfDescontoCatFormatter = new MaskFormatter("##");
				tfDescontoCatFormatter.setPlaceholderCharacter('0');
				
				
			}catch(Exception e) {}
			
			
			
			tfPrecoProd = new JTextField();
			tfPrecoProd.setForeground(new Color(75, 80, 85));
			tfPrecoProd.setFont(new Font("Roboto", Font.PLAIN, 20));
			tfPrecoProd.setColumns(10);
			tfPrecoProd.setBorder(null);
			tfPrecoProd.setBackground(Color.WHITE);
			tfPrecoProd.setBounds(204, 248, 377, 32);
			painelProdutos.add(tfPrecoProd);
			
			tfBuscaNomePro = new JTextField();
			tfBuscaNomePro.setForeground(new Color(75, 80, 85));
			tfBuscaNomePro.setFont(new Font("Roboto", Font.PLAIN, 18));
			tfBuscaNomePro.setColumns(10);
			tfBuscaNomePro.setBorder(null);
			tfBuscaNomePro.setBackground(Color.WHITE);
			tfBuscaNomePro.setBounds(863, 466, 418, 32);
			painelProdutos.add(tfBuscaNomePro);
			
			tfBuscaIdPro = new JTextField();
			tfBuscaIdPro.setToolTipText("");
			tfBuscaIdPro.setForeground(new Color(75, 80, 85));
			tfBuscaIdPro.setFont(new Font("Roboto", Font.PLAIN, 18));
			tfBuscaIdPro.setColumns(10);
			tfBuscaIdPro.setBorder(null);
			tfBuscaIdPro.setBackground(Color.WHITE);
			tfBuscaIdPro.setBounds(158, 468, 418, 31);
			painelProdutos.add(tfBuscaIdPro);
			
			JLabel lblFornecedorPro = new JLabel("Fornecedor");
			lblFornecedorPro.setForeground(new Color(75, 80, 85));
			lblFornecedorPro.setFont(new Font("Roboto", Font.PLAIN, 20));
			lblFornecedorPro.setBounds(640, 190, 119, 42);
			painelProdutos.add(lblFornecedorPro);
			
			JLabel lblCategoria = new JLabel("Categoria");
			lblCategoria.setForeground(new Color(75, 80, 85));
			lblCategoria.setFont(new Font("Roboto", Font.PLAIN, 20));
			lblCategoria.setBounds(640, 243, 119, 42);
			painelProdutos.add(lblCategoria);
			
			JComboBox comboBox_1 = new JComboBox();
			comboBox_1.setFont(new Font("Roboto", Font.PLAIN, 20));
			comboBox_1.setBounds(769, 204, 290, 28);
			painelProdutos.add(comboBox_1);
			
			JComboBox comboBox_2 = new JComboBox();
			comboBox_2.setFont(new Font("Roboto", Font.PLAIN, 20));
			comboBox_2.setBounds(769, 257, 290, 28);
			painelProdutos.add(comboBox_2);
			
			tfTamanho = new JTextField();
			tfTamanho.setForeground(new Color(75, 80, 85));
			tfTamanho.setFont(new Font("Roboto", Font.PLAIN, 20));
			tfTamanho.setColumns(10);
			tfTamanho.setBorder(null);
			tfTamanho.setBackground(Color.WHITE);
			tfTamanho.setBounds(167, 195, 290, 32);
			painelProdutos.add(tfTamanho);
			
			JLabel lblR = new JLabel("R$");
			lblR.setForeground(new Color(75, 80, 85));
			lblR.setFont(new Font("Roboto", Font.PLAIN, 20));
			lblR.setBounds(167, 243, 27, 42);
			painelProdutos.add(lblR);
			
			JSpinner spinner = new JSpinner();
			spinner.setFont(new Font("Roboto", Font.PLAIN, 20));
			spinner.setBounds(774, 141, 119, 32);
			painelProdutos.add(spinner);
		
			
			///PARTE DE CATEGORIA
			
			

			painelCategoria.setLayout(null);
			painelCategoria.setBorder(new EmptyBorder(5, 5, 5, 5));
			painelCategoria.setBackground(Color.WHITE);
			painelCategoria.setBounds(0, 0, 1386, 886);
			painelPrincipal.add(painelCategoria);
			
					
					JLabel lblNomeCat = new JLabel("Nome");
					lblNomeCat.setBounds(38, 136, 60, 42);
					lblNomeCat.setForeground(new Color(75, 80, 85));
					lblNomeCat.setFont(new Font("Roboto", Font.PLAIN, 20));
					painelCategoria.add(lblNomeCat);
					
					JLabel lblDescontoCat = new JLabel("Desconto");
					lblDescontoCat.setBounds(38, 190, 119, 42);
					lblDescontoCat.setForeground(new Color(75, 80, 85));
					lblDescontoCat.setFont(new Font("Roboto", Font.PLAIN, 20));
					painelCategoria.add(lblDescontoCat);
					
					JLabel lblIdCat = new JLabel("ID");
					lblIdCat.setBounds(38, 250, 42, 42);
					lblIdCat.setForeground(new Color(75, 80, 85));
					lblIdCat.setFont(new Font("Roboto", Font.PLAIN, 20));
					painelCategoria.add(lblIdCat);
					
					JLabel lblDescricaoCat = new JLabel("Descri\u00E7\u00E3o");
					lblDescricaoCat.setBounds(704, 136, 119, 42);
					lblDescricaoCat.setForeground(new Color(75, 80, 85));
					lblDescricaoCat.setFont(new Font("Roboto", Font.PLAIN, 20));
					painelCategoria.add(lblDescricaoCat);
					
					JLabel lblBuscarCat = new JLabel("Buscar");
					lblBuscarCat.setForeground(new Color(75, 80, 85));
					lblBuscarCat.setFont(new Font("Roboto", Font.PLAIN, 24));
					lblBuscarCat.setBounds(38, 427, 119, 42);
					painelCategoria.add(lblBuscarCat);
					
					
					JLabel lblBuscaIdCat = new JLabel("ID");
					lblBuscaIdCat.setForeground(new Color(75, 80, 85));
					lblBuscaIdCat.setFont(new Font("Roboto", Font.PLAIN, 18));
					lblBuscaIdCat.setBounds(65, 467, 119, 42);
					painelCategoria.add(lblBuscaIdCat);
					
					JLabel lblBuscaNomeCat = new JLabel("Nome");
					lblBuscaNomeCat.setForeground(new Color(75, 80, 85));
					lblBuscaNomeCat.setFont(new Font("Roboto", Font.PLAIN, 18));
					lblBuscaNomeCat.setBounds(774, 461, 79, 42);
					painelCategoria.add(lblBuscaNomeCat);
					
					spNomeCat.setBounds(167, 176, 409, 2);
					spNomeCat.setBackground(new Color(176, 176, 176));
					painelCategoria.add(spNomeCat);
					spIdCat.setBounds(167, 290, 55, 2);
					spIdCat.setBackground(new Color(176, 176, 176));
					painelCategoria.add(spIdCat);
					
					spDescontoCat.setBounds(167, 230, 55, 2);
					spDescontoCat.setBackground(new Color(176, 176, 176));
					painelCategoria.add(spDescontoCat);
					
					JSeparator spBuscaNomeCat = new JSeparator();
					spBuscaNomeCat.setBackground(new Color(176, 176, 176));
					spBuscaNomeCat.setBounds(867, 500, 418, 2);
					painelCategoria.add(spBuscaNomeCat);
					
					spDesc2Cat.setBackground(new Color(176, 176, 176));
					spDesc2Cat.setBounds(876, 230, 407, 2);
					painelCategoria.add(spDesc2Cat);
					JSeparator spBuscaIdCat = new JSeparator();

					spDesc3Cat.setBackground(new Color(176, 176, 176));
					spDesc3Cat.setBounds(876, 290, 407, 2);
					painelCategoria.add(spDesc3Cat);
					
					spBuscaIdCat.setBackground(new Color(176, 176, 176));
					spBuscaIdCat.setBounds(158, 501, 418, 2);
					painelCategoria.add(spBuscaIdCat);
					
					spDesc1Cat.setBackground(new Color(176, 176, 176));
					spDesc1Cat.setBounds(876, 171, 407, 2);
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
							tabelaCategoria.setModel(new DefaultTableModel(
								new Object[][] {
								},
								new String[] {
									"Codigo", "Descrição", "Desconto", "Nome"
								}
							));
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
											cat.setNome(String.valueOf(tfNomeCat.getText()));

											
											catDao.CadastrarCategoria(cat);
										} catch (Exception e1) {}
									}else {
										JOptionPane.showMessageDialog(null,"Um ou mais campos não foram preenchidos corretamente" , "Erro",JOptionPane.ERROR_MESSAGE );
									}
									atualizarTabelaCat();
									limparCat();
								}
								
							});
							btCadastrarCat.setForeground(new Color(75, 80, 85));
							btCadastrarCat.setFont(new Font("Roboto", Font.PLAIN, 18));
							btCadastrarCat.setBorder(null);
							btCadastrarCat.setBackground(new Color(118, 184, 184));
							painelCategoria.add(btCadastrarCat);
							
							
							
							JButton btnEditarCat = new JButton("Editar");
							btnEditarCat.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									atualizaCamposCat();
									if (camposEstaoCorretosCat(camposCorretosCat)) {
										cat.setDescricao(String.valueOf(tfDescricaoCat.getText()));
										cat.setDesconto(Double.parseDouble(tfDescontoCat.getText()));
										cat.setNome(String.valueOf(tfNomeCat.getText()));
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
							btnEditarCat.setForeground(new Color(75, 80, 85));
							btnEditarCat.setFont(new Font("Roboto", Font.PLAIN, 18));
							btnEditarCat.setBorder(null);
							btnEditarCat.setBackground(new Color(118, 184, 184));
							painelCategoria.add(btnEditarCat);
							
							
	
							JButton btnExcluirCat = new JButton("Excluir");
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
									if (tabelaCategoria.getSelectedRow() != -1){
										Object[] options3 = {"Excluir", "Cancelar"};
										if(JOptionPane.showOptionDialog(null, "Tem certeza que deseja excluir o registro:\n>   " 
												+ tabelaCategoria.getValueAt(tabelaCategoria.getSelectedRow(), 0) + "   -   "
												+ tabelaCategoria.getValueAt(tabelaCategoria.getSelectedRow(), 1), null,
												JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]) == 0){
											try {
											System.out.println("TOP");
												cat.setId(Integer.parseInt(tfIdCat.getText()));
												
												catDao.deletarCategoria(cat);
												
											
											} catch (Exception e1) {
												JOptionPane.showMessageDialog(null, e1.getMessage());
											}
											atualizarTabelaCat();
											limparCat();
										}
									}
									else{
										JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
									}
								}
							});
							btnExcluirCat.setBounds(728, 362, 215, 54);
							btnExcluirCat.setForeground(new Color(75, 80, 85));
							btnExcluirCat.setFont(new Font("Roboto", Font.PLAIN, 18));
							btnExcluirCat.setBorder(null);
							btnExcluirCat.setBackground(corGeral);
							painelCategoria.add(btnExcluirCat);
							
							
							
							
							
							JButton btnLimparCat = new JButton("Limpar");
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
							btnLimparCat.setForeground(new Color(75, 80, 85));
							btnLimparCat.setFont(new Font("Roboto", Font.PLAIN, 18));
							btnLimparCat.setBorder(null);
							btnLimparCat.setBackground(new Color(118, 184, 184));
							painelCategoria.add(btnLimparCat);
							
							
							
							
							

							
							panelImgNomeCat.setBounds(6, 142, 27, 26);
							painelCategoria.add(panelImgNomeCat);
							panelImgNomeCat.setBackground(Color.WHITE);
							panelImgNomeCat.setForeground(Color.WHITE);
							
							JLabel labelXnomeCat = new JLabel("");
							labelXnomeCat.setToolTipText("O nome \u00E9 obrigat\u00F3rio e n\u00E3o deve conter numeros e  simbolos");
							labelXnomeCat.setBackground(Color.WHITE);
							labelXnomeCat.setForeground(Color.WHITE);
							labelXnomeCat.setIcon(new ImageIcon(imagemX));
							panelImgNomeCat.add(labelXnomeCat);
							labelXnomeCat.setIcon(new ImageIcon(imagemX));
							
							panelImgDescontoCat.setBounds(6, 194, 27, 26);
							painelCategoria.add(panelImgDescontoCat);
							panelImgDescontoCat.setForeground(Color.WHITE);
							panelImgDescontoCat.setBackground(Color.WHITE);
							
							JLabel labelXDescontoCat = new JLabel(new ImageIcon(imagemX));
							labelXDescontoCat.setToolTipText("O email \u00E9 obrigat\u00F3rio");
							panelImgDescontoCat.add(labelXDescontoCat);
							
							panelImgDescCat.setForeground(Color.WHITE);
							panelImgDescCat.setBackground(Color.WHITE);
							panelImgDescCat.setBounds(667, 142, 27, 26);
							painelCategoria.add(panelImgDescCat);
							
							JLabel labelXDescCat = new JLabel(new ImageIcon(imagemX));
							labelXDescCat.setToolTipText("O email \u00E9 obrigat\u00F3rio");
							panelImgDescCat.add(labelXDescCat);
							
							
							

							tfIdCat = new JTextField();
							tfIdCat.setBounds(167, 255, 55, 32);
							tfIdCat.setForeground(new Color(75, 80, 85));
							tfIdCat.setFont(new Font("Roboto", Font.PLAIN, 20));
							tfIdCat.setBackground(painelCategoria.getBackground());
							tfIdCat.setBorder(null);
							painelCategoria.add(tfIdCat);
							tfIdCat.setHorizontalAlignment(SwingConstants.CENTER);
							try {
								tfIdCat.setText(String.valueOf(catDao.RetornarProximoidCategoria()));
							} catch (Exception e2) {}
							tfIdCat.setEditable(false);
							tfIdCat.setColumns(10);
							
							
	
							
							
							
							
							
							
							tfBuscaNomeCat = new JTextField();
							tfBuscaNomeCat.addFocusListener(new FocusAdapter() {
								@Override
								public void focusLost(FocusEvent e) {
									tfBuscaNomeCat.setText(null);
								}
								
							});
							tfBuscaNomeCat.setForeground(new Color(75, 80, 85));
							tfBuscaNomeCat.setFont(new Font("Roboto", Font.PLAIN, 18));
							tfBuscaNomeCat.setColumns(10);
							tfBuscaNomeCat.setBorder(null);
							tfBuscaNomeCat.setBackground(Color.WHITE);
							tfBuscaNomeCat.setBounds(863, 466, 418, 32);
							
							tfBuscaNomeCat.addCaretListener(new CaretListener() {
								public void caretUpdate(CaretEvent arg0) {
									
									//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por nome
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
									
									//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por codigo
									TableRowSorter<TableModel> filtro = null;  
									DefaultTableModel model = (DefaultTableModel) tabelaCategoria.getModel();  
									filtro = new TableRowSorter<TableModel>(model);  
									tabelaCategoria.setRowSorter(filtro);
									
									if (tfBuscaIdCat.getText().length() == 0) {
										filtro.setRowFilter(null);
									} else {  
										filtro.setRowFilter(RowFilter.regexFilter("(?i)"+ tfBuscaIdCat.getText(), 0));  
									}  
								}
							});
							tfBuscaIdCat.setForeground(new Color(75, 80, 85));
							tfBuscaIdCat.setColumns(10);
							tfBuscaIdCat.setBorder(null);
							tfBuscaIdCat.setBounds(158, 468, 418, 31);
							painelCategoria.add(tfBuscaIdCat);
							
							
							
							
							tfNomeCat = new JTextField();
							tfNomeCat.setBounds(167, 141, 409, 32);
							painelCategoria.add(tfNomeCat);
							tfNomeCat.setForeground(corTexto);
							tfNomeCat.setFont(new Font("Roboto", Font.PLAIN, 20));
							tfNomeCat.setBorder(null);
							tfNomeCat.setBackground(painelCategoria.getBackground());
							tfNomeCat.addFocusListener(new FocusAdapter() {
								@Override
								public void focusLost(FocusEvent arg0) {
									camposCorretosCat[0] = (tfNomeCat.getText().matches("[\\p{L}\\s]+")) ?  true: false;
									
									if(tfNomeCat.getText().length() < 1) {
										camposCorretosCat[0] = false;
									}
									
									if(camposCorretosCat[0]) {
										spNomeCat.setBackground(corGeral);
										panelImgNomeCat.setVisible(false);

										
									}else {
										spNomeCat.setBackground(corVermelho);
										panelImgNomeCat.setVisible(true);
									}
								}
								@Override
								public void focusGained(FocusEvent arg0) {
									spNomeCat.setBackground(corGeral);
									panelImgNomeCat.setVisible(false);

								}
							});
							tfNomeCat.setColumns(10);
							
							
							try {
								MaskFormatter tfDescontoCatFormatter = new MaskFormatter("##");
								tfDescontoCatFormatter.setPlaceholderCharacter('0');
								tfDescontoCat = new JFormattedTextField(tfDescontoCatFormatter);
								tfDescontoCat.setForeground(corTexto);
								tfDescontoCat.setFont(new Font("Roboto", Font.PLAIN, 20));
								tfDescontoCat.setBorder(null);
								tfDescontoCat.setBackground(painelCategoria.getBackground());
							}catch(Exception e) {}
							tfDescontoCat.addFocusListener(new FocusAdapter() {
								@Override
								public void focusLost(FocusEvent arg0) {
									String numerosDescontoCat = tfDescontoCat.getText();
									numerosDescontoCat = numerosDescontoCat.replaceAll("[^\\d]", "");
									camposCorretos[2] = (numerosDescontoCat.length() < 2) ? false : true;
									spDescontoCat.setBackground(corGeral);
									
									if(camposCorretos[2]) {
										spDescontoCat.setBackground(corGeral);
										panelImgDescontoCat.setVisible(false);
									
									}else {
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
							
							
							
							tfDescricaoCat = 	new JTextField();
							tfDescricaoCat.setOpaque(false);
							tfDescricaoCat.setBounds(876, 142, 417, 32);
							tfDescricaoCat.setForeground(new Color(75, 80, 85));
							tfDescricaoCat.setFont(new Font("Roboto", Font.PLAIN, 18));
							tfDescricaoCat.setColumns(10);
							tfDescricaoCat.setBorder(null);
							tfDescricaoCat.setBackground(painelFornecedor.getBackground());
							tfDescricaoCat.addFocusListener(new FocusAdapter() {
								@Override
								public void focusLost(FocusEvent arg0) {
									camposCorretosCat[1] = (tfDescricaoCat.getText().matches("[\\p{L}\\s]+")) ? true : false;
									
									if(tfDescricaoCat.getText().length() < 1) {
										camposCorretosCat[1] = false;
									}
									
									if(camposCorretosCat[1]) {
										spDesc1Cat.setBackground(corGeral);
										panelImgDescCat.setVisible(false);
									}else {
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
							
							
=======
		painelFornecedor = new JPanel();
		painelFornecedor.setBackground(Color.WHITE);
		painelFornecedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painelFornecedor);
		
>>>>>>> 7fd443ed37f79ab4b3bf63d8127ad89bf7c3d8f7
		painelFornecedor.setLayout(null);
		painelFornecedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelFornecedor.setBackground(Color.WHITE);
		painelFornecedor.setBounds(0, 0, 1386, 886);
		painelPrincipal.add(painelFornecedor);

		
		
				
		
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(38, 136, 60, 42);
		lblNome.setForeground(new Color(75, 80, 85));
		lblNome.setFont(new Font("Roboto", Font.PLAIN, 20));
		painelFornecedor.add(lblNome);
		
		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setBounds(38, 243, 119, 42);
		lblCnpj.setForeground(new Color(75, 80, 85));
		lblCnpj.setFont(new Font("Roboto", Font.PLAIN, 20));
		painelFornecedor.add(lblCnpj);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(38, 190, 119, 42);
		lblEmail.setForeground(new Color(75, 80, 85));
		lblEmail.setFont(new Font("Roboto", Font.PLAIN, 20));
		painelFornecedor.add(lblEmail);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(38, 299, 119, 42);
		lblTelefone.setForeground(new Color(75, 80, 85));
		lblTelefone.setFont(new Font("Roboto", Font.PLAIN, 20));
		painelFornecedor.add(lblTelefone);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(774, 148, 69, 42);
		lblEstado.setForeground(new Color(75, 80, 85));
		lblEstado.setFont(new Font("Roboto", Font.PLAIN, 18));
		painelFornecedor.add(lblEstado);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(774, 244, 79, 42);
		lblBairro.setForeground(new Color(75, 80, 85));
		lblBairro.setFont(new Font("Roboto", Font.PLAIN, 18));
		painelFornecedor.add(lblBairro);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(774, 196, 119, 42);
		lblCidade.setForeground(new Color(75, 80, 85));
		lblCidade.setFont(new Font("Roboto", Font.PLAIN, 18));
		painelFornecedor.add(lblCidade);
		
		JLabel lblRua = new JLabel("Rua");
		lblRua.setBounds(774, 296, 119, 42);
		lblRua.setForeground(new Color(75, 80, 85));
		lblRua.setFont(new Font("Roboto", Font.PLAIN, 18));
		painelFornecedor.add(lblRua);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(1187, 136, 42, 42);
		lblId.setForeground(new Color(75, 80, 85));
		lblId.setFont(new Font("Roboto", Font.PLAIN, 20));
		painelFornecedor.add(lblId);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setBounds(640, 136, 119, 42);
		lblEndereo.setForeground(new Color(75, 80, 85));
		lblEndereo.setFont(new Font("Roboto", Font.PLAIN, 20));
		painelFornecedor.add(lblEndereo);
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setForeground(new Color(75, 80, 85));
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 24));
		lblBuscar.setBounds(38, 427, 119, 42);
		painelFornecedor.add(lblBuscar);
		
		
		JLabel lblBuscaId = new JLabel("ID");
		lblBuscaId.setForeground(new Color(75, 80, 85));
		lblBuscaId.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblBuscaId.setBounds(65, 467, 119, 42);
		painelFornecedor.add(lblBuscaId);
		
		JLabel lblBuscaNome = new JLabel("Nome");
		lblBuscaNome.setForeground(new Color(75, 80, 85));
		lblBuscaNome.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblBuscaNome.setBounds(774, 461, 79, 42);
		painelFornecedor.add(lblBuscaNome);
		
		spNome.setBounds(167, 176, 409, 2);
		spNome.setBackground(new Color(176, 176, 176));
		painelFornecedor.add(spNome);
		spCNPJ.setBounds(167, 283, 409, 2);
		spCNPJ.setBackground(new Color(176, 176, 176));
		painelFornecedor.add(spCNPJ);
		spId.setBounds(1230, 176, 55, 2);
		spId.setBackground(new Color(176, 176, 176));
		painelFornecedor.add(spId);
		
		spEmail.setBounds(167, 230, 407, 2);
		spEmail.setBackground(new Color(176, 176, 176));
		painelFornecedor.add(spEmail);
		
		spTelefone.setBounds(167, 339, 409, 2);
		spTelefone.setBackground(new Color(176, 176, 176));
		painelFornecedor.add(spTelefone);
		
		spCidade.setBounds(867, 230, 418, 2);
		spCidade.setBackground(new Color(176, 176, 176));
		painelFornecedor.add(spCidade);
		
		spBairro.setBounds(867, 283, 418, 2);
		spBairro.setBackground(new Color(176, 176, 176));
		painelFornecedor.add(spBairro);
		
		spRua.setBounds(868, 339, 417, 2);
		spRua.setBackground(new Color(176, 176, 176));
		painelFornecedor.add(spRua);
		
		
		
		JSeparator spBuscaNome = new JSeparator();
		spBuscaNome.setBackground(new Color(176, 176, 176));
		spBuscaNome.setBounds(867, 500, 418, 2);
		painelFornecedor.add(spBuscaNome);
		
		JSeparator spBuscaID = new JSeparator();
		spBuscaID.setBackground(new Color(176, 176, 176));
		spBuscaID.setBounds(158, 501, 418, 2);
		painelFornecedor.add(spBuscaID);
		
		cbEstado_1.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		
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
						cbEstado_1.setSelectedItem(String.valueOf(tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 8)));
					}
				});
				scrollPane.setViewportView(tabelaFornecedor);
				tabelaFornecedor.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Codigo", "Nome", "CNPJ", "Email","Telefone", "Rua","Bairro","Cidade","Estado"
					}
				));
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
						if (Integer.parseInt(tfId.getText())==fornDao.RetornarProximoidFornecedor()) {
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
							}else {
								JOptionPane.showMessageDialog(null, "Erro, um ou mais campos não foram preenchidos corretamente");
							}
						}else {
							JOptionPane.showMessageDialog(null,"Este ID já esta cadastrado!!\nResetando... ");
							limpar();
						}
					} catch (Exception e1) {}
					
				
				atualizarTabela();
				limpar();
			}
			
		});
		btCadastrar.setForeground(new Color(75, 80, 85));
		btCadastrar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btCadastrar.setBorder(null);
		btCadastrar.setBackground(new Color(118, 184, 184));
		painelFornecedor.add(btCadastrar);
		
		
		
		JButton btnEditar = new JButton("Editar");
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
					} catch (Exception e) {}
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
		btnEditar.setForeground(new Color(75, 80, 85));
		btnEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnEditar.setBorder(null);
		btnEditar.setBackground(new Color(118, 184, 184));
		painelFornecedor.add(btnEditar);
		
		
	
		JButton btnExcluir = new JButton("Excluir");
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
				if (tabelaFornecedor.getSelectedRow() != -1){
					Object[] options3 = {"Excluir", "Cancelar"};
					if(JOptionPane.showOptionDialog(null, "Tem certeza que deseja excluir o registro:\n>   " 
							+ tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 0) + "   -   "
							+ tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 1), null,
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]) == 0){
						try {
						
							forn.setId(Integer.parseInt(tfId.getText()));
							
							fornDao.deletarFornecedor(forn);
							
						
							atualizarTabela();
							limpar();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
				}
			}
		});
		btnExcluir.setBounds(728, 362, 215, 54);
		btnExcluir.setForeground(new Color(75, 80, 85));
		btnExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnExcluir.setBorder(null);
		btnExcluir.setBackground(corGeral);
		painelFornecedor.add(btnExcluir);
		
		
		
		
		
		JButton btnLimpar = new JButton("Limpar");
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
		btnLimpar.setForeground(new Color(75, 80, 85));
		btnLimpar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnLimpar.setBorder(null);
		btnLimpar.setBackground(new Color(118, 184, 184));
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
		tfId.setForeground(new Color(75, 80, 85));
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
				
				if(tfNome.getText().length() < 1) {
					camposCorretos[0] = false;
				}
				
				if(camposCorretos[0]) {
					spNome.setBackground(corGeral);
					panelImgNome.setVisible(false);
					
				}else {
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
		tfEmail.setForeground(new Color(75, 80, 85));
		tfEmail.setFont(new Font("Roboto", Font.PLAIN, 20));
		tfEmail.setColumns(10);
		tfEmail.setBorder(null);
		
		
		
		tfEmail.setBackground(painelFornecedor.getBackground());
		tfEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				camposCorretos[1] = (tfEmail.getText().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) ? true : false;
				
				if(tfEmail.getText().length() < 1) {
					camposCorretos[1] = false;
				}
				
				if(camposCorretos[1]) {
					spEmail.setBackground(corGeral);
					panelImgEmail.setVisible(false);
					
				}else {
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
		}catch(Exception e) {}
		tfCNPJ.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String numerosCNPJ = tfCNPJ.getText();
				numerosCNPJ = numerosCNPJ.replaceAll("[^\\d]", "");
				camposCorretos[2] = (numerosCNPJ.length() < 11) ? false : true;
				spCNPJ.setBackground(corGeral);
				
				if(camposCorretos[2]) {
					spCNPJ.setBackground(corGeral);
					panelImgCNPJ.setVisible(false);
				
				}else {
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
		}catch(Exception e) {}
		tfTelefone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String numerosTelefone = tfTelefone.getText();
				numerosTelefone = numerosTelefone.replaceAll("[^\\d]", "");
				camposCorretos[3] = (numerosTelefone.length() < 10) ? false : true;
				spTelefone.setBackground(corGeral);
				
				if(camposCorretos[3]) {
					spTelefone.setBackground(corGeral);
					panelImgTelefone.setVisible(false);
					
				}else {
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
		tfCidade.setForeground(new Color(75, 80, 85));
		tfCidade.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfCidade.setColumns(10);
		tfCidade.setBorder(null);
		tfCidade.setBackground(painelFornecedor.getBackground());
		tfCidade.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				camposCorretos[4] = (tfCidade.getText().matches("[\\p{L}\\s]+")) ? true : false;
				
				if(tfCidade.getText().length() < 1) {
					camposCorretos[4] = false;
				}
				
				if(camposCorretos[4]) {
					spCidade.setBackground(corGeral);
					panelImgCidade.setVisible(false);
				}else {
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
		tfBairro.setForeground(new Color(75, 80, 85));
		tfBairro.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfBairro.setColumns(10);
		tfBairro.setBorder(null);
		tfBairro.setBackground(painelFornecedor.getBackground());
		tfBairro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				camposCorretos[5] = (tfBairro.getText().matches("[\\p{L}\\s]+")) ? true : false;
				
				if(tfBairro.getText().length() < 1) {
					camposCorretos[5] = false;
				
				}
				
				if(camposCorretos[5]) {
					spBairro.setBackground(corGeral);
					panelImgBairro.setVisible(false);
				}else {
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
		
		
		
		
		
	
		
		
		
		
		tfRua = 	new JTextField();
		tfRua.setBounds(868, 305, 417, 32);
		tfRua.setForeground(new Color(75, 80, 85));
		tfRua.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfRua.setColumns(10);
		tfRua.setBorder(null);
		tfRua.setBackground(painelFornecedor.getBackground());
		tfRua.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				camposCorretos[6] = (tfRua.getText().matches("[\\p{L}\\s]+")) ? true : false;
				
				if(tfRua.getText().length() < 1) {
					camposCorretos[6] = false;
				}
				
				if(camposCorretos[6]) {
					spRua.setBackground(corGeral);
					panelImgRua.setVisible(false);
					
				}else {
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
		tfBuscaNome.setForeground(new Color(75, 80, 85));
		tfBuscaNome.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfBuscaNome.setColumns(10);
		tfBuscaNome.setBorder(null);
		tfBuscaNome.setBackground(Color.WHITE);
		tfBuscaNome.setBounds(863, 466, 418, 32);
		
		tfBuscaNome.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por nome
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
						
						//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por codigo
						TableRowSorter<TableModel> filtro = null;  
						DefaultTableModel model = (DefaultTableModel) tabelaFornecedor.getModel();  
						filtro = new TableRowSorter<TableModel>(model);  
						tabelaFornecedor.setRowSorter(filtro);
						
						if (tfBuscaId.getText().length() == 0) {
							filtro.setRowFilter(null);
						} else {  
							filtro.setRowFilter(RowFilter.regexFilter("(?i)"+ tfBuscaId.getText(), 0));  
						}  
					}
				});
				tfBuscaId.setToolTipText("");
				tfBuscaId.setForeground(new Color(75, 80, 85));
				tfBuscaId.setFont(new Font("Roboto", Font.PLAIN, 18));
				tfBuscaId.setColumns(10);
				tfBuscaId.setBorder(null);
				tfBuscaId.setBackground(Color.WHITE);
				tfBuscaId.setBounds(158, 468, 418, 31);
				painelFornecedor.add(tfBuscaId);

		//tela Inicia Maximizada
		Rectangle area = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		setMaximizedBounds(area);
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		
		
		
		
		
		
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
		}catch(Exception e) {}
		
		
		try {
			MaskFormatter tfTelefoneFormatter = new MaskFormatter("(##) ####-####");
			tfTelefoneFormatter.setPlaceholderCharacter('_');
		}catch(Exception e) {}
		
	
		
		
		
		
	}
	
	
		
		//metodos
		
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
			for (int x=0; x!=fornecedor.size(); x++)
				{
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
			for(boolean a : camposCorretos) {
				x = x && a;
			}
			return x;
		}
		
		public void atualizarCampos() {
			camposCorretos[0] = (tfNome.getText().matches("[\\p{L}\\s]+")) ? true : false;
			
			if(tfNome.getText().length() < 1) {
				camposCorretos[0] = false;
			}
			
			if(camposCorretos[0]) {
				spNome.setBackground(corGeral);
				panelImgNome.setVisible(false);
				
			}else {
				spNome.setBackground(corVermelho);
				panelImgNome.setVisible(true);
			}
	
			
			camposCorretos[1] = (tfEmail.getText().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) ? true : false;
			
			if(tfEmail.getText().length() < 1) {
				camposCorretos[1] = false;
			}
			
			if(camposCorretos[1]) {
				spEmail.setBackground(corGeral);
				panelImgEmail.setVisible(false);
				
			}else {
				spEmail.setBackground(corVermelho);
				panelImgEmail.setVisible(true);
			}
			
			
			
			String numerosCNPJ = tfCNPJ.getText();
			numerosCNPJ = numerosCNPJ.replaceAll("[^\\d]", "");
			camposCorretos[2] = (numerosCNPJ.length() < 11) ? false : true;
			spCNPJ.setBackground(corGeral);
			
			if(camposCorretos[2]) {
				spCNPJ.setBackground(corGeral);
				panelImgCNPJ.setVisible(false);
			
			}else {
				spCNPJ.setBackground(corVermelho);
				panelImgCNPJ.setVisible(true);
			}
			
			
			String numerosTelefone = tfTelefone.getText();
			numerosTelefone = numerosTelefone.replaceAll("[^\\d]", "");
			camposCorretos[3] = (numerosTelefone.length() < 10) ? false : true;
			spTelefone.setBackground(corGeral);
			
			if(camposCorretos[3]) {
				spTelefone.setBackground(corGeral);
				panelImgTelefone.setVisible(false);
				
			}else {
				spTelefone.setBackground(corVermelho);
				panelImgTelefone.setVisible(true);

			}
			
			
			camposCorretos[4] = (tfCidade.getText().matches("[\\p{L}\\s]+")) ? true : false;
			
			if(tfCidade.getText().length() < 1) {
				camposCorretos[4] = false;
			}
			
			if(camposCorretos[4]) {
				spCidade.setBackground(corGeral);
				panelImgCidade.setVisible(false);
			}else {
				spCidade.setBackground(corVermelho);
				panelImgCidade.setVisible(true);
			}
		
			
			
			
			camposCorretos[5] = (tfBairro.getText().matches("[\\p{L}\\s]+")) ? true : false;
			
			if(tfBairro.getText().length() < 1) {
				camposCorretos[5] = false;
			
			}
			
			if(camposCorretos[5]) {
				spBairro.setBackground(corGeral);
				panelImgBairro.setVisible(false);
			}else {
				spBairro.setBackground(corVermelho);
				panelImgBairro.setVisible(true);
				
			}
			
			camposCorretos[6] = (tfRua.getText().matches("[\\p{L}\\s]+")) ? true : false;
			
			if(tfRua.getText().length() < 1) {
				camposCorretos[6] = false;
			}
			
			if(camposCorretos[6]) {
				spRua.setBackground(corGeral);
				panelImgRua.setVisible(false);
				
			}else {
				spRua.setBackground(corVermelho);
				panelImgRua.setVisible(true);
			}
		}
		
		public void clickarFornecedor() {
			painelFornecedor.setVisible(true);
			painelCategoria.setVisible(false);
			painelProdutos.setVisible(false);
		//	setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tfNome, tfEmail, tfCNPJ , tfTelefone, cbEstado_1, tfCidade, tfBairro, tfRua,tfBuscaId,tfBuscaNome}));

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
		
					btnCategoria.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY,0.2f));

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
					btnCategoria.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY,0.2f));

				}
			});
			btnProdutos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
		
					btnProdutos.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY,0.2f));

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
					btnProdutos.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY,0.2f));

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
		
					btnFornecedor.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY,0.2f));

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
					btnFornecedor.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY,0.2f));

				}
			});
			btnProdutos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
		
					btnProdutos.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY,0.2f));

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
					btnProdutos.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY,0.2f));

				}
			});
			
		}
		
		
		public void clickarProdutos() {
			painelCategoria.setVisible(false);
			painelFornecedor.setVisible(false);
			painelProdutos.setVisible(true);
			btnFornecedor.setBackground(Color.LIGHT_GRAY);
			btnCategoria.setBackground(Color.LIGHT_GRAY);
			
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
		
					btnCategoria.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY,0.2f));

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
					btnCategoria.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY,0.2f));

				}
			});
			btnFornecedor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
		
					btnFornecedor.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY,0.2f));

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
					btnFornecedor.setBackground(Cor.corMaisClara(Color.LIGHT_GRAY,0.2f));

				}
			});
			
		}
		public void atualizarTabelaCat() {
			try {
				categoria = catDao.buscarTodos();
				DefaultTableModel model = (DefaultTableModel) tabelaCategoria.getModel();
				model.setNumRows(0);
			for (int x=0; x!=categoria.size(); x++)
				{
					model.addRow((Object[]) categoria.get(x));
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		private void limparCat() {
			
			try {
				tfIdCat.setText(String.valueOf(catDao.RetornarProximoidCategoria()));
				tfNomeCat.setText(null);
				tfDescricaoCat.setText(null);
				tfDescontoCat.setText("0");
			} catch (Exception e) {}
			atualizarTabelaCat();
		}

		
		public void setCamposFromTabelaCat() {
			tfIdCat.setText(String.valueOf(tabelaCategoria.getValueAt(tabelaCategoria.getSelectedRow(), 0)));
			tfNomeCat.setText(String.valueOf(tabelaCategoria.getValueAt(tabelaCategoria.getSelectedRow(), 3)));
			tfDescontoCat.setText(String.valueOf(tabelaCategoria.getValueAt(tabelaCategoria.getSelectedRow(), 2)));
			tfDescricaoCat.setText(String.valueOf(tabelaCategoria.getValueAt(tabelaCategoria.getSelectedRow(), 1)));			
			
		}
		public void deixarCertoCat() {
			
			spNomeCat.setBackground(corGeral);
			panelImgNomeCat.setVisible(false);
			spDescontoCat.setBackground(corGeral);
			panelImgDescontoCat.setVisible(false);

			spDesc1Cat.setBackground(corGeral);
			spDesc2Cat.setBackground(corGeral);
			spDesc3Cat.setBackground(corGeral);
			panelImgDescCat.setVisible(false);
			
			
		}
		boolean camposEstaoCorretosCat(boolean[] camposCorretosCat) {
			boolean x = true;
			for(boolean a : camposCorretosCat) {
				x = x && a;
			}
			return x;
		}
		private void atualizaCamposCat() {
			
				
				
				
			
			
			
					camposCorretosCat[0] = (tfNomeCat.getText().matches("[\\p{L}\\s]+")) ?  true: false;
					
					if(tfNomeCat.getText().length() < 1) {
						camposCorretosCat[0] = false;
					}
					
					if(camposCorretosCat[0]) {
						spNomeCat.setBackground(corGeral);
						panelImgNomeCat.setVisible(false);

						
					}else {
						spNomeCat.setBackground(corVermelho);
						panelImgNomeCat.setVisible(true);
					}
				
			

					camposCorretosCat[1] = true ;
					
					if(tfDescricaoCat.getText().length() < 1) {
						camposCorretosCat[1] = false;
					}
					
					if(camposCorretosCat[1]) {
						spDesc1Cat.setBackground(corGeral);
						panelImgDescCat.setVisible(false);
					}else {
						spDesc1Cat.setBackground(corVermelho);
						panelImgDescCat.setVisible(true);
					}
					
					
			
					String numerosDescontoCat = tfDescontoCat.getText();
					numerosDescontoCat = numerosDescontoCat.replaceAll("[^\\d]", "");
					camposCorretosCat[2] = (numerosDescontoCat.length() < 2) ? false : true;
					spDescontoCat.setBackground(corGeral);
					
					if(camposCorretosCat[2]) {
						spDescontoCat.setBackground(corGeral);
						panelImgDescontoCat.setVisible(false);
					
					}else {
						spDescontoCat.setBackground(corVermelho);
						panelImgDescontoCat.setVisible(true);
					}
				
		
				
		}
}

