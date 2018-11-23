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
import javax.swing.text.MaskFormatter;
import br.edu.ifcvideira.Classes.Cliente;
import br.edu.ifcvideira.utils.Cor;
import br.edu.ifcvideira.utils.Preferencias;
public class TelaInfoCliente extends JFrame {

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
					TelaInfoCliente frame = new TelaInfoCliente(null);
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
	public TelaInfoCliente(Cliente cliente) {
		setName("Tela Info Cliente");
		Dimension tela = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int largura = 513;
		int altura = 700;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((tela.width / 2) - (largura / 2), (tela.height / 2) - (altura / 2), 513, 609);
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
		panelCadastro.setBounds(0, 32, 513, 577);
		contentPane.add(panelCadastro);
		panelCadastro.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(74, 60, 119, 42);
		lblNome.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblNome.setForeground(corTexto);
		panelCadastro.add(lblNome);
		
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
		
		JLabel lblRua = new JLabel("Rua");
		lblRua.setBounds(74, 340, 119, 42);
		lblRua.setForeground(corTexto);
		lblRua.setFont(new Font("Roboto", Font.PLAIN, 20));
		panelCadastro.add(lblRua);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(74, 395, 163, 42);
		lblBairro.setForeground(corTexto);
		lblBairro.setFont(new Font("Roboto", Font.PLAIN, 20));
		panelCadastro.add(lblBairro);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setForeground(new Color(75, 85, 85));
		lblCidade.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblCidade.setBounds(74, 450, 163, 42);
		panelCadastro.add(lblCidade);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setForeground(new Color(75, 85, 85));
		lblEstado.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblEstado.setBounds(74, 510, 163, 42);
		panelCadastro.add(lblEstado);
		
		JSeparator spNome = new JSeparator();
		spNome.setBounds(236, 104, 215, 2);
		spNome.setBackground(corSeparador);
		panelCadastro.add(spNome);
		
		JSeparator spCpf = new JSeparator();
		spCpf.setBounds(236, 159, 215, 2);
		spCpf.setBackground(corSeparador);
		panelCadastro.add(spCpf);
		
		JSeparator spTelefone = new JSeparator();
		spTelefone.setBounds(236, 214, 215, 2);
		spTelefone.setBackground(corSeparador);
		panelCadastro.add(spTelefone);
		
		JSeparator spCelular = new JSeparator();
		spCelular.setBounds(236, 269, 215, 2);
		spCelular.setBackground(corSeparador);
		panelCadastro.add(spCelular);
		
		JSeparator spRua = new JSeparator();
		spRua.setBounds(236, 377, 215, 2);
		spRua.setBackground(corSeparador);
		panelCadastro.add(spRua);
		
		JSeparator spBairro = new JSeparator();
		spBairro.setBounds(236, 432, 215, 2);
		spBairro.setBackground(corSeparador);
		panelCadastro.add(spBairro);
		
		JSeparator spCidade = new JSeparator();
		spCidade.setBounds(236, 487, 215, 2);
		spCidade.setBackground(corSeparador);
		panelCadastro.add(spCidade);
		
		JSeparator spEstado = new JSeparator();
		spEstado.setBounds(236, 542, 215, 2);
		spEstado.setBackground(corSeparador);
		panelCadastro.add(spEstado);
		
		tfNome = new JTextField();
		tfNome.setEditable(false);
		tfNome.setText(cliente.getNome());
		tfNome.setBounds(236, 72, 215, 32);
		tfNome.setForeground(corTexto);
		tfNome.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfNome.setBorder(null);
		tfNome.setBackground(panelCadastro.getBackground());
		panelCadastro.add(tfNome);
		
		try {
			MaskFormatter tfCpfFormatter = new MaskFormatter("###.###.###-##");
			tfCpfFormatter.setPlaceholderCharacter('_');
			tfCpf = new JFormattedTextField(tfCpfFormatter);
			tfCpf.setEditable(false);
			tfCpf.setText(cliente.getCpf());
			tfCpf.setBounds(236, 127, 215, 32);
			tfCpf.setForeground(corTexto);
			tfCpf.setFont(new Font("Roboto", Font.PLAIN, 18));
			tfCpf.setBorder(null);
			tfCpf.setBackground(panelCadastro.getBackground());
		}catch(Exception e) {}
		panelCadastro.add(tfCpf);
		
		try {
			MaskFormatter tfRgFormatter = new MaskFormatter("#.###.###");
			tfRgFormatter.setPlaceholderCharacter('_');
		}catch(Exception e) {}
		
		
		try {
			MaskFormatter tfTelefoneFormatter = new MaskFormatter("(##) ####-####");
			tfTelefoneFormatter.setPlaceholderCharacter('_');
			tfTelefone = new JFormattedTextField(tfTelefoneFormatter);
			tfTelefone.setEditable(false);
			tfTelefone.setText(cliente.getTelefone());
			tfTelefone.setBounds(236, 182, 215, 32);
			tfTelefone.setForeground(corTexto);
			tfTelefone.setFont(new Font("Roboto", Font.PLAIN, 18));
			tfTelefone.setBorder(null);
			tfTelefone.setBackground(panelCadastro.getBackground());
		}catch(Exception e) {}
		panelCadastro.add(tfTelefone);
		
		try {
			MaskFormatter tfCelularFormatter = new MaskFormatter("(##) #####-####");
			tfCelularFormatter.setPlaceholderCharacter('_');
			tfCelular = new JFormattedTextField(tfCelularFormatter);
			tfCelular.setEditable(false);
			tfCelular.setText(cliente.getCelular());
			tfCelular.setBounds(236, 237, 215, 32);
			tfCelular.setForeground(corTexto);
			tfCelular.setFont(new Font("Roboto", Font.PLAIN, 18));
			tfCelular.setBorder(null);
			tfCelular.setBackground(panelCadastro.getBackground());
		}catch(Exception e) {}
		panelCadastro.add(tfCelular);
		
		JTextField tfRua = new JTextField();
		tfRua.setEditable(false);
		tfRua.setText(cliente.getRua());
		tfRua.setBounds(236, 345, 215, 32);
		tfRua.setForeground(corTexto);
		tfRua.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfRua.setBorder(null);
		tfRua.setBackground(panelCadastro.getBackground());
		panelCadastro.add(tfRua);
		
		JTextField tfBairro = new JTextField();
		tfBairro.setEditable(false);
		tfBairro.setText(cliente.getBairro());
		tfBairro.setBounds(236, 400, 215, 32);
		tfBairro.setForeground(corTexto);
		tfBairro.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfBairro.setBorder(null);
		tfBairro.setBackground(panelCadastro.getBackground());
		panelCadastro.add(tfBairro);
		
		JTextField tfCidade = new JTextField();
		tfCidade.setEditable(false);
		tfCidade.setBounds(236, 455, 215, 32);
		tfCidade.setText(cliente.getCidade());
		tfCidade.setForeground(corTexto);
		tfCidade.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfCidade.setBorder(null);
		tfCidade.setBackground(panelCadastro.getBackground());
		panelCadastro.add(tfCidade);
		
		JTextField tfEstado = new JTextField();
		tfEstado.setEditable(false);
		tfEstado.setBounds(236, 510, 215, 32);
		tfEstado.setText(cliente.getEstado());
		tfEstado.setForeground(corTexto);
		tfEstado.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfEstado.setBorder(null);
		tfEstado.setBackground(panelCadastro.getBackground());
		panelCadastro.add(tfEstado);
		
		tfData = new JTextField();
		tfData.setEditable(false);
		tfData.setText(cliente.getDataCadastro().toString());
		tfData.setBounds(100, 13, 167, 29);
		tfData.setForeground(corTexto);
		tfData.setFont(new Font("Roboto", Font.PLAIN, 14));
		tfData.setBorder(null);
		tfData.setBackground(Color.WHITE);
		panelCadastro.add(tfData);
		
		JSeparator spData = new JSeparator();
		spData.setBounds(100, 42, 167, 2);
		spData.setBackground(corSeparador);
		panelCadastro.add(spData);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(37, 10, 37, 42);
		lblData.setForeground(new Color(75, 80, 85));
		lblData.setFont(new Font("Roboto", Font.PLAIN, 15));
		panelCadastro.add(lblData);
		
		JTextField tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setText(String.valueOf(cliente.getId()));
		tfId.setBounds(420, 13, 15, 29);
		tfId.setForeground(corTexto);
		tfId.setFont(new Font("Roboto", Font.PLAIN, 14));
		tfId.setBorder(null);
		tfId.setBackground(Color.WHITE);
		panelCadastro.add(tfId);
		
		JSeparator spId = new JSeparator();
		spId.setBounds(420, 42, 15, 2);
		spId.setBackground(corSeparador);
		panelCadastro.add(spId);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(390, 10, 37, 42);
		lblId.setForeground(new Color(75, 80, 85));
		lblId.setFont(new Font("Roboto", Font.PLAIN, 15));
		panelCadastro.add(lblId);
		
		JPanel panelCampos = new JPanel();
		panelCampos.setBounds(0, 0, 512, 578);
		panelCampos.setOpaque(false);
		panelCampos.setBackground(panelCadastro.getBackground());
		panelCadastro.add(panelCampos);
		panelCampos.setLayout(null);
		
		JSeparator spCampos = new JSeparator();
		spCampos.setBounds(54, 311, 417, 2);
		panelCampos.add(spCampos);
		
	}
}
