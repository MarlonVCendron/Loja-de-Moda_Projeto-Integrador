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
import br.edu.ifcvideira.Classes.Usuario;
import br.edu.ifcvideira.DAOs.ClienteDao;
import br.edu.ifcvideira.DAOs.UsuarioDao;
import br.edu.ifcvideira.utils.Cor;
import br.edu.ifcvideira.utils.Preferencias;
import br.edu.ifcvideira.utils.Senha;

import javax.swing.Icon;
public class TelaCadastroFuncionario extends JFrame {

	private JPanel contentPane;
	
	private JTextField tfCelular;
	private JTextField tfTelefone;
	private JTextField tfCpf;
	private JTextField tfNome;
	private JTextField tfRg;
	
	private JPanel panelImgNome;
	private JPanel panelImgCpf;
	private JPanel panelImgTelefone;
	private JPanel panelImgRg;
	private JPanel panelImgSenha;
	private JPanel panelImgRepetir;
	private JPanel panelImgEmail;
	
	JComboBox cbTipo = new JComboBox();

	JComboBox cbStatus = new JComboBox();

	
	public static JLabel lblLogo;
	
	
	Color corTexto = new Color(75, 80, 85);
	Color corGeral = new Color(Preferencias.getR(), Preferencias.getG(), Preferencias.getB());
	Color corSecundaria = Cor.corMaisClara(corGeral, 0.2f);
	Color corTerciaria = Cor.corMaisClara(corGeral, 0.4f);
	Color corSeparador = new Color(176, 176, 176);
	Color corVermelho = new Color (230, 20, 20);
	
	Point posMouseInicial;

	private JPasswordField pfSenha;
	private JPasswordField pfRepetir;
	JSeparator spSenha = new JSeparator();
	JSeparator spRepetir = new JSeparator();
	boolean[] camposCorretos = {false, false, false, false, false,false,false};
	
	
	JSeparator spRg = new JSeparator();

	JSeparator spTelefone = new JSeparator();

	JSeparator spCpf = new JSeparator();

	JSeparator spNome = new JSeparator();
	JSeparator spEmail = new JSeparator();

	
	
	Usuario us = new Usuario();
	private static UsuarioDao usDao = new UsuarioDao();


	
	ImageIcon imageIconX = new ImageIcon(TelaLogin.class.getResource("/br/edu/ifcvideira/img/xerro.png"));
	Image imagemX = imageIconX.getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
	private JTextField tfEmail;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroFuncionario frame = new TelaCadastroFuncionario();
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
	public TelaCadastroFuncionario() {
		setName("Tela Cadastro Cliente");
		Dimension tela = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int largura = 512;
		int altura = 700;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((tela.width / 2) - (largura / 2), (tela.height / 2) - (altura / 2), largura, altura);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setUndecorated(true);
		contentPane.setLayout(null);
		setResizable(false);
		getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, corGeral));
		
		ImageIcon imageIconLogo = new ImageIcon(Preferencias.getImagem());
		Image imagemLogo = imageIconLogo.getImage().getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH);
		setIconImage(imagemLogo);
		
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 0, 512, 32);
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
		btnX.setBounds(470, 0, 42, 30);
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
		btnMinimizar.setBounds(424, 0, 42, 30);
		panelSuperior.add(btnMinimizar);
		
		JPanel panelCadastro = new JPanel();
		panelCadastro.setBackground(new Color(255, 255, 255));
		panelCadastro.setBounds(0, 32, 512, 700);
		contentPane.add(panelCadastro);
		panelCadastro.setLayout(null);
		
		JLabel lblEmail2 = new JLabel("Email");
		lblEmail2.setBounds(74, 60, 119, 42);
		lblEmail2.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblEmail2.setForeground(corTexto);
		panelCadastro.add(lblEmail2);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(74, 115, 119, 42);
		lblCpf.setForeground(corTexto);
		lblCpf.setFont(new Font("Roboto", Font.PLAIN, 20));
		panelCadastro.add(lblCpf);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(74, 170, 119, 42);
		lblTelefone.setForeground(corTexto);
		lblTelefone.setFont(new Font("Roboto", Font.PLAIN, 20));
		panelCadastro.add(lblTelefone);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setBounds(74, 225, 163, 42);
		lblCelular.setForeground(corTexto);
		lblCelular.setFont(new Font("Roboto", Font.PLAIN, 20));
		panelCadastro.add(lblCelular);
		
		spNome.setBounds(236, 51, 215, 2);
		spNome.setBackground(corSeparador);
		panelCadastro.add(spNome);

		
		spCpf.setBounds(236, 159, 215, 2);
		spCpf.setBackground(corSeparador);
		panelCadastro.add(spCpf);

		spTelefone.setBounds(236, 214, 215, 2);
		spTelefone.setBackground(corSeparador);
		panelCadastro.add(spTelefone);
		
		JSeparator spCelular = new JSeparator();
		spCelular.setBounds(236, 269, 215, 2);
		spCelular.setBackground(corSeparador);
		panelCadastro.add(spCelular);
		
		tfNome = new JTextField();
		tfNome.setBounds(236, 19, 215, 32);
		tfNome.setForeground(corTexto);
		tfNome.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfNome.setBorder(null);
		tfNome.setBackground(panelCadastro.getBackground());
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
			}
		});
		
		spRg.setBackground(new Color(176, 176, 176));
		spRg.setBounds(236, 324, 215, 2);
		panelCadastro.add(spRg);
		
		spRepetir.setBackground(new Color(176, 176, 176));
		spRepetir.setBounds(236, 548, 215, 2);
		panelCadastro.add(spRepetir);
		
				
				
				
				
				spEmail.setBackground(new Color(176, 176, 176));
				spEmail.setBounds(236, 104, 215, 2);
				panelCadastro.add(spEmail);
		
		
		spSenha.setBackground(new Color(176, 176, 176));
		spSenha.setBounds(236, 493, 215, 2);
		panelCadastro.add(spSenha);
		panelCadastro.add(tfNome);
		
		try {
			MaskFormatter tfCpfFormatter = new MaskFormatter("###.###.###-##");
			tfCpfFormatter.setPlaceholderCharacter('_');
			tfCpf = new JFormattedTextField(tfCpfFormatter);
			tfCpf.setBounds(236, 127, 215, 32);
			tfCpf.setForeground(corTexto);
			tfCpf.setFont(new Font("Roboto", Font.PLAIN, 18));
			tfCpf.setBorder(null);
			tfCpf.setBackground(panelCadastro.getBackground());
		}catch(Exception e) {}
		tfCpf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String numerosCpf = tfCpf.getText();
				numerosCpf = numerosCpf.replaceAll("[^\\d]", "");
				camposCorretos[1] = (numerosCpf.length() < 11) ? false : true;
				spCpf.setBackground(corSeparador);
				
				if(camposCorretos[1]) {
					spCpf.setBackground(corSeparador);
					panelImgCpf.setVisible(false);
				}else {
					spCpf.setBackground(corVermelho);
					panelImgCpf.setVisible(true);
				}
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				spCpf.setBackground(corGeral);
			}
		});
		panelCadastro.add(tfCpf);
		
		try {
			MaskFormatter tfRgFormatter = new MaskFormatter("#.###.###");
			tfRgFormatter.setPlaceholderCharacter('_');
		}catch(Exception e) {}
		
		
		try {
			MaskFormatter tfTelefoneFormatter = new MaskFormatter("(##) ####-####");
			tfTelefoneFormatter.setPlaceholderCharacter('_');
			tfTelefone = new JFormattedTextField(tfTelefoneFormatter);
			tfTelefone.setBounds(236, 182, 215, 32);
			tfTelefone.setForeground(corTexto);
			tfTelefone.setFont(new Font("Roboto", Font.PLAIN, 18));
			tfTelefone.setBorder(null);
			tfTelefone.setBackground(panelCadastro.getBackground());
		}catch(Exception e) {}
		tfTelefone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String numerosTelefone = tfTelefone.getText();
				numerosTelefone = numerosTelefone.replaceAll("[^\\d]", "");
				camposCorretos[2] = (numerosTelefone.length() < 10) ? false : true;
				spTelefone.setBackground(corSeparador);
				
				if(camposCorretos[2]) {
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
			}
		});
		panelCadastro.add(tfTelefone);
		
		try {
			MaskFormatter tfCelularFormatter = new MaskFormatter("(##) #####-####");
			tfCelularFormatter.setPlaceholderCharacter('_');
			tfCelular = new JFormattedTextField(tfCelularFormatter);
			tfCelular.setBounds(236, 237, 215, 32);
			tfCelular.setForeground(corTexto);
			tfCelular.setFont(new Font("Roboto", Font.PLAIN, 18));
			tfCelular.setBorder(null);
			tfCelular.setBackground(panelCadastro.getBackground());
		}catch(Exception e) {}
		tfCelular.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String numerosCelular = tfCelular.getText();
				numerosCelular = numerosCelular.replaceAll("[^\\d]", "");
				if(numerosCelular.length() < 11) {
					tfCelular.setText("");
				}
				spCelular.setBackground(corSeparador);
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				spCelular.setBackground(corGeral);
			}
		});
		panelCadastro.add(tfCelular);
		
		
		
		
		
		
		try {
			MaskFormatter tfRgFormatter = new MaskFormatter("#.###.###");
			tfRgFormatter.setPlaceholderCharacter('_');
			tfRg = new JFormattedTextField(tfRgFormatter);
			tfRg.setForeground(corTexto);
			tfRg.setFont(new Font("Roboto", Font.PLAIN, 18));
			tfRg.setBorder(null);
			tfRg.setBackground(panelCadastro.getBackground());
		}catch(Exception e) {}
		tfRg.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String numerosRg = tfRg.getText();
				numerosRg = numerosRg.replaceAll("[^\\d]", "");
				camposCorretos[3] = (numerosRg.length() < 7) ? false : true;
				spRg.setBackground(corSeparador);
				
				if(camposCorretos[3]) {
					spRg.setBackground(corSeparador);
					panelImgRg.setVisible(false);
				}else {
					spRg.setBackground(corVermelho);
					panelImgRg.setVisible(true);
				}
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				spRg.setBackground(corGeral);
			}
		});
		tfRg.setColumns(10);
		tfRg.setBounds(236, 292, 215, 32);
		panelCadastro.add(tfRg);
		
		
		pfSenha = new JPasswordField();
		pfSenha.setForeground(corTexto);
		

		pfSenha.setFont(new Font("Roboto", Font.PLAIN, 18));
		pfSenha.setBorder(null);
		pfSenha.setEchoChar('•');
		pfSenha.setBackground(panelCadastro.getBackground());
		pfSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				camposCorretos[4] = (pfSenha.getPassword().length < 8 || pfSenha.getPassword().length > 25) ? false : true;
				spSenha.setBackground(corSeparador);
				
				if(camposCorretos[4]) {
					spSenha.setBackground(corSeparador);
					panelImgSenha.setVisible(false);
				}else {
					spSenha.setBackground(corVermelho);
					panelImgSenha.setVisible(true);
				}
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				spSenha.setBackground(corGeral);
				panelImgSenha.setVisible(false);

			}
		});
		
		pfSenha.setColumns(10);
		pfSenha.setBounds(236, 461, 215, 32);
		panelCadastro.add(pfSenha);
		
		pfRepetir = new JPasswordField();
		pfRepetir.setForeground(corTexto);
		pfRepetir.setFont(new Font("Roboto", Font.PLAIN, 18));
		pfRepetir.setBorder(null);
		pfRepetir.setEchoChar('•');
		pfRepetir.setBackground(panelCadastro.getBackground());
		pfRepetir.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				camposCorretos[5] = (pfRepetir.getText().equals(pfSenha.getText())) ? true : false;
				spRepetir.setBackground(corSeparador);
				
				if(camposCorretos[5]) {
					spRepetir.setBackground(corSeparador);
					panelImgRepetir.setVisible(false);
				}else {
					spRepetir.setBackground(corVermelho);
					panelImgRepetir.setVisible(true);
				}
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				spRepetir.setBackground(corGeral);
				panelImgRepetir.setVisible(false);

			}
		});
		pfRepetir.setBounds(236, 516, 215, 32);
		panelCadastro.add(pfRepetir);
		pfRepetir.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setForeground(new Color(75, 80, 85));
		tfEmail.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfEmail.setBorder(null);
		tfEmail.setBackground(Color.WHITE);
		tfEmail.setBounds(236, 72, 215, 32);
		panelCadastro.add(tfEmail);
		
		tfEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				camposCorretos[6] = (tfEmail.getText()
						.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) ? true : false;

				if (tfEmail.getText().length() < 1) {
					camposCorretos[6] = false;
				}

				if (camposCorretos[6]) {
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
		
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"Caixa - Tipo 0", "Estoque - Tipo 1", "Gerente - Tipo 2"}));
		cbTipo.setForeground(new Color(75, 80, 85));
		cbTipo.setFont(new Font("Roboto", Font.PLAIN, 18));
		cbTipo.setBackground(Color.WHITE);
		cbTipo.setBounds(236, 349, 215, 32);
		panelCadastro.add(cbTipo);
		cbStatus.setModel(new DefaultComboBoxModel(new String[] {"Inativo", "Ativo"}));
		cbStatus.setForeground(new Color(75, 80, 85));
		cbStatus.setFont(new Font("Roboto", Font.PLAIN, 18));
		cbStatus.setBackground(Color.WHITE);
		cbStatus.setBounds(236, 406, 215, 32);
		panelCadastro.add(cbStatus);
		
		JLabel lblRg = new JLabel("RG");
		lblRg.setForeground(new Color(75, 80, 85));
		lblRg.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblRg.setBounds(74, 280, 119, 42);
		panelCadastro.add(lblRg);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setForeground(new Color(75, 80, 85));
		lblTipo.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblTipo.setBounds(74, 335, 119, 42);
		panelCadastro.add(lblTipo);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setForeground(new Color(75, 80, 85));
		lblStatus.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblStatus.setBounds(74, 392, 119, 42);
		panelCadastro.add(lblStatus);
		
		JLabel lblRepitaASenh = new JLabel("Repita a senha");
		lblRepitaASenh.setForeground(new Color(75, 80, 85));
		lblRepitaASenh.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblRepitaASenh.setBounds(74, 504, 163, 42);
		panelCadastro.add(lblRepitaASenh);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(new Color(75, 80, 85));
		lblNome.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblNome.setBounds(74, 11, 119, 42);
		panelCadastro.add(lblNome);
		
		JLabel lblSenh = new JLabel("Senha");
		lblSenh.setForeground(new Color(75, 80, 85));
		lblSenh.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblSenh.setBounds(74, 449, 119, 42);
		panelCadastro.add(lblSenh);
		
		
		JLabel imgXNome = new JLabel(new ImageIcon(imagemX));
		imgXNome.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		imgXNome.setBackground(new Color(255, 255, 255));
		imgXNome.setToolTipText("O nome é obrigatório e não pode conter símbolos");
		
		JLabel imgXCpf = new JLabel(new ImageIcon(imagemX));
		imgXCpf.setBackground(new Color(255, 255, 255));
		imgXCpf.setToolTipText("O CPF é obrigatório");
		
		JLabel imgXTelefone = new JLabel(new ImageIcon(imagemX));
		imgXTelefone.setBackground(new Color(255, 255, 255));
		imgXTelefone.setToolTipText("O Telefone é obrigatório");
		
		JLabel imgXRua = new JLabel(new ImageIcon(imagemX));
		imgXRua.setBackground(new Color(255, 255, 255));
		imgXRua.setToolTipText("O RG \u00E9 obrigat\u00F3rio");

		JLabel imgXSenha = new JLabel(new ImageIcon(imagemX));
		imgXSenha.setBackground(new Color(255, 255, 255));
		imgXSenha.setToolTipText("A senha \u00E9 obrigat\u00F3rio");
		

		JLabel imgXRepetir= new JLabel(new ImageIcon(imagemX));
		imgXRepetir.setBackground(new Color(255, 255, 255));
		imgXRepetir.setToolTipText("A repita a senha corretamente");
		
		
		JLabel imgXEmail= new JLabel(new ImageIcon(imagemX));
		imgXEmail.setBackground(new Color(255, 255, 255));
		imgXEmail.setToolTipText("O email é obrigatorio e deve ser preenchido corretamente");
		
		panelImgNome = new JPanel();
		panelImgNome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelImgNome.setOpaque(false);
		panelImgNome.setVisible(false);
		panelImgNome.setBackground(new Color(255, 255, 255));
		panelImgNome.setBounds(37, 17, 32, 32);
		panelCadastro.add(panelImgNome); 
		panelImgNome.add(imgXNome);
		
		panelImgCpf = new JPanel();
		panelImgCpf.setVisible(false);
		panelImgCpf.setBackground(new Color(255, 255, 255));
		panelImgCpf.setBounds(37, 123, 32, 32);
		panelCadastro.add(panelImgCpf); 
		panelImgCpf.add(imgXCpf);
		
		panelImgTelefone = new JPanel();
		panelImgTelefone.setVisible(false);
		panelImgTelefone.setBackground(new Color(255, 255, 255));
		panelImgTelefone.setBounds(37, 178, 32, 32);
		panelCadastro.add(panelImgTelefone); 
		panelImgTelefone.add(imgXTelefone);
		
		panelImgRg = new JPanel();
		panelImgRg.setVisible(false);
		panelImgRg.setBackground(new Color(255, 255, 255));
		panelImgRg.setBounds(37, 286, 32, 32);
		panelCadastro.add(panelImgRg); 
		panelImgRg.add(imgXRua);
		
		panelImgSenha = new JPanel();
		panelImgSenha.setVisible(false);
		panelImgSenha.setBackground(new Color(255, 255, 255));
		panelImgSenha.setBounds(37, 453, 32, 32);
		panelCadastro.add(panelImgSenha); 
		panelImgSenha.add(imgXSenha);

		panelImgRepetir = new JPanel();
		panelImgRepetir.setVisible(false);
		panelImgRepetir.setBackground(new Color(255, 255, 255));
		panelImgRepetir.setBounds(37, 510, 32, 32);
		panelCadastro.add(panelImgRepetir); 
		panelImgRepetir.add(imgXRepetir);
		
		panelImgEmail = new JPanel();
		panelImgEmail.setVisible(false);
		panelImgEmail.setBackground(new Color(255, 255, 255));
		panelImgEmail.setBounds(37, 68, 32, 32);
		panelCadastro.add(panelImgEmail); 
		panelImgEmail.add(imgXEmail);
		
		
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setUI((ButtonUI) BasicButtonUI.createUI(btnCadastrar));
		btnCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnCadastrar.setBackground(corSecundaria);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCadastrar.setBackground(corGeral);
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				btnCadastrar.setBackground(corTerciaria);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnCadastrar.setBackground(corGeral);
			}
		});
		btnCadastrar.setBounds(154, 600, 215, 54);
		panelCadastro.add(btnCadastrar);
		btnCadastrar.setBackground(corGeral);
		btnCadastrar.setBorder(null);
		btnCadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizaCamposPro();

				if(camposEstaoCorretos(camposCorretos)) {
					Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
							us.setDataCadastro(dataDeHoje);
							us.setNome(tfNome.getText());
							us.setCpf(tfCpf.getText());
							us.setRg(tfRg.getText());
							us.setTelefone(tfTelefone.getText());
							us.setCelular(tfCelular.getText());
							us.setStatus(cbStatus.getSelectedIndex());
							us.setTipo(cbTipo.getSelectedIndex());
							us.setSenha(Senha.encriptarSenha(pfSenha.getText()));
							us.setEmail(tfEmail.getText());
							
							try {
								usDao.CadastrarUsuario(us);
								dispose();
							} catch (Exception e) {
								e.printStackTrace();
							}
							
						} else {
								JOptionPane.showMessageDialog(null, "Um ou mais campos não foram preenchidos corretamente", "Erro", JOptionPane.ERROR_MESSAGE);
						}
					
					
					
					
					
				
			}
		});
		btnCadastrar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnCadastrar.setForeground(corTexto);
		
	}

	private void atualizaCamposPro() {
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
		
		String numerosCpf = tfCpf.getText();
		numerosCpf = numerosCpf.replaceAll("[^\\d]", "");
		camposCorretos[1] = (numerosCpf.length() < 11) ? false : true;
		spCpf.setBackground(corSeparador);
		
		if(camposCorretos[1]) {
			spCpf.setBackground(corSeparador);
			panelImgCpf.setVisible(false);
		}else {
			spCpf.setBackground(corVermelho);
			panelImgCpf.setVisible(true);
		}
		
		String numerosTelefone = tfTelefone.getText();
		numerosTelefone = numerosTelefone.replaceAll("[^\\d]", "");
		camposCorretos[2] = (numerosTelefone.length() < 10) ? false : true;
		spTelefone.setBackground(corSeparador);
		
		if(camposCorretos[2]) {
			spTelefone.setBackground(corSeparador);
			panelImgTelefone.setVisible(false);
		}else {
			spTelefone.setBackground(corVermelho);
			panelImgTelefone.setVisible(true);
		}
		String numerosRg = tfRg.getText();
		numerosRg = numerosRg.replaceAll("[^\\d]", "");
		camposCorretos[3] = (numerosRg.length() < 7) ? false : true;
		spRg.setBackground(corSeparador);
		
		if(camposCorretos[3]) {
			spRg.setBackground(corSeparador);
			panelImgRg.setVisible(false);
		}else {
			spRg.setBackground(corVermelho);
			panelImgRg.setVisible(true);
		}
		camposCorretos[4] = (pfSenha.getPassword().length < 8 || pfSenha.getPassword().length > 25) ? false : true;
		spSenha.setBackground(corSeparador);
		
		if(camposCorretos[4]) {
			spSenha.setBackground(corSeparador);
			panelImgSenha.setVisible(false);
		}else {
			spSenha.setBackground(corVermelho);
			panelImgSenha.setVisible(true);
		}
		
		
		camposCorretos[5] = (pfRepetir.getText().equals(pfSenha.getText())) ? true : false;
		spRepetir.setBackground(corSeparador);
		
		if(camposCorretos[5]) {
			spRepetir.setBackground(corSeparador);
			panelImgRepetir.setVisible(false);
		}else {
			spRepetir.setBackground(corVermelho);
			panelImgRepetir.setVisible(true);
		}
		
		
		camposCorretos[6] = (tfEmail.getText()
				.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) ? true : false;

		if (tfEmail.getText().length() < 1) {
			camposCorretos[6] = false;
		}

		if (camposCorretos[6]) {
			spEmail.setBackground(corGeral);
			panelImgEmail.setVisible(false);

		} else {
			spEmail.setBackground(corVermelho);
			panelImgEmail.setVisible(true);
		}
	
	}
	
	boolean camposEstaoCorretos(boolean[] camposCorretos) {
		boolean x = true;
		for(boolean a : camposCorretos) {
			x = x && a;
		}
		return x;
	}
}
