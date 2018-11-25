package br.edu.ifcvideira.Jframes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;

import br.edu.ifcvideira.Classes.Usuario;
import br.edu.ifcvideira.DAOs.UsuarioDao;
import br.edu.ifcvideira.utils.Cor;
import br.edu.ifcvideira.utils.Preferencias;
import br.edu.ifcvideira.utils.Senha;
public class TelaTrocarSenha extends JFrame {	
	private JPanel contentPane;
	
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
					TelaTrocarSenha frame = new TelaTrocarSenha(us);
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
	public TelaTrocarSenha(Usuario us) {
		setName("Tela Trocar Senha");
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
		
		
		boolean[] camposCorretos = {false, false};
		
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
		btnMinimizar.setUI((ButtonUI) BasicButtonUI.createUI(btnMinimizar));
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
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(53, 33, 119, 42);
		lblSenha.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblSenha.setForeground(corTexto);
		panelCadastro.add(lblSenha);
		
		JLabel lblRepetir = new JLabel("Repita a senha");
		lblRepetir.setBounds(53, 99, 148, 42);
		lblRepetir.setForeground(corTexto);
		lblRepetir.setFont(new Font("Roboto", Font.PLAIN, 20));
		panelCadastro.add(lblRepetir);
		
		JSeparator spSenha = new JSeparator();
		spSenha.setBounds(227, 73, 215, 2);
		spSenha.setBackground(corSeparador);
		panelCadastro.add(spSenha);
		
		JSeparator spRepetir = new JSeparator();
		spRepetir.setBounds(227, 134, 215, 2);
		spRepetir.setBackground(corSeparador);
		panelCadastro.add(spRepetir);
		
		JLabel lblErroSenha = new JLabel("A senha deve estar entre 8 e 25 caracteres");
		lblErroSenha.setVisible(false);
		lblErroSenha.setForeground(new Color(230, 20, 20));
		lblErroSenha.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblErroSenha.setBounds(227, 13, 270, 22);
		panelCadastro.add(lblErroSenha);
		
		JLabel lblErroRepetir = new JLabel("As senhas devem ser iguais");
		lblErroRepetir.setVisible(false);
		lblErroRepetir.setForeground(new Color(230, 20, 20));
		lblErroRepetir.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblErroRepetir.setBounds(227, 80, 204, 22);
		panelCadastro.add(lblErroRepetir);
		
		JPasswordField pfSenha = new JPasswordField();
		pfSenha.setForeground(corTexto);
		pfSenha.setFont(new Font("Roboto", Font.PLAIN, 18));
		pfSenha.setBorder(null);
		pfSenha.setBackground(panelCadastro.getBackground());
		pfSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				camposCorretos[0] = (pfSenha.getPassword().length < 8 || pfSenha.getPassword().length > 25) ? false : true;
				spSenha.setBackground(corSeparador);
				
				if(camposCorretos[0]) {
					spSenha.setBackground(corSeparador);
					lblErroSenha.setVisible(false);
				}else {
					spSenha.setBackground(corVermelho);
					lblErroSenha.setVisible(true);
				}
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				spSenha.setBackground(corGeral);
			}
		});
		pfSenha.setColumns(10);
		pfSenha.setBounds(227, 39, 215, 32);
		pfSenha.setEchoChar('•');
		panelCadastro.add(pfSenha);
		
		JPasswordField pfRepetir = new JPasswordField();
		pfRepetir.setForeground(corTexto);
		pfRepetir.setFont(new Font("Roboto", Font.PLAIN, 18));
		pfRepetir.setBorder(null);
		pfRepetir.setBackground(panelCadastro.getBackground());
		pfRepetir.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				camposCorretos[1] = (pfRepetir.getText().equals(pfSenha.getText())) ? true : false;
				spRepetir.setBackground(corSeparador);
				
				if(camposCorretos[1]) {
					spRepetir.setBackground(corSeparador);
					lblErroRepetir.setVisible(false);
				}else {
					spRepetir.setBackground(corVermelho);
					lblErroRepetir.setVisible(true);
				}
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				spRepetir.setBackground(corGeral);
			}
		});
		pfRepetir.setBounds(227, 100, 215, 32);
		panelCadastro.add(pfRepetir);
		pfRepetir.setEchoChar('•');
		pfRepetir.setColumns(10);
	
		
		JButton btnTrocar = new JButton("Alterar senha");
		btnTrocar.setUI((ButtonUI) BasicButtonUI.createUI(btnTrocar));
		btnTrocar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTrocar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				if(camposEstaoCorretos(camposCorretos)) {
					lblErroSenha.setVisible(false);
					
					String novaSenha = Senha.encriptarSenha(pfSenha.getText());
					
					try {
						UsuarioDao usDao = new UsuarioDao();
						usDao.AlterarSenha(us.getId(), novaSenha);
						
						Mensagem mensagem = new Mensagem("Senha alterada com sucesso", "Senha alterada");
					    mensagem.setVisible(true);
						
						Window[] janelas = Window.getWindows();
						
						for(Window janela : janelas) {
							if(janela.getName().equals("Tela Trocar Senha") || janela.getName().equals("Tela Recuperar Senha")) {
								janela.dispose();
							}
						}
					}catch(Exception err) {
						JOptionPane.showMessageDialog(null, err.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					lblErroSenha.setVisible(true);
				}
			}
		});
		btnTrocar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnTrocar.setBackground(corSecundaria);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnTrocar.setBackground(corGeral);
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				btnTrocar.setBackground(corTerciaria);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnTrocar.setBackground(corGeral);
			}
		});
		btnTrocar.setForeground(new Color(75, 80, 85));
		btnTrocar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnTrocar.setBorder(null);
		btnTrocar.setBackground(new Color(0, 204, 204));
		btnTrocar.setBounds(180, 170, 148, 54);
		panelCadastro.add(btnTrocar);
	}
	
	boolean camposEstaoCorretos(boolean[] camposCorretos) {
		boolean x = true;
		for(boolean a : camposCorretos) {
			x = x && a;
		}
		return x;
	}
}
