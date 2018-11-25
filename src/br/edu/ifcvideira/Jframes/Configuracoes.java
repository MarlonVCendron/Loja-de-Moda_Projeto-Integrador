package br.edu.ifcvideira.Jframes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.FileChooserUI;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

import br.edu.ifcvideira.utils.Cor;
import br.edu.ifcvideira.utils.Preferencias;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;;

public class Configuracoes extends JFrame {

	private JPanel contentPane;
	
	Color corGeral = new Color(Preferencias.getR(), Preferencias.getG(), Preferencias.getB());
	Color corSecundaria = Cor.corMaisClara(corGeral, 0.2f);
	
	Point posMouseInicial;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Configuracoes frame = new Configuracoes();
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
	public Configuracoes() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
			}
		});
		
		setName("Configurações");
		Dimension tela = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int largura = 250;
		int altura = 350;
		setBounds((tela.width / 2) - (largura / 2), (tela.height / 2) - (altura / 2), largura, altura);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setContentPane(contentPane);
		getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, corGeral));
		
		ImageIcon imageIconLogo = new ImageIcon(Preferencias.getImagem());
		Image imagemLogo = imageIconLogo.getImage().getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH);
		setIconImage(imagemLogo);
		
		setTitle(Preferencias.getNomeLoja());
		
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
		contentPane.setLayout(null);
		panelSuperior.setBounds(0, 0, 250, 31);
		panelSuperior.setBackground(Color.WHITE);
		contentPane.add(panelSuperior);
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
		});
		btnX.setBackground(corGeral);
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnX.setBounds(207, 0, 42, 30);
		btnX.setMaximumSize(new Dimension(80, 50));
		btnX.setFont(new Font("Roboto", Font.PLAIN, 13));
		btnX.setBorder(null);
		panelSuperior.add(btnX);
		
		JButton button = new JButton("-");
		button.setUI((ButtonUI) BasicButtonUI.createUI(button));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				button.setBackground(corSecundaria);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(corGeral);
			}
		});
		button.setBackground(corGeral);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		button.setMaximumSize(new Dimension(80, 50));
		button.setFont(new Font("Roboto", Font.PLAIN, 15));
		button.setBorder(null);
		button.setBounds(164, 0, 42, 30);
		panelSuperior.add(button);
		
		JPanel panelConfigs = new JPanel();
		panelConfigs.setBackground(Color.WHITE);
		panelConfigs.setBounds(0, 31, 250, 319);
		contentPane.add(panelConfigs);
		panelConfigs.setLayout(null);
		
		JButton btnEscolherLogo = new JButton("Escolher logo");
		btnEscolherLogo.setUI((ButtonUI) BasicButtonUI.createUI(btnEscolherLogo));
		btnEscolherLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnEscolherLogo.setBackground(corSecundaria);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnEscolherLogo.setBackground(corGeral);
			}
		});
		btnEscolherLogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fcImagem = new JFileChooser();
				fcImagem.setDialogTitle("Escolha a logo");
				fcImagem.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagem", "png", "jpg", "gif");
				fcImagem.setFileFilter(filtro);
	
				int op = fcImagem.showOpenDialog(Configuracoes.this);
				
				if(op == JFileChooser.APPROVE_OPTION) {
					File arquivoImg = fcImagem.getSelectedFile();
					Preferencias.setImagem(arquivoImg.getPath());
					ImageIcon imageIconLogo = new ImageIcon(Preferencias.getImagem());
					Image imagemLogo = imageIconLogo.getImage().getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH);
				//	TelaLogin.lblLogo.setIcon(new ImageIcon(imagemLogo));
				}
				Window[] janelas = Window.getWindows();

				for(Window j : janelas) {
					j.dispose();
				}
				
				TelaLogin telaLogin = new TelaLogin();				
				telaLogin.setVisible(true);
				avisoReiniciar();
			}
		});
		btnEscolherLogo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEscolherLogo.setMaximumSize(new Dimension(80, 50));
		btnEscolherLogo.setFont(new Font("Roboto", Font.PLAIN, 15));
		btnEscolherLogo.setBorder(null);
		btnEscolherLogo.setBackground(corGeral);
		btnEscolherLogo.setBounds(49, 56, 161, 42);
		panelConfigs.add(btnEscolherLogo);
		
		JButton btnEscolherCor = new JButton("Escolher cor");
		btnEscolherCor.setUI((ButtonUI) BasicButtonUI.createUI(btnEscolherCor));
		btnEscolherCor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnEscolherCor.setBackground(corSecundaria);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnEscolherCor.setBackground(corGeral);
			}
		});
		btnEscolherCor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JColorChooser ccCor = new JColorChooser();
				Color corEscolhida = ccCor.showDialog(panelConfigs, "Escolha a cor", corGeral);
				
				if(corEscolhida != null) {
					Preferencias.setR(corEscolhida.getRed());
					Preferencias.setG(corEscolhida.getGreen());
					Preferencias.setB(corEscolhida.getBlue());
				}
				Window[] janelas = Window.getWindows();

				for(Window j : janelas) {
					j.dispose();
				}
				
				TelaLogin telaLogin = new TelaLogin();				
				telaLogin.setVisible(true);
				avisoReiniciar();
			}
		});
		btnEscolherCor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEscolherCor.setMaximumSize(new Dimension(80, 50));
		btnEscolherCor.setFont(new Font("Roboto", Font.PLAIN, 15));
		btnEscolherCor.setBorder(null);
		btnEscolherCor.setBackground(corGeral);
		btnEscolherCor.setBounds(49, 123, 161, 42);
		panelConfigs.add(btnEscolherCor);
		
		
		
		
		btnEscolherLogo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEscolherLogo.setMaximumSize(new Dimension(80, 50));
		btnEscolherLogo.setFont(new Font("Roboto", Font.PLAIN, 15));
		btnEscolherLogo.setBorder(null);
		btnEscolherLogo.setBackground(corGeral);
		btnEscolherLogo.setBounds(49, 56, 161, 42);
		panelConfigs.add(btnEscolherLogo);
		
		JButton btnConfigLoja = new JButton("Configura\u00E7\u00F5es da Loja");
		btnConfigLoja.setUI((ButtonUI) BasicButtonUI.createUI(btnConfigLoja));
		btnConfigLoja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnConfigLoja.setBackground(corSecundaria);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnConfigLoja.setBackground(corGeral);
			}
		});
		btnConfigLoja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConfigEmpresa telaConfigEmpresa= new TelaConfigEmpresa();
				telaConfigEmpresa.setVisible(true);
			}
		});
		btnConfigLoja.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConfigLoja.setMaximumSize(new Dimension(80, 50));
		btnConfigLoja.setFont(new Font("Roboto", Font.PLAIN, 15));
		btnConfigLoja.setBorder(null);
		btnConfigLoja.setBackground(corGeral);
		btnConfigLoja.setBounds(49, 190, 161, 42);
		panelConfigs.add(btnConfigLoja);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnEscolherLogo, btnEscolherCor}));
	}
	
	void avisoReiniciar() {
		Mensagem mensagem = new Mensagem("Reinicie o programa para aplicar as mudanças definitivamente", "Reinicie");
		mensagem.setVisible(true);
	}
}