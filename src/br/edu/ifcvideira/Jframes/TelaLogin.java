package br.edu.ifcvideira.Jframes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import br.edu.ifcvideira.Classes.*;
import br.edu.ifcvideira.DAOs.*;
import br.edu.ifcvideira.utils.*;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Cursor;
import javax.swing.JSeparator;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JTextPane;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Icon;


public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField tfCelular;
	private JTextField tfTelefone;
	private JTextField tfRg;
	private JTextField tfCpf;
	private JTextField tfNome;
	private JTextField tfEmail;
	private JPasswordField pfSenha;
	private JPasswordField pfRepetir;
	
	private JPanel panelImgNome;
	private JPanel panelImgCpf;
	private JPanel panelImgRg;
	private JPanel panelImgTelefone;
	private JPanel panelImgSenha;
	private JPanel panelImgRepetir;
	private JPanel panelImgEmail;
	
	public static JLabel lblLogo;
	
	private Usuario us = new Usuario();
	private UsuarioDao daoUs = new UsuarioDao();
	
	Color corTexto = new Color(75, 80, 85);
	Color corGeral = new Color(Preferencias.getR(), Preferencias.getG(), Preferencias.getB());
	Color corSecundaria = Cor.corMaisClara(corGeral, 0.2f);
	Color corTerciaria = Cor.corMaisClara(corGeral, 0.4f);
	Color corSeparador = new Color(176, 176, 176);
	Color corVermelho = new Color (230, 20, 20);
	
	Point posMouseInicial;
	
	ImageIcon imageIconX = new ImageIcon(TelaLogin.class.getResource("/br/edu/ifcvideira/img/xerro.png"));
	Image imagemX = imageIconX.getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
	
	ImageIcon imageIconEngrenagem = new ImageIcon(TelaLogin.class.getResource("/br/edu/ifcvideira/img/engrenagem.png"));
	Image imagemEngrenagem = imageIconEngrenagem.getImage().getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
	
	Configuracoes confView = new Configuracoes();
	private JTextField tfNomeLogin;
	private JPasswordField pfSenhaLogin;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
	public TelaLogin() {		
		setName("Tela Inicial");
		Dimension tela = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int largura = 982;
		int altura = 652;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		
		
		
		//Variáveis que cuidam para que os campos sejam preenchidos adequadamente
		boolean[] camposCorretos = {false, false, false, false, false, false, false, false};
		
		JPanel panelEsquerda = new JPanel();
		panelEsquerda.setBounds(0, 0, 476, 653);
		contentPane.add(panelEsquerda);
		panelEsquerda.setBackground(corGeral);
		panelEsquerda.setLayout(null);
		
		JPanel panelLogo = new JPanel();
		panelLogo.setOpaque(false);
		panelLogo.setBounds(88, 168, 300, 300);
		panelEsquerda.add(panelLogo);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(imagemLogo));
		panelLogo.add(lblLogo);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 0, 983, 32);
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
		
		JPanel panelCadastro = new JPanel();
		panelCadastro.setBackground(new Color(255, 255, 255));
		panelCadastro.setBounds(1000/*477*/, 32, 512, 621);
		contentPane.add(panelCadastro);
		panelCadastro.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblNome.setForeground(corTexto);
		lblNome.setBounds(74, 60, 119, 42);
		panelCadastro.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(corTexto);
		lblCpf.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblCpf.setBounds(74, 115, 119, 42);
		panelCadastro.add(lblCpf);
		
		JLabel lblRg = new JLabel("RG");
		lblRg.setForeground(corTexto);
		lblRg.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblRg.setBounds(74, 170, 119, 42);
		panelCadastro.add(lblRg);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setForeground(corTexto);
		lblTelefone.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblTelefone.setBounds(74, 225, 119, 42);
		panelCadastro.add(lblTelefone);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setForeground(corTexto);
		lblCelular.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblCelular.setBounds(74, 280, 163, 42);
		panelCadastro.add(lblCelular);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(corTexto);
		lblSenha.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblSenha.setBounds(74, 335, 119, 42);
		panelCadastro.add(lblSenha);
		
		JLabel lblRepitaASenha = new JLabel("Repita a senha");
		lblRepitaASenha.setForeground(corTexto);
		lblRepitaASenha.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblRepitaASenha.setBounds(74, 390, 163, 42);
		panelCadastro.add(lblRepitaASenha);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setForeground(corTexto);
		lblEmail.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblEmail.setBounds(74, 445, 163, 42);
		panelCadastro.add(lblEmail);
		
		JSeparator spNome = new JSeparator();
		spNome.setBackground(corSeparador);
		spNome.setBounds(236, 104, 215, 2);
		panelCadastro.add(spNome);
		
		JSeparator spCpf = new JSeparator();
		spCpf.setBackground(corSeparador);
		spCpf.setBounds(236, 159, 215, 2);
		panelCadastro.add(spCpf);
		
		JSeparator spRg = new JSeparator();
		spRg.setBackground(corSeparador);
		spRg.setBounds(236, 214, 215, 2);
		panelCadastro.add(spRg);
		
		JSeparator spTelefone = new JSeparator();
		spTelefone.setBackground(corSeparador);
		spTelefone.setBounds(236, 269, 215, 2);
		panelCadastro.add(spTelefone);
		
		JSeparator spCelular = new JSeparator();
		spCelular.setBackground(corSeparador);
		spCelular.setBounds(236, 324, 215, 2);
		panelCadastro.add(spCelular);
		
		JSeparator spSenha = new JSeparator();
		spSenha.setBackground(corSeparador);
		spSenha.setBounds(236, 379, 215, 2);
		panelCadastro.add(spSenha);
		
		JSeparator spRepetir = new JSeparator();
		spRepetir.setBackground(corSeparador);
		spRepetir.setBounds(236, 434, 215, 2);
		panelCadastro.add(spRepetir);
		
		JSeparator spEmail = new JSeparator();
		spEmail.setBackground(corSeparador);
		spEmail.setBounds(236, 489, 215, 2);
		panelCadastro.add(spEmail);
		
		tfNome = new JTextField();
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
		tfNome.setColumns(10);
		tfNome.setBounds(236, 72, 215, 32);
		panelCadastro.add(tfNome);
		
		try {
			MaskFormatter tfCpfFormatter = new MaskFormatter("###.###.###-##");
			tfCpfFormatter.setPlaceholderCharacter('_');
			tfCpf = new JFormattedTextField(tfCpfFormatter);
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
		tfCpf.setColumns(10);
		tfCpf.setBounds(236, 127, 215, 32);
		panelCadastro.add(tfCpf);
		
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
				camposCorretos[2] = (numerosRg.length() < 7) ? false : true;
				spRg.setBackground(corSeparador);
				
				if(camposCorretos[2]) {
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
		tfRg.setBounds(236, 182, 215, 32);
		panelCadastro.add(tfRg);
		
		
		try {
			MaskFormatter tfTelefoneFormatter = new MaskFormatter("(##) ####-####");
			tfTelefoneFormatter.setPlaceholderCharacter('_');
			tfTelefone = new JFormattedTextField(tfTelefoneFormatter);
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
			}
		});
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(236, 237, 215, 32);
		panelCadastro.add(tfTelefone);
		
		try {
			MaskFormatter tfCelularFormatter = new MaskFormatter("(##) #####-####");
			tfCelularFormatter.setPlaceholderCharacter('_');
			tfCelular = new JFormattedTextField(tfCelularFormatter);
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
				camposCorretos[4] = true;
				spCelular.setBackground(corSeparador);
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				spCelular.setBackground(corGeral);
			}
		});
		tfCelular.setColumns(10);
		tfCelular.setBounds(236, 292, 215, 32);
		panelCadastro.add(tfCelular);
		
		pfSenha = new JPasswordField();
		pfSenha.setForeground(corTexto);
		pfSenha.setFont(new Font("Roboto", Font.PLAIN, 18));
		pfSenha.setBorder(null);
		pfSenha.setBackground(panelCadastro.getBackground());
		pfSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				camposCorretos[5] = (pfSenha.getPassword().length < 8 || pfSenha.getPassword().length > 25) ? false : true;
				spSenha.setBackground(corSeparador);
				
				if(camposCorretos[5]) {
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
			}
		});
		pfSenha.setColumns(10);
		pfSenha.setBounds(236, 347, 215, 32);
		panelCadastro.add(pfSenha);
		
		pfRepetir = new JPasswordField();
		pfRepetir.setForeground(corTexto);
		pfRepetir.setFont(new Font("Roboto", Font.PLAIN, 18));
		pfRepetir.setBorder(null);
		pfRepetir.setBackground(panelCadastro.getBackground());
		pfRepetir.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				camposCorretos[6] = (pfRepetir.getText().equals(pfSenha.getText())) ? true : false;
				spRepetir.setBackground(corSeparador);
				
				if(camposCorretos[6]) {
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
			}
		});
		pfRepetir.setBounds(236, 402, 215, 32);
		panelCadastro.add(pfRepetir);
		pfRepetir.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setForeground(corTexto);
		tfEmail.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfEmail.setBorder(null);
		tfEmail.setBackground(panelCadastro.getBackground());
		tfEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				camposCorretos[7] = (tfEmail.getText().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) ? true : false;

				if (tfEmail.getText().length() < 1) {
					camposCorretos[7] = false;
				}

				if (camposCorretos[7]) {
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
		tfEmail.setColumns(10);
		tfEmail.setBounds(236, 457, 215, 32);
		panelCadastro.add(tfEmail);
		
		JPanel panelCampos = new JPanel();
		panelCampos.setOpaque(false);
		panelCampos.setBounds(0, 0, 506, 620);
		panelCampos.setBackground(panelCadastro.getBackground());
		panelCadastro.add(panelCampos);
		panelCampos.setLayout(null);
		
		JButton btnCadastrar = new JButton("Cadastrar");
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
		btnCadastrar.setBounds(165, 512, 215, 54);
		panelCampos.add(btnCadastrar);
		btnCadastrar.setBackground(corGeral);
		btnCadastrar.setBorder(null);
		btnCadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(camposEstaoCorretos(camposCorretos)) {
					Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
					
					us.setId(1);
					us.setStatus(1);
					us.setTipo(2);
					us.setDataCadastro(dataDeHoje);
					us.setNome(tfNome.getText());
					us.setCpf(tfCpf.getText());
					us.setRg(tfRg.getText());
					us.setTelefone(tfTelefone.getText());
					us.setCelular(tfCelular.getText());
					us.setSenha(Senha.encriptarSenha(pfSenha.getText()));
					us.setEmail(tfEmail.getText());
					
					try {
						daoUs.CadastrarUsuario(us);
						fecharJanelas();
						TelaGerente telaGerente = new TelaGerente();
						
						telaGerente.setVisible(true);
					}catch(Exception e) { }
				}else {
					if(!camposCorretos[0]) {
						spNome.setBackground(corVermelho);
						panelImgNome.setVisible(true);
					}else {
						spNome.setBackground(corSeparador);
						panelImgNome.setVisible(false);
					}
					
					if(!camposCorretos[1]) {
						spCpf.setBackground(corVermelho);
						panelImgCpf.setVisible(true);
					}else {
						spCpf.setBackground(corSeparador);
						panelImgCpf.setVisible(false);
					}
					
					if(!camposCorretos[2]) {
						spRg.setBackground(corVermelho);
						panelImgRg.setVisible(true);
					}else {
						spRg.setBackground(corSeparador);
						panelImgRg.setVisible(false);
					}
					
					if(!camposCorretos[3]) {
						spTelefone.setBackground(corVermelho);
						panelImgTelefone.setVisible(true);
					}else {
						spTelefone.setBackground(corSeparador);
						panelImgTelefone.setVisible(false);
					}
					
					if(!camposCorretos[5]) {
						spSenha.setBackground(corVermelho);
						panelImgSenha.setVisible(true);
					}else {
						spSenha.setBackground(corSeparador);
						panelImgSenha.setVisible(false);
					}
					
					if(!camposCorretos[6]) {
						spRepetir.setBackground(corVermelho);
						panelImgRepetir.setVisible(true);
					}else {
						spRepetir.setBackground(corSeparador);
						panelImgRepetir.setVisible(false);
					}
					
					if(!camposCorretos[7]) {
						spEmail.setBackground(corVermelho);
						panelImgEmail.setVisible(true);
					}else {
						spEmail.setBackground(corSeparador);
						panelImgEmail.setVisible(false);
					}
				}
			}
		});
		btnCadastrar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnCadastrar.setForeground(corTexto);
		
		
		JLabel imgXNome = new JLabel(new ImageIcon(imagemX));
		imgXNome.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		imgXNome.setBackground(new Color(255, 255, 255));
		imgXNome.setToolTipText("O nome é obrigatório e não pode conter símbolos");
		
		JLabel imgXCpf = new JLabel(new ImageIcon(imagemX));
		imgXCpf.setBackground(new Color(255, 255, 255));
		imgXCpf.setToolTipText("O CPF é obrigatório");
		
		JLabel imgXRg = new JLabel(new ImageIcon(imagemX));
		imgXRg.setBackground(new Color(255, 255, 255));
		imgXRg.setToolTipText("O RG é obrigatório");
		
		JLabel imgXTelefone = new JLabel(new ImageIcon(imagemX));
		imgXTelefone.setBackground(new Color(255, 255, 255));
		imgXTelefone.setToolTipText("O Telefone é obrigatório");
		
		JLabel imgXSenha = new JLabel(new ImageIcon(imagemX));
		imgXSenha.setBackground(new Color(255, 255, 255));
		imgXSenha.setToolTipText("A senha deve conter entre 8 e 25 caracteres");
		
		JLabel imgXRepetir = new JLabel(new ImageIcon(imagemX));
		imgXRepetir.setBackground(new Color(255, 255, 255));
		imgXRepetir.setToolTipText("Repita a senha corretamente");
		
		JLabel imgXEmail = new JLabel(new ImageIcon(imagemX));
		imgXEmail.setBackground(new Color(255, 255, 255));
		imgXEmail.setToolTipText("E-mail inválido");
		
		panelImgNome = new JPanel();
		panelImgNome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelImgNome.setOpaque(false);
		panelImgNome.setVisible(false);
		panelImgNome.setBackground(new Color(255, 255, 255));
		panelImgNome.setBounds(37, 68, 32, 32);
		panelCampos.add(panelImgNome); 
		panelImgNome.add(imgXNome);
		
		panelImgCpf = new JPanel();
		panelImgCpf.setVisible(false);
		panelImgCpf.setBackground(new Color(255, 255, 255));
		panelImgCpf.setBounds(37, 123, 32, 32);
		panelCampos.add(panelImgCpf); 
		panelImgCpf.add(imgXCpf);
		
		panelImgRg = new JPanel();
		panelImgRg.setVisible(false);
		panelImgRg.setBackground(new Color(255, 255, 255));
		panelImgRg.setBounds(37, 178, 32, 32);
		panelCampos.add(panelImgRg); 
		panelImgRg.add(imgXRg);
		
		panelImgTelefone = new JPanel();
		panelImgTelefone.setVisible(false);
		panelImgTelefone.setBackground(new Color(255, 255, 255));
		panelImgTelefone.setBounds(37, 233, 32, 32);
		panelCampos.add(panelImgTelefone); 
		panelImgTelefone.add(imgXTelefone);
		
		panelImgSenha = new JPanel();
		panelImgSenha.setVisible(false);
		panelImgSenha.setBackground(new Color(255, 255, 255));
		panelImgSenha.setBounds(37, 343, 32, 32);
		panelCampos.add(panelImgSenha); 
		panelImgSenha.add(imgXSenha);
		
		panelImgRepetir = new JPanel();
		panelImgRepetir.setVisible(false);
		panelImgRepetir.setBackground(new Color(255, 255, 255));
		panelImgRepetir.setBounds(37, 398, 32, 32);
		panelCampos.add(panelImgRepetir); 
		panelImgRepetir.add(imgXRepetir);
		
		panelImgEmail = new JPanel();
		panelImgEmail.setVisible(false);
		panelImgEmail.setBackground(new Color(255, 255, 255));
		panelImgEmail.setBounds(37, 453, 32, 32);
		panelCampos.add(panelImgEmail); 
		panelImgEmail.add(imgXEmail);
		
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(new Color(255, 255, 255));
		panelLogin.setBounds(471, 32, 512, 621);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);
		
		JLabel lblNomeLogin = new JLabel("Nome");
		lblNomeLogin.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomeLogin.setFont(new Font("Roboto", Font.PLAIN, 22));
		lblNomeLogin.setBounds(87, 123, 85, 51);
		panelLogin.add(lblNomeLogin);
		
		JSeparator spSenhaLogin = new JSeparator();
		spSenhaLogin.setBackground(corSeparador);
		spSenhaLogin.setBounds(87, 389, 331, 2);
		panelLogin.add(spSenhaLogin);
		
		JSeparator spNomeLogin = new JSeparator();
		spNomeLogin.setBackground(corSeparador);
		spNomeLogin.setBounds(87, 230, 331, 2);
		panelLogin.add(spNomeLogin);
		
		tfNomeLogin = new JTextField();
		tfNomeLogin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				spNomeLogin.setBackground(corGeral);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				spNomeLogin.setBackground(corSeparador);
			}
		});		
		tfNomeLogin.setForeground(new Color(75, 80, 85));
		tfNomeLogin.setFont(new Font("Roboto", Font.PLAIN, 22));
		tfNomeLogin.setColumns(10);
		tfNomeLogin.setBorder(null);
		tfNomeLogin.setBackground(Color.WHITE);
		tfNomeLogin.setBounds(87, 171, 331, 57);
		panelLogin.add(tfNomeLogin);
		
		JLabel lblSenhaLogin = new JLabel("Senha");
		lblSenhaLogin.setHorizontalAlignment(SwingConstants.LEFT);
		lblSenhaLogin.setFont(new Font("Roboto", Font.PLAIN, 22));
		lblSenhaLogin.setBounds(88, 284, 100, 51);
		panelLogin.add(lblSenhaLogin);
		
		pfSenhaLogin = new JPasswordField();
		pfSenhaLogin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				spSenhaLogin.setBackground(corGeral);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				spSenhaLogin.setBackground(corSeparador);
			}
		});	
		pfSenhaLogin.setBorder(null);
		pfSenhaLogin.setForeground(new Color(75, 80, 85));
		pfSenhaLogin.setFont(new Font("Roboto", Font.PLAIN, 22));
		pfSenhaLogin.setBounds(87, 330, 331, 57);
		panelLogin.add(pfSenhaLogin);
		
		JLabel lblErroNome = new JLabel("Nome inexistente");
		lblErroNome.setVisible(false);
		lblErroNome.setForeground(corVermelho);
		lblErroNome.setFont(new Font("Roboto", Font.PLAIN, 15));
		lblErroNome.setBounds(87, 116, 331, 16);
		panelLogin.add(lblErroNome);
		
		JLabel lblErroSenha = new JLabel("Senha incorreta");
		lblErroSenha.setVisible(false);
		lblErroSenha.setForeground(corVermelho);
		lblErroSenha.setFont(new Font("Roboto", Font.PLAIN, 15));
		lblErroSenha.setBounds(87, 284, 331, 16);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnEntrar.setBackground(corSecundaria);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnEntrar.setBackground(corGeral);
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				btnEntrar.setBackground(corTerciaria);
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				btnEntrar.setBackground(corGeral);
			}
		});
		btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEntrar.setBorder(null);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = tfNomeLogin.getText();
				String senha = Senha.encriptarSenha(pfSenhaLogin.getText());
				List<Object> infoUsuario = new ArrayList<>();
				try {
					infoUsuario = daoUs.buscarUsuario(nome);
					if(!infoUsuario.isEmpty()) {
						lblErroNome.setVisible(false);
						spNomeLogin.setBackground(corSeparador);
						
						int idUsuario = (int) infoUsuario.get(0);
						String senhaUsuario = String.valueOf(infoUsuario.get(3));
						int tipoUsuario = Integer.parseInt(String.valueOf(infoUsuario.get(1)));
						
						if(senha.equals(senhaUsuario)) {
							lblErroSenha.setVisible(false);
							spSenhaLogin.setBackground(corSeparador);
							
							if(tipoUsuario == 0) {
								fecharJanelas();
								TelaCaixa telaCaixa = new TelaCaixa(idUsuario);
								
								telaCaixa.setVisible(true);
							}else if(tipoUsuario == 1) {
								fecharJanelas();
								TelaEstoque telaEstoque = new TelaEstoque();
								
								telaEstoque.setVisible(true);
							}else if(tipoUsuario == 2) {
								fecharJanelas();
								TelaGerente telaGerente = new TelaGerente();
								
								telaGerente.setVisible(true);
							}
						}else {
							lblErroSenha.setVisible(true);
							spSenhaLogin.setBackground(corVermelho);
						}
					}else {
						lblErroNome.setText("Nome inexistente");
						lblErroNome.setVisible(true);
						spNomeLogin.setBackground(corVermelho);
					}
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		panelLogin.add(lblErroSenha);
		btnEntrar.setFont(new Font("Roboto", Font.PLAIN, 22));
		btnEntrar.setBounds(139, 471, 242, 57);
		btnEntrar.setBackground(corGeral);
		panelLogin.add(btnEntrar);
		
		JLabel btnRecuperarSenha = new JLabel("Recuperar senha");
		btnRecuperarSenha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String nome = tfNomeLogin.getText();
				List<Object> infoUsuario = new ArrayList<>();
				if(nome.equals("")) {
					lblErroNome.setText("Preencha o nome");
					lblErroNome.setVisible(true);
				}else {
					lblErroNome.setVisible(false);
					try {
						infoUsuario = daoUs.buscarUsuario(nome);
						
						if(!infoUsuario.isEmpty()) {
							Usuario usuario = new Usuario();
							
							usuario.setId((int) infoUsuario.get(0));
							/*usuario.setTipo((int) infoUsuario.get(1));
							usuario.setNome((String) infoUsuario.get(2));
							usuario.setSenha((String) infoUsuario.get(3));
							usuario.setCpf((String) infoUsuario.get(4));
							usuario.setRg((String) infoUsuario.get(5));
							usuario.setTelefone((String) infoUsuario.get(6));
							usuario.setCelular((String) infoUsuario.get(7));
							usuario.setDataCadastro((Timestamp) infoUsuario.get(8));
							usuario.setStatus((int) infoUsuario.get(9));*/
							usuario.setEmail((String) infoUsuario.get(10));
							
							TelaRecuperarSenha telaRecuperarSenha = new TelaRecuperarSenha(usuario);
							
							telaRecuperarSenha.setVisible(true);
						}else {
							lblErroNome.setText("Nome inexistente");
							lblErroNome.setVisible(true);
						}
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnRecuperarSenha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRecuperarSenha.setHorizontalAlignment(SwingConstants.LEFT);
		btnRecuperarSenha.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnRecuperarSenha.setBounds(87, 398, 250, 29);
		btnRecuperarSenha.setForeground(corGeral);
		panelLogin.add(btnRecuperarSenha);
		
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tfNome, tfCpf, tfRg, tfTelefone, tfCelular, pfSenha, pfRepetir, tfEmail, btnCadastrar, tfNomeLogin, pfSenhaLogin, btnEntrar}));
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tfNome, tfCpf, tfRg, tfTelefone, tfCelular, pfSenha, pfRepetir, tfEmail, btnCadastrar, tfNomeLogin, pfSenhaLogin, btnEntrar}));
		
		
		
		try {
			if(daoUs.RetornarProximoCodigoUsuario() > 1) {
				//setContentPane(panelLogin);
				panelCadastro.setVisible(false);
				panelLogin.setVisible(true);
			}else {
				//setContentPane(panelCadastro);
				panelCadastro.setVisible(true);
				panelLogin.setVisible(false);
			}
		}catch(Exception e) { }
	}
	
	void fecharJanelas() {
		Window[] janelas = Window.getWindows();
		for(Window j : janelas) {
			j.dispose();
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
