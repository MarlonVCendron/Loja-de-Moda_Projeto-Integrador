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
		Dimension tela = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int largura = 1000;
		int altura = 700;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((tela.width / 2) - (largura / 2), (tela.height / 2) - (altura / 2), largura, altura);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		//Variáveis que cuidam para que os campos sejam preenchidos adequadamente
		boolean[] camposCorretos = {false, false, false, false, false, false, false};
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(new Color(230, 230, 230));
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
					// Usuário logou com sucesso
				}
			}
		});
		btnEntrar.setFont(new Font("Roboto", Font.PLAIN, 22));
		btnEntrar.setBounds(417, 482, 187, 64);
		panelLogin.add(btnEntrar);
		
		JPanel panelCadastro = new JPanel();
		panelCadastro.setBackground(new Color(230, 230, 230));
		panelCadastro.setBounds(0, 0, 982, 653);
		contentPane.add(panelCadastro);
		panelCadastro.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblNome.setForeground(new Color(20, 20, 20));
		lblNome.setBounds(12, 70, 119, 42);
		panelCadastro.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(new Color(20, 20, 20));
		lblCpf.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblCpf.setBounds(12, 125, 119, 42);
		panelCadastro.add(lblCpf);
		
		JLabel lblRg = new JLabel("RG");
		lblRg.setForeground(new Color(20, 20, 20));
		lblRg.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblRg.setBounds(12, 180, 119, 42);
		panelCadastro.add(lblRg);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setForeground(new Color(20, 20, 20));
		lblTelefone.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblTelefone.setBounds(12, 235, 119, 42);
		panelCadastro.add(lblTelefone);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setForeground(new Color(20, 20, 20));
		lblCelular.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblCelular.setBounds(12, 289, 163, 42);
		panelCadastro.add(lblCelular);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(new Color(20, 20, 20));
		lblSenha.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblSenha.setBounds(12, 341, 119, 42);
		panelCadastro.add(lblSenha);
		
		JLabel lblRepitaASenha = new JLabel("Repita a senha");
		lblRepitaASenha.setForeground(new Color(20, 20, 20));
		lblRepitaASenha.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblRepitaASenha.setBounds(12, 395, 163, 42);
		panelCadastro.add(lblRepitaASenha);
		
		tfNome = new JTextField();
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
		tfNome.setBounds(174, 82, 215, 32);
		panelCadastro.add(tfNome);
		
		try {
			MaskFormatter tfCpfFormatter = new MaskFormatter("###.###.###-##");
			tfCpf = new JFormattedTextField(tfCpfFormatter);
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
		tfCpf.setBounds(174, 137, 215, 32);
		panelCadastro.add(tfCpf);
		
		try {
			MaskFormatter tfRgFormatter = new MaskFormatter("#.###.###");
			tfRg = new JFormattedTextField(tfRgFormatter);
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
		tfRg.setBounds(174, 192, 215, 32);
		panelCadastro.add(tfRg);
		
		try {
			MaskFormatter tfTelefoneFormatter = new MaskFormatter("(##) ####-####");
			tfTelefone = new JFormattedTextField(tfTelefoneFormatter);
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
		tfTelefone.setBounds(174, 247, 215, 32);
		panelCadastro.add(tfTelefone);
		
		try {
			MaskFormatter tfCelularFormatter = new MaskFormatter("(##) #####-####");
			tfCelular = new JFormattedTextField(tfCelularFormatter);
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
		tfCelular.setBounds(174, 301, 215, 32);
		panelCadastro.add(tfCelular);
		
		pfSenha = new JPasswordField();
		pfSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				camposCorretos[5] = (pfSenha.getPassword().length < 8) ? false : true;
			}
		});
		pfSenha.setColumns(10);
		pfSenha.setBounds(174, 353, 215, 32);
		panelCadastro.add(pfSenha);
		
		pfRepetir = new JPasswordField();
		pfRepetir.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				camposCorretos[6] = (pfRepetir.getText().equals(pfSenha.getText())) ? true : false;
			}
		});
		pfRepetir.setBounds(174, 402, 215, 32);
		panelCadastro.add(pfRepetir);
		pfRepetir.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
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
		btnCadastrar.setBounds(118, 465, 150, 54);
		panelCadastro.add(btnCadastrar);
		
		
		
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
