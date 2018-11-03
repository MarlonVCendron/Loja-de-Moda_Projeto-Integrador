package br.edu.ifcvideira.Jframes;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import org.eclipse.wb.swing.FocusTraversalOnArray;

import br.edu.ifcvideira.Classes.Fornecedor;
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

public class TelaEstoque extends JFrame {

	private JPanel painelFornecedor;
	private JTextField tfNome;
	private JTextField tfEmail;
	private JTextField tfTelefone;
	private JTextField tfBairro;
	private JTextField tfRua;
	private JTextField tfCidade;
	private JTextField tfId;
	private JComboBox cbEstado;

	private FornecedorDao fornDao = new FornecedorDao();
	private Fornecedor forn = new Fornecedor();
	
	Color corTexto = new Color(75, 80, 85);
	Color corGeral = new Color(118, 184, 184);
	Color corSecundaria = Cor.corMaisClara(corGeral, 0.2f);
	Color corTerciaria = Cor.corMaisClara(corGeral, 0.4f);
	Color corSeparador = new Color(176, 176, 176);
	Color corVermelho = new Color (230, 20, 20);
	Point posMouseInicial;
	private JTextField tfCNPJ;
	private List<Object> fornecedor = new ArrayList<Object>();
	private JTable tabelaFornecedor;
	private JTable table;
	private JTable tabela;
	JSeparator spNome = new JSeparator();

	JSeparator spCNPJ = new JSeparator();
	
	JSeparator spId = new JSeparator();
	JSeparator spEmail = new JSeparator();
	JSeparator spTelefone = new JSeparator();
	JSeparator spCidade = new JSeparator();
	JSeparator spBairro = new JSeparator();
	JSeparator spRua = new JSeparator();
	JPanel panelImgCNPJ = new JPanel();

	JPanel panelImgNome = new JPanel();
	JPanel panelImgEmail = new JPanel();
	JPanel panelImgTelefone = new JPanel();
	JPanel panelImgCidade = new JPanel();
	JPanel panelImgBairro = new JPanel();
	JPanel panelImgRua = new JPanel();

	ImageIcon imageIconX = new ImageIcon(TelaLogin.class.getResource("/br/edu/ifcvideira/img/xerro.png"));
	Image imagemX = imageIconX.getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
	
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
			}
		});
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1386, 886);
		painelFornecedor = new JPanel();
		painelFornecedor.setBackground(Color.WHITE);
		painelFornecedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painelFornecedor);
		
		
		
		//Variáveis que cuidam para que os campos sejam preenchidos adequadamente
		boolean[] camposCorretos = {false, false, false, false, false, false};
		painelFornecedor.setLayout(null);
		
				
		
		
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
		
		JButton btnFornecedor = new JButton("Fornecedor");
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
		btnFornecedor.setBounds(38, 57, 400, 54);
		btnFornecedor.setForeground(new Color(75, 80, 85));
		btnFornecedor.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnFornecedor.setBorder(null);
		btnFornecedor.setBackground(new Color(118, 184, 184));
		painelFornecedor.add(btnFornecedor);
		
		JButton btnCategoria = new JButton("Categoria");
		btnCategoria.setBounds(482, 57, 400, 54);
		btnCategoria.setForeground(new Color(75, 80, 85));
		btnCategoria.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnCategoria.setBorder(null);
		btnCategoria.setBackground(Color.LIGHT_GRAY);
		painelFornecedor.add(btnCategoria);
		
		JButton btnProdutos = new JButton("Produtos");
		btnProdutos.setBounds(927, 57, 400, 54);
		btnProdutos.setForeground(new Color(75, 80, 85));
		btnProdutos.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnProdutos.setBorder(null);
		btnProdutos.setBackground(Color.LIGHT_GRAY);
		painelFornecedor.add(btnProdutos);
		
		JComboBox cbEstado = new JComboBox();
		cbEstado.setBounds(863, 158, 55, 20);
		cbEstado.setBackground(Color.WHITE);
		cbEstado.setForeground(new Color(0, 0, 0));
		cbEstado.setFont(new Font("Roboto", Font.PLAIN, 18));
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		painelFornecedor.add(cbEstado);
		
		
		
		
		//
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 551, 1301, 177);
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
				
				
		
	
		
		
		

		
		
	
		
	
		
		
		
		JButton btSair = new JButton("X");
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
		btSair.setBounds(1326, 0, 42, 42);
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		btSair.setMaximumSize(new Dimension(80, 50));
		btSair.setFont(new Font("Roboto", Font.PLAIN, 13));
		btSair.setBorder(null);
		btSair.setBackground(new Color(118, 184, 184));
		painelFornecedor.add(btSair);
		
		
		
		JButton btMinimizar = new JButton("-");
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
		btMinimizar.setBounds(1248, 0, 42, 42);
		btMinimizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		btMinimizar.setMaximumSize(new Dimension(80, 50));
		btMinimizar.setFont(new Font("Roboto", Font.PLAIN, 15));
		btMinimizar.setBorder(null);
		btMinimizar.setBackground(new Color(118, 184, 184));
		painelFornecedor.add(btMinimizar);
		
		
		

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
			e1.printStackTrace();
		}
		tfId.setEditable(false);
		tfId.setColumns(10);
		JButton btCadastrar = new JButton("Cadastrar");
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
		btCadastrar.setBounds(87, 363, 215, 54);
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (camposEstaoCorretos(camposCorretos)) {
					
					
					try {
						forn.setNome(tfNome.getText());
						forn.setCnpj(tfCNPJ.getText());
						forn.setTelefone(tfTelefone.getText());
						forn.setEmail(tfEmail.getText());
						forn.setRua(tfRua.getText());
						forn.setCidade(tfCidade.getText());
						forn.setBairro(tfBairro.getText());
						forn.setEstado(cbEstado.getSelectedItem().toString());
						forn.setId(fornDao.RetornarProximoidFornecedor());
						
						fornDao.CadastrarFornecedor(forn);
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}else {
					
				}
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
			if (camposEstaoCorretos(camposCorretos)) {
					
					forn.setNome(tfNome.getText());
					forn.setCnpj(tfCNPJ.getText());
					forn.setTelefone(tfTelefone.getText());
					forn.setEmail(tfEmail.getText());
					forn.setRua(tfRua.getText());
					forn.setCidade(tfCidade.getText());
					forn.setBairro(tfBairro.getText());
					forn.setEstado(cbEstado.getSelectedItem().toString());
					forn.setId(Integer.parseInt(tfId.getText()));
					
					try {
						fornDao.AlterarFornecedor(forn);
					} catch (Exception e) {
						
					
				}
				
				
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
		btnEditar.setBounds(414, 363, 215, 54);
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
		btnExcluir.setBounds(739, 363, 215, 54);
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
		btnLimpar.setBounds(1059, 363, 215, 54);
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
		
		JPanel panelCampos = new JPanel();
		panelCampos.setForeground(Color.WHITE);
		panelCampos.setBackground(corTerciaria);
		painelFornecedor.add(panelCampos);
		panelCampos.setLayout(null);
		
		
		
		
		
		JLabel imgXNome = new JLabel(new ImageIcon(imagemX));
		imgXNome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgXNome.setBackground(new Color(255, 255, 255));
		imgXNome.setToolTipText("O nome é obrigatório e não pode conter símbolos");
		
		
		
		
		

		
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
					spNome.setBackground(corSeparador);
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
					spEmail.setBackground(corSeparador);
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
				spCNPJ.setBackground(corSeparador);
				
				if(camposCorretos[2]) {
					spCNPJ.setBackground(corSeparador);
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
				spTelefone.setBackground(corSeparador);
				
				if(camposCorretos[3]) {
					spTelefone.setBackground(corSeparador);
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
					spCidade.setBackground(corSeparador);
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
					spBairro.setBackground(corSeparador);
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
		
		
		
		
		
	
		
		
		
		
		tfRua = new JTextField();
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
					spRua.setBackground(corSeparador);
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
		

			
		
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tfNome, tfEmail, tfCNPJ , tfTelefone, cbEstado, tfCidade, tfBairro, tfRua}));

		
		
		
		//tela Inicia Maximizada
		Rectangle area = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		setMaximizedBounds(area);
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
	
		
		
	
	}
	
	
		
		//metodos
		
		public void deixarCerto() {
			spCNPJ.setBackground(corGeral);
			panelImgCNPJ.setVisible(false);
			spTelefone.setBackground(corSeparador);
			panelImgTelefone.setVisible(false);
			spRua.setBackground(corSeparador);
			panelImgRua.setVisible(false);
			spNome.setBackground(corSeparador);
			panelImgNome.setVisible(false);
			spEmail.setBackground(corSeparador);
			panelImgEmail.setVisible(false);
			spCidade.setBackground(corSeparador);
			panelImgCidade.setVisible(false);
			spBairro.setBackground(corSeparador);
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
		
		public void sair() {
			System.exit(0);
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
			//cbEstado.set;
			deixarCerto();
			
		}
		
		boolean camposEstaoCorretos(boolean[] camposCorretos) {
			boolean x = true;
			for(boolean a : camposCorretos) {
				x = x && a;
			}
			return x;
		}
}

