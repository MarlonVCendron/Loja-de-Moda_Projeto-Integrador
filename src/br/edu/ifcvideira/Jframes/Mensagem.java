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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.text.MaskFormatter;
import br.edu.ifcvideira.Classes.Cliente;
import br.edu.ifcvideira.utils.Cor;
import br.edu.ifcvideira.utils.Preferencias;
public class Mensagem extends JFrame {

	private JPanel contentPane;
	
	private JTextField tfCelular;
	private JTextField tfTelefone;
	private JTextField tfCpf;
	private JTextField tfNome;
	
	public static JLabel lblLogo;
	
	Color corTexto = new Color(75, 80, 85);
	Color corGeral = new Color(Preferencias.getR(), Preferencias.getG(), Preferencias.getB());
	Color corSecundaria = Cor.corMaisClara(corGeral, 0.2f);
	Color corTerciaria = Cor.corMaisClara(corGeral, 0.4f);
	Color corSeparador = new Color(176, 176, 176);
	Color corVermelho = new Color (230, 20, 20);
	
	Point posMouseInicial;
	
	private JTextField tfData;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mensagem frame = new Mensagem("mensagem", "titulo");
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
	public Mensagem(String mensagem, String titulo) {
		setName("Tela Info Cliente");
		Dimension tela = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int largura = 500;
		int altura = 150;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((tela.width / 2) - (largura / 2), (tela.height / 2) - (altura / 2), largura, altura);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setUndecorated(true);
		contentPane.setLayout(null);
		setResizable(false);
		contentPane.setBackground(Color.WHITE);
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
		btnX.setBounds(458, 0, 42, 30);
		btnX.setMaximumSize(new Dimension(80, 50));
		btnX.setFont(new Font("Roboto", Font.PLAIN, 13));
		btnX.setBorder(null);
		panelSuperior.add(btnX);		
		
		JLabel lblTitulo = new JLabel(titulo);
		lblTitulo.setBounds(12, 0, 446, 30);
		lblTitulo.setForeground(corTexto);
		lblTitulo.setFont(new Font("Roboto", Font.PLAIN, 19));
		panelSuperior.add(lblTitulo);
		
		JLabel lblMensagem = new JLabel(mensagem);
		lblMensagem.setBounds(10, 50, 476, 47);
		lblMensagem.setForeground(corTexto);
		lblMensagem.setFont(new Font("Roboto", Font.PLAIN, 20));
		contentPane.add(lblMensagem);
		
		
		JButton btnOk = new JButton("Ok");
		btnOk.setUI((ButtonUI) BasicButtonUI.createUI(btnOk));
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnOk.setBackground(corSecundaria);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnOk.setBackground(corGeral);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				btnOk.setBackground(corTerciaria);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnOk.setBackground(corGeral);
			}
		});
		btnOk.setBackground(corGeral);
		btnOk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();	
			}
		});
		btnOk.setBounds(177, 107, 123, 30);
		btnOk.setMaximumSize(new Dimension(80, 50));
		btnOk.setFont(new Font("Roboto", Font.PLAIN, 16));
		btnOk.setBorder(null);
		contentPane.add(btnOk);	
	}
}
