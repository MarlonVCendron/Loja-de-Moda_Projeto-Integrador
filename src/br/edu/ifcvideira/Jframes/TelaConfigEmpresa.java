package br.edu.ifcvideira.Jframes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FocusTraversalPolicy;
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
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import br.edu.ifcvideira.Classes.Cliente;
import br.edu.ifcvideira.DAOs.ClienteDao;
import br.edu.ifcvideira.DAOs.VendaDao;
import br.edu.ifcvideira.utils.Cor;
import br.edu.ifcvideira.utils.Preferencias;
import javax.swing.JTextArea;
import javax.swing.DropMode;
public class TelaConfigEmpresa extends JFrame {

	private JPanel contentPane;
	private JTextField tfTelefone;
	private JTextField tfNome;
	
	private JPanel panelImgNome;
	private JPanel panelImgTelefone;
	private JPanel panelImgEndereco;
	private JPanel panelImgDesconto;
	
	JSeparator spTelefone = new JSeparator();
	JSeparator spEndereco = new JSeparator();
	JSeparator spNome = new JSeparator();

	JTextField tfEndereco = new JTextField();
	JTextField tfDesconto= new JTextField();


	public static JLabel lblLogo;
	
	private Preferencias pref = new Preferencias();
	
	Color corTexto = new Color(75, 80, 85);
	Color corGeral = new Color(Preferencias.getR(), Preferencias.getG(), Preferencias.getB());
	Color corSecundaria = Cor.corMaisClara(corGeral, 0.2f);
	Color corTerciaria = Cor.corMaisClara(corGeral, 0.4f);
	Color corSeparador = new Color(176, 176, 176);
	Color corVermelho = new Color (230, 20, 20);
	
	boolean[] camposCorretos = {false, false, false};

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
					TelaConfigEmpresa frame = new TelaConfigEmpresa();
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
	public TelaConfigEmpresa() {
		setName("Tela Editar Cliente");
		Dimension tela = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int largura = 700;
		int altura = 425;
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
		
		//Variáveis que cuidam para que os campos sejam preenchidos adequadamente
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 0, 700, 32);
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
		btnX.setBounds(658, 0, 42, 30);
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
		btnMinimizar.setBounds(612, 0, 42, 30);
		panelSuperior.add(btnMinimizar);
		
		JPanel panelCadastro = new JPanel();
		panelCadastro.setBackground(new Color(255, 255, 255));
		panelCadastro.setBounds(0, 32, 700, 396);
		contentPane.add(panelCadastro);
		panelCadastro.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(74, 60, 119, 42);
		lblNome.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblNome.setForeground(corTexto);
		panelCadastro.add(lblNome);
		
		JLabel lblRua = new JLabel("Endere\u00E7o");
		lblRua.setBounds(74, 180, 119, 42);
		lblRua.setForeground(corTexto);
		lblRua.setFont(new Font("Roboto", Font.PLAIN, 20));
		panelCadastro.add(lblRua);
		
		spNome.setBounds(236, 104, 374, 2);
		spNome.setBackground(corSeparador);
		panelCadastro.add(spNome);
		
		tfNome = new JTextField();
		tfNome.setText(pref.getNomeLoja());
		tfNome.setBounds(236, 72, 374, 32);
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
		panelCadastro.add(tfNome);
		
		tfEndereco.setBounds(236, 186, 374, 32);
		panelCadastro.add(tfEndereco);
		tfEndereco.setText(pref.getEnderecoLoja());
		tfEndereco.setForeground(corTexto);
		tfEndereco.setFont(new Font("Roboto", Font.PLAIN, 18));
		tfEndereco.setBorder(null);
		tfEndereco.setBackground(panelCadastro.getBackground());
		tfEndereco.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				camposCorretos[1] = (tfEndereco.getText().matches("[\\p{L}\\s]+")) ? true : false;
				
				if(tfEndereco.getText().length() < 1) {
					camposCorretos[1] = false;
				}
				
				if(camposCorretos[1]) {
					spEndereco.setBackground(corSeparador);
					panelImgEndereco.setVisible(false);
				}else {
					spEndereco.setBackground(corVermelho);
					panelImgEndereco.setVisible(true);
				}
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				spNome.setBackground(corGeral);
			}
		});
	

		try {
			MaskFormatter tfTelefoneFormatter = new MaskFormatter("(##) ####-####");
			tfTelefoneFormatter.setPlaceholderCharacter('_');
			tfTelefone = new JFormattedTextField(tfTelefoneFormatter);
			tfTelefone.setBounds(236, 128, 374, 32);
			panelCadastro.add(tfTelefone);
			tfTelefone.setText(pref.getTelefoneLoja());
			tfTelefone.setForeground(corTexto);
			tfTelefone.setFont(new Font("Roboto", Font.PLAIN, 18));
		}catch(Exception e) {}
		

		tfTelefone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String numerosTelefone = tfTelefone.getText();
				numerosTelefone = numerosTelefone.replaceAll("[^\\d]", "");
				camposCorretos[2] = (numerosTelefone.length() < 10) ? false : true;
				spTelefone.setBackground(corSeparador);
				
				if(camposCorretos[2]) {
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
		tfTelefone.setBorder(null);
		tfTelefone.setBackground(panelCadastro.getBackground());
		
		JLabel imgXNome = new JLabel(new ImageIcon(imagemX));
		imgXNome.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		imgXNome.setBackground(new Color(255, 255, 255));
		imgXNome.setToolTipText("O nome é obrigatório e não pode conter símbolos");
		
		JLabel imgXTelefone = new JLabel(new ImageIcon(imagemX));
		imgXTelefone.setBackground(new Color(255, 255, 255));
		imgXTelefone.setToolTipText("O Telefone é obrigatório");
		
		JLabel imgXEndereco = new JLabel(new ImageIcon(imagemX));
		imgXEndereco.setBackground(new Color(255, 255, 255));
		imgXEndereco.setToolTipText("O endere\u00E7o \u00E9 obrigat\u00F3rio");
		
		JLabel imgXDesconto = new JLabel(new ImageIcon(imagemX));
		imgXDesconto.setBackground(new Color(255, 255, 255));
		imgXDesconto.setToolTipText("O endere\u00E7o \u00E9 obrigat\u00F3rio");
		
		panelImgNome = new JPanel();
		panelImgNome.setVisible(false);
		panelImgNome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelImgNome.setOpaque(false);
		
		JButton btnExcluir = new JButton("Cadastrar/Alterar");
		btnExcluir.setUI((ButtonUI) BasicButtonUI.createUI(btnExcluir));
		btnExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnExcluir.setBackground(corSecundaria);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnExcluir.setBackground(corGeral);
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				btnExcluir.setBackground(corTerciaria);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnExcluir.setBackground(corGeral);
			}
		});
		
		
		
		
		
		btnExcluir.setBounds(252, 315, 215, 54);
		panelCadastro.add(btnExcluir);
		btnExcluir.setBackground(corGeral);
		btnExcluir.setBorder(null);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizaCampos();
				if (camposEstaoCorretos(camposCorretos)) {
					pref.setEnderecoLoja(tfEndereco.getText());
					pref.setTelefoneLoja(tfTelefone.getText());
					pref.setNomeLoja(tfNome.getText());
					pref.setDesconto(Double.parseDouble((tfDesconto.getText())));
				} else {
					JOptionPane.showMessageDialog(null, "Um ou mais campos preenchidos incorretamente", "Erro", JOptionPane.ERROR_MESSAGE);

				}
			dispose();
			}
		});
		btnExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnExcluir.setForeground(corTexto);
		
		panelImgNome.setBackground(new Color(255, 255, 255));
		panelImgNome.setBounds(37, 68, 32, 32);
		panelCadastro.add(panelImgNome); 
		panelImgNome.add(imgXNome);
		
		panelImgTelefone = new JPanel();
		panelImgTelefone.setVisible(false);
		panelImgTelefone.setBackground(new Color(255, 255, 255));
		panelImgTelefone.setBounds(37, 128, 32, 32);
		panelCadastro.add(panelImgTelefone); 
		panelImgTelefone.add(imgXTelefone);
		
		panelImgEndereco = new JPanel();
		panelImgEndereco.setVisible(false);
		panelImgEndereco.setBackground(new Color(255, 255, 255));
		panelImgEndereco.setBounds(37, 184, 32, 32);
		panelCadastro.add(panelImgEndereco); 
		panelImgEndereco.add(imgXEndereco);
		
		panelImgDesconto = new JPanel();
		panelImgDesconto.setVisible(false);
		panelImgDesconto.setBackground(new Color(255, 255, 255));
		panelImgDesconto.setBounds(37, 245, 32, 32);
		panelCadastro.add(panelImgDesconto); 
		panelImgDesconto.add(imgXDesconto);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(74, 120, 119, 42);
		panelCadastro.add(lblTelefone);
		lblTelefone.setForeground(corTexto);
		lblTelefone.setFont(new Font("Roboto", Font.PLAIN, 20));
		
		
		
		spTelefone.setBounds(236, 160, 374, 2);
		panelCadastro.add(spTelefone);
		spTelefone.setBackground(corSeparador);
		
		spEndereco.setBounds(236, 218, 374, 2);
		panelCadastro.add(spEndereco);
		spEndereco.setBackground(corSeparador);
		
		JLabel lblDescontoGeral = new JLabel("Desconto Geral");
		lblDescontoGeral.setForeground(new Color(75, 80, 85));
		lblDescontoGeral.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblDescontoGeral.setBounds(74, 240, 152, 42);
		panelCadastro.add(lblDescontoGeral);
		
		JSeparator spDesconto = new JSeparator();
		spDesconto.setBackground(new Color(176, 176, 176));
		spDesconto.setBounds(236, 278, 95, 2);
		panelCadastro.add(spDesconto);
		
		
		DecimalFormat dc = new DecimalFormat("0.00");
        NumberFormatter numFormatter = new NumberFormatter(dc);
        DecimalFormatSymbols custom=new DecimalFormatSymbols();
        custom.setDecimalSeparator('.');
        dc.setDecimalFormatSymbols(custom);        numFormatter.setFormat(dc);
        numFormatter.setAllowsInvalid(false);
        DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(numFormatter);
        tfDesconto = new JFormattedTextField(dfFactory);
        
		tfDesconto.setOpaque(false);
		tfDesconto.setBounds(167, 199, 409, 32);
		tfDesconto.setText(String.valueOf(dc.format(pref.getDesconto())));
		tfDesconto.setForeground(corTexto);
		tfDesconto.setFont(new Font("Roboto", Font.PLAIN, 20));
		tfDesconto.setBorder(null);
		tfDesconto.setBackground(panelCadastro.getBackground());
		tfDesconto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				camposCorretos[2] = true;
				if (tfDesconto.getText().length() == 0) {
					camposCorretos[2] = false;
					tfDesconto.setText(dc.format(tfDesconto.getText()));
				}

				if (camposCorretos[2]) {
					spDesconto.setBackground(corGeral);
					panelImgDesconto.setVisible(false);

				} else {
					spDesconto.setBackground(corVermelho);
					panelImgDesconto.setVisible(true);

				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				spDesconto.setBackground(corGeral);
				panelImgDesconto.setVisible(false);

			}
		});
		tfDesconto.addKeyListener(new KeyAdapter() {
			

			public void keyTyped(KeyEvent e) {

				char q = e.getKeyChar();
				if (((Character.isAlphabetic(q)))) {
					e.consume();
				}

			}
		});
		tfDesconto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String numerosDesconto = tfDesconto.getText();
				numerosDesconto = numerosDesconto.replaceAll("[^\\d]", "");
				camposCorretos[1] = (numerosDesconto.length() < 2) ? false : true;
				spDesconto.setBackground(corGeral);

				if (camposCorretos[1]) {
					spDesconto.setBackground(corGeral);

				} else {
					spDesconto.setBackground(corVermelho);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				spDesconto.setBackground(corGeral);

			}
		});
		tfDesconto.setColumns(10);
		tfDesconto.setBounds(236, 245, 95, 32);
		panelCadastro.add(tfDesconto);		
		
		JLabel label = new JLabel("%");
		label.setForeground(new Color(75, 80, 85));
		label.setFont(new Font("Roboto", Font.PLAIN, 20));
		label.setBounds(341, 240, 152, 42);
		panelCadastro.add(label);
		panelCadastro.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tfNome, tfTelefone, tfEndereco, tfDesconto, btnExcluir}));
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tfNome, tfTelefone, tfEndereco, tfDesconto, btnExcluir}));
		
		
		
		
		
	}
	public void atualizaCampos() {
		String numerosTelefone = tfTelefone.getText();
		numerosTelefone = numerosTelefone.replaceAll("[^\\d]", "");
		camposCorretos[2] = (numerosTelefone.length() < 10) ? false : true;
		spTelefone.setBackground(corSeparador);
		
		if(camposCorretos[2]) {
			spTelefone.setBackground(corSeparador);
			panelImgTelefone.setVisible(false);
		}else {
			spTelefone.setBackground(corVermelho);
			panelImgTelefone.setVisible(true);
		}
		
		camposCorretos[1] = (tfEndereco.getText().matches("[\\p{L}\\s]+")) ? true : false;
		
		if(tfEndereco.getText().length() < 1) {
			camposCorretos[1] = false;
		}
		
		if(camposCorretos[1]) {
			spEndereco.setBackground(corSeparador);
			panelImgEndereco.setVisible(false);
		}else {
			spEndereco.setBackground(corVermelho);
			panelImgEndereco.setVisible(true);
		}
		
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
	
	boolean camposEstaoCorretos(boolean[] camposCorretos) {
		boolean x = true;
		for(boolean a : camposCorretos) {
			x = x && a;
		}
		return x;
	}
}
