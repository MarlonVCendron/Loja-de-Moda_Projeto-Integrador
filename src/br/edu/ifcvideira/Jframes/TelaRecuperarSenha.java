package br.edu.ifcvideira.Jframes;

import java.awt.Color;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.text.MaskFormatter;

import br.edu.ifcvideira.Classes.Usuario;
import br.edu.ifcvideira.utils.Cor;
import br.edu.ifcvideira.utils.Email;
import br.edu.ifcvideira.utils.Preferencias;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class TelaRecuperarSenha extends JFrame {
	private String codigo;
	
	private JPanel contentPane;
	private JTextField tfCodigo;
	private JTextField tfEmail;
	
	JButton btnX;
	JButton btnMinimizar;
	JButton btnEnviar;
	JButton btnRecuperar;
	
	public static JLabel lblLogo;
	
	Color corTexto = new Color(75, 80, 85);
	Color corGeral = new Color(Preferencias.getR(), Preferencias.getG(), Preferencias.getB());
	Color corSecundaria = Cor.corMaisClara(corGeral, 0.2f);
	Color corTerciaria = Cor.corMaisClara(corGeral, 0.4f);
	Color corSeparador = new Color(176, 176, 176);
	Color corVermelho = new Color (230, 20, 20);
	
	Point posMouseInicial;
	
	ImageIcon imageIconX = new ImageIcon(TelaLogin.class.getResource("/br/edu/ifcvideira/img/xerro.png"));
	Image imagemX = imageIconX.getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario us = new Usuario();
					us.setEmail("a");
					TelaRecuperarSenha frame = new TelaRecuperarSenha(us);
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
	public TelaRecuperarSenha(Usuario us) {
		setName("Tela Recuperar Senha");
		Dimension tela = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int largura = 513;
		int altura = 300;
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
		setTitle(Preferencias.getNomeLoja());
		
		
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
		
		btnX = new JButton("X");
		btnX.setUI((ButtonUI) BasicButtonUI.createUI(btnX));
		btnX.setBackground(corGeral);
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
		
		btnMinimizar = new JButton("-");
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
		panelCadastro.setBounds(0, 32, 513, 268);
		contentPane.add(panelCadastro);
		panelCadastro.setLayout(null);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(14, 91, 119, 42);
		lblEmail.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblEmail.setForeground(corTexto);
		panelCadastro.add(lblEmail);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(14, 184, 119, 42);
		lblCodigo.setForeground(corTexto);
		lblCodigo.setFont(new Font("Roboto", Font.PLAIN, 20));
		panelCadastro.add(lblCodigo);
		
		JSeparator spEmail = new JSeparator();
		spEmail.setBounds(96, 131, 245, 2);
		spEmail.setBackground(corSeparador);
		panelCadastro.add(spEmail);
		
		JSeparator spCodigo = new JSeparator();
		spCodigo.setBounds(96, 224, 245, 2);
		spCodigo.setBackground(corSeparador);
		panelCadastro.add(spCodigo);
		
		tfEmail = new JTextField();
		tfEmail.setEditable(false);
		tfEmail.setBorder(null);
		tfEmail.setText(us.getEmail());
		tfEmail.setBounds(96, 99, 245, 32);
		tfEmail.setForeground(corTexto);
		tfEmail.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfEmail.setBackground(panelCadastro.getBackground());
		panelCadastro.add(tfEmail);
		tfEmail.setBackground(panelCadastro.getBackground());
		tfEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				spEmail.setBackground(corSeparador);
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				spEmail.setBackground(corGeral);
			}
		});
		
		tfCodigo = new JTextField();
		tfCodigo.setBorder(null);
		tfCodigo.setBounds(96, 192, 245, 32);
		tfCodigo.setForeground(corTexto);
		tfCodigo.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfCodigo.setBackground(panelCadastro.getBackground());
		tfCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				spCodigo.setBackground(corSeparador);
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				spCodigo.setBackground(corGeral);
			}
		});
		tfCodigo.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {

				char q = e.getKeyChar();
				if(tfCodigo.getText().length() > 4) {
					e.consume();
				}
				
				if (!((Character.isDigit(q))) && Character.isAlphabetic(q)) {
					e.consume();
				}
			}
		});
		panelCadastro.add(tfCodigo);
		
		btnEnviar = new JButton("Enviar c\u00F3digo");
		btnEnviar.setBounds(353, 91, 148, 54);
		btnEnviar.setUI((ButtonUI) BasicButtonUI.createUI(btnEnviar));
		panelCadastro.add(btnEnviar);
		btnEnviar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnEnviar.setBackground(corSecundaria);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnEnviar.setBackground(corGeral);
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				btnEnviar.setBackground(corTerciaria);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnEnviar.setBackground(corGeral);
			}
		});
		btnEnviar.setBackground(corGeral);
		btnEnviar.setBorder(null);
		btnEnviar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				codigo = "";
				for (int i = 0; i < 5; i++) {
					codigo += String.valueOf((int) Math.floor(Math.random() * 9));
				}
				
				Email.enviarEmailRecuperacao(us.getEmail(), codigo);
			}
		});
		btnEnviar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnEnviar.setForeground(corTexto);		
		
		JLabel lblInfo = new JLabel("Clique no bot\u00E3o abaixo para enviar um c\u00F3digo");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setForeground(new Color(75, 80, 85));
		lblInfo.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblInfo.setBounds(4, 10, 513, 32);
		panelCadastro.add(lblInfo);
		
		JLabel lblInfoDois = new JLabel("ao seu e-mail e recuperar sua senha");
		lblInfoDois.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoDois.setForeground(new Color(75, 80, 85));
		lblInfoDois.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblInfoDois.setBounds(4, 31, 513, 27);
		panelCadastro.add(lblInfoDois);
		
		JLabel lblErroCodigo = new JLabel("C\u00F3digo incorreto");
		lblErroCodigo.setVisible(false);
		lblErroCodigo.setForeground(new Color(230, 20, 20));
		lblErroCodigo.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblErroCodigo.setBounds(96, 169, 119, 22);
		panelCadastro.add(lblErroCodigo);
		
		btnRecuperar = new JButton("Recuperar senha");
		btnRecuperar.setUI((ButtonUI) BasicButtonUI.createUI(btnRecuperar));
		btnRecuperar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRecuperar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				if(tfCodigo.getText().equals(codigo)) {
					lblErroCodigo.setVisible(false);
					
					TelaTrocarSenha telaTrocarSenha = new TelaTrocarSenha(us);
					
					telaTrocarSenha.setVisible(true);
				}else {
					lblErroCodigo.setVisible(true);
				}
			}
		});
		btnRecuperar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnRecuperar.setBackground(corSecundaria);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRecuperar.setBackground(corGeral);
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				btnRecuperar.setBackground(corTerciaria);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnRecuperar.setBackground(corGeral);
			}
		});
		btnRecuperar.setForeground(new Color(75, 80, 85));
		btnRecuperar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnRecuperar.setBorder(null);
		btnRecuperar.setBackground(new Color(0, 204, 204));
		btnRecuperar.setBounds(353, 184, 148, 54);
		panelCadastro.add(btnRecuperar);
	}
}
