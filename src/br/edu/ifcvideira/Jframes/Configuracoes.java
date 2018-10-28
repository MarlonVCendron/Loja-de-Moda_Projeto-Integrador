package br.edu.ifcvideira.Jframes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FileChooserUI;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class Configuracoes extends JFrame {

	private JPanel contentPane;
	
	Color corGeral = new Color(118, 184, 184);
	
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
		btnX.setBackground(corGeral);
		btnX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnX.setBounds(208, 0, 42, 30);
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
		button.setBounds(164, 0, 42, 30);
		panelSuperior.add(button);
		
		JPanel panelConfigs = new JPanel();
		panelConfigs.setBackground(Color.WHITE);
		panelConfigs.setBounds(0, 31, 250, 319);
		contentPane.add(panelConfigs);
		panelConfigs.setLayout(null);
		
		JButton btnEscolherLogo = new JButton("Escolher logo");
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
				}
			}
		});
		btnEscolherLogo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEscolherLogo.setMaximumSize(new Dimension(80, 50));
		btnEscolherLogo.setFont(new Font("Roboto", Font.PLAIN, 15));
		btnEscolherLogo.setBorder(null);
		btnEscolherLogo.setBackground(new Color(118, 184, 184));
		btnEscolherLogo.setBounds(49, 56, 161, 42);
		panelConfigs.add(btnEscolherLogo);
		
		JButton btnEscolherCor = new JButton("Escolher cor");
		btnEscolherCor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Cor
			}
		});
		btnEscolherCor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEscolherCor.setMaximumSize(new Dimension(80, 50));
		btnEscolherCor.setFont(new Font("Roboto", Font.PLAIN, 15));
		btnEscolherCor.setBorder(null);
		btnEscolherCor.setBackground(new Color(118, 184, 184));
		btnEscolherCor.setBounds(49, 185, 161, 42);
		panelConfigs.add(btnEscolherCor);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnEscolherLogo, btnEscolherCor}));
	}
}
