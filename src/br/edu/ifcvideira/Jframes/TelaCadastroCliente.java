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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import br.edu.ifcvideira.Classes.Cliente;
import br.edu.ifcvideira.DAOs.ClienteDao;
import br.edu.ifcvideira.utils.Cor;
import br.edu.ifcvideira.utils.Preferencias;
public class TelaCadastroCliente extends JFrame {

	private JPanel contentPane;
	
	private JTextField tfCelular;
	private JTextField tfTelefone;
	private JTextField tfCpf;
	private JTextField tfNome;
	
	private JPanel panelImgNome;
	private JPanel panelImgCpf;
	private JPanel panelImgTelefone;
	private JPanel panelImgRua;
	private JPanel panelImgBairro;
	
	public static JLabel lblLogo;
	
	private Cliente cl = new Cliente();
	private ClienteDao daoCl = new ClienteDao();
	
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
					TelaCadastroCliente frame = new TelaCadastroCliente();
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
	public TelaCadastroCliente() {
		setName("Tela Cadastro Cliente");
		Dimension tela = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int largura = 982;
		int altura = 652;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((tela.width / 2) - (largura / 2), (tela.height / 2) - (altura / 2), 513, 652);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setUndecorated(true);
		contentPane.setLayout(null);
		setResizable(false);
		getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, corGeral));
		
		
		//Variáveis que cuidam para que os campos sejam preenchidos adequadamente
		boolean[] camposCorretos = {false, false, false, false, false, false, false};
		
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
				System.exit(0);
			}
		});
		btnX.setBounds(467, 0, 42, 30);
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
		panelCadastro.setBounds(0, 32, 512, 621);
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
		lblRua.setBounds(74, 335, 119, 42);
		lblRua.setForeground(corTexto);
		lblRua.setFont(new Font("Roboto", Font.PLAIN, 20));
		panelCadastro.add(lblRua);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(74, 390, 163, 42);
		lblBairro.setForeground(corTexto);
		lblBairro.setFont(new Font("Roboto", Font.PLAIN, 20));
		panelCadastro.add(lblBairro);
		
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
		
		tfNome = new JTextField();
		tfNome.setBounds(236, 72, 215, 32);
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
		tfCpf.setColumns(10);
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
				camposCorretos[4] = true;
				spCelular.setBackground(corSeparador);
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				spCelular.setBackground(corGeral);
			}
		});
		tfCelular.setColumns(10);
		panelCadastro.add(tfCelular);
		
		JPanel panelCampos = new JPanel();
		panelCampos.setBounds(0, 0, 512, 620);
		panelCampos.setOpaque(false);
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
		btnCadastrar.setBounds(164, 535, 215, 54);
		panelCampos.add(btnCadastrar);
		btnCadastrar.setBackground(corGeral);
		btnCadastrar.setBorder(null);
		btnCadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(camposEstaoCorretos(camposCorretos)) {
					Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
					
					cl.setNome(tfNome.getText());
					cl.setCpf(tfCpf.getText());
					cl.setTelefone(tfTelefone.getText());
					cl.setCelular(tfCelular.getText());
					cl.setDataCadastro(dataDeHoje);

					
					try {
						daoCl.CadastrarCliente(cl);
						dispose();
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
						spRua.setBackground(corVermelho);
						panelImgRua.setVisible(true);
					}else {
						spRua.setBackground(corSeparador);
						panelImgRua.setVisible(false);
					}
					
					if(!camposCorretos[6]) {
						spBairro.setBackground(corVermelho);
						panelImgBairro.setVisible(true);
					}else {
						spBairro.setBackground(corSeparador);
						panelImgBairro.setVisible(false);
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
		
		JLabel imgXTelefone = new JLabel(new ImageIcon(imagemX));
		imgXTelefone.setBackground(new Color(255, 255, 255));
		imgXTelefone.setToolTipText("O Telefone é obrigatório");
		
		JLabel imgXRua = new JLabel(new ImageIcon(imagemX));
		imgXRua.setBackground(new Color(255, 255, 255));
		imgXRua.setToolTipText("A senha deve conter entre 8 e 25 caracteres");
		
		JLabel imgXBairro = new JLabel(new ImageIcon(imagemX));
		imgXBairro.setBackground(new Color(255, 255, 255));
		imgXBairro.setToolTipText("Repita a senha corretamente");
		
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
		
		panelImgTelefone = new JPanel();
		panelImgTelefone.setVisible(false);
		panelImgTelefone.setBackground(new Color(255, 255, 255));
		panelImgTelefone.setBounds(37, 178, 32, 32);
		panelCampos.add(panelImgTelefone); 
		panelImgTelefone.add(imgXTelefone);
		
		panelImgRua = new JPanel();
		panelImgRua.setVisible(false);
		panelImgRua.setBackground(new Color(255, 255, 255));
		panelImgRua.setBounds(37, 343, 32, 32);
		panelCampos.add(panelImgRua); 
		panelImgRua.add(imgXRua);
		
		panelImgBairro = new JPanel();
		panelImgBairro.setVisible(false);
		panelImgBairro.setBackground(new Color(255, 255, 255));
		panelImgBairro.setBounds(37, 398, 32, 32);
		panelCampos.add(panelImgBairro); 
		panelImgBairro.add(imgXBairro);
		
		JSeparator spCampos = new JSeparator();
		spCampos.setBounds(54, 311, 417, 2);
		panelCampos.add(spCampos);
	}

	
	boolean camposEstaoCorretos(boolean[] camposCorretos) {
		boolean x = true;
		for(boolean a : camposCorretos) {
			x = x && a;
		}
		return x;
	}
}
