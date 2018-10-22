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
import java.awt.Font;
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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import br.edu.ifcvideira.Classes.*;
import br.edu.ifcvideira.DAOs.*;
import br.edu.ifcvideira.utils.Senha;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.JSeparator;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;


public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField tfCelular;
	private JTextField tfTelefone;
	private JTextField tfRg;
	private JTextField tfCpf;
	private JTextField tfNome;
	private JPasswordField pfSenha;
	private JPasswordField pfRepetir;

	private Usuario us = new Usuario();
	private UsuarioDao daoUs = new UsuarioDao();
	private JTextField tfNomeLogin;
	private JPasswordField pfSenhaLogin;
	
	Color corTexto = new Color(75, 80, 85);
	Color corGeral = new Color(118, 184, 184);
	Point posMouseInicial;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
		setName("TelaInicial");
		Dimension tela = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int largura = 800;
		int altura = 700;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((tela.width / 2) - (largura / 2), (tela.height / 2) - (altura / 2), 985, 652);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setUndecorated(true);
		contentPane.setLayout(null);
		setResizable(false);
		
		
		//Vari�veis que cuidam para que os campos sejam preenchidos adequadamente
		boolean[] camposCorretos = {false, false, false, false, false, false, false};
		
		JPanel panelCadastro = new JPanel();
		panelCadastro.setBackground(new Color(255, 255, 255));
		panelCadastro.setBounds(0, 0, 982, 653);
		contentPane.add(panelCadastro);
		panelCadastro.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblNome.setForeground(corTexto);
		lblNome.setBounds(551, 93, 119, 42);
		panelCadastro.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(corTexto);
		lblCpf.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblCpf.setBounds(551, 148, 119, 42);
		panelCadastro.add(lblCpf);
		
		JLabel lblRg = new JLabel("RG");
		lblRg.setForeground(corTexto);
		lblRg.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblRg.setBounds(551, 203, 119, 42);
		panelCadastro.add(lblRg);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setForeground(corTexto);
		lblTelefone.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblTelefone.setBounds(551, 258, 119, 42);
		panelCadastro.add(lblTelefone);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setForeground(corTexto);
		lblCelular.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblCelular.setBounds(551, 313, 163, 42);
		panelCadastro.add(lblCelular);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(corTexto);
		lblSenha.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblSenha.setBounds(551, 368, 119, 42);
		panelCadastro.add(lblSenha);
		
		JLabel lblRepitaASenha = new JLabel("Repita a senha");
		lblRepitaASenha.setForeground(corTexto);
		lblRepitaASenha.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblRepitaASenha.setBounds(551, 423, 163, 42);
		panelCadastro.add(lblRepitaASenha);
		
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
			}
		});
		tfNome.setColumns(10);
		tfNome.setBounds(713, 105, 215, 32);
		panelCadastro.add(tfNome);
		
		try {
			MaskFormatter tfCpfFormatter = new MaskFormatter("###.###.###-##");
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
				numerosCpf = numerosCpf.replaceAll("[^\\w]", "");
				camposCorretos[1] = (numerosCpf.length() < 11) ? false : true;
			}
		});
		tfCpf.setColumns(10);
		tfCpf.setBounds(713, 160, 215, 32);
		panelCadastro.add(tfCpf);
		
		try {
			MaskFormatter tfRgFormatter = new MaskFormatter("#.###.###");
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
				numerosRg = numerosRg.replaceAll("[^\\w]", "");
				camposCorretos[2] = (numerosRg.length() < 7) ? false : true;
			}
		});
		tfRg.setColumns(10);
		tfRg.setBounds(713, 215, 215, 32);
		panelCadastro.add(tfRg);
		
		try {
			MaskFormatter tfTelefoneFormatter = new MaskFormatter("(##) ####-####");
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
				numerosTelefone = numerosTelefone.replaceAll("[^\\w]", "");
				camposCorretos[3] = (numerosTelefone.length() < 10) ? false : true;
			}
		});
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(713, 270, 215, 32);
		panelCadastro.add(tfTelefone);
		
		try {
			MaskFormatter tfCelularFormatter = new MaskFormatter("(##) #####-####");
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
				numerosCelular = numerosCelular.replaceAll("[^\\w]", "");
				camposCorretos[4] = (numerosCelular.length() < 11) ? false : true;
				if(numerosCelular.length() == 0) {
					camposCorretos[4] = true;
				}
			}
		});
		tfCelular.setColumns(10);
		tfCelular.setBounds(713, 325, 215, 32);
		panelCadastro.add(tfCelular);
		
		pfSenha = new JPasswordField();
		pfSenha.setForeground(corTexto);
		pfSenha.setFont(new Font("Roboto", Font.PLAIN, 18));
		pfSenha.setBorder(null);
		pfSenha.setBackground(panelCadastro.getBackground());
		pfSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				camposCorretos[5] = (pfSenha.getPassword().length < 8) ? false : true;
			}
		});
		pfSenha.setColumns(10);
		pfSenha.setBounds(713, 380, 215, 32);
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
			}
		});
		pfRepetir.setBounds(713, 435, 215, 32);
		panelCadastro.add(pfRepetir);
		pfRepetir.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(176, 176, 176));
		separator.setBounds(713, 137, 215, 2);
		panelCadastro.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(176, 176, 176));
		separator_1.setBounds(713, 192, 215, 2);
		panelCadastro.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(new Color(176, 176, 176));
		separator_2.setBounds(713, 247, 215, 2);
		panelCadastro.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(new Color(176, 176, 176));
		separator_3.setBounds(713, 302, 215, 2);
		panelCadastro.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(new Color(176, 176, 176));
		separator_4.setBounds(713, 357, 215, 2);
		panelCadastro.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBackground(new Color(176, 176, 176));
		separator_5.setBounds(713, 412, 215, 2);
		panelCadastro.add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBackground(new Color(176, 176, 176));
		separator_6.setBounds(713, 467, 215, 2);
		panelCadastro.add(separator_6);
		
		JPanel panelCampos = new JPanel();
		panelCampos.setBounds(477, 41, 506, 612);
		panelCampos.setBackground(panelCadastro.getBackground());
		panelCadastro.add(panelCampos);
		panelCampos.setLayout(null);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(166, 473, 215, 54);
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
					us.setCpf(tfCpf.getText().replaceAll("[^\\w]", ""));
					us.setRg(tfRg.getText().replaceAll("[^\\w]", ""));
					us.setTelefone(tfTelefone.getText().replaceAll("[^\\w]", ""));
					us.setCelular(tfCelular.getText().replaceAll("[^\\w]", ""));
					us.setSenha(Senha.encriptarSenha(pfSenha.getText()));
					
					try {
						daoUs.CadastrarUsuario(us);
					}catch(Exception e) { }
				}else {
					System.out.println("Erro");
				}
			}
		});
		btnCadastrar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnCadastrar.setForeground(corTexto);
		
		JPanel panelEsquerda = new JPanel();
		panelEsquerda.setBackground(corGeral);
		panelEsquerda.setBounds(0, 0, 476, 653);
		panelCadastro.add(panelEsquerda);
		
		JPanel panelSuperior = new JPanel();
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
		panelSuperior.setBounds(0, 0, 983, 42);
		panelSuperior.setBackground(new Color(255, 255, 255));
		panelCadastro.add(panelSuperior);
		panelSuperior.setLayout(null);
		
		JButton btnX = new JButton("X");
		btnX.setBackground(corGeral);
		btnX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnX.setBounds(941, 0, 42, 42);
		btnX.setMaximumSize(new Dimension(80, 50));
		btnX.setFont(new Font("Roboto", Font.PLAIN, 13));
		btnX.setBorder(null);
		panelSuperior.add(btnX);
		
		JButton button = new JButton("-");
		button.setBackground(corGeral);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		button.setMaximumSize(new Dimension(80, 50));
		button.setFont(new Font("Roboto", Font.PLAIN, 15));
		button.setBorder(null);
		button.setBounds(887, 0, 42, 42);
		panelSuperior.add(button);
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(new Color(255, 255, 255));
		panelLogin.setBounds(0, 0, 982, 653);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);
		
		tfNomeLogin = new JTextField();
		tfNomeLogin.setBounds(332, 197, 349, 64);
		panelLogin.add(tfNomeLogin);
		tfNomeLogin.setColumns(10);
		
		JLabel lblNomeLogin = new JLabel("Nome");
		lblNomeLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeLogin.setFont(new Font("Roboto", Font.PLAIN, 22));
		lblNomeLogin.setBounds(332, 145, 349, 51);
		panelLogin.add(lblNomeLogin);
		
		JLabel lblSenhaLogin = new JLabel("Senha");
		lblSenhaLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenhaLogin.setFont(new Font("Roboto", Font.PLAIN, 22));
		lblSenhaLogin.setBounds(332, 300, 349, 51);
		panelLogin.add(lblSenhaLogin);
		
		pfSenhaLogin = new JPasswordField();
		pfSenhaLogin.setBounds(332, 351, 349, 75);
		panelLogin.add(pfSenhaLogin);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Roboto", Font.PLAIN, 30));
		lblLogin.setBounds(332, 13, 349, 51);
		panelLogin.add(lblLogin);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = tfNomeLogin.getText();
				String senha = Senha.encriptarSenha(pfSenhaLogin.getText());
				List<Object> infoUsuario = new ArrayList<>();
				try {
					infoUsuario = daoUs.buscarUsuario(nome);
				}catch(Exception e) { }
				
				int idUsuario = Integer.parseInt(String.valueOf(infoUsuario.get(0)));
				String senhaUsuario = String.valueOf(infoUsuario.get(1));
				
				if(senha.equals(senhaUsuario)) {
					// Usu�rio logou com sucesso
				}
			}
		});
		btnEntrar.setFont(new Font("Roboto", Font.PLAIN, 22));
		btnEntrar.setBounds(417, 482, 187, 64);
		panelLogin.add(btnEntrar);
		
		
		
		try {
			if(daoUs.RetornarProximoCodigoUsuario() > 1) {
				setContentPane(panelLogin);
			}else {
				setContentPane(panelCadastro);
			}
		}catch(Exception e) { }
	}
	
	boolean camposEstaoCorretos(boolean[] camposCorretos) {
		boolean x = true;
		for(boolean a : camposCorretos) {
			x = x && a;
		}
		return x;
	}
}
