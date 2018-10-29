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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import br.edu.ifcvideira.Classes.Fornecedor;
import br.edu.ifcvideira.DAOs.FornecedorDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField.AbstractFormatter;

public class TelaEstoque extends JFrame {

	private JPanel painelFornecedor;
	private JTextField tfNome;
	private JTextField tfEmail;
	private JTextField tfTelefone;
	private JTextField tfBairro;
	private JTextField tfRua;
	private JTextField tfCidade;
	private JTextField tfId;

	private FornecedorDao fornDao = new FornecedorDao();
	private Fornecedor forn = new Fornecedor();
	
	Color corTexto = new Color(75, 80, 85);
	Color corGeral = new Color(118, 184, 184);
	//Color corSecundaria = corMaisClara(corGeral, 0.2f);
	Color corSeparador = new Color(176, 176, 176);
	Color corVermelho = new Color (230, 20, 20);
	Point posMouseInicial;
	private JTextField tfCNPJ;
	private List<Object> fornecedor = new ArrayList<Object>();

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEstoque frame = new TelaEstoque();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1386, 886);
		painelFornecedor = new JPanel();
		painelFornecedor.setBackground(Color.WHITE);
		painelFornecedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painelFornecedor);
		painelFornecedor.setLayout(null);
		
		
		
		//Variáveis que cuidam para que os campos sejam preenchidos adequadamente
		boolean[] camposCorretos = {false, false, false, false, false, false, false, false};
		
		JSeparator spNome = new JSeparator();
		spNome.setBackground(new Color(176, 176, 176));
		spNome.setBounds(609, 176, 215, 2);
		painelFornecedor.add(spNome);
		
		JSeparator spCNPJ = new JSeparator();
		spCNPJ.setBackground(new Color(176, 176, 176));
		spCNPJ.setBounds(609, 276, 215, 2);
		painelFornecedor.add(spCNPJ);
		
		JSeparator spId = new JSeparator();
		spId.setBackground(new Color(176, 176, 176));
		spId.setBounds(961, 176, 55, 2);
		painelFornecedor.add(spId);
		
		JSeparator spEmail = new JSeparator();
		spEmail.setBackground(new Color(176, 176, 176));
		spEmail.setBounds(609, 230, 407, 2);
		painelFornecedor.add(spEmail);
		
		JSeparator spTelefone = new JSeparator();
		spTelefone.setBackground(new Color(176, 176, 176));
		spTelefone.setBounds(1047, 276, 215, 2);
		painelFornecedor.add(spTelefone);
		
		JSeparator spCidade = new JSeparator();
		spCidade.setBackground(new Color(176, 176, 176));
		spCidade.setBounds(1086, 326, 176, 2);
		painelFornecedor.add(spCidade);
		
		JSeparator spBairro = new JSeparator();
		spBairro.setBackground(new Color(176, 176, 176));
		spBairro.setBounds(678, 375, 146, 2);
		painelFornecedor.add(spBairro);
		
		JSeparator spRua = new JSeparator();
		spRua.setBackground(new Color(176, 176, 176));
		spRua.setBounds(1086, 375, 176, 2);
		painelFornecedor.add(spRua);
		
				
		
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(new Color(75, 80, 85));
		lblNome.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblNome.setBounds(480, 136, 119, 42);
		painelFornecedor.add(lblNome);
		
		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setForeground(new Color(75, 80, 85));
		lblCnpj.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblCnpj.setBounds(480, 243, 119, 42);
		painelFornecedor.add(lblCnpj);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(75, 80, 85));
		lblEmail.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblEmail.setBounds(480, 190, 119, 42);
		painelFornecedor.add(lblEmail);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setForeground(new Color(75, 80, 85));
		lblTelefone.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblTelefone.setBounds(918, 243, 119, 42);
		painelFornecedor.add(lblTelefone);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setForeground(new Color(75, 80, 85));
		lblEstado.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEstado.setBounds(607, 294, 69, 42);
		painelFornecedor.add(lblEstado);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setForeground(new Color(75, 80, 85));
		lblCidade.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblCidade.setBounds(1011, 295, 79, 42);
		painelFornecedor.add(lblCidade);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setForeground(new Color(75, 80, 85));
		lblBairro.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblBairro.setBounds(607, 339, 119, 42);
		painelFornecedor.add(lblBairro);
		
		JLabel lblRua = new JLabel("Rua");
		lblRua.setForeground(new Color(75, 80, 85));
		lblRua.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblRua.setBounds(1011, 339, 119, 42);
		painelFornecedor.add(lblRua);
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(new Color(75, 80, 85));
		lblId.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblId.setBounds(918, 136, 42, 42);
		painelFornecedor.add(lblId);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setForeground(new Color(75, 80, 85));
		lblEndereo.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblEndereo.setBounds(480, 294, 119, 42);
		painelFornecedor.add(lblEndereo);
		
		JButton btnForfencedor = new JButton("Fornecedor");
		btnForfencedor.setForeground(new Color(75, 80, 85));
		btnForfencedor.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnForfencedor.setBorder(null);
		btnForfencedor.setBackground(new Color(118, 184, 184));
		btnForfencedor.setBounds(524, 53, 215, 54);
		painelFornecedor.add(btnForfencedor);
		
		JButton btnCategoria = new JButton("Categoria");
		btnCategoria.setForeground(new Color(75, 80, 85));
		btnCategoria.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnCategoria.setBorder(null);
		btnCategoria.setBackground(Color.LIGHT_GRAY);
		btnCategoria.setBounds(808, 53, 215, 54);
		painelFornecedor.add(btnCategoria);
		
		JButton btnProdutos = new JButton("Produtos");
		btnProdutos.setForeground(new Color(75, 80, 85));
		btnProdutos.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnProdutos.setBorder(null);
		btnProdutos.setBackground(Color.LIGHT_GRAY);
		btnProdutos.setBounds(1089, 53, 215, 54);
		painelFornecedor.add(btnProdutos);
		
		JComboBox cbEstado = new JComboBox();
		cbEstado.setBackground(Color.WHITE);
		cbEstado.setForeground(new Color(0, 0, 0));
		cbEstado.setFont(new Font("Roboto", Font.PLAIN, 18));
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cbEstado.setBounds(706, 305, 69, 20);
		painelFornecedor.add(cbEstado);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(118, 184, 184));
		panel.setBounds(-112, 0, 548, 946);
		painelFornecedor.add(panel);
		
		JButton button = new JButton("X");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		button.setMaximumSize(new Dimension(80, 50));
		button.setFont(new Font("Roboto", Font.PLAIN, 13));
		button.setBorder(null);
		button.setBackground(new Color(118, 184, 184));
		button.setBounds(1326, 0, 42, 42);
		painelFornecedor.add(button);
		
		JButton button_1 = new JButton("-");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		button_1.setMaximumSize(new Dimension(80, 50));
		button_1.setFont(new Font("Roboto", Font.PLAIN, 15));
		button_1.setBorder(null);
		button_1.setBackground(new Color(118, 184, 184));
		button_1.setBounds(1248, 0, 42, 42);
		painelFornecedor.add(button_1);
		
		tfNome = new JTextField();
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
					//panelImgNome.setVisible(false);
				}else {
					spNome.setBackground(corVermelho);
				//	panelImgNome.setVisible(true);
				}
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				spNome.setBackground(corGeral);
			}
		});
		tfNome.setColumns(10);
		tfNome.setBounds(609, 142, 215, 32);
		painelFornecedor.add(tfNome);
			
		
		

		
		
		tfEmail = new JTextField();
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
					//panelImgNome.setVisible(false);
				}else {
					spEmail.setBackground(corVermelho);
				//	panelImgNome.setVisible(true);
				}
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				spEmail.setBackground(corGeral);
			}
		});
		tfEmail.setBounds(609, 196, 414, 32);
		painelFornecedor.add(tfEmail);
		
		
		
		
		
		try {
			MaskFormatter tfTelefoneFormatter = new MaskFormatter("(##) ####-####");
			tfTelefoneFormatter.setPlaceholderCharacter('_');
			tfTelefone = new JFormattedTextField(tfTelefoneFormatter);
			tfTelefone.setForeground(corTexto);
			tfTelefone.setFont(new Font("Roboto", Font.PLAIN, 20));
			tfTelefone.setBorder(null);
			tfTelefone.setBackground(painelFornecedor.getBackground());
		}catch(Exception e) {}
		tfTelefone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String numerosTelefone = tfTelefone.getText();
				numerosTelefone = numerosTelefone.replaceAll("[^\\w]", "");
				camposCorretos[3] = (numerosTelefone.length() < 10) ? false : true;
				spTelefone.setBackground(corSeparador);
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				spTelefone.setBackground(corGeral);
			}
		});
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(1047, 243, 215, 32);
		painelFornecedor.add(tfTelefone);
		
		
		
		
		
	
		
		
		
		
		
		tfBairro = new JTextField();
		tfBairro.setForeground(new Color(75, 80, 85));
		tfBairro.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfBairro.setColumns(10);
		tfBairro.setBorder(null);
		tfBairro.setBackground(painelFornecedor.getBackground());
		tfBairro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				camposCorretos[4] = (tfBairro.getText().matches("[\\p{L}\\s]+")) ? true : false;
				
				if(tfBairro.getText().length() < 1) {
					camposCorretos[4] = false;
				}
				
				if(camposCorretos[4]) {
					spBairro.setBackground(corSeparador);
					//panelImgNome.setVisible(false);
				}else {
					spBairro.setBackground(corVermelho);
				//	panelImgNome.setVisible(true);
				}
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				spBairro.setBackground(corGeral);
			}
		});
		tfBairro.setBounds(678, 342, 146, 32);
		painelFornecedor.add(tfBairro);
		
		
		
		
		
		
		tfRua = new JTextField();
		tfRua.setForeground(new Color(75, 80, 85));
		tfRua.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfRua.setColumns(10);
		tfRua.setBorder(null);
		tfRua.setBackground(painelFornecedor.getBackground());
		tfRua.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				camposCorretos[5] = (tfRua.getText().matches("[\\p{L}\\s]+")) ? true : false;
				
				if(tfRua.getText().length() < 1) {
					camposCorretos[5] = false;
				}
				
				if(camposCorretos[5]) {
					spRua.setBackground(corSeparador);
					//panelImgNome.setVisible(false);
				}else {
					spRua.setBackground(corVermelho);
				//	panelImgNome.setVisible(true);
				}
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				spRua.setBackground(corGeral);
			}
		});		
		tfRua.setBounds(1086, 348, 176, 26);
		painelFornecedor.add(tfRua);
		
		tfCidade = new JTextField();
		tfCidade.setForeground(new Color(75, 80, 85));
		tfCidade.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfCidade.setColumns(10);
		tfCidade.setBorder(null);
		tfCidade.setBackground(painelFornecedor.getBackground());
		tfCidade.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				camposCorretos[6] = (tfCidade.getText().matches("[\\p{L}\\s]+")) ? true : false;
				
				if(tfCidade.getText().length() < 1) {
					camposCorretos[6] = false;
				}
				
				if(camposCorretos[6]) {
					spCidade.setBackground(corSeparador);
					//panelImgNome.setVisible(false);
				}else {
					spCidade.setBackground(corVermelho);
				//	panelImgNome.setVisible(true);
				}
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				spCidade.setBackground(corGeral);
			}
		});		tfCidade.setBounds(1086, 294, 176, 31);
		painelFornecedor.add(tfCidade);
		
	
		
		JButton btCadastrar = new JButton("Cadastrar");
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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


					
					
					// chamada do método de cadastro na fornasse Dao
					fornDao.CadastrarFornecedor(forn);
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				//atualizarTabela();
				limpar();
			}
			
		});
		btCadastrar.setForeground(new Color(75, 80, 85));
		btCadastrar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btCadastrar.setBorder(null);
		btCadastrar.setBackground(new Color(118, 184, 184));
		btCadastrar.setBounds(458, 414, 215, 54);
		painelFornecedor.add(btCadastrar);
		try {
			MaskFormatter tfCNPJFormatter = new MaskFormatter("##.###.###/####-##");
			tfCNPJFormatter.setPlaceholderCharacter('_');
			tfCNPJ = new JFormattedTextField(tfCNPJFormatter);
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
				camposCorretos[1] = (numerosCNPJ.length() < 11) ? false : true;
				spCNPJ.setBackground(corSeparador);
				
				if(camposCorretos[1]) {
					spCNPJ.setBackground(corSeparador);
					//panelImgCNPJ.setVisible(false);
				}else {
					spCNPJ.setBackground(corVermelho);
					//panelImgCNPJ.setVisible(true);
				}
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				spCNPJ.setBackground(corGeral);
			}
		});
		tfCNPJ.setColumns(10);
		tfCNPJ.setBounds(609, 243, 215, 32);
		painelFornecedor.add(tfCNPJ);
		
		

		tfId = new JTextField();
		tfId.setForeground(new Color(75, 80, 85));
		tfId.setFont(new Font("Roboto", Font.PLAIN, 20));
		tfId.setBackground(painelFornecedor.getBackground());
		tfId.setBounds(961, 141, 55, 32);		
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
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tfNome, tfEmail, tfCNPJ , tfTelefone, cbEstado, tfCidade, tfBairro, tfRua}));
		
		
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setForeground(new Color(75, 80, 85));
		btnEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnEditar.setBorder(null);
		btnEditar.setBackground(new Color(118, 184, 184));
		btnEditar.setBounds(684, 414, 215, 54);
		painelFornecedor.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");

		btnExcluir.setForeground(new Color(75, 80, 85));
		btnExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnExcluir.setBorder(null);
		btnExcluir.setBackground(new Color(118, 184, 184));
		btnExcluir.setBounds(907, 414, 215, 54);
		painelFornecedor.add(btnExcluir);
		
		
		
		
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			limpar();
			
			}
		});
		btnLimpar.setForeground(new Color(75, 80, 85));
		btnLimpar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnLimpar.setBorder(null);
		btnLimpar.setBackground(new Color(118, 184, 184));
		btnLimpar.setBounds(1132, 414, 215, 54);
		painelFornecedor.add(btnLimpar);
		
		
		
	
		JScrollPane spFornecedor = new JScrollPane();
		spFornecedor.setBounds(458, 517, 889, 214);
		painelFornecedor.add(spFornecedor);
		
		
		
		
		
		JTable tabelaFornecedor = new JTable();
		tabelaFornecedor.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				setCamposFromTabela();
			}

			public void setCamposFromTabela() {
					tfId.setText(String.valueOf(tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 0)));
					tfNome.setText(String.valueOf(tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 1)));
					tfCNPJ.setText(String.valueOf(tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 2)));
					tfEmail.setText(String.valueOf(tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 3)));
					tfTelefone.setText(String.valueOf(tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 4)));
					tfBairro.setText(String.valueOf(tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 5)));
					tfRua.setText(String.valueOf(tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 6)));
					tfCidade.setText(String.valueOf(tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 7)));
				
			}
		});
		spFornecedor.setViewportView(tabelaFornecedor);
		tabelaFornecedor.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nome", "CNPJ","Email","Telefone", "Rua","Bairro","Cidade","Estado"
			}
		));
		
		
		
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

				public void atualizarTabela() {
					try {
						fornecedor= fornDao.buscarTodos();
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
			
		});
		
		
		
		//tela Inicia Maximizada
		Rectangle area = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		setMaximizedBounds(area);
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
	
		
		
	
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
		
		
	
}

